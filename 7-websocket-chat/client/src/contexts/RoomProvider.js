/* eslint-disable no-unused-vars */
/* eslint-disable react/prop-types */
import React, { useContext, useState, useEffect, useCallback } from 'react';
import { useSocket } from './SocketProvider';

const SERVER_SENDER = 'SERVER';

const RoomContext = React.createContext();

export function useRooms() {
  return useContext(RoomContext);
}

export function RoomProvider({ login, children }) {
  const socket = useSocket();
  const [messages, setMessages] = useState([]);
  const [roomUsers, setRoomUsers] = useState([]);

  const addMessageToConversation = ({ sender, text, date, prevMessages, users, file }) => {
    let newMessages = [...messages];
    if (users) {
      setRoomUsers([...users]);
    }
    if (prevMessages) {
      newMessages = [...newMessages, ...prevMessages];
    }
    if (sender && text && date) {
      newMessages.push({ sender, text, date, file });
    }
    setMessages(newMessages);
  };

  useEffect(() => {
    if (!socket) return;
    socket.on('receive-message', addMessageToConversation);

    return () => socket.off('receive-message');
  }, [socket, addMessageToConversation]);

  function sendMessage({ text, file }) {
    const date = new Date();
    if (file) {
      socket.emit('send-message', {
        file: file,
        mimeType: file.type,
        fileName: file.name,
        date: date,
        sender: login
      });
    }
    if (text) {
      socket.emit('send-message', {
        text: text,
        date: date,
        sender: login
      });
    }
    addMessageToConversation({
      text: text,
      date: date,
      sender: login,
      file: file
    });
  }

  function emitTyping() {
    socket.emit('typing', { sender: login });
  }

  function emitStopTyping() {
    socket.emit('stop-typing', { sender: login });
  }

  function leaveRoom() {
    socket.disconnect(true);
  }

  const filteredMessages = () =>
    messages.filter((mess) => !(mess.sender === SERVER_SENDER && mess.text.includes(login)));

  const isMessageFromMe = (sender) => sender === login;
  const isMessageFromServer = (sender) => sender === SERVER_SENDER;

  const typingUsers = () =>
    roomUsers.filter(({ user, typing }) => typing === true && user !== login) ?? [];

  return (
    <RoomContext.Provider
      value={{
        sendMessage,
        isMessageFromMe,
        isMessageFromServer,
        messages,
        filteredMessages,
        leaveRoom,
        emitTyping,
        emitStopTyping,
        roomUsers,
        typingUsers
      }}>
      {children}
    </RoomContext.Provider>
  );
}
