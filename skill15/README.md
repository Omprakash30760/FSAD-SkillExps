# Skill 15: JWT-Based Authentication and Role Authorization

Backend project implemented in:
- `skill15/backend`

## Features Completed

1. `User` entity with `username`, `password`, and `role`.
2. `/login` endpoint generates JWT token for valid credentials.
3. Spring Security configured with JWT filter and stateless session policy.
4. Secured endpoints:
   - `POST /admin/add` (ADMIN only)
   - `DELETE /admin/delete` (ADMIN only)
   - `GET /employee/profile` (EMPLOYEE only)
5. Authentication and authorization tests for valid/invalid JWT tokens.
6. Postman testing guide included below.

## Default Users (Seeded)

- ADMIN: `admin / admin123`
- EMPLOYEE: `employee / emp123`

## Run the Backend

```bash
cd skill15/backend
mvn spring-boot:run
```

Server URL: `http://localhost:8080`

## API Usage

### 1) Login and get JWT

`POST /login`

Body:

```json
{
  "username": "admin",
  "password": "admin123"
}
```

Sample response:

```json
{
  "token": "<jwt-token>",
  "username": "admin",
  "role": "ROLE_ADMIN"
}
```

### 2) Call secured endpoints with token

Header:

`Authorization: Bearer <jwt-token>`

Examples:
- `POST /admin/add?employeeName=John`
- `DELETE /admin/delete?employeeId=E001`
- `GET /employee/profile`

## Postman Test Checklist

1. Login with valid admin credentials -> should return token.
2. Login with invalid credentials -> should return `401 Unauthorized`.
3. Call `/admin/add` without token -> should return `403 Forbidden`.
4. Call `/admin/delete` with EMPLOYEE token -> should return `403 Forbidden`.
5. Call `/admin/add` with ADMIN token -> should return `200 OK`.
6. Call `/employee/profile` with EMPLOYEE token -> should return `200 OK`.
7. Call `/employee/profile` with invalid token -> should return `403 Forbidden`.

## Automated Test Execution

```bash
cd skill15/backend
mvn clean test
```

## Push to GitHub

From your workspace root:

```bash
git add skill15
git commit -m "Add Skill 15 JWT auth and role authorization backend"
git remote add origin <your-repo-url>
git branch -M main
git push -u origin main
```

If remote already exists, skip `git remote add origin`.
