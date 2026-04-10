import api from "./api";

export const registerUser = (payload) => api.post("/api/auth/register", payload);
export const loginUser = (payload) => api.post("/api/auth/login", payload);

export const fetchProfile = ({ userId, username }) => {
  const params = {};
  if (userId) {
    params.userId = userId;
  }
  if (username) {
    params.username = username;
  }
  return api.get("/api/users/profile", { params });
};
