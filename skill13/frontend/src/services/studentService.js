import axios from "axios";

const base = process.env.REACT_APP_API_BASE_URL || "http://localhost:8082";
const API_URL = `${base}/students`;

export const getStudents = () => axios.get(API_URL);
export const addStudent = (student) => axios.post(API_URL, student);
export const updateStudent = (id, student) => axios.put(`${API_URL}/${id}`, student);
export const deleteStudent = (id) => axios.delete(`${API_URL}/${id}`);
