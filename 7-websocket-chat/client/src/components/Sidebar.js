/* eslint-disable no-unused-vars */
/* eslint-disable react/prop-types */
import React, { useState } from 'react';
import { Tab, Nav, Button, Modal, ListGroup } from 'react-bootstrap';
import './Sidebar.scss';
import Conversations from './Conversations';
import Contacts from './Contacts';
import NewConversationModal from './NewConversationModal';
import NewContactModal from './NewContactModal';
import { useRooms } from '../contexts/RoomProvider';

const CONVERSATIONS_KEY = 'conversations';
const CONTACTS_KEY = 'contacts';
const ROOMS_KEY = 'room';
const CONVERSATION_OPTION = 'my conversations';

export default function Sidebar({ login, chosenOption, returnToLogin }) {
  const [activeKey, setActiveKey] = useState(CONVERSATIONS_KEY);
  const [modalOpen, setModalOpen] = useState(false);
  const { leaveRoom, roomUsers } = useRooms();

  const onLeftClick = () => {
    console.log(returnToLogin);
    returnToLogin();
    leaveRoom();
  };

  return (
    <div className="sidebar-wrapper d-flex flex-column">
      <Tab.Container activeKey={activeKey} onSelect={setActiveKey}>
        {chosenOption === CONVERSATION_OPTION ? (
          <>
            <Nav variant="tabs" className="justify-content-center">
              <Nav.Item>
                <Nav.Link eventKey={CONVERSATIONS_KEY}>Conversations</Nav.Link>
              </Nav.Item>
              <Nav.Item>
                <Nav.Link eventKey={CONTACTS_KEY}>Contacts</Nav.Link>
              </Nav.Item>
            </Nav>
            <Tab.Content className="border border-top-0 overflow-auto flex-grow-1">
              <Tab.Pane eventKey={CONVERSATIONS_KEY}>
                <Conversations />
              </Tab.Pane>
              <Tab.Pane eventKey={CONTACTS_KEY}>
                <Contacts />
              </Tab.Pane>
            </Tab.Content>
          </>
        ) : (
          <>
            <Nav variant="tabs" className="justify-content-center">
              <Nav.Item>
                <Nav.Link eventKey={ROOMS_KEY}>{chosenOption}</Nav.Link>
              </Nav.Item>
            </Nav>
            <Tab.Content className="border border-top-0 overflow-auto flex-grow-1">
              <ListGroup>
                {roomUsers.map(({ user }, id) => {
                  return <ListGroup.Item key={id}>{user}</ListGroup.Item>;
                })}
              </ListGroup>
            </Tab.Content>
          </>
        )}

        <div className="p-2 border border-top-0">Your login: {login}</div>
        {chosenOption === CONVERSATION_OPTION ? (
          <Button className="rounded-0" onClick={() => setModalOpen(true)}>
            {' '}
            New {activeKey.slice(0, -1)}
          </Button>
        ) : (
          <Button className="rounded-0 btn-danger" onClick={onLeftClick}>
            Leave room
          </Button>
        )}
      </Tab.Container>

      <Modal show={modalOpen} onHide={() => setModalOpen(false)}>
        {activeKey === CONVERSATIONS_KEY ? (
          <NewConversationModal closeModal={() => setModalOpen(false)} />
        ) : (
          <NewContactModal closeModal={() => setModalOpen(false)} />
        )}
      </Modal>
    </div>
  );
}
