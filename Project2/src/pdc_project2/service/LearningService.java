package pdc_project2.service;

import java.util.List;

import pdc_project2.model.Learning;

public interface LearningService {

	/**
	 * Get all the Learning *
	 */
	public List<Learning> browseLearning();

	/**
	 * Load Learning *
	 */
	public Learning loadLearning(int learningid);

	/**
	 * Delete Learning *
	 */
	public boolean delLearning(int learningid);

	/**
	 * Add new Learning *
	 */
	public boolean addLearning(Learning learning);

	/**
	 * Update Learning *
	 */
	public boolean updateLearning(Learning learning);

	public List<Learning> browseLearningByStudentId(int studentId);
	public List<Learning> browseLearningByCourseId(int courseId);
}
