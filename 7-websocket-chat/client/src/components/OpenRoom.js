/* eslint-disable no-unused-vars */
import React, { useState, useCallback } from 'react';
import { Form, InputGroup, Button } from 'react-bootstrap';
import './OpenRoom.scss';
import { useRooms } from '../contexts/RoomProvider';
import { debounce } from 'lodash';

export default function OpenRoom() {
  const [text, setText] = useState('');

  const setRef = useCallback((node) => {
    if (node) {
      node.scrollIntoView({ smooth: true }, []);
    }
  });

  const {
    sendMessage,
    isMessageFromMe,
    isMessageFromServer,
    filteredMessages,
    emitTyping,
    emitStopTyping,
    typingUsers
  } = useRooms();

  const handleSubmit = (e) => {
    e.preventDefault();
    sendMessage(text);
    setText('');
  };

  const handleKeyDown = debounce(() => {
    emitTyping();
  }, 500); // wait for 500 milliseconds

  const handleKeyUp = debounce(() => {
    emitStopTyping();
  }, 3000); // wait for 1 second

  const messages = filteredMessages();

  const usersTyping = typingUsers();

  return (
    <div className="d-flex flex-column flex-grow-1 openroom-wrapper">
      <div className="flex-grow-1 overflow-auto conversation">
        <div className="d-flex flex-column align-items-start justify-content-end px-3">
          {messages.map(({ text, sender, date }, idx) => {
            const lastMessage = messages.length - 1;
            return isMessageFromServer(sender) ? (
              <div
                ref={lastMessage ? setRef : null}
                key={idx}
                className="my-1 d-flex flex-column server-message">
                <div className="rounded px-2 py-1 server-message-inner">
                  {text}{' '}
                  <span className="text-muted small">
                    {'(' + new Date(date).getHours() + ':' + new Date(date).getMinutes() + ')'}
                  </span>
                </div>
              </div>
            ) : (
              <div
                ref={lastMessage ? setRef : null}
                key={idx}
                className={`my-1 d-flex flex-column ${
                  isMessageFromMe(sender) ? 'align-self-end' : ''
                }`}>
                <div
                  className={`rounded px-2 py-1 ${
                    isMessageFromMe(sender) ? 'bg-primary text-white' : 'border'
                  }`}>
                  {text}
                </div>
                <div className={`text-muted small ${isMessageFromMe(sender) ? 'text-end' : ''}`}>
                  {isMessageFromMe(sender) ? 'You' : sender},{' '}
                  {new Date(date).getHours() + ':' + new Date(date).getMinutes()}
                </div>
              </div>
            );
          })}
          {usersTyping.length ? (
            usersTyping.map(({ user }) => (
              <div className="text-muted small" key={user}>
                {user} is typing...
              </div>
            ))
          ) : (
            <></>
          )}
        </div>
      </div>
      <Form onSubmit={handleSubmit}>
        <Form.Group className="m-2">
          <InputGroup>
            <Form.Control
              id="message-control"
              as="textarea"
              required
              value={text}
              onChange={(e) => setText(e.target.value)}
              onKeyDown={handleKeyDown}
              onKeyUp={handleKeyUp}
            />
            <Button type="submit">Send</Button>
          </InputGroup>
        </Form.Group>
      </Form>
    </div>
  );
}
