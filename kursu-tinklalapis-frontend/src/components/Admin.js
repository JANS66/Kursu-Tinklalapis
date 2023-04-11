import React, { useState } from 'react';
import UserList from './UserList';

function Admin() {
  const [showUserList, setShowUserList] = useState(false);
  const [selectedUser, setSelectedUser] = useState(null);

  const handleShowUserList = () => {
    setShowUserList(true);
  };

  const handleEditUser = (user) => {
    setSelectedUser(user);
  };

  const handleSaveUser = (updatedUser) => {
    console.log(updatedUser);
    setSelectedUser(null);
  };

  const handleCancelEdit = () => {
    setSelectedUser(null);
  };

  return (
    <div>
      <h1>Admin Panel</h1>
      <button onClick={handleShowUserList}>List Users</button>
      {showUserList && (
        <UserList
          onEditUser={handleEditUser}
          onSaveUser={handleSaveUser}
          onCancelEdit={handleCancelEdit}
          selectedUser={selectedUser}
        />
      )}
    </div>
  );
}

export default Admin;