package pdc_project2.tests;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;

import org.junit.Test;

import pdc_project2.model.Course;
import pdc_project2.model.Learning;
import pdc_project2.model.Student;

public class GPATest {

	@Test
	public void test() {
		Student student = new Student("01", "Tao", "Li");
		Course c1 = new Course("MATH500", 15);
		Course c2 = new Course("MATH600", 10);

		Learning l1 = new Learning("01", "MATH500", 50);
		l1.setStudent(student);
		l1.setCourse(c1);
		Learning l2 = new Learning("01", "MATH600", 50);
		l2.setStudent(student);
		l2.setCourse(c2);

		student.setStudentLearnings(new HashSet<Learning>());
		student.getStudentLearnings().add(l1);
		student.getStudentLearnings().add(l2);

		assertEquals(1, student.getGpa(), 0.001);

		l1.setScore(40);
		assertEquals(0.4, student.getGpa(), 0.001);
	}

}
