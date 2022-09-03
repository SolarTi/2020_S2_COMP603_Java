package pdc_project2.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import pdc_project2.model.Course;

public class CourseTest {

	Course course1;
	Course course2;

	@Before
	public void setUp() throws Exception {
		course1 = new Course();
		course2 = new Course("MATH500", 14);
	}

	@Test
	public void testCourse() {
		assertEquals("MATH500", course2.getCourseName());
		assertEquals(14, course2.getCredit());
	}

	@Test
	public void testSetId() {
		assertEquals(0, course1.getId());
		course1.setId(300);
		assertEquals(300, course1.getId());
	}

	@Test
	public void testSetCourseName() {
		course1.setCourseName("PROGRAM");
		assertEquals("PROGRAM", course1.getCourseName());
	}
}
