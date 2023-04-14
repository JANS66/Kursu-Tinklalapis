import React, { useState } from 'react';

const Admin = () => {
  const [users, setUsers] = useState([]);
  const [editableUser, setEditableUser] = useState(null);
  const [courses, setCourses] = useState([]);
  const [editableCourse, setEditableCourse] = useState(null);
  const [showCreateCourseForm, setShowCreateCourseForm] = useState(false);
  const [professors, setProfessors] = useState([]);
  const [editableProfessor, setEditableProfessor] = useState(null);
  const [showCreateProfessorForm, setShowCreateProfessorForm] = useState(false);
  const [registrations, setRegistrations] = useState([]);
  const [editableRegistration, setEditableRegistration] = useState(null);

  const handleGetUsers = async () => {
    try {
      const response = await fetch('/students');
      const data = await response.json();
      setUsers(data);
    } catch (error) {
      console.error(error);
    }
  };

  const handleDeleteUser = async (userId) => {
    try {
      await fetch(`/students/${userId}/delete`, {
        method: 'DELETE',
      });
      setUsers(users.filter((user) => user.id !== userId));
    } catch (error) {
      console.error(error);
    }
  };

  const handleEditUser = (user) => {
    setEditableUser(user);
  };

  const handleSaveUser = async (user) => {
    try {
      const modifiedUser = {
        firstname: user.firstname,
        lastname: user.lastname,
        email: user.email,
        password: user.password,
      };
      await fetch(`/students/${user.id}/update`, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(modifiedUser),
      });
      const response = await fetch('/students');
      const data = await response.json();
      setUsers(data);
      setEditableUser(null);
    } catch (error) {
      console.error(error);
    }
  };

  const handleGetCourses = async () => {
    try {
      const response = await fetch("/courses");
      const data = await response.json();
      setCourses(data);
      setShowCreateCourseForm(true);
    } catch (error) {
      console.error(error);
    }
  };

  const handleDeleteCourse = async (courseId) => {
    try {
      await fetch(`/courses/${courseId}/delete`, {
        method: 'DELETE',
      });
      setCourses(courses.filter((course) => course.id !== courseId));
    } catch (error) {
      console.error(error);
    }
  };

  const handleEditCourse = (course) => {
    setEditableCourse(course);
  };

  const handleSaveCourse = async (course) => {
    try {
      const modifiedCourse = {
        description: course.description,
        professorName: course.professorName,
        subject: course.subject
      };
      await fetch(`/courses/${course.id}/update`, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(modifiedCourse),
      });
      const response = await fetch('/courses');
      const data = await response.json();
      setCourses(data);
      setEditableCourse(null);
    } catch (error) {
      console.log(error);
    }
  };

  const handleCreateCourse = async (event) => {
    event.preventDefault();
    try {
      const response = await fetch("/courses", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          subject: event.target.elements.subject.value,
          description: event.target.elements.description.value,
          professorName: event.target.elements.professorName.value
        }),
      });
      const data = await response.json();
      setCourses([...courses, data]);
    } catch (error) {
      console.error(error);
    }
  };

  const handleGetProfessors = async () => {
    try {
      const response = await fetch("/professors");
      const data = await response.json();
      setProfessors(data);
      setShowCreateProfessorForm(true);
    } catch (error) {
      console.error(error);
    }
  };

  const handleEditProfessor = (professor) => {
    setEditableProfessor(professor);
  };

  const handleSaveProfessor = async (professor) => {
    try {
      const modifiedProfessor = {
        email: professor.email,
        fullName: professor.fullName
      };
      await fetch(`/professors/${professor.id}/update`, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(modifiedProfessor),
      });
      const response = await fetch('/professors');
      const data = await response.json();
      setProfessors(data);
      setEditableProfessor(null);
    } catch (error) {
      console.log(error);
    }
  };

  const handleDeleteProfessor = async (professorId) => {
    try {
      await fetch(`/professors/${professorId}/delete`, {
        method: 'DELETE',
      });
      setProfessors(professors.filter((professor) => professor.id !== professorId));
    } catch (error) {
      console.error(error);
    }
  };

  const handleCreateProfessor = async (event) => {
    event.preventDefault();
    try {
      const response = await fetch("/professors", {
        method: 'POST',
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          email: event.target.elements.email.value,
          fullName: event.target.elements.fullName.value
        }),
      });
      const data = await response.json();
      setProfessors([...professors, data]);
    } catch (error) {
      console.error(error);
    }
  };

  const handleGetRegistrations = async () => {
    try {
      const response = await fetch("/api/registrations");
      const data = await response.json();
      setRegistrations(data);
    } catch (error) {
      console.error(error);
    }
  };

  const handleEditRegistration = (registration) => {
    setEditableRegistration(registration);
  };

  const handleSaveRegistration = async (registration) => {
    try {
      const modifiedRegistration = {
        course: {
        id: registration.courseId,
      },
      user: {
        id: registration.userId,
      }
    };
      await fetch(`/api/registrations/${registration.id}/update`, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(modifiedRegistration),
      });
      const response = await fetch('/api/registrations');
      const data = await response.json();
      setRegistrations(data);
      setEditableRegistration(null);
    } catch (error) {
      console.log(error);
    }
  };

  const handleDeleteRegistration = async (registrationId) => {
    try {
      await fetch(`/registrations/${registrationId}/delete`, {
        method: 'DELETE',
      });
      setRegistrations(registrations.filter((registration) => registration.id !== registrationId));
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <div>
      <h1>Admin Panel</h1>
      <button onClick={handleGetUsers}>Get Students</button>
      <button onClick={handleGetCourses}>Get Courses</button>
      <button onClick={handleGetProfessors}>Get Professors</button>
      <button onClick={handleGetRegistrations}>Get Registrations</button>
      {registrations.length > 0 && (
        <table>
          <thead>
            <tr>
              <th>ID</th>
              <th>Course ID</th>
              <th>User ID</th>
            </tr>
          </thead>
          <tbody>
            {registrations.map((registration) => (
              <tr key={registration.id}>
                <td>{registration.id}</td>
                <td>
                  {editableRegistration?.id === registration.id ? (
                    <input
                      type="text"
                      value={editableRegistration.courseId}
                      onChange={(e) =>
                        setEditableRegistration({ ...editableRegistration, courseId: e.target.value })
                      }
                    />
                  ) : (
                    registration.course.id
                  )}
                </td>
                <td>
                  {editableRegistration?.id === registration.id ? (
                    <input
                      type="text"
                      value={editableRegistration.userId}
                      onChange={(e) =>
                        setEditableRegistration({ ...editableRegistration, userId: e.target.value })
                      }
                    />
                  ) : (
                    registration.user.id
                  )}
                </td>
                <td>
                  {editableRegistration?.id === registration.id ? (
                    <>
                      <button onClick={() => handleSaveRegistration(editableRegistration)}>Save</button>
                      <button onClick={() => setEditableRegistration(null)}>Cancel</button>
                    </>
                  ) : (
                    <>
                      <button onClick={() => handleEditRegistration(registration)}>Edit</button>
                      <button onClick={() => handleDeleteRegistration(registration.id)}>Delete</button>
                    </>
                  )}
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
      {professors.length > 0 && (
        <table>
          <thead>
            <tr>
              <th>ID</th>
              <th>Email</th>
              <th>Full Name</th>
            </tr>
          </thead>
          <tbody>
            {professors.map((professor) => (
              <tr key={professor.id}>
                <td>{professor.id}</td>
                <td>
                  {editableProfessor?.id === professor.id ? (
                    <input
                      type="text"
                      value={editableProfessor.email}
                      onChange={(e) =>
                        setEditableProfessor({ ...editableProfessor, email: e.target.value })
                      }
                    />
                  ) : (
                    professor.email
                  )}
                </td>
                <td>
                  {editableProfessor?.id === professor.id ? (
                    <input
                      type="text"
                      value={editableProfessor.fullName}
                      onChange={(e) =>
                        setEditableProfessor({ ...editableProfessor, fullName: e.target.value })
                      }
                    />
                  ) : (
                    professor.fullName
                  )}
                </td>
                <td>
                  {editableProfessor?.id === professor.id ? (
                    <>
                      <button onClick={() => handleSaveProfessor(editableProfessor)}>Save</button>
                      <button onClick={() => setEditableProfessor(null)}>Cancel</button>
                    </>
                  ) : (
                    <>
                      <button onClick={() => handleEditProfessor(professor)}>Edit</button>
                      <button onClick={() => handleDeleteProfessor(professor.id)}>Delete</button>
                    </>
                  )}
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      )}

      {showCreateProfessorForm && (
        <div>
          <h2>Create a New Professor</h2>
          <form onSubmit={handleCreateProfessor}>
            <label htmlFor="email">Email:</label>
            <input type="text" id="email" name="email" />
            <label htmlFor="fullName">Professor Name:</label>
            <input type="text" id="fullName" name="fullName" />
            <button type="submit">Create Professor</button>
          </form>
        </div>
      )}

      {courses.length > 0 && (
        <table>
          <thead>
            <tr>
              <th>ID</th>
              <th>Subject</th>
              <th>Description</th>
              <th>Professor Name</th>
              <th>Edit/Delete</th>
            </tr>
          </thead>
          <tbody>
            {courses.map((course) => (
              <tr key={course.id}>
                <td>{course.id}</td>
                <td>
                  {editableCourse?.id === course.id ? (
                    <input
                      type="text"
                      value={editableCourse.subject}
                      onChange={(e) =>
                        setEditableCourse({ ...editableCourse, subject: e.target.value })
                      }
                    />
                  ) : (
                    course.subject
                  )}
                </td>
                <td>
                  {editableCourse?.id === course.id ? (
                    <input
                      type="text"
                      value={editableCourse.description}
                      onChange={(e) =>
                        setEditableCourse({ ...editableCourse, description: e.target.value })
                      }
                    />
                  ) : (
                    course.description
                  )}
                </td>
                <td>
                  {editableCourse?.id === course.id ? (
                    <input
                      type="text"
                      value={editableCourse.professorName}
                      onChange={(e) =>
                        setEditableCourse({ ...editableCourse, professorName: e.target.value })
                      }
                    />
                  ) : (
                    course.professorName
                  )}
                </td>
                <td>
                  {editableCourse?.id === course.id ? (
                    <>
                      <button onClick={() => handleSaveCourse(editableCourse)}>Save</button>
                      <button onClick={() => setEditableCourse(null)}>Cancel</button>
                    </>
                  ) : (
                    <>
                      <button onClick={() => handleEditCourse(course)}>Edit</button>
                      <button onClick={() => handleDeleteCourse(course.id)}>Delete</button>
                    </>
                  )}
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
      {showCreateCourseForm && (
        <div>
          <h2>Create a New Course</h2>
          <form onSubmit={handleCreateCourse}>
            <label htmlFor="subject">Subject:</label>
            <input type="text" id="subject" name="subject" />
            <label htmlFor="description">Description:</label>
            <input type="text" id="description" name="description" />
            <label htmlFor="professorName">Professor Name:</label>
            <input type="text" id="professorName" name="professorName" />
            <button type="submit">Create Course</button>
          </form>
        </div>
      )}

      {users.length > 0 && (
        <table>
          <thead>
            <tr>
              <th>ID</th>
              <th>First Name</th>
              <th>Last Name</th>
              <th>Email</th>
              <th>Edit/Delete</th>
            </tr>
          </thead>
          <tbody>
            {users.map((user) => (
              <tr key={user.id}>
                <td>{user.id}</td>
                <td>
                  {editableUser?.id === user.id ? (
                    <input
                      type="text"
                      value={editableUser.firstname}
                      onChange={(e) =>
                        setEditableUser({ ...editableUser, firstname: e.target.value })
                      }
                    />
                  ) : (
                    user.firstname
                  )}
                </td>
                <td>
                  {editableUser?.id === user.id ? (
                    <input
                      type="text"
                      value={editableUser.lastname}
                      onChange={(e) =>
                        setEditableUser({ ...editableUser, lastname: e.target.value })
                      }
                    />
                  ) : (
                    user.lastname
                  )}
                </td>
                <td>
                  {editableUser?.id === user.id ? (
                    <input
                      type="text"
                      value={editableUser.email}
                      onChange={(e) =>
                        setEditableUser({ ...editableUser, email: e.target.value })
                      }
                    />
                  ) : (
                    user.email
                  )}
                </td>
                <td>
                  {editableUser?.id === user.id ? (
                    <>
                      <button onClick={() => handleSaveUser(editableUser)}>Save</button>
                      <button onClick={() => setEditableUser(null)}>Cancel</button>
                    </>
                  ) : (
                    <>
                      <button onClick={() => handleEditUser(user)}>Edit</button>
                      <button onClick={() => handleDeleteUser(user.id)}>Delete</button>
                    </>
                  )}
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
};

export default Admin;