const STORAGE_KEY = "skill14_user";

export const saveUserSession = (user, remember = true) => {
  const storage = remember ? localStorage : sessionStorage;
  storage.setItem(STORAGE_KEY, JSON.stringify(user));
  if (remember) {
    sessionStorage.removeItem(STORAGE_KEY);
  } else {
    localStorage.removeItem(STORAGE_KEY);
  }
};

export const getUserSession = () => {
  const local = localStorage.getItem(STORAGE_KEY);
  if (local) {
    return JSON.parse(local);
  }

  const session = sessionStorage.getItem(STORAGE_KEY);
  if (session) {
    return JSON.parse(session);
  }

  return null;
};

export const clearUserSession = () => {
  localStorage.removeItem(STORAGE_KEY);
  sessionStorage.removeItem(STORAGE_KEY);
};

export const isLoggedIn = () => Boolean(getUserSession());
