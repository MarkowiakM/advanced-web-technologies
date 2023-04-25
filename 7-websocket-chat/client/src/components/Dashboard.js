/* eslint-disable react/prop-types */
import React from 'react';
import Sidebar from './Sidebar';
import './Dashboard.scss';
import OpenConversation from './OpenConversation';
import OpenRoom from './OpenRoom';

const CONVERSATION_OPTION = 'my conversations';

export default function Dashboard({ login, chosenOption, returnToLogin }) {
  return (
    <div className="d-flex dashboard-wrapper">
      <Sidebar login={login} chosenOption={chosenOption} returnToLogin={returnToLogin} />
      {chosenOption === CONVERSATION_OPTION ? <OpenConversation /> : <OpenRoom />}
    </div>
  );
}
