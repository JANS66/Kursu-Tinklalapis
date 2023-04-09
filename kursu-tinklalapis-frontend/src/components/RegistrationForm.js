import React, { useState } from 'react';
import axios from 'axios';
import './RegistrationLoginForm.css';
import logo from './logo.png';

function RegistrationForm() {
  const [username, setUsername] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');
  const [passwordsMatch, setPasswordsMatch] = useState(true);
  const [usernameError, setUsernameError] = useState('');
  const [emailError, setEmailError] = useState('');
  const [passwordError, setPasswordError] = useState('');
  const [confirmPasswordError, setConfirmPasswordError] = useState('');
  const [registrationSuccess, setRegistrationSuccess] = useState(false);

  const handleSubmit = (event) => {
    event.preventDefault();
  
    let hasError = false;

    if (!username.trim()) {
      setUsernameError('Vartotojo vardas negali būti tuščias');
      hasError = true;
    } else {
      setUsernameError('');
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
      const data = { username, email, password };
      axios.post('/user/register', data)
        .then((response) => {
          console.log(response);
          setRegistrationSuccess(true);
        })
        .catch((error) => {
          console.log(error);
        });
    }
  };

  const inputErrorStyle = {
    borderBottom: '1px solid red',
    outlineColor: 'red',
  };

  const confirmPassStyle = passwordsMatch ? {} : { border: '1px solid red'};

  if (registrationSuccess) {
    return (
      <div className="RegistrationForm">
        <p>Registracija sėkminga. Dabar galite prisijungti spustelėdami mygtuką "Prisijungti".</p>
        <button className="registrastionSuccess" onClick={() => { window.location.href = '/login'; }}>Prisijungti</button>
      </div>
    );
  } else {
    return (
      <div className="RegistrationForm">
        <img src={logo} className="App-logo" alt="logo" />
        <form className="container" onSubmit={handleSubmit}>
        <h2 className='registracija-title'>Užsiregistruokite</h2>
        <div>
          <input
            type="text"
            id="username"
            value={username}
            onChange={(event) => setUsername(event.target.value)}
            onFocus={(event) => event.target.placeholder = ''}
            onBlur={(event) => event.target.placeholder = 'Vartotojo vardas'}
            style={usernameError ? inputErrorStyle : null}
            placeholder="Vartotojo vardas"
          />
          {usernameError && <span className="error">{usernameError}</span>}
        </div>
        <div>
          <label htmlFor="email"></label>
          <input
            type="email"
            id="email"
            value={email}
            onChange={(event) => setEmail(event.target.value)}
            onFocus={(event) => event.target.placeholder = ''}
            onBlur={(event) => event.target.placeholder = 'El. Paštas'}
            style={emailError ? inputErrorStyle : null}
            placeholder="El. Paštas"
          />
          {emailError && <span className="error">{emailError}</span>}
        </div>
        <div>
          <label htmlFor="password"></label>
          <input
            type="password"
            id="password"
            value={password}
            onChange={(event) => setPassword(event.target.value)}
            onFocus={(event) => event.target.placeholder = ''}
            onBlur={(event) => event.target.placeholder = 'Slaptažodis'}
            style={passwordError ? inputErrorStyle : null}
            placeholder="Slaptažodis"
          />
          {passwordError && <span className="error">{passwordError}</span>}
        </div>
        <div>
          <label htmlFor="confirmPassword"></label>
          <input
            type="password"
            id="confirmPassword"
            value={confirmPassword}
            onChange={(event) => setConfirmPassword(event.target.value)}
            onFocus={(event) => event.target.placeholder = ''}
            onBlur={(event) => event.target.placeholder = 'Pakartokite slaptažodį'}
            style={confirmPasswordError ? inputErrorStyle : confirmPassStyle}
            placeholder="Pakartokite slaptažodį"
          />
          {!passwordsMatch && (
            <span className="error">{confirmPasswordError}</span>
          )}
        </div>
        <button type="submit">Registruotis</button>
      </form>
    </div>
  );
}
}

export default RegistrationForm;