import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import logo from './logo.png';
import './Homepage.css';

function Header(props) {
    const handleProfileButtonClick = () => {
      window.location.href = '/profile';
    }

    return (
      <div className="fixed-header">
        <div className="button-wrapper">
          <button className="buttonsHomepage" onClick={handleProfileButtonClick}>
            Profilis
          </button>
          <button className="buttonsHomepage" onClick={props.onLogout}>
            Atsijungti
          </button>
        </div>
        <img src={logo} className="App-logo" alt="logo" />
      </div>
    );
  }

function LoggedInHomePage(props) {
    const [courses, setCourses] = useState([]);

    useEffect(() => {
        fetch('/courses')
          .then(response => response.json())
          .then(data => setCourses(data))
          .catch(error => console.error(error));
    }, []);

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
      <div className="button-row">
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

export default LoggedInHomePage;