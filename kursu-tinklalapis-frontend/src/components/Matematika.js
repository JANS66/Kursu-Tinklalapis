import React, { useEffect, useState } from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faPlus, faTimes } from '@fortawesome/free-solid-svg-icons';
import './Matematika.css';
import axios from 'axios';
import Cookies from 'js-cookie';

function Matematika({ isLoggedIn, user }) {
  const [courses, setCourses] = useState([]);
  const [expandedCourseId, setExpandedCourseId] = useState(null);
  const [buttonText, setButtonText] = useState('Dalyvauti');
  const [buttonDisabled, setButtonDisabled] = useState(false);
  

  useEffect(() => {
    fetch('/courses')
      .then(response => response.json())
      .then(data => setCourses(data.filter(course => course.subject === 'Matematika')))
      .catch(error => console.error(error));
  }, []);

  const toggleCourse = (id) => {
    if (id === expandedCourseId) {
      setExpandedCourseId(null);
    } else {
      setExpandedCourseId(id);
    }
  }

  const handleButtonClick = (courseId) => {
    if (!isLoggedIn) {
      alert('Please log in or register to participate.');
      return;
    }

    const userId = Cookies.get('userId'); 
    const registration = { courseId: courseId, userId: userId }; // Include userId in registration object
    setButtonDisabled(true);

    axios.post('/api/registrations', registration, {
      headers: {
        'Authorization': 'Bearer ${token}' 
      }
    })
    .then(response => {
      console.log(response.data);
      setButtonText('Dalyvaujate');
    })
    .catch(error => console.error(error));
  }
  
  return (
    <div className="Matematika">
      <h2 className='title'>Matematika</h2>
      {courses.map(course => (
        <div key={course.id} className="course-container">
          <div className="course-header" onClick={() => toggleCourse(course.id)}>
            <h3 className="description">{course.description}</h3>
            <FontAwesomeIcon className='icon' icon={expandedCourseId === course.id ? faTimes : faPlus} />
          </div>
          {expandedCourseId === course.id &&
            <div className="course-description">
              Kurso profesorius - {course.professorName}
              <div className="button-container">
              <button className="buttonsHomepage" onClick={() => handleButtonClick(course.id)} disabled={buttonDisabled}>
                {buttonText}
              </button>
              </div>
            </div>
          }
        </div>
      ))}
    </div>
  );
}

export default Matematika;