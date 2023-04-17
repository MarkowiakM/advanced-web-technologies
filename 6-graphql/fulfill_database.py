import mysql.connector
import requests

db = mysql.connector.connect(
    host= 'mysql-halpressteam.halpress.eu',
    user= 'db100072579',
    password= 'wdFXWSTaW6QbS',
    database= 'db100072579'
)


users_data = requests.get("https://jsonplaceholder.typicode.com/users").json()
for user in users_data:
    cursor = db.cursor()
    sql_query = "INSERT INTO Users (id, name, email, login) VALUES (%s, %s, %s, %s)"
    values = (user["id"], user["name"], user["email"], user["username"])
    try:
        cursor.execute(sql_query, values)
    except RuntimeError:
        print("Error")
    db.commit()


todos_data = requests.get("https://jsonplaceholder.typicode.com/todos").json()
for todo in todos_data:
    cursor = db.cursor()
    sql_query = "INSERT INTO ToDos (id, title, completed, userId) VALUES (%s, %s, %s, %s)"
    values = (todo["id"], todo["title"], todo["completed"], todo["userId"])
    try:
        cursor.execute(sql_query, values)
    except RuntimeError:
        print("Error")
    db.commit()

db.close()