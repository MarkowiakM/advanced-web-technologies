import React from 'react';
import { useConversations } from '../contexts/ConversationsProvider';
import { ListGroup } from 'react-bootstrap';

export default function Conversations() {
  const { conversations, selectedConversation, setSelectedConversation } = useConversations();
  return (
    <ListGroup variant="flush">
      {conversations.map((conversation, id) => {
        return (
          <ListGroup.Item
            key={id}
            active={selectedConversation === id}
            action
            onClick={() => setSelectedConversation(id)}>
            {conversation.name}
          </ListGroup.Item>
        );
      })}
    </ListGroup>
  );
}
