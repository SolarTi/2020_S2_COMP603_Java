package pdc_project2.service;

import java.util.List;

import pdc_project2.model.Student;

public interface StudentService {

    /**
     * Get all the Student *
     */
    public List<Student> browseStudent();

    /**
     * Load Student *
     */
    public Student loadStudent(int studentid);

    /**
     * Load Student *
     */
    public Student loadStudent(String studentId);
    
    /**
     * Delete Student *
     */
    public boolean delStudent(int studentid);

    /**
     * Add new Student *
     */
    public boolean addStudent(Student student);

    /**
     * Update Student *
     */
    public boolean updateStudent(Student student);
}
