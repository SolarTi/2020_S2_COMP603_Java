/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pdc_project2.model;

import java.util.Set;

/**
 *
 * @author Tai Zhang 17970814
 */
public class Course {
	private int id;
	private String courseName;
	private int credit;

	private Set<Learning> courseLearnings;

	public Course() {
		 
	}
	
	
	public Course(String courseName, int credit) {
		this.courseName = courseName;
		this.credit = credit;
	}

	@Override
	public String toString() {
		return courseName + "\t" + credit;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public Set<Learning> getCourseLearnings() {
		return courseLearnings;
	}

	public void setCourseLearnings(Set<Learning> courseLearnings) {
		this.courseLearnings = courseLearnings;
	}


	public Learning getLearning(String studentId) {
		for (Learning learning : courseLearnings) {
			if(learning.getStudentId().equalsIgnoreCase(studentId)) {
				return learning;
			}
		}
		return null;
	}
}
