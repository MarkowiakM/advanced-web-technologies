const { GraphQLServer } = require('graphql-yoga');
const mysql = require("mysql");

PORT = 4002

const connection = mysql.createConnection({
    host: 'mysql-halpressteam.halpress.eu',
    user: 'db100072579',
    password: 'wdFXWSTaW6QbS',
    database: 'db100072579'
});

connection.connect(function (err) {
    if (err) throw err;
})

function query(sql, args) {
    return new Promise((resolve, reject) => {
        connection.query(sql, args, (error, results) => {
            if (error) {
                reject(error);
            } else {
                resolve(results);
            }
        });
    });
}

function getToDosByUserId(id) {
    return query(`select * from ToDos where userId = ?`, [id])
}

function getTodoById(id) {
    return query(`select * from ToDos where id = ?`, [id])
}

function getUserById(id) {
    return query(`select * from Users where id = ?`, [id])
}

const resolvers = {
    Query: {
        users: async () => query('select * from Users'),
        todos: async () => query('select * from ToDos'),
        todo: async (parent, args, context, info) =>{
            const responce = await getTodoById(args.id);
            return responce[0];
        },
        user: async (parent, args, context, info) => {
            const responce = await getUserById(args.id)
            return responce[0];
        },
    },
    User: {
        todos: async (parent, args, context, info) => {
            // Not optimal - a lot of requests
            const responce = getToDosByUserId(parent.id);
            return responce;
        }
    },
    ToDoItem: {
        user: async (parent, args, context, info) => {
            // Not optimal - a lot of requests
            const responce = await getUserById(parent.id)
            return responce[0];
        }
    },
    Mutation: {
        createUser: async (parent, { input }, context, info) => {
            const { name, email, login } = input;
            const sql = 'INSERT INTO Users (name, email, login) VALUES (?, ?, ?)';
            const args = [name, email, login];
            const result = await query(sql, args);
            const userId = result.insertId;
            const user = await query('SELECT * FROM Users WHERE id = ?', [userId]);
            return user[0];
        },

        updateUser: async (parent,  { id, input }, context, info) => {
            const {name, email, login } = input;
            const sql_query = 'UPDATE Users SET name = ?, email = ?, login = ? WHERE id = ?'
            const args = [name, email, login, id]
            const update = await query(sql_query, args);
            const updatedUser = await query('SELECT * FROM Users WHERE id = ?', [id]);
            return updatedUser[0];
        },

        deleteUser: async (parent, { id }, context, info) => {
            const sql_query = 'DELETE FROM Users WHERE id = ?';
            const args = [id];
            await query(sql_query, args);
            return id;
        },
        createToDo: async (parent, { input }, context, info) => {
            const { title, completed, userId } = input;
            const sql_query = 'INSERT INTO ToDos (title, completed, userId) VALUES (?, ?, ?)';
            const args = [title, completed, userId];
            const result = await query(sql_query, args);
            const todoId = result.insertId;
            const todo = await query('SELECT * FROM ToDos WHERE id = ?', [todoId]);
            return todo[0];
        },

        updateToDo: async (parent, { id, input }, context, info) => {
            const { title, completed } = input;
            await query('UPDATE ToDos SET title = ?, completed = ? WHERE id = ?', [title, completed, id]);
            const todo = await query('SELECT * FROM ToDos WHERE id = ?', [id]);
            return todo[0];
        },

        deleteToDo: async (parent, { id }, context, info) => {
            const sql_query = 'DELETE FROM ToDos WHERE id = ?';
            const args = [id];
            await query(sql_query, args);
            return id;
        }
    }
}

const server = new GraphQLServer({
    typeDefs: './src/database_schema.graphql',
    resolvers,
});

server.start({ port: PORT }, () => console.log(`Server is running on http://localhost:` + PORT));