/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pdc_project2.model;

import java.util.Set;

import pdc_project2.util.ScoreUtil;

/**
 *
 * @author Tai Zhang 17970814
 */
public class Student {
	private int id;
	private String studentId;
	private String givenName;
	private String surname;

	private Set<Learning> studentLearnings;

	public Student() {

	}

	public Student(String studentId, String givenName, String surname) {
		this.studentId = studentId;
		this.givenName = givenName;
		this.surname = surname;
	}

	@Override
	public String toString() {
		return studentId + "\t" + givenName + "\t" + surname;
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

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getGivenName() {
		return givenName;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	public Set<Learning> getStudentLearnings() {
		return studentLearnings;
	}

	public void setStudentLearnings(Set<Learning> studentLearnings) {
		this.studentLearnings = studentLearnings;
	}

	public float getGpa() {
		int sumCourseCredit = 0;
		int sumStudentCredit = 0;
		for (Learning learning : studentLearnings) {
			Course course = learning.getCourse();
			sumCourseCredit += course.getCredit();
			sumStudentCredit += ScoreUtil.getCredit(learning.getScore()) * course.getCredit();
		}

		return sumStudentCredit * 1F / (sumCourseCredit == 0 ? 1 : sumCourseCredit);
	}

	public Learning getLearning(String courseName) {
		for (Learning learning : studentLearnings) {
			if(learning.getCourseName().equalsIgnoreCase(courseName)) {
				return learning;
			}
		}
		return null;
	}
}
