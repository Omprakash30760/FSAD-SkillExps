import React, { useEffect, useState } from "react";
import { fetchProfile } from "../services/authService";
import { getUserSession } from "../utils/authStorage";

function Profile() {
  const [profile, setProfile] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => {
    const loadProfile = async () => {
      setLoading(true);
      setError("");

      const user = getUserSession();
      if (!user) {
        setError("No active user session found.");
        setLoading(false);
        return;
      }

      try {
        const response = await fetchProfile({
          userId: user.userId,
          username: user.username,
        });
        setProfile(response.data);
      } catch (err) {
        setError(err?.response?.data || "Failed to fetch profile.");
      } finally {
        setLoading(false);
      }
    };

    loadProfile();
  }, []);

  if (loading) {
    return <section className="card">Loading profile...</section>;
  }

  if (error) {
    return (
      <section className="card">
        <p className="msg error">{error}</p>
      </section>
    );
  }

  return (
    <section className="card">
      <h2>Profile</h2>
      <div className="profile-grid">
        <p>
          <strong>User ID:</strong> {profile?.id}
        </p>
        <p>
          <strong>Full Name:</strong> {profile?.fullName}
        </p>
        <p>
          <strong>Username:</strong> {profile?.username}
        </p>
        <p>
          <strong>Email:</strong> {profile?.email}
        </p>
      </div>
    </section>
  );
}

export default Profile;
