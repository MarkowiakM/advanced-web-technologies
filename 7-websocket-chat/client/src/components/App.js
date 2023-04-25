import React, { useState } from 'react';
import { Login } from './Login';
import { useLocalStorage } from '../hooks/useLocalStorage';
import Dashboard from './Dashboard';
import { ContactsProvider } from '../contexts/ContactsProvider';
import { ConversationsProvider } from '../contexts/ConversationsProvider';
import { SocketProvider } from '../contexts/SocketProvider';
import { RoomProvider } from '../contexts/RoomProvider';

const CONVERSATION_OPTION = 'my conversations';

function App() {
  const [login, setLogin] = useLocalStorage();
  const [showDashboard, setShowDashboard] = useState(false);
  const [chosenOption, setChosenOption] = useState('');
  return (
    <>
      {showDashboard ? (
        <SocketProvider login={login} option={chosenOption}>
          <ContactsProvider>
            <RoomProvider login={login}>
              {chosenOption === CONVERSATION_OPTION ? (
                <ConversationsProvider login={login}>
                  <Dashboard login={login} chosenOption={chosenOption} />
                </ConversationsProvider>
              ) : (
                <Dashboard
                  login={login}
                  chosenOption={chosenOption}
                  returnToLogin={() => setShowDashboard(false)}
                />
              )}
            </RoomProvider>
          </ContactsProvider>
        </SocketProvider>
      ) : (
        <Login
          login={login}
          onLoginSubmit={setLogin}
          setShowDashboard={setShowDashboard}
          setChosenOption={setChosenOption}
        />
      )}
    </>
  );
}

export default App;
