package pdc_project2.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import pdc_project2.model.Student;

public class StudentTest {

	Student student1;
	Student student2;

	@Before
	public void setUp() throws Exception {
		student1 = new Student();
		student2 = new Student("01", "Tao", "Li");
	}

	@Test
	public void testStudent() {
		assertEquals("01", student2.getStudentId());
		assertEquals("Tao", student2.getGivenName());
		assertEquals("Li", student2.getSurname());
	}

	@Test
	public void testSetId() {
		student1.setId(60);
		assertEquals(60, student1.getId());
	}

	@Test
	public void testSetStudentId() {
		student1.setStudentId("02");
		assertEquals("02", student1.getStudentId());
	}

	@Test
	public void testSetSurname() {
		student1.setSurname("Sur");
		assertEquals("Sur", student1.getSurname());
	}
}
