package pdc_project2.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import pdc_project2.model.Course;
import pdc_project2.model.Learning;
import pdc_project2.model.Student;
import pdc_project2.service.CourseService;
import pdc_project2.service.CourseServiceImpl;
import pdc_project2.service.LearningService;
import pdc_project2.service.LearningServiceImpl;
import pdc_project2.service.StudentService;
import pdc_project2.service.StudentServiceImpl;

public class ServiceTest {

	private StudentService studentService = new StudentServiceImpl();
	private LearningService learningService = new LearningServiceImpl();
	private CourseService courseService = new CourseServiceImpl();

	@Test
	public void testStudentService() {
		int studentNum = studentService.browseStudent().size();
		Student student = new Student("01", "James", "Li");
		studentService.addStudent(student);
		assertEquals(studentNum + 1, studentService.browseStudent().size());

		student = studentService.loadStudent(student.getId());
		student.setGivenName("Tao");
		studentService.updateStudent(student);
		student = studentService.loadStudent(student.getId());
		assertEquals("Tao", student.getGivenName());

		studentService.delStudent(student.getId());
		assertEquals(studentNum, studentService.browseStudent().size());
	}

	@Test
	public void testCourseService() {
		int studentNum = courseService.browseCourse().size();
		Course course = new Course("MATH500", 14);
		courseService.addCourse(course);
		assertEquals(studentNum + 1, courseService.browseCourse().size());

		course = courseService.loadCourse(course.getId());
		course.setCredit(10);
		courseService.updateCourse(course);
		course = courseService.loadCourse(course.getId());
		assertEquals(10, course.getCredit());

		courseService.delCourse(course.getId());
		assertEquals(studentNum, courseService.browseCourse().size());
	}

	@Test
	public void testLearningService() {
		Student student = new Student("01", "James", "Li");
		studentService.addStudent(student);
		
		student = studentService.loadStudent(student.getId());
		assertEquals(0, student.getStudentLearnings().size());
		
		Course course = new Course("MATH500", 14);
		courseService.addCourse(course);

		Learning learning = new Learning("01", "MATH500", 80);
		learning.setStudent(student);
		learning.setCourse(course);
		learningService.addLearning(learning);

		student = studentService.loadStudent(student.getId());
		assertEquals(1, student.getStudentLearnings().size());

                learningService.delLearning(learning.getId());
                studentService.delStudent(student.getId());
                courseService.delCourse(course.getId());
	}
}
