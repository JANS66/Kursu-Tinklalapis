import React from 'react';
import { Link } from 'react-router-dom';
import logo from './logo.png';
import './Homepage.css';

function Header() {
  return (
    <header className="App-header">
      <div className="nav-container">
        <button className="nav-button">Prisijungti</button>
        <Link to="/registration">
          <button className="nav-button">Registruotis</button>
        </Link>
      </div>
      <img src={logo} className="App-logo" alt="logo" />
      <h1 className="App-title">Kursu tinklalapis</h1>
    </header>
  );
}

function Homepage() {
  return (
    <div className="Homepage">
      <Header />
      <h2>Welcome to our homepage!</h2>
    </div>
  );
}

export default Homepage;