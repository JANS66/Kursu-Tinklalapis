import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import logo from './logo.png';
import './Homepage.css';

function Header() {
  return (
    <header className="App-header">
      <div className="nav-container">
        <Link to="/login">
          <button className="nav-button">Prisijungti</button>
        </Link>
        <Link to="/registration">
          <button className="nav-button">Registruotis</button>
        </Link>
      </div>
      <img src={logo} className="App-logo" alt="logo" />
    </header>
  );
}

function Homepage() {
  const [courses, setCourses] = useState([]);

  useEffect(() => {
    fetch('http://localhost:8080/course/all')
      .then(response => response.json())
      .then(data => setCourses(data))
      .catch(error => console.error(error));
  }, []);

  return (
    <div className="Homepage">
      <Header />
      <h2>Welcome to our homepage!</h2>
      <div className="courses-container">
        <h3>List of Courses:</h3>
        <ul>
          {courses.map(course => (
            <li key={course.id}>
              <h4>{course.subject}</h4>
              <p>{course.description}</p>
              <p>Professor: {course.professorName}</p>
            </li>
          ))}
        </ul>
      </div>
    </div>
  );
}

export default Homepage;