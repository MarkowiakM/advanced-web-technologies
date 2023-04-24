/* eslint-disable react/prop-types */
import React, { useRef } from 'react';
import { Container, Form, Button } from 'react-bootstrap';
import './Login.scss';

export function Login({ login, onLoginSubmit, setShowDashboard }) {
  const loginRef = useRef();
  const handleSubmit = (e) => {
    e.preventDefault();
    onLoginSubmit(loginRef.current.value);
    setShowDashboard(true);
  };

  return (
    <Container className="align-items-center d-flex" id="login-wrapper">
      <Form onSubmit={handleSubmit} className="w-100">
        <Form.Group>
          <Form.Label>Enter your login</Form.Label>
          <Form.Control type="text" ref={loginRef} required defaultValue={login}></Form.Control>
        </Form.Group>
        <Button type="submit" className="mt-2">
          Join chat
        </Button>
      </Form>
    </Container>
  );
}
