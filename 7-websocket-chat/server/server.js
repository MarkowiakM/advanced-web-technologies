const CONVERSATION_OPTION = "my conversations";
const SERVER_SENDER = "SERVER";

const http = require("http");
const socketIO = require("socket.io");
const server = http.createServer();
const io = socketIO(server, {
  cors: {
    origin: "*",
    methods: ["GET", "POST"],
  },
});

const MESSAGES = {
  work: [],
  hobbys: [],
  animals: [],
  holidays: [],
};

const USERS = {
  work: [],
  hobbys: [],
  animals: [],
  holidays: [],
};

io.on("connection", (socket) => {
  const login = socket.handshake.query.login;
  const room = socket.handshake.query.option;
  if (room === CONVERSATION_OPTION) {
    socket.join(login);
  } else {
    socket.join(room);
    if (!USERS[room].includes(login)) {
      USERS[room].push({ user: login, typing: false });
    }
    const message = {
      sender: SERVER_SENDER,
      text: `${login} has joined the ${room} chat!`,
      date: new Date(),
      users: USERS[room],
    };
    MESSAGES[room].push(message);
    socket.emit("receive-message", {
      sender: SERVER_SENDER,
      text: `Welcome to ${room}!`,
      date: new Date(),
      prevMessages: MESSAGES[room],
    });
    socket.broadcast.to(room).emit("receive-message", message);
  }

  if (room === CONVERSATION_OPTION) {
    socket.on(
      "send-message",
      ({ recipients, text, date, conversationId, sender }) => {
        recipients.forEach((recipient) => {
          socket.broadcast.to(recipient).emit("receive-message", {
            recipients: recipients,
            text: text,
            date: date,
            conversationId: conversationId,
            sender: sender,
          });
        });
      }
    );
  } else {
    socket.on("send-message", (message) => {
      socket.broadcast.to(room).emit("receive-message", message);
      MESSAGES[room].push(message);
    });

    socket.on("typing", ({ sender }) => {
      const senderIndex = USERS[room].findIndex(({ user }) => user === sender);
      USERS[room][senderIndex].typing = true;

      const message = {
        sender: SERVER_SENDER,
        users: USERS[room],
      };
      socket.broadcast.to(room).emit("receive-message", message);
    });

    socket.on("stop-typing", ({ sender }) => {
      const senderIndex = USERS[room].findIndex(({ user }) => user === sender);
      USERS[room][senderIndex].typing = false;
      const message = {
        sender: SERVER_SENDER,
        users: USERS[room],
      };
      socket.broadcast.to(room).emit("receive-message", message);
    });

    socket.on("disconnect", () => {
      USERS[room] = USERS[room].filter(({ user }) => user !== login);
      const message = {
        sender: SERVER_SENDER,
        text: `${login} has left the ${room} chat!`,
        date: new Date(),
        users: USERS[room],
      };
      io.to(room).emit("receive-message", message);
      MESSAGES[room].push(message);
    });
  }
});

server.listen(5000, () => {
  console.log("Server listening on port 5000");
});
