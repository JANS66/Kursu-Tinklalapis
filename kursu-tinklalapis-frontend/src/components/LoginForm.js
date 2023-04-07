import React, { useState } from 'react';
import './RegistrationLoginForm.css';
import { useNavigate } from 'react-router-dom';

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
        const response = await fetch('/authenticate', {
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
          alert('Neteisingas vartotojo vardas ar slaptažodis');
        }
      } catch (error) {
        console.error(error);
        alert('Įvyko klaida');
      }
    }
  };

  const inputErrorStyle = {
    border: '1px solid red',
    outlineColor: 'red',
  };

  return (
    <div className="RegistrationForm">
      <form onSubmit={handleLogin}>
        <div>
          <label htmlFor="username">Vartotojo Vardas:</label>
          <input 
            type="text" 
            id="username" 
            value={username} 
            onChange={(e) => setUsername(e.target.value)}
            style={usernameError ? inputErrorStyle : null}
          />
          {usernameError && <span className="error">{usernameError}</span>}
        </div>
        <div>
          <label htmlFor="password">Slaptažodis:</label>
          <input 
            type="password" 
            id="password" 
            value={password} 
            onChange={(e) => setPassword(e.target.value)}
            style={passwordError ? inputErrorStyle : null}
          />
          {passwordError && <span className="error">{passwordError}</span>}
        </div>
        <button type="submit">Prisijungti</button>
      </form>
    </div>
  );
  }
export default Login;