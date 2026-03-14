import { useEffect, useState } from "react";

function UserList() {
  const [users, setUsers] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => {
    let isMounted = true;

    async function loadUsers() {
      try {
        setLoading(true);
        setError("");

        const response = await fetch(
          "https://jsonplaceholder.typicode.com/users"
        );

        if (!response.ok) {
          throw new Error(`Request failed with status ${response.status}`);
        }

        const data = await response.json();
        if (isMounted) {
          setUsers(data);
        }
      } catch (err) {
        if (isMounted) {
          setError(err.message || "Failed to load users API data.");
        }
      } finally {
        if (isMounted) {
          setLoading(false);
        }
      }
    }

    loadUsers();

    return () => {
      isMounted = false;
    };
  }, []);

  return (
    <section>
      <h2>Users API (JSONPlaceholder)</h2>
      {loading && <p className="status">Loading users...</p>}
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

export default UserList;
