import React from "react";
import { Link, useNavigate } from "react-router-dom";
import { clearUserSession, isLoggedIn } from "../utils/authStorage";

function NavBar() {
  const navigate = useNavigate();
  const loggedIn = isLoggedIn();

  const handleLogout = () => {
    clearUserSession();
    navigate("/login");
  };

  return (
    <header className="top-nav">
      <h1>Skill 14 Auth Demo</h1>
      <nav>
        {loggedIn ? (
          <>
            <Link to="/home">Home</Link>
            <Link to="/profile">Profile</Link>
            <button type="button" onClick={handleLogout} className="link-btn">
              Logout
            </button>
          </>
        ) : (
          <>
            <Link to="/login">Login</Link>
            <Link to="/register">Register</Link>
          </>
        )}
      </nav>
    </header>
  );
}

export default NavBar;
