import React, { useState } from 'react';
import axios from 'axios';
import './RegistrationForm.css';

function RegistrationForm() {
  const [firstName, setFirstName] = useState('');
  const [lastName, setLastName] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');
  const [passwordsMatch, setPasswordsMatch] = useState(true);
  const [firstNameError, setFirstNameError] = useState('');
  const [lastNameError, setLastNameError] = useState('');
  const [emailError, setEmailError] = useState('');
  const [passwordError, setPasswordError] = useState('');
  const [confirmPasswordError, setConfirmPasswordError] = useState('');

  const handleSubmit = (event) => {
    event.preventDefault();
  
    let hasError = false;
    if (!firstName.trim()) {
      setFirstNameError('Vardas negali būti tuščias');
      hasError = true;
    } else {
      setFirstNameError('');
    }

    if (!lastName.trim()) {
      setLastNameError('Pavardė negali būti tuščia');
      hasError = true;
    } else {
      setLastNameError('');
    }

    if (!email.trim()) {
      setEmailError('El. paštas negali būti tuščias');
      hasError = true;
    } else if (!/\S+@\S+\.\S+/.test(email)) {
      setEmailError('Netinkamas el. pašto formatas');
      hasError = true;
    } else {
      setEmailError('');
    }

    if (!password.trim()) {
      setPasswordError('Slaptažodis negali būti tuščias');
      hasError = true;
    } else if (password.length < 8) {
      setPasswordError('Slaptažodis turi būti bent 8 simbolių ilgio');
      hasError = true;
    } else {
      setPasswordError('');
    }
  
    if (password !== confirmPassword) {
      setPasswordsMatch(false);
      setConfirmPasswordError('Slaptažodžiai nesutampa')
      hasError = true;
    } else {
      setPasswordsMatch(true);
      setConfirmPasswordError('');
    }
  
    if (!hasError) {
      const data = { firstName, lastName, email, password };
      axios.post('/api/register', data)
        .then((response) => {
          console.log(response);
        })
        .catch((error) => {
          console.log(error);
        });
    }
  };

  const inputErrorStyle = {
    border: '1px solid red',
    outlineColor: 'red',
  };

  const confirmPassStyle = passwordsMatch ? {} : { border: '1px solid red'};

  return (
    <div className="RegistrationForm">
      <form onSubmit={handleSubmit}>
        <div>
          <label htmlFor="firstName">Vardas:</label>
          <input
            type="text"
            id="firstName"
            value={firstName}
            onChange={(event) => setFirstName(event.target.value)}
            style={firstNameError ? inputErrorStyle : null}
          />
          {firstNameError && <span className="error">{firstNameError}</span>}
        </div>
        <div>
          <label htmlFor="lastName">Pavardė:</label>
          <input
            type="text"
            id="lastName"
            value={lastName}
            onChange={(event) => setLastName(event.target.value)}
            style={lastNameError ? inputErrorStyle : null}
          />
          {lastNameError && <span className="error">{lastNameError}</span>}
        </div>
        <div>
          <label htmlFor="email">El. Paštas:</label>
          <input
            type="email"
            id="email"
            value={email}
            onChange={(event) => setEmail(event.target.value)}
            style={emailError ? inputErrorStyle : null}
          />
          {emailError && <span className="error">{emailError}</span>}
        </div>
        <div>
          <label htmlFor="password">Slaptažodis:</label>
          <input
            type="password"
            id="password"
            value={password}
            onChange={(event) => setPassword(event.target.value)}
            style={passwordError ? inputErrorStyle : null}
          />
          {passwordError && <span className="error">{passwordError}</span>}
        </div>
        <div>
          <label htmlFor="confirmPassword">Pakartokite Slaptažodį:</label>
          <input
            type="password"
            id="confirmPassword"
            value={confirmPassword}
            onChange={(event) => setConfirmPassword(event.target.value)}
            style={confirmPasswordError ? inputErrorStyle : null}
          />
          {!passwordsMatch && (
            <span className="error">Slaptažodžiai nesutampa</span>
          )}
        </div>
        <button type="submit">Registruotis</button>
      </form>
    </div>
  );
}

export default RegistrationForm;