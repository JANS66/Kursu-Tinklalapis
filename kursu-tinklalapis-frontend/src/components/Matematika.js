import React, { useEffect, useState } from 'react';
import './Matematika.css';

function Matematika() {
  const [courses, setCourses] = useState([]);

  useEffect(() => {
    fetch('/courses')
      .then(response => response.json())
      .then(data => setCourses(data.filter(course => course.subject === 'Matematika')))
      .catch(error => console.error(error));
  }, []);

  const [expandedCourseId, setExpandedCourseId] = useState(null);

  const toggleCourse = (id) => {
    if (id === expandedCourseId) {
      setExpandedCourseId(null);
    } else {
      setExpandedCourseId(id);
    }
  }

  return (
    <div className="Matematika">
      <h2 className='title'>Matematika</h2>
      {courses.map(course => (
        <div key={course.id} className="course-container">
          <div className="course-header" onClick={() => toggleCourse(course.id)}>
            <h3 className="description">{course.description}</h3>
            <button>Sign up</button>
          </div>
          {expandedCourseId === course.id &&
            <div className="course-description">{course.description}</div>
          }
        </div>
      ))}
    </div>
  );
}

export default Matematika;