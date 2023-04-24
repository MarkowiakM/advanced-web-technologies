import React from 'react';
import { useConversations } from '../contexts/ConversationsProvider';
import { ListGroup } from 'react-bootstrap';

export default function Conversations() {
  const { conversations, selectedConversation, setSelectedConversation, getRecipients } =
    useConversations();
  return (
    <ListGroup variant="flush">
      {conversations.map((_, id) => {
        return (
          <ListGroup.Item
            key={id}
            active={selectedConversation === id}
            action
            onClick={() => setSelectedConversation(id)}>
            {getRecipients(id).join(', ')}
          </ListGroup.Item>
        );
      })}
    </ListGroup>
  );
}
