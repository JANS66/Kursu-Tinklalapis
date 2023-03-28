import React, { useState } from 'react';
import axios from 'axios';
import './RegistrationForm.css';

function RegistrationForm() {
  const [firstName, setFirstName] = useState('');
  const [lastName, setLastName] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');

  const handleSubmit = (event) => {
    event.preventDefault();
    const data = { firstName, lastName, email, password, confirmPassword };
    axios.post('/api/register', data)
      .then((response) => {
        console.log(response);
      })
      .catch((error) => {
        console.log(error);
      });
  };

  return (
    <div className="RegistrationForm">
      <form onSubmit={handleSubmit}>
        <div>
          <label htmlFor="firstName">Vardas:</label>
          <input type="text" id="firstName" value={firstName} onChange={(event) => setFirstName(event.target.value)} required />
        </div>
        <div>
          <label htmlFor="lastName">Pavardė:</label>
          <input type="text" id="lastName" value={lastName} onChange={(event) => setLastName(event.target.value)} required />
        </div>
        <div>
          <label htmlFor="email">El. Paštas:</label>
          <input type="email" id="email" value={email} onChange={(event) => setEmail(event.target.value)} required />
        </div>
        <div>
          <label htmlFor="password">Slaptažodis:</label>
          <input type="password" id="password" value={password} onChange={(event) => setPassword(event.target.value)} minLength={8} required />
        </div>
        <div>
          <label htmlFor="confirmPassword">Pakartokite Slaptažodi:</label>
          <input type="password" id="confirmPassword" value={confirmPassword} onChange={(event) => setConfirmPassword(event.target.value)} minLength={8} required />
        </div>
        <div>
          <button type="submit">Registruotis</button>
        </div>
      </form>
    </div>
  );
}

export default RegistrationForm;