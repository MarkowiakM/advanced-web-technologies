/* eslint-disable react/prop-types */
import React, { useContext, useState } from 'react';
import { useLocalStorage } from '../hooks/useLocalStorage';

const ConversationsContext = React.createContext();

export function useConversations() {
  return useContext(ConversationsContext);
}

export function ConversationsProvider({ login, children }) {
  const [conversations, setConversations] = useLocalStorage('conversations', []);
  const [selectedConversation, setSelectedConversation] = useState(0);

  function createConversation(logins) {
    setConversations((prevConversations) => [
      ...prevConversations,
      { users: [...logins, login], messages: [] }
    ]);
  }

  function addMessageToConversation(conversationId, message) {
    const newConversations = [...conversations];
    newConversations[conversationId].messages.push(message);
    setConversations(newConversations);
  }

  function sendMessage(conversationId, text) {
    addMessageToConversation(conversationId, {
      recipients: getRecipients(conversationId),
      text: text,
      sender: login,
      date: new Date()
    });
    console.log(conversations[conversationId]);
  }

  const getRecipients = (conversationId) => {
    return conversations[conversationId].users.filter((user) => user !== login);
  };

  const isMessageFromMe = (message) => message.sender === login;

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
