import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { loginUser } from "../services/authService";
import { saveUserSession } from "../utils/authStorage";

function Login() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [rememberMe, setRememberMe] = useState(true);
  const [error, setError] = useState("");
  const navigate = useNavigate();

  const handleSubmit = async (event) => {
    event.preventDefault();
    setError("");

    if (!username || !password) {
      setError("Username and password are required.");
      return;
    }

    try {
      const response = await loginUser({ username, password });
      const { userId, username: loggedInUser } = response.data;
      saveUserSession({ userId, username: loggedInUser }, rememberMe);
      navigate("/home");
    } catch (err) {
      setError(err?.response?.data || "Login failed.");
    }
  };

  return (
    <section className="card auth-card">
      <h2>Login</h2>
      <form onSubmit={handleSubmit} className="form-grid">
        <input
          type="text"
          placeholder="Username"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
        />
        <input
          type="password"
          placeholder="Password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />
        <label className="checkbox-row">
          <input
            type="checkbox"
            checked={rememberMe}
            onChange={(e) => setRememberMe(e.target.checked)}
          />
          Remember me (localStorage)
        </label>
        <button type="submit">Login</button>
      </form>
      {error && <p className="msg error">{error}</p>}
    </section>
  );
}

export default Login;
