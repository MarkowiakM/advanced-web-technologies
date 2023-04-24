/* eslint-disable no-unused-vars */
import React, { useState, useCallback } from 'react';
import { Form, InputGroup, Button } from 'react-bootstrap';
import './OpenConversation.scss';
import { useConversations } from '../contexts/ConversationsProvider';

export default function OpenConversation() {
  const [text, setText] = useState('');
  const setRef = useCallback((node) => {
    if (node) {
      node.scrollIntoView({ smooth: true }, []);
    }
  });
  const { sendMessage, selectedConversation, conversations, isMessageFromMe } = useConversations();

  const handleSubmit = (e) => {
    e.preventDefault();
    sendMessage(selectedConversation, text);
    setText('');
  };

  return conversations.length ? (
    <div className="d-flex flex-column flex-grow-1 openconversation-wrapper">
      <div className="flex-grow-1 overflow-auto conversation">
        <div className="d-flex flex-column align-items-start justify-content-end px-3">
          {conversations[selectedConversation].messages.map(({ text, sender, date }, idx) => {
            const lastMessage = conversations[selectedConversation].messages.length - 1;
            return (
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
            />
            <Button type="submit">Send</Button>
          </InputGroup>
        </Form.Group>
      </Form>
    </div>
  ) : (
    <></>
  );
}
