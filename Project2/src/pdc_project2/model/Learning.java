/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pdc_project2.model;

/**
 *
 * @author Tai Zhang 17970814
 */
public class Learning {
	private int id;
	private String studentId;
	private String courseName;
	private float score;

	private Student student;
	private Course course;
	
	public Learning() {

	}
	
	public Learning(String studentId, String courseName, float score) {
		this.studentId = studentId;
		this.courseName = courseName;
		this.score = score;
	}

	@Override
	public String toString() {
		return studentId + "\t" + courseName + "\t" + score;
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}
	
	public Student getStudent() {
		return student;
	}
	
	public void setStudent(Student student) {
		this.student = student;
	}
	
	public Course getCourse() {
		return course;
	}
	
	public void setCourse(Course course) {
		this.course = course;
	}
}
