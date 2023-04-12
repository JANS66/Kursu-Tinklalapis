/* eslint-disable */
import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import logo from './logo.png';
import './Homepage.css';

function Header() {
  return (
    <header>
      <div className="button-wrapper">
        <Link to="/login">
          <button className="buttonsHomepage">Prisijungti</button>
        </Link>
        <Link to="/registration">
          <button className="buttonsHomepage">Registruotis</button>
        </Link>
      </div>
      <img src={logo} className="App-logo" alt="logo" />
    </header>
  );
}

function Homepage() {

  const handleButtonClick = (course) => {
    window.location.href = `/${course}`;
  };

  return (
    <div className="Homepage">
      <Header />
      <div className="Homepage-title">
        <h2>Atrakinkite neribotą prieigą prie šimtų kursų.</h2>
        <h2 className='title-2'>Mokykitės bet kur.</h2>
        <h2 className='title-3'>Pasiruošę pradėti mokytis? Registruokites.</h2>
      </div>
      <div class="button-row">
        <button className="math-button" onClick={() => handleButtonClick('matematika')}>
        Matematika.</button>
        <button className="english-button" onClick={() => handleButtonClick('anglu')}>
        Anglu k.</button>
      </div>
      <div class="button-row">
        <button className="history-button" onClick={() => handleButtonClick('istorija')}>
        Istorija.</button>
        <button className="biology-button" onClick={() => handleButtonClick('biologija')}>
        Biologija.</button>
      </div>
      <div class="button-row">
        <button className="chemistry-button" onClick={() => handleButtonClick('chemija')}>
        Chemija.</button>
        <button className="physics-button" onClick={() => handleButtonClick('fizika')}>
        Fizika.</button>
      </div>
      <div class="button-row">
        <button className="art-button" onClick={() => handleButtonClick('menai')}>
        Menai.</button>
        <button className="geography-button" onClick={() => handleButtonClick('geografija')}>
        Geografija.</button>
      </div>
      <footer className="footer">
        &copy; 2023 
      </footer>
    </div>
  );
}

export default Homepage;