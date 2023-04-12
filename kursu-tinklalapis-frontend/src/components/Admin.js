import React, { useState } from 'react';

const Admin = () => {
  const [users, setUsers] = useState([]);

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

  return (
    <div>
      <h1>Admin Panel</h1>
      <button onClick={handleGetUsers}>Get Students</button>
      {users.length > 0 && (
        <table>
          <thead>
            <tr>
              <th>ID</th>
              <th>First Name</th>
              <th>Last Name</th>
              <th>Email</th>
              <th>Delete</th>
            </tr>
          </thead>
          <tbody>
            {users.map((user) => (
              <tr key={user.id}>
                <td>{user.id}</td>
                <td>{user.firstname}</td>
                <td>{user.lastname}</td>
                <td>{user.email}</td>
                <td>
                  <button onClick={() => handleDeleteUser(user.id)}>Delete</button>
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