import React, { useState } from 'react';
import { Login } from './Login';
import { useLocalStorage } from '../hooks/useLocalStorage';
import Dashboard from './Dashboard';
import { ContactsProvider } from '../contexts/ContactsProvider';
import { ConversationsProvider } from '../contexts/ConversationsProvider';

function App() {
  const [login, setLogin] = useLocalStorage();
  const [showDashboard, setShowDashboard] = useState(false);
  return (
    <>
      {showDashboard ? (
        <ContactsProvider>
          <ConversationsProvider login={login}>
            <Dashboard login={login} />
          </ConversationsProvider>
        </ContactsProvider>
      ) : (
        <Login login={login} onLoginSubmit={setLogin} setShowDashboard={setShowDashboard} />
      )}
    </>
  );
}

export default App;
