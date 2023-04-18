const { GraphQLServer } = require('graphql-yoga');

PORT = 4000

const usersList = [
  { id: 1, name: "Jan Konieczny", email: "jan.konieczny@wonet.pl", login: "jkonieczny"},
  { id: 2, name: "Anna Wesołowska", email: "anna.w@sad.gov.pl", login: "anna.wesolowska" },
  { id: 3, name: "Piotr Waleczny", email: "piotr.waleczny@gp.pl", login: "p.waleczny" } ];

const todosList = [
  { id: 1, title: "Naprawić samochód",completed: false, user_id: 3 },
  { id: 2, title:"Posprzątać garaż",completed: true,  user_id: 3 },
  { id: 3, title:"Napisać e-mail",completed: false, user_id: 3 },
  { id: 4, title:"Odebrać buty",completed: false, user_id: 2 },
  { id: 5, title: "Wysłać paczkę",  completed: true,  user_id: 2 },
  { id: 6, title: "Zamówic kuriera",  completed: false, user_id: 3 }
];

function todoById(parent, args, context, info) {
  return todosList.find(t => t.id == args.id);
}
function userById(parent, args, context, info) {
  return usersList.find(u => u.id == args.id);
}

const resolvers = {
  Query: {
      demo: () => 'Witaj, GraphQL działa',
      users: () => usersList,
      todos: () => todosList,
      todo: (parent, args, context, info) => todoById(parent, args, context, info),
      user: (parent, args, context, info) => userById(parent, args, context, info),
  },
  User:{
    todos: (parent, args, context, info) => {
        return todosList.filter(t => t.user_id == parent.id);
    }
  },
  ToDoItem:{
    user: (parent, args, context, info) => {
        return usersList.find(u => u.id == parent.user_id);
    }
  } 
}

const server = new GraphQLServer({
  typeDefs: './src/schema.graphql',
  resolvers,
});

server.start({ port: PORT }, () => console.log(`Server is running on http://localhost:`+PORT));