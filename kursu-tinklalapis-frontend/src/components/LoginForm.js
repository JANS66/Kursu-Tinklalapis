import React, { useState } from 'react';
import './LoginForm.css';

function Login() {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [emailError, setEmailError] = useState('');
    const [passwordError, setPasswordError] = useState('');

    const handleLogin = async (e) => {
        e.preventDefault();

        let hasError = false;

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

        if (!hasError) {
          try {
            const response = await fetch('/api/login', {
              method: 'POST',
              headers: {
                'Content-Type': 'application/json'
              },
              body: JSON.stringify({ email, password })
            });

            if (response.ok) {
              window.location.href = '/';
            } else {
              alert('Klaidingas el pastas ir slaptazodis');
            }
          } catch (error) {
            console.error(error);
            alert('Ivyko klaida');
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
              <label htmlFor="email">El. Paštas:</label>
              <input 
                type="text" 
                id="email" 
                value={email} 
                onChange={(e) => setEmail(e.target.value)}
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