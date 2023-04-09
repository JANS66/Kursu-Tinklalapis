import React, { useState } from 'react';
import './RegistrationLoginForm.css';
import { useNavigate } from 'react-router-dom';
import logo from './logo.png';

function Login(props) {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [usernameError, setUsernameError] = useState('');
  const [passwordError, setPasswordError] = useState('');
  const navigate = useNavigate();

  const handleLogin = async (e) => {
    e.preventDefault();

    let hasError = false;

    if (!username.trim()) {
      setUsernameError('Vartotojo vardas negali būti tuščias');
      hasError = true;
    } else {
      setUsernameError('');
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

    if (!hasError) {
      try {
        const response = await fetch('/user/authenticate', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify({ username, password }),
        });

        if (response.ok) {
          props.onLogin({ username });
          navigate('/');
        } else {
          setUsernameError('Vartotojas nerastas');
        }
      } catch (error) {
        console.error(error);
        alert('Įvyko klaida');
      }
    }
  };

  const inputErrorStyle = {
    borderBottom: '1px solid red',
    outlineColor: 'red',
  };

  return (
    <div className="RegistrationForm">
      <img src={logo} className="App-logo" alt="logo" />
      <form className="containerLogin" onSubmit={handleLogin}>
      <h2 className='registracija-title'>Prisijunkite</h2>
      {usernameError ==='Vartotojas nerastas'}
        <div>
          <label htmlFor="username"></label>
          <input 
            type="text" 
            id="username" 
            value={username} 
            onChange={(e) => setUsername(e.target.value)}
            onFocus={(event) => event.target.placeholder = ''}
            onBlur={(event) => event.target.placeholder = 'Vartotojo vardas'}
            style={usernameError ? inputErrorStyle : null}
            placeholder="Vartotojo vardas"
          />
          {usernameError && <span className="error">{usernameError}</span>}
        </div>
        <div>
          <label htmlFor="password"></label>
          <input 
            type="password" 
            id="password" 
            value={password} 
            onChange={(e) => setPassword(e.target.value)}
            onFocus={(event) => event.target.placeholder = ''}
            onBlur={(event) => event.target.placeholder = 'Slaptažodis'}
            style={passwordError ? inputErrorStyle : null}
            placeholder="Slaptažodis"
          />
          {passwordError && <span className="error">{passwordError}</span>}
        </div>
        <button type="submit">Prisijungti</button>
      </form>
    </div>
  );
  }
export default Login;