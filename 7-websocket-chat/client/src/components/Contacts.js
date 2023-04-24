import React from 'react';
import { useContacts } from '../contexts/ContactsProvider';
import { ListGroup } from 'react-bootstrap';

export default function Contacts() {
  const { contacts } = useContacts();

  return (
    <ListGroup variant="flush">
      {contacts.map(({ login }) => (
        <ListGroup.Item key={login}>{login}</ListGroup.Item>
      ))}
    </ListGroup>
  );
}
