import React from "react";
import { getUserSession } from "../utils/authStorage";

function Home() {
  const user = getUserSession();

  return (
    <section className="card">
      <h2>Home</h2>
      <p>Welcome, {user?.username}.</p>
      <p>You are logged in successfully and can now access your profile details.</p>
    </section>
  );
}

export default Home;
