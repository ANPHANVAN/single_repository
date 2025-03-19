import os

from cs50 import SQL
from flask import Flask, flash, redirect, render_template, request, session
from flask_session import Session
from werkzeug.security import check_password_hash, generate_password_hash

from helpers import apology, login_required, lookup, usd
import yfinance as yf

# Configure application
app = Flask(__name__)

# Custom filter
app.jinja_env.filters["usd"] = usd

# Configure session to use filesystem (instead of signed cookies)
app.config["SESSION_PERMANENT"] = False
app.config["SESSION_TYPE"] = "filesystem"
Session(app)

# Configure CS50 Library to use SQLite database
db = SQL("sqlite:///finance.db")


@app.after_request
def after_request(response):
    """Ensure responses aren't cached"""
    response.headers["Cache-Control"] = "no-cache, no-store, must-revalidate"
    response.headers["Expires"] = 0
    response.headers["Pragma"] = "no-cache"
    return response


@app.route("/")
@login_required
def index():
    """Show portfolio of stocks"""
    datas = db.execute("SELECT symbol, SUM(shares) AS sum_shares FROM transactions WHERE user_id = ? GROUP BY symbol HAVING sum_shares > 0", session["user_id"])
    
    total_stock = 0
    for data in datas:
        stock = yf.Ticker(data["symbol"])
        price = stock.info.get('currentPrice', 'N/A')

        total = price * data["sum_shares"] 
        data["price"] = price
        data["total"] = total
        total_stock += total
    cash = db.execute("SELECT cash FROM users WHERE id = ?", session["user_id"])[0]["cash"]
    total = total_stock + cash
    return render_template("index.html", datas=datas, cash=cash, total=total, usd=usd )


@app.route("/buy", methods=["GET", "POST"])
@login_required
def buy():
    if request.method == "POST":
        symbol = request.form.get("symbol")
        shares = request.form.get("shares")
        shares = int(shares)

        # type wrong symbol => out
        if lookup(symbol) == None:
            return apology("Invalid Symbol")
        
        # your cash = 0 => out
        cash = db.execute("SELECT cash FROM users WHERE id = ?", session["user_id"])
        if cash:
            cash = cash[0]["cash"]
        else:
            return apology("you dont have enough cash")

        stock = yf.Ticker(symbol)
        price = stock.info.get('currentPrice', 'N/A')
        total = price * shares
        if price * shares > cash:
            # transaction > cash => error
            return apology("you dont have enough cash")
        else:
            # add table transaction have shares and price and money
            db.execute("INSERT INTO transactions (user_id, symbol, shares, price, total) VALUES(?,?,?,?,?)", session["user_id"], symbol, shares, price, total)

            # action minus cash
            cashbefore = db.execute("SELECT cash FROM users WHERE id = ?", session["user_id"])
            cashbefore = cashbefore[0]["cash"]
            cashafter = cashbefore - price*shares

            db.execute("UPDATE users SET cash = ? WHERE id = ?", cashafter, session["user_id"])
            return redirect("/")
    else:
        cash = db.execute("SELECT cash FROM users WHERE id = ?", session["user_id"])[0]["cash"]
            
        return render_template("buy.html", cash=cash, usd=usd)


@app.route("/history")
@login_required
def history():
    """Show history of transactions"""
    rows = db.execute("SELECT * FROM transactions WHERE user_id = ? ORDER BY timestamp DESC", session["user_id"])
    return render_template("history.html", rows=rows, usd=usd)



@app.route("/login", methods=["GET", "POST"])
def login():
    """Log user in"""

    # Forget any user_id
    session.clear()

    # User reached route via POST (as by submitting a form via POST)
    if request.method == "POST":
        # Ensure username was submitted
        if not request.form.get("username"):
            return apology("must provide username", 403)

        # Ensure password was submitted
        elif not request.form.get("password"):
            return apology("must provide password", 403)

        # Query database for username
        rows = db.execute(
            "SELECT * FROM users WHERE username = ?", request.form.get("username")
        )

        # Ensure username exists and password is correct
        if len(rows) != 1 or not check_password_hash(
            rows[0]["hash"], request.form.get("password")
        ):
            return apology("invalid username and/or password", 403)

        # Remember which user has logged in
        session["user_id"] = rows[0]["id"]

        # Redirect user to home page
        return redirect("/")

    # User reached route via GET (as by clicking a link or via redirect)
    else:
        return render_template("login.html")


@app.route("/logout")
def logout():
    """Log user out"""

    # Forget any user_id
    session.clear()

    # Redirect user to login form
    return redirect("/")


@app.route("/quote", methods=["GET", "POST"])
@login_required
def quote():
    def topsymbol():
        # Lấy danh sách các symbol phổ biến
        popular_symbols = ["NFLX", "AAPL", "GOOGL", "TSLA", "MSFT", "NVDA", "AMZN"]

        infos_top7 = {}
        for symbol in popular_symbols:
            stock = yf.Ticker(symbol)
            # Chỉ lưu symbol và longname, không cần cấu trúc phức tạp
            infos_top7[symbol] = {
                "longname": stock.info.get('longName', 'N/A'),
                "currentPrice": stock.info.get('currentPrice', 'N/A')  # Lấy giá cổ phiếu hiện tại
            }
        return infos_top7
    
    if request.method == "POST":
        try:
            looksymbol = request.form.get("symbol")
            if looksymbol == None: 
                return apology("Invalid symbol", 400)
            
            stock = yf.Ticker(looksymbol)
            if not stock.info:
                return apology("your quote dont true")
            
            infos = {
            "longname": stock.info.get('longName', 'N/A'),
            "currentPrice": stock.info.get('currentPrice', 'N/A') 
            }
            
            infos_top7 = topsymbol()
            return render_template("quote.html", usd=usd, infos_top7=infos_top7, looksymbol=looksymbol, infos=infos)
        except:
            return apology("Invalid Symbol")
    else:
        infos_top7 = topsymbol()
        looksymbol = "NULL"
        infos = {"longname": "N/A",  "currentPrice":"N/A"}
        return render_template("quote.html", usd=usd, infos_top7=infos_top7, looksymbol=looksymbol, infos=infos)



@app.route("/register", methods=["GET", "POST"])
def register():
    """Register user"""
    if request.method == "POST" :
        # take user and password cause register
        register_username = request.form.get("username")
        register_password = request.form.get("password")

        # check password and re_password are the same
        if register_password != request.form.get("re_password"):
            return apology("Password and password(again) dont same")

        # check user name is exit, if dont exit => add users into database
        row = db.execute("SELECT * FROM users WHERE username = ?", register_username)
        if len(row) >= 1:
            return apology("users already exist")
        else:
            # add infomation
            db.execute("INSERT INTO users (username, hash) VALUES(?, ?)", register_username, generate_password_hash(register_password))
            # take data in database
            row = db.execute("SELECT * FROM users WHERE username = ?", register_username)
            session["user_id"] = row[0]["id"]
            return redirect("/")
    
    else:
        return render_template("register.html")


@app.route("/sell", methods=["GET", "POST"])
@login_required
def sell():
    """Sell shares of stock"""
    if request.method == "POST":
        symbol = request.form.get("symbol")
        shares = int(request.form.get("shares"))
        new_shares = int(-shares)
        # check shares you have and shares you want sell
        totalshares = db.execute("SELECT SUM(shares) AS sum_shares FROM transactions WHERE user_id = ? AND symbol =? GROUP BY user_id ",session["user_id"], symbol)

        if shares > totalshares[0]["sum_shares"]:
            return apology("you dont have enough shares")
        else:
            #sell, updatedatabase transaction shares = - shares
            stock = yf.Ticker(symbol)
            price = stock.info.get('currentPrice', 'N/A')
            total = new_shares * price
            db.execute("INSERT INTO transactions (user_id, symbol, shares, price, total) VALUES(?,?,?,?,?)", session["user_id"], symbol, new_shares , price, total)

            # action plus cash
            cashbefore = db.execute("SELECT cash FROM users WHERE id = ?", session["user_id"])
            cashbefore = cashbefore[0]["cash"]
            cashafter = cashbefore - total

            db.execute("UPDATE users SET cash = ? WHERE id = ?", cashafter, session["user_id"])
            return redirect("/")

    else:
        # find you have how many symbol
        symbol_differences = db.execute("SELECT DISTINCT symbol FROM transactions WHERE user_id = ?", session["user_id"])
        symbol_differences = db.execute("SELECT symbol FROM transactions WHERE user_id = ? GROUP BY symbol HAVING SUM(shares) > 0", session["user_id"])

        datas = db.execute("SELECT symbol, SUM(shares) AS sum_shares FROM transactions WHERE user_id = ? GROUP BY symbol HAVING sum_shares > 0", session["user_id"])

        return render_template("sell.html", symbol_differences=symbol_differences, datas=datas, usd=usd)
