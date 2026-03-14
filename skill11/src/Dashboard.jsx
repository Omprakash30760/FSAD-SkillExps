import { useState } from "react";
import LocalUserList from "./LocalUserList";
import UserList from "./UserList";
import FakePostList from "./FakePostList";

const VIEWS = {
  HOME: "HOME",
  LOCAL: "LOCAL",
  API_USERS: "API_USERS",
  FAKE_POSTS: "FAKE_POSTS",
};

function Dashboard() {
  const [activeView, setActiveView] = useState(VIEWS.HOME);

  return (
    <div className="app-shell">
      <header className="hero">
        <h1>Skill 11 - React API Integration</h1>
        <p>
          Explore data from local JSON, JSONPlaceholder, and a fake API using
          fetch and axios.
        </p>
      </header>

      <nav className="nav-links" aria-label="Dashboard navigation">
        <button type="button" onClick={() => setActiveView(VIEWS.LOCAL)}>
          Local Users
        </button>
        <button type="button" onClick={() => setActiveView(VIEWS.API_USERS)}>
          Users API
        </button>
        <button type="button" onClick={() => setActiveView(VIEWS.FAKE_POSTS)}>
          Fake API Posts
        </button>
        <button type="button" onClick={() => setActiveView(VIEWS.HOME)}>
          Home
        </button>
      </nav>

      <main className="content-card">
        {activeView === VIEWS.HOME && (
          <section>
            <h2>Dashboard</h2>
            <p>Select a section to view data.</p>
            <ul>
              <li>Part A: Fetch local users JSON from public folder.</li>
              <li>Part B: Fetch users from JSONPlaceholder API.</li>
              <li>Part C: Fetch posts using axios with refresh and filter.</li>
            </ul>
          </section>
        )}

        {activeView === VIEWS.LOCAL && <LocalUserList />}
        {activeView === VIEWS.API_USERS && <UserList />}
        {activeView === VIEWS.FAKE_POSTS && <FakePostList />}
      </main>
    </div>
  );
}

export default Dashboard;
