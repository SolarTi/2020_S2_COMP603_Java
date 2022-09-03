package pdc_project2.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import pdc_project2.model.Learning;

public class LearningTest {
	Learning learning;

	@Before
	public void setUp() throws Exception {
		learning = new Learning("01", "MATH500", 50);
	}

	@Test
	public void testLearning() {
		assertEquals("01", learning.getStudentId());
		assertEquals("MATH500", learning.getCourseName());
		assertEquals(50, learning.getScore(), 0.001);
	}

	@Test
	public void testSetStudentId() {
		learning.setStudentId("02");
		assertEquals("02", learning.getStudentId());
	}

	@Test
	public void testSetCourseName() {
		learning.setCourseName("MATH400");
		assertEquals("MATH400", learning.getCourseName());
	}

	@Test
	public void testSetScore() {
		learning.setScore(10);
		assertEquals(10, learning.getScore(), 0.001);
	}
}
