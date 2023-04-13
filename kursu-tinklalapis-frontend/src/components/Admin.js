import React, { useState } from 'react';

const Admin = () => {
  const [users, setUsers] = useState([]);
  const [editableUser, setEditableUser] = useState(null);

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