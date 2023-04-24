/* eslint-disable react/prop-types */
import React, { useContext, useEffect, useState } from 'react';
import io from 'socket.io-client';
const SocketContext = React.createContext();

export function useSocket() {
  return useContext(SocketContext);
}

export function SocketProvider({ login, children }) {
  const [socket, setSocket] = useState();

  useEffect(() => {
    const newSocket = io('http://localhost:5000', { query: { login } });
    setSocket(newSocket);

    return () => newSocket.close();
  }, [login]);
  return <SocketContext.Provider value={socket}>{children}</SocketContext.Provider>;
}
