import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { registerUser } from "../services/authService";

const initialState = {
  fullName: "",
  username: "",
  email: "",
  password: "",
};

function Register() {
  const [formData, setFormData] = useState(initialState);
  const [error, setError] = useState("");
  const [success, setSuccess] = useState("");
  const navigate = useNavigate();

  const handleChange = (event) => {
    const { name, value } = event.target;
    setFormData((prev) => ({ ...prev, [name]: value }));
  };

  const handleSubmit = async (event) => {
    event.preventDefault();
    setError("");
    setSuccess("");

    if (!formData.fullName || !formData.username || !formData.email || !formData.password) {
      setError("All fields are required.");
      return;
    }

    try {
      await registerUser(formData);
      setSuccess("Registration successful. Redirecting to login...");
      setFormData(initialState);
      setTimeout(() => navigate("/login"), 900);
    } catch (err) {
      setError(err?.response?.data || "Registration failed.");
    }
  };

  return (
    <section className="card auth-card">
      <h2>Create Account</h2>
      <form onSubmit={handleSubmit} className="form-grid">
        <input
          type="text"
          name="fullName"
          placeholder="Full Name"
          value={formData.fullName}
          onChange={handleChange}
        />
        <input
          type="text"
          name="username"
          placeholder="Username"
          value={formData.username}
          onChange={handleChange}
        />
        <input
          type="email"
          name="email"
          placeholder="Email"
          value={formData.email}
          onChange={handleChange}
        />
        <input
          type="password"
          name="password"
          placeholder="Password"
          value={formData.password}
          onChange={handleChange}
        />
        <button type="submit">Register</button>
      </form>
      {error && <p className="msg error">{error}</p>}
      {success && <p className="msg success">{success}</p>}
    </section>
  );
}

export default Register;
