/* eslint-disable react/prop-types */
import React from 'react';
import Sidebar from './Sidebar';
import './Dashboard.scss';
import OpenConversation from './OpenConversation';
// import { useConversations } from '../contexts/ConversationsProvider';

export default function Dashboard({ login }) {
  // const { selectedConversation } = useConversations();
  return (
    <div className="d-flex dashboard-wrapper">
      <Sidebar login={login} />
      <OpenConversation />
    </div>
  );
}
