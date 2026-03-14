import { useEffect, useMemo, useState } from "react";
import axios from "axios/dist/browser/axios.cjs";

function FakePostList() {
  const [posts, setPosts] = useState([]);
  const [selectedUserId, setSelectedUserId] = useState("all");
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  const fetchPosts = async () => {
    try {
      setLoading(true);
      setError("");

      const response = await axios.get("https://dummyjson.com/posts");
      setPosts(response.data.posts || []);
    } catch (err) {
      setError(err.message || "Failed to load fake API posts.");
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchPosts();
  }, []);

  const userOptions = useMemo(() => {
    const ids = [...new Set(posts.map((post) => post.userId))].sort((a, b) => a - b);
    return ids;
  }, [posts]);

  const filteredPosts = useMemo(() => {
    if (selectedUserId === "all") {
      return posts;
    }

    return posts.filter((post) => String(post.userId) === selectedUserId);
  }, [posts, selectedUserId]);

  return (
    <section>
      <div className="section-head">
        <h2>Fake API Posts (DummyJSON via Axios)</h2>
        <button type="button" className="refresh-btn" onClick={fetchPosts}>
          Refresh
        </button>
      </div>

      <label htmlFor="userFilter" className="filter-label">
        Filter by userId:
      </label>
      <select
        id="userFilter"
        value={selectedUserId}
        onChange={(event) => setSelectedUserId(event.target.value)}
      >
        <option value="all">All users</option>
        {userOptions.map((id) => (
          <option value={String(id)} key={id}>
            User {id}
          </option>
        ))}
      </select>

      {loading && <p className="status">Loading posts...</p>}
      {error && <p className="status error">Error: {error}</p>}

      {!loading && !error && (
        <div className="grid-list">
          {filteredPosts.map((post) => (
            <article className="item-card" key={post.id}>
              <h3>{post.title}</h3>
              <p>{post.body}</p>
              <p>
                <strong>User ID:</strong> {post.userId}
              </p>
            </article>
          ))}
        </div>
      )}
    </section>
  );
}

export default FakePostList;
