# SKILL-13: Deployment of Full-Stack Application (Spring Boot + React)

## Project Layout
- backend: Spring Boot REST API + static hosting
- frontend: React app

## Tasks Covered
1. Generate React production build.
2. Package Spring Boot backend as JAR.
3. Configure environment variables in React build.
4. Run backend JAR and verify APIs.
5. Deploy React build through Spring Boot static folder.
6. Test browser integration.

## Environment Variable Configuration
Frontend file: frontend/.env.production
- REACT_APP_API_BASE_URL=

Keep it empty for same-origin deployment (Spring Boot serves frontend and API together).
For split deployment, set a full backend URL, for example:
- REACT_APP_API_BASE_URL=http://your-server:8080

## Deployment Commands
### Build frontend
cd frontend
npm install
npm run build

### Copy frontend build into backend static
Copy frontend/build/* into backend/src/main/resources/static/

### Package backend JAR
cd ../backend
mvn clean package -DskipTests

### Run packaged JAR
java -jar target/skill13-backend-0.0.1-SNAPSHOT.jar

### Test
- App: http://localhost:8080/
- API: http://localhost:8080/students
