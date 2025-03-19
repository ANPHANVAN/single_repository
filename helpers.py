from flask import redirect, render_template, session
from functools import wraps
import pandas as pd


def apology(message, code=400):
    """Render message as an apology to user."""

    def escape(s):
        """
        Escape special characters.

        https://github.com/jacebrowning/memegen#special-characters
        """
        for old, new in [
            ("-", "--"),
            (" ", "-"),
            ("_", "__"),
            ("?", "~q"),
            ("%", "~p"),
            ("#", "~h"),
            ("/", "~s"),
            ('"', "''"),
        ]:
            s = s.replace(old, new)
        return s

    return render_template("apology.html", top=code, bottom=escape(message)), code


def login_required(f):
    """
    Decorate routes to require login.

    https://flask.palletsprojects.com/en/latest/patterns/viewdecorators/
    """

    @wraps(f)
    def decorated_function(*args, **kwargs):
        if session.get("user_id") is None:
            return redirect("/login")
        return f(*args, **kwargs)

    return decorated_function

def groupExpense(df, range_time="month"):

    df_expense = df[df["amount"] < 0].copy()
    df_expense_group = df_expense.groupby(range_time)["amount"].sum().reset_index()
    df_expense_group[range_time] = df_expense_group[range_time].astype(str)
    # minus amount
    df_expense_group["amount"] = - df_expense_group["amount"]
    df_json_expense_group = df_expense_group.to_json(orient="records")
    return df_json_expense_group

