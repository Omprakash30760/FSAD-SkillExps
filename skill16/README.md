# Skill 16: API Documentation for Student CRUD using Swagger/OpenAPI

This experiment is implemented in:
- `skill16/backend`

It integrates Swagger/OpenAPI into the Student CRUD backend (based on Skill 12) and documents all endpoints with schemas, validation, examples, and response structures.

## Part I - Swagger/OpenAPI Setup

Completed:
1. Added OpenAPI dependency in `pom.xml`.
2. Added OpenAPI config class for metadata.
3. Configured Swagger UI path.

### Run Application

```bash
cd skill16/backend
mvn spring-boot:run
```

### Open Swagger UI

Use either:
- http://localhost:8080/swagger-ui.html
- http://localhost:8080/swagger-ui/index.html

OpenAPI JSON:
- http://localhost:8080/v3/api-docs

## Part II - Documented Student CRUD APIs

Endpoints shown in Swagger UI:
- `POST /students` - Add a new student
- `GET /students` - Retrieve all students
- `GET /students/{id}` - Retrieve one student by ID
- `PUT /students/{id}` - Update a student
- `DELETE /students/{id}` - Delete a student

Student schema fields:
- `id`
- `name`
- `email`
- `course`

Additional documentation included:
- `@Operation` summaries and descriptions
- `@ApiResponse` for success and error cases
- Example request body for create endpoint
- Validation messages via Bean Validation (`@NotBlank`, `@Email`)
- Structured error response model (`ApiErrorResponse`)

## Part III - Swagger UI Testing Checklist

From Swagger UI, test:
1. Add student using `POST /students`
2. View all students using `GET /students`
3. Update student using `PUT /students/{id}`
4. Delete student using `DELETE /students/{id}`
5. Invalid ID `999` for GET/PUT/DELETE and verify `404 Not Found`

Verify Swagger displays:
- Schemas: `Student`, `ApiErrorResponse`
- Validation errors (`400 Bad Request`)
- Example request body (POST)
- Response structures for success and errors

## Validation

Automated tests included at:
- `src/test/java/com/example/skill16/StudentApiIntegrationTest.java`

Run:

```bash
cd skill16/backend
mvn clean test
```

## GitHub Push

```bash
git add skill16
git commit -m "Add Skill 16 Swagger/OpenAPI documented Student CRUD backend"
git remote add origin <your-repo-url>
git branch -M main
git push -u origin main
```

If remote already exists, skip `git remote add origin`.
