import React, { useState } from 'react';
import { Login } from './Login';
import { useLocalStorage } from '../hooks/useLocalStorage';
import Dashboard from './Dashboard';
import { ContactsProvider } from '../contexts/ContactsProvider';
import { ConversationsProvider } from '../contexts/ConversationsProvider';
import { SocketProvider } from '../contexts/SocketProvider';

function App() {
  const [login, setLogin] = useLocalStorage();
  const [showDashboard, setShowDashboard] = useState(false);
  const [chosenOption, setChosenOption] = useState('');
  return (
    <>
      {showDashboard ? (
        <SocketProvider login={login}>
          <ContactsProvider>
            <ConversationsProvider login={login}>
              <Dashboard login={login} chosenOption={chosenOption} />
            </ConversationsProvider>
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
