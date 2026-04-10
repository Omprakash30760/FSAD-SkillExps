import React, { useEffect, useState } from "react";

function AddStudent({ onSubmitStudent, selectedStudent, onCancel }) {
  const [formData, setFormData] = useState({ name: "", email: "", course: "" });

  useEffect(() => {
    if (selectedStudent) {
      setFormData({
        name: selectedStudent.name || "",
        email: selectedStudent.email || "",
        course: selectedStudent.course || "",
      });
    } else {
      setFormData({ name: "", email: "", course: "" });
    }
  }, [selectedStudent]);

  const handleChange = (event) => {
    const { name, value } = event.target;
    setFormData((prev) => ({ ...prev, [name]: value }));
  };

  const handleSubmit = async (event) => {
    event.preventDefault();
    if (!formData.name || !formData.email || !formData.course) {
      alert("All fields are required");
      return;
    }
    await onSubmitStudent(formData);
    if (!selectedStudent) {
      setFormData({ name: "", email: "", course: "" });
    }
  };

  return (
    <form onSubmit={handleSubmit} className="card">
      <h2>{selectedStudent ? "Update Student" : "Add Student"}</h2>
      <div className="form-grid">
        <input name="name" placeholder="Name" value={formData.name} onChange={handleChange} />
        <input name="email" placeholder="Email" value={formData.email} onChange={handleChange} />
        <input name="course" placeholder="Course" value={formData.course} onChange={handleChange} />
      </div>
      <div className="row">
        <button type="submit">{selectedStudent ? "Update" : "Add"}</button>
        {selectedStudent && (
          <button type="button" onClick={onCancel} className="secondary">
            Cancel
          </button>
        )}
      </div>
    </form>
  );
}

export default AddStudent;
