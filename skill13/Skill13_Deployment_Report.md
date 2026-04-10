# SKILL-13: Deployment of Full-Stack Application (Spring Boot + React)

## Objective
Deploy a production-ready full-stack application by building React frontend, packaging Spring Boot backend, configuring environment variables, running deployment artifacts, and validating integration.

## Prerequisites
- Basic Spring Boot knowledge
- React build process understanding
- Hosting basics (Nginx/Apache/Spring Boot static hosting)

## Implementation Location
- Backend: skill13/backend
- Frontend: skill13/frontend

## Task 1: Generate React Production Build
Commands used:
```powershell
Set-Location "d:\ALL WORK\FULL STACK\FSAD_LAB\skill13\frontend"
npm install
npm run build
```
Result:
- Build created successfully in skill13/frontend/build

## Task 2: Package Spring Boot as JAR
Commands used:
```powershell
Set-Location "d:\ALL WORK\FULL STACK\FSAD_LAB\skill13\backend"
mvn clean package -DskipTests
```
Result:
- JAR generated at: skill13/backend/target/skill13-backend-0.0.1-SNAPSHOT.jar

## Task 3: Configure Environment Variables in React Build
Configured file:
- skill13/frontend/.env.production

Configured variable:
```env
REACT_APP_API_BASE_URL=
```
Notes:
- Empty value means same-origin calls (for Spring Boot static deployment).
- For split hosting, set full backend URL (example: http://server:8080).

## Task 4: Run Backend JAR and Verify APIs
Run command:
```powershell
Set-Location "d:\ALL WORK\FULL STACK\FSAD_LAB\skill13\backend"
java -jar .\target\skill13-backend-0.0.1-SNAPSHOT.jar
```
Verified APIs:
- POST /students
- GET /students
- PUT /students/{id}
- DELETE /students/{id}

Observed checks:
- Added student successfully.
- Retrieved student list successfully.
- Updated student successfully.
- Deleted student successfully.

## Task 5: Deploy React Build Through Spring Boot Static Folder
Command used:
```powershell
Set-Location "d:\ALL WORK\FULL STACK\FSAD_LAB\skill13"
New-Item -ItemType Directory -Force -Path ".\backend\src\main\resources\static" | Out-Null
Copy-Item -Path ".\frontend\build\*" -Destination ".\backend\src\main\resources\static" -Recurse -Force
```
Result:
- Frontend static files copied into backend static folder.
- Spring Boot serves frontend and backend together from same server.

## Task 6: Test Deployed Application in Browser
Browser URL tested:
- http://localhost:8080/

Integration outcome:
- Deployed UI is accessible.
- Frontend communicates with backend endpoints successfully via same-origin /students.
- CRUD behavior works without page reload.

## Conclusion
Skill-13 deployment tasks were completed end-to-end:
1. React production build generated
2. Spring Boot backend packaged as executable JAR
3. Environment variable strategy configured for production
4. APIs verified after running packaged JAR
5. Frontend deployed through Spring Boot static hosting
6. Browser-level integration validated
