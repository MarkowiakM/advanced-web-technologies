const http = require("http");
const socketIO = require("socket.io");

const server = http.createServer();
const io = socketIO(server, {
  cors: {
    origin: "*",
    methods: ["GET", "POST"],
  },
});

io.on("connection", (socket) => {
  const login = socket.handshake.query.login;
  const option = socket.handshake.query.option;
  socket.join(login);

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
});

server.listen(5000, () => {
  console.log("Server listening on port 5000");
});
