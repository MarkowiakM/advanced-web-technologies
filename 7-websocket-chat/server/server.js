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

io.on("connection", (socket) => {
  const login = socket.handshake.query.login;
  const room = socket.handshake.query.option;
  if (room === CONVERSATION_OPTION) {
    socket.join(login);
  } else {
    socket.join(room);
    const message = {
      sender: SERVER_SENDER,
      text: `${login} has joined the ${room} chat!`,
      date: new Date(),
    };
    MESSAGES[room].push(message);
    socket.emit("receive-message", {
      sender: SERVER_SENDER,
      text: `Welcome to ${room}!`,
      date: new Date(),
      prevMessages: MESSAGES[room],
    });
    socket.broadcast.to(room).emit("receive-message", message);
    console.log(MESSAGES[room]);
  }

  // dodac mozliwosc opuszczenia czatu

  if (room === CONVERSATION_OPTION) {
    socket.on(
      "send-message",
      ({ recipients, text, date, conversationId, sender }) => {
        console.log(recipients);
        console.log(text);
        console.log(sender);
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
    socket.on("send-message", ({ text, date, sender }) => {
      console.log(text);
      console.log(sender);
      socket.broadcast.to(room).emit("receive-message", { text, date, sender });
      MESSAGES[room].push({ text, date, sender });
    });

    socket.on("typing", ({ sender }) => {
      const message = {
        sender: SERVER_SENDER,
        text: `${sender} is typing`,
        date: new Date(),
        typing: true,
      };
      console.log(sender + " is typing");
      socket.broadcast.to(room).emit("receive-message", message);
    });

    socket.on("disconnect", () => {
      const message = {
        sender: SERVER_SENDER,
        text: `${login} has left the ${room} chat!`,
        date: new Date(),
      };
      io.to(room).emit("receive-message", message);
      MESSAGES[room].push(message);
    });
  }
});

server.listen(5000, () => {
  console.log("Server listening on port 5000");
});
