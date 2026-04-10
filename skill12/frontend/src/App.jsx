import React, { useState } from "react";
import AddStudent from "./components/AddStudent";
import StudentList from "./components/StudentList";
import { addStudent, updateStudent } from "./services/studentService";

function App() {
  const [selectedStudent, setSelectedStudent] = useState(null);
  const [refreshToken, setRefreshToken] = useState(0);

  const handleSubmitStudent = async (studentData) => {
    if (selectedStudent) {
      await updateStudent(selectedStudent.id, studentData);
      setSelectedStudent(null);
    } else {
      await addStudent(studentData);
    }
    setRefreshToken((prev) => prev + 1);
  };

  return (
    <main className="container">
      <h1>Skill 12: Student Management CRUD</h1>
      <AddStudent
        onSubmitStudent={handleSubmitStudent}
        selectedStudent={selectedStudent}
        onCancel={() => setSelectedStudent(null)}
      />
      <StudentList refreshToken={refreshToken} onEdit={setSelectedStudent} />
    </main>
  );
}

export default App;
