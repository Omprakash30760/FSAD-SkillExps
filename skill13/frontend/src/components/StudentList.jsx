import React, { useEffect, useState } from "react";
import { deleteStudent, getStudents } from "../services/studentService";

function StudentList({ refreshToken, onEdit }) {
  const [students, setStudents] = useState([]);

  const loadStudents = async () => {
    const response = await getStudents();
    setStudents(response.data);
  };

  useEffect(() => {
    loadStudents();
  }, [refreshToken]);

  const handleDelete = async (id) => {
    await deleteStudent(id);
    await loadStudents();
  };

  return (
    <div className="card">
      <h2>Student List</h2>
      {students.length === 0 ? (
        <p>No records found.</p>
      ) : (
        <table>
          <thead>
            <tr>
              <th>ID</th>
              <th>Name</th>
              <th>Email</th>
              <th>Course</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            {students.map((student) => (
              <tr key={student.id}>
                <td>{student.id}</td>
                <td>{student.name}</td>
                <td>{student.email}</td>
                <td>{student.course}</td>
                <td>
                  <button onClick={() => onEdit(student)}>Update</button>
                  <button onClick={() => handleDelete(student.id)} className="danger">
                    Delete
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
}

export default StudentList;
