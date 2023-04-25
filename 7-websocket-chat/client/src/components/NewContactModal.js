/* eslint-disable react/prop-types */
import React, { useRef } from 'react';
import { Modal, Form, Button } from 'react-bootstrap';
import { useContacts } from '../contexts/ContactsProvider';

export default function NewContactModal({ closeModal }) {
  const loginRef = useRef();
  const { createContact } = useContacts();

  const handleSubmit = (e) => {
    e.preventDefault();
    createContact(loginRef.current.value);
    closeModal();
  };
  return (
    <>
      <Modal.Header closeButton>Create contact</Modal.Header>
      <Modal.Body>
        <Form onSubmit={handleSubmit}>
          <Form.Label>Login</Form.Label>
          <Form.Control type="text" ref={loginRef} required></Form.Control>
          <Button type="submit" className="mt-2">
            Create
          </Button>
        </Form>
      </Modal.Body>
    </>
  );
}
