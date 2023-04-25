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

  const addMessageToConversation = ({ sender, text, date, prevMessages, typing }) => {
    let newMessages = [...messages];
    if (typing) {
      console.log('typing from server');
    }
    if (prevMessages) {
      newMessages = [...newMessages, ...prevMessages];
    }
    newMessages.push({ sender, text, date });
    setMessages(newMessages);
  };

  useEffect(() => {
    if (!socket) return;
    socket.on('receive-message', addMessageToConversation);

    return () => socket.off('receive-message');
  }, [socket, addMessageToConversation]);

  function sendMessage(text) {
    const date = new Date();
    socket.emit('send-message', {
      text: text,
      date: date,
      sender: login
    });
    addMessageToConversation({
      text: text,
      date: date,
      sender: login
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
        emitStopTyping
      }}>
      {children}
    </RoomContext.Provider>
  );
}
