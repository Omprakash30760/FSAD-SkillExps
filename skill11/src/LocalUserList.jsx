import { useEffect, useState } from "react";

function LocalUserList() {
  const [users, setUsers] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => {
    let isMounted = true;

    async function loadLocalUsers() {
      try {
        setLoading(true);
        setError("");

        const response = await fetch("/users.json");
        if (!response.ok) {
          throw new Error(`Request failed with status ${response.status}`);
        }

        const data = await response.json();
        if (isMounted) {
          setUsers(data);
        }
      } catch (err) {
        if (isMounted) {
          setError(err.message || "Failed to load local users.");
        }
      } finally {
        if (isMounted) {
          setLoading(false);
        }
      }
    }

    loadLocalUsers();

    return () => {
      isMounted = false;
    };
  }, []);

  return (
    <section>
      <h2>Local Users (public/users.json)</h2>
      {loading && <p className="status">Loading local users...</p>}
      {error && <p className="status error">Error: {error}</p>}

      {!loading && !error && (
        <div className="grid-list">
          {users.map((user) => (
            <article className="item-card" key={user.id}>
              <h3>{user.name}</h3>
              <p>
                <strong>Email:</strong> {user.email}
              </p>
              <p>
                <strong>Phone:</strong> {user.phone}
              </p>
            </article>
          ))}
        </div>
      )}
    </section>
  );
}

export default LocalUserList;
