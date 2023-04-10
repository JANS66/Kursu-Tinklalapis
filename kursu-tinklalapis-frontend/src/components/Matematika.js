import React, { useEffect, useState } from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faPlus, faTimes } from '@fortawesome/free-solid-svg-icons';
import './Matematika.css';

function Matematika({ isLoggedIn }) {
  const [courses, setCourses] = useState([]);
  const [expandedCourseId, setExpandedCourseId] = useState(null);
  const [buttonText, setButtonText] = useState(isLoggedIn ? 'Dalyvauti' : 'Prisijunkite, kad galetumete dalyvauti');
  const [buttonDisabled, setButtonDisabled] = useState(!isLoggedIn);

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

  const handleButtonClick = () => {
    setButtonText('Dalyvaujate');
    setButtonDisabled(true);
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
              <button className="buttonsHomepage" onClick={handleButtonClick} disabled={buttonDisabled}>
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