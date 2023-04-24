/* eslint-disable react/prop-types */
import React from 'react';
import Sidebar from './Sidebar';
import './Dashboard.scss';
import OpenConversation from './OpenConversation';

export default function Dashboard({ login, chosenOption }) {
  return (
    <div className="d-flex dashboard-wrapper">
      <Sidebar login={login} chosenOption={chosenOption} />
      <OpenConversation />
      {chosenOption}
    </div>
  );
}
