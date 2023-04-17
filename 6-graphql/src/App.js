const { GraphQLServer } = require('graphql-yoga');
const axios = require("axios");

PORT = 5000

async function getRestUsersList() {
  try {
    const users = await axios.get("https://jsonplaceholder.typicode.com/users");
    console.log(users);
    return users.data.map(({ id, name, email, username }) => ({
      id: id,
      name: name,
      email: email,
      login: username,
    }));
  } catch (error) {
    throw error;
  }
}

async function getRestUserById(id) {
  try {
    const user = await axios.get(`https://jsonplaceholder.typicode.com/users/${id}`);
    console.log(user);
    return {
      id: user.data.id,
      name: user.data.name,
      email: user.data.email,
      login: user.data.username,
    };
  } catch (error) {
    throw error;
  }
}

async function getRestTodosList() {
  try {
    const todos = await axios.get("https://jsonplaceholder.typicode.com/todos");
    console.log(todos);
    return todos.data.map(({ id, title, completed, userId}) => ({
      id: id,
      title: title,
      completed: completed,
      user: user
    }));
  } catch (error) {
    throw error;
  }
}

async function getRestTodoById(id) {
  try {
    const todo = await axios.get(`https://jsonplaceholder.typicode.com/todos/${id}`);
    console.log(todo);
    return {
      id: todo.data.id,
      title: todo.data.title,
      completed: todo.data.completed,
      user: todo.data.userId
    };
  } catch (error) {
    throw error;
  }
}

async function getRestTodoByUserId(userId) {
  try {
    const todos = await axios.get(`https://jsonplaceholder.typicode.com/todos/?userId=${userId}`);
    console.log(todos);
    return todos.data.map(({ id, title, completed, userId}) => ({
      id: id,
      title: title,
      completed: completed,
      user: {
        id: userId,
      }
    }));
  } catch (error) {
    throw error;
  }
}


const resolvers = {
  Query: {
      users: async () => getRestUsersList(),
      todos: async () => getRestTodosList(),
      todo: async (parent, args, context, info) => getRestTodoById(args.id),
      user: async (parent, args, context, info) => getRestUserById(args.id),
  },
  User:{
    todos: (parent, args, context, info) => {
        return getRestTodoByUserId(parent.id);
    }
  },
  ToDoItem:{
    user: (parent, args, context, info) => {
        return getRestUserById(parent.id);
    }
  } 
}

const server = new GraphQLServer({
  typeDefs: './src/schema.graphql',
  resolvers,
});

server.start({ port: PORT }, () => console.log(`Server is running on http://localhost:`+PORT));