# Skill 14: User Authentication and Session Management (React + Spring Boot)

This project implements:
- User registration
- User login
- Session storage using `localStorage` or `sessionStorage`
- Protected Home and Profile pages
- Profile fetch from backend database using stored user identity
- Logout with session clear and redirect

## Repository Structure

```
skill14/
  backend/   -> Spring Boot code
  frontend/  -> React code
```

## Backend (Spring Boot)

### Tech
- Spring Boot 3
- Spring Web
- Spring Data JPA
- H2 Database (in-memory)

### Run Backend

```bash
cd backend
mvn spring-boot:run
```

Backend runs at: `http://localhost:8080`

### API Endpoints
- `POST /api/auth/register`
  - body: `{ "fullName", "username", "email", "password" }`
- `POST /api/auth/login`
  - body: `{ "username", "password" }`
- `GET /api/users/profile?userId=1` or `GET /api/users/profile?username=john`

## Frontend (React)

### Tech
- React 18
- React Router DOM
- Axios

### Run Frontend

```bash
cd frontend
npm install
npm start
```

Frontend runs at: `http://localhost:3000`

## Authentication Flow Implemented

1. Register user in backend DB from Register page.
2. Redirect to Login page after successful registration.
3. Validate credentials in Login page via backend API.
4. Store `{ userId, username }` in storage:
   - `localStorage` if "Remember me" is checked
   - `sessionStorage` otherwise
5. Allow Home/Profile only for logged-in users.
6. Read stored identity in Profile and fetch full user details from backend.
7. Logout clears storage and redirects to Login.

## Build Verification (Completed)

- Backend: `mvn clean test` -> BUILD SUCCESS
- Frontend: `npm run build` -> Compiled successfully

## Push to GitHub (Single Full-Stack Repository)

From project root (`FSAD_LAB`), run:

```bash
git add skill14
git commit -m "Add Skill 14 auth and session management full-stack project"
git remote add origin <your-github-repo-url>
git branch -M main
git push -u origin main
```

If remote already exists, skip `git remote add origin` and directly push.
