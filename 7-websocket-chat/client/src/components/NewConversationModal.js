/* eslint-disable react/prop-types */
import React, { useState, useRef } from 'react';
import { Modal, Form, Button } from 'react-bootstrap';
import { useContacts } from '../contexts/ContactsProvider';
import { useConversations } from '../contexts/ConversationsProvider';
export default function NewConversationModal({ closeModal }) {
  const { contacts } = useContacts();
  const [selectedContacts, setSelectedContacts] = useState([]);
  const { createConversation } = useConversations();
  const nameRef = useRef();
  const handleSubmit = (e) => {
    e.preventDefault();
    createConversation(nameRef.current.value, selectedContacts);
    closeModal();
  };

  const handleCheckboxChange = (login) => {
    setSelectedContacts((prevSelectedContacts) => {
      if (prevSelectedContacts.includes(login)) {
        return prevSelectedContacts.filter((prevId) => prevId !== login);
      } else {
        return [...prevSelectedContacts, login];
      }
    });
  };

  return (
    <>
      <Modal.Header closeButton>Create conversation</Modal.Header>
      <Modal.Body>
        <Form onSubmit={handleSubmit}>
          {contacts.map((contact) => (
            <Form.Group controlId={contact.login} key={contact.login}>
              <Form.Check
                type="checkbox"
                value={selectedContacts.includes(contact.login)}
                label={contact.login}
                onChange={() => handleCheckboxChange(contact.login)}></Form.Check>
            </Form.Group>
          ))}
          <Form.Label>Conversation name</Form.Label>
          <Form.Control type="text" ref={nameRef} required></Form.Control>
          <Button type="submit" className="mt-2">
            Create
          </Button>
        </Form>
      </Modal.Body>
    </>
  );
}
