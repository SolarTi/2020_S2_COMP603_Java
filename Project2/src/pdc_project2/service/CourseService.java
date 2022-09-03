package pdc_project2.service;

import java.util.List;

import pdc_project2.model.Course;

public interface CourseService {

    /**
     * Get all the Course *
     */
    public List<Course> browseCourse();

    /**
     * Load Course *
     */
    public Course loadCourse(int courseid);

    /**
     * Delete Course *
     */
    public boolean delCourse(int courseid);

    /**
     * Add new Course *
     */
    public boolean addCourse(Course course);

    /**
     * Update Course *
     */
    public boolean updateCourse(Course course);

	public Course loadCourse(String courseName);
}
