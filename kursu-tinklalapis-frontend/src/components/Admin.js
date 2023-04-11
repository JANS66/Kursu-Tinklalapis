import React, { useState } from 'react';
import UserList from './UserList';

function Admin() {
  const [showUserList, setShowUserList] = useState(false);

  const handleShowUserList = () => {
    setShowUserList(true);
  };

  return (
    <div>
      <h1>Admin Panel</h1>
      <button onClick={handleShowUserList}>List Users</button>
      {showUserList && <UserList />}
    </div>
  );
}

export default Admin;