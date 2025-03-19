"""
database
table: users
columns: user_id, username, hash, cash,

table: transactions
columns: id, user_id, catalog_id, amount, date

table: catalog
columns: id, name, father_id, father_name

link to see relationships: https://dbdiagram.io/d/Expensive-app-67c29cdd263d6cf9a0dde917


"""
from cs50 import SQL
import sqlite3

db = SQL("sqlite:///expensive_app.db")

from cs50 import SQL

db = SQL("sqlite:///expensive_app.db")

def create_tables():
    db.execute("""CREATE TABLE users (
        id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
        username TEXT NOT NULL,
        hash TEXT NOT NULL,
        cash FLOAT NOT NULL
    )""")

    db.execute("""CREATE TABLE categories (
        id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
        name TEXT NOT NULL,
        father_id INTEGER NOT NULL,
        father_name TEXT NOT NULL
    )""")

    db.execute("""CREATE TABLE transactions (
        id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
        user_id INTEGER NOT NULL,
        catalogies TEXT NOT NULL,
        amount FLOAT NOT NULL,
        date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        FOREIGN KEY (user_id) REFERENCES users(id)
    )""")
    db.execute("ALTER TABLE transactions ADD COLUMN description TEXT;")

    db.execute("""
        CREATE TABLE IF NOT EXISTS categories_salary (
            id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
            name TEXT NOT NULL
                )""")

def add_categories():
    db.execute("INSERT INTO categories (name, father_id, father_name) VALUES (?, ?, ?)", add, add ,add)


def add_begin_categories():
    db.execute("INSERT INTO categories (name, father_id, father_name) VALUES ('Bills & Utilities', 1, 'Bills & Utilities')")
    db.execute("INSERT INTO categories (name, father_id, father_name) VALUES ('Education', 2, 'Education')")
    db.execute("INSERT INTO categories (name, father_id, father_name) VALUES ('Entertainment', 3, 'Entertainment')")
    db.execute("INSERT INTO categories (name, father_id, father_name) VALUES ('Family', 4, 'Family')")
    db.execute("INSERT INTO categories (name, father_id, father_name) VALUES ('Food & Beverage', 5, 'Food & Beverage')")
    # create insert categories gifts & Donations
    db.execute("INSERT INTO categories (name, father_id, father_name) VALUES ('Gifts & Donations', 6, 'Gifts & Donations')")
    # create insert categories Health & Fitness
    db.execute("INSERT INTO categories (name, father_id, father_name) VALUES ('Health & Fitness', 7, 'Health & Fitness')")
    # create insert categories Insurance
    db.execute("INSERT INTO categories (name, father_id, father_name) VALUES ('Insurance', 8, 'Insurance')")
    # create insert categories Investment
    db.execute("INSERT INTO categories (name, father_id, father_name) VALUES ('Investment', 9, 'Investment')")
    # create insert categories Other expenses
    db.execute("INSERT INTO categories (name, father_id, father_name) VALUES ('Other expenses', 10, 'Other expenses')")
    # create insert categories Shopping
    db.execute("INSERT INTO categories (name, father_id, father_name) VALUES ('Shopping', 11, 'Shopping')")
    # create insert categories Transportation
    db.execute("INSERT INTO categories (name, father_id, father_name) VALUES ('Transportation', 12, 'Transportation')")
    # create insert categories Work 
    db.execute("INSERT INTO categories (name, father_id, father_name) VALUES ('Work', 13, 'Work')")
    
    # create insert categories Salary
    db.execute("INSERT INTO categories_salary (name) VALUES ('Salary')")
    # create insert categories Other income
    db.execute("INSERT INTO categories_salary (name) VALUES ('Other income')")


def category_symbol():
    return db.execute("SELECT DISTINCT name FROM categories")

def category_symbol_salary():
    return db.execute("SELECT DISTINCT name FROM categories_salary")

def add_category_expense(name):
    return db.execute("INSERT INTO categories (name) VALUES(?)", name )

def add_category_salary(name):
    return db.execute("INSERT INTO categories_salary (name) VALUES(?)", name )

