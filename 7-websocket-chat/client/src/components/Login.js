/* eslint-disable react/prop-types */
import React, { useRef, useState } from 'react';
import { Container, Form, Button } from 'react-bootstrap';
import './Login.scss';

const OPTIONS = ['my conversations', 'work', 'hobbys', 'animals', 'holidays'];

export function Login({ login, onLoginSubmit, setShowDashboard, setChosenOption }) {
  const loginRef = useRef();
  const [selectedOption, setSelectedOption] = useState('holidays');
  const handleSubmit = (e) => {
    e.preventDefault();
    onLoginSubmit(loginRef.current.value);
    setShowDashboard(true);
    setChosenOption(selectedOption);
  };

  return (
    <Container className="align-items-center d-flex" id="login-wrapper">
      <Form onSubmit={handleSubmit} className="w-100">
        <Form.Group className="pb-5">
          <Form.Label>Enter your login</Form.Label>
          <Form.Control type="text" ref={loginRef} required defaultValue={login}></Form.Control>
        </Form.Group>
        <Form.Label>Choose room</Form.Label>
        {OPTIONS.map((option) => (
          <Form.Group controlId={option} key={option}>
            <Form.Check
              type="radio"
              checked={selectedOption == option}
              label={option}
              onChange={() => setSelectedOption(option)}></Form.Check>
          </Form.Group>
        ))}
        <Button type="submit" className="mt-2">
          Join
        </Button>
      </Form>
    </Container>
  );
}
