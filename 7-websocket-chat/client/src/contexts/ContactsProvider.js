/* eslint-disable react/prop-types */
import React, { useContext } from 'react';
import { useLocalStorage } from '../hooks/useLocalStorage';

const ContactsContext = React.createContext();

export function useContacts() {
  return useContext(ContactsContext);
}

export function ContactsProvider({ children }) {
  const [contacts, setContacts] = useLocalStorage('contacts', []);

  function createContact(login) {
    setContacts((prevContacts) => [...prevContacts, { login }]);
  }

  return (
    <ContactsContext.Provider value={{ contacts, createContact }}>
      {children}
    </ContactsContext.Provider>
  );
}
