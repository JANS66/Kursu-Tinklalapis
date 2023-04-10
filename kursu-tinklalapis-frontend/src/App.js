import React, { useState } from 'react';
import './App.css';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import HomePage from './components/HomePage';
import RegistrationForm from './components/RegistrationForm';
import LoginForm from './components/LoginForm';
import LoggedInHomePage from './components/LoggedInHomePage.js';
import ProfilePage from './components/ProfilePage';
import Matematika from './components/Matematika';

function App() {
  const [loggedInUser, setLoggedInUser] = useState(
    JSON.parse(sessionStorage.getItem('loggedInUser')) || {}
  );
  const [isLoggedIn, setIsLoggedIn] = useState(
    sessionStorage.getItem('isLoggedIn') === 'true'
  );

  function handleLogin(user) {
    setIsLoggedIn(true);
    setLoggedInUser(user);
    sessionStorage.setItem('isLoggedIn', 'true');
    sessionStorage.setItem('loggedInUser', JSON.stringify(user));
  }

  function handleLogout() {
    console.log('Logging out...');
    setIsLoggedIn(false);
    setLoggedInUser({});
    sessionStorage.removeItem('isLoggedIn');
    sessionStorage.removeItem('loggedInUser');
  }

  return (
    <div className="App">
      <Router>
        <Routes>
          {isLoggedIn ? (
            <Route
              path="/"
              element={<LoggedInHomePage onLogout={handleLogout} username={loggedInUser.username} />}
            />
          ) : (
            <Route path="/" element={<HomePage onLogin={handleLogin} />} />
          )}
          <Route path="/registration" element={<RegistrationForm />} />
          <Route path="/login" element={<LoginForm onLogin={handleLogin} />} />
          <Route 
            path="/profile"
            element={
              isLoggedIn ? (
                <ProfilePage username={loggedInUser.username} />
              ) : (
                <Navigate to="/" />
              )
            }
          />
          <Route path="/matematika" element={<Matematika />} />
        </Routes>
      </Router>
    </div>
  );
}

export default App;
