/* eslint-disable no-unused-vars */
/* eslint-disable react/prop-types */
import React, { useContext, useState, useEffect, useCallback } from 'react';
import { useLocalStorage } from '../hooks/useLocalStorage';
import { useSocket } from './SocketProvider';

const ConversationsContext = React.createContext();

export function useConversations() {
  return useContext(ConversationsContext);
}

export function ConversationsProvider({ login, children }) {
  const [conversations, setConversations] = useLocalStorage('conversations', []);
  const [selectedConversation, setSelectedConversation] = useState(0);
  const socket = useSocket();

  function createConversation(name, logins) {
    setConversations((prevConversations) => [
      ...prevConversations,
      { name: name, users: [...logins, login], messages: [] }
    ]);
  }

  const addMessageToConversation = useCallback(
    ({ recipients, text, date, conversationId, sender }) => {
      console.log(conversationId);
      console.log(conversations);
      console.log(conversations[conversationId].messages);
      const newConversations = [...conversations];
      newConversations[conversationId].messages.push({ recipients, sender, text, date });
      setConversations(newConversations);
    },
    [setConversations]
  );

  useEffect(() => {
    if (!socket) return;
    socket.on('receive-message', addMessageToConversation);

    return () => socket.off('receive-message');
  }, [socket, addMessageToConversation]);

  // useEffect(() => {
  //   if (!socket) return;
  //   socket.on('receive-server-message', addMessageToConversation);

  //   return () => socket.off('receive-server-message');
  // }, [socket, addMessageToConversation]);

  function sendMessage(conversationId, text) {
    const date = new Date();
    socket.emit('send-message', {
      recipients: getRecipients(conversationId),
      text: text,
      date: date,
      conversationId: conversationId,
      sender: login
    });
    addMessageToConversation({
      recipients: getRecipients(conversationId),
      text: text,
      date: date,
      conversationId: conversationId,
      sender: login
    });
    console.log(conversations[conversationId]);
  }

  const getRecipients = (conversationId) => {
    return conversations[conversationId].users.filter((user) => user !== login);
  };

  const isMessageFromMe = (sender) => sender === login;

  return (
    <ConversationsContext.Provider
      value={{
        conversations,
        createConversation,
        selectedConversation,
        setSelectedConversation,
        sendMessage,
        isMessageFromMe,
        getRecipients
      }}>
      {children}
    </ConversationsContext.Provider>
  );
}
