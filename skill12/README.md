# SKILL-12: Full-Stack CRUD Application using React and Spring Boot

## Folder Structure
- backend: Spring Boot REST API
- frontend: React application

## Backend Endpoints
- POST /students: Add a new student
- GET /students: Retrieve all students
- PUT /students/{id}: Update existing student
- DELETE /students/{id}: Delete student by ID

## Features Implemented
- Layered backend architecture: Controller, Service, Repository
- ResponseEntity used for all endpoint responses
- React StudentList fetches students and renders Update/Delete actions
- React AddStudent form captures name, email, and course with useState
- POST creates students and clears form after successful submit
- Update prefills the form and sends PUT request
- Delete removes selected row and refreshes list
- UI updates immediately after add, update, and delete using refetch/state refresh

## Run Instructions

### 1. Start Backend
```powershell
Set-Location "d:\ALL WORK\FULL STACK\FSAD_LAB\skill12\backend"
mvn spring-boot:run
```

### 2. Start Frontend
```powershell
Set-Location "d:\ALL WORK\FULL STACK\FSAD_LAB\skill12\frontend"
npm install
npm start
```

### 3. Verify
- Frontend URL: http://localhost:3000
- Backend URL: http://localhost:8080/students

## GitHub Repository Requirement (Task 6)
This project is prepared in the required structure for a single repository:
- frontend/
- backend/

If your remote is configured, push with:
```powershell
Set-Location "d:\ALL WORK\FULL STACK\FSAD_LAB\skill12"
git init
git add .
git commit -m "Skill 12 full-stack CRUD app"
git branch -M main
git remote add origin <YOUR_GITHUB_REPO_URL>
git push -u origin main
```
