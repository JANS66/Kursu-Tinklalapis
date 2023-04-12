/* eslint-disable */
import React from 'react';
import { Link } from 'react-router-dom'
import logo from './logo.png'
import './Homepage.css'

function Header(props) {
    const handleHomeButtonClick = () => {
        window.location.href = '/';
    }


    return (
        <div className="App-header fixed-header">
            <img src={logo} className="App-logo" alt="logo" />
            <div className="nav-container">
                <button className="nav-button" onClick={handleHomeButtonClick}>
                    Pradzia
                </button>
                <button className="nav-button" onClick={props.onLogout}>
                    Atsijungti
                </button>
            </div>
        </div>
    );
}

function ProfilePage(props) {
    return (
        <div className="Homepage">
            <Header onLogout={props.onLogout} />
            <h2>Profilis</h2>
            <div className="profile-container">
                <h3>Vartotojo informacija:</h3>
                {props.user && (
                    <>
                        <p>El. pastas: {props.user.email}</p>
                        <p>Vardas: {props.user.firstName}</p>
                        <p>Pavardė: {props.user.lastName}</p>
                    </>
                )}
            </div>
        </div>
    );
}

export default ProfilePage;