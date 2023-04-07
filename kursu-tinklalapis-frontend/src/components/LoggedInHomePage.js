import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import logo from './logo.png';
import './Homepage.css';

function Header(props) {
    const handleProfileButtonClick = () => {
      window.location.href = '/profile';
    }

    return (
      <div className="App-header">
        <div className="nav-container">
          <button className="nav-button" onClick={handleProfileButtonClick}>
            Profilis
          </button>
          <button className="nav-button" onClick={props.onLogout}>
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
        fetch('http://localhost:8080/course/all')
          .then(response => response.json())
          .then(data => setCourses(data))
          .catch(error => console.error(error));
    }, []);

    return (
        <div className="Homepage">
          <Header onLogout={props.onLogout} />
          <h2>Welcome back, user!</h2>
          <div className="courses-container">
            <h3>Galimu kursu sarasas:</h3>
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

export default LoggedInHomePage;