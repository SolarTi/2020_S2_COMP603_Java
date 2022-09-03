package pdc_project2.util;

import java.util.List;

import pdc_project2.model.Course;
import pdc_project2.model.Learning;
import pdc_project2.model.Student;
import pdc_project2.service.CourseService;
import pdc_project2.service.CourseServiceImpl;
import pdc_project2.service.LearningService;
import pdc_project2.service.LearningServiceImpl;
import pdc_project2.service.StudentService;
import pdc_project2.service.StudentServiceImpl;

public class DatabaseInit {

	private static final String DATA_FILE = "data.txt";

	public static void initData() throws Exception {
		parseFile();
	}

	private static void parseFile() {
		List<String> lines = FileUtil.readFileString(DATA_FILE);

		CourseService courseService = new CourseServiceImpl();
		StudentService studentService = new StudentServiceImpl();
		LearningService learningService = new LearningServiceImpl();
 
                                
		Course course = null;

		int courseStart = 0;
		for (int i = 0; i < lines.size(); i++) {
			String line = lines.get(i);
			if (line.trim().isEmpty()) {
				courseStart = 0;
				continue;
			}

			courseStart++;
			if (courseStart == 1) {
				course = new Course(line.split(",")[0], Integer.parseInt(line.split(",")[1]));
			} else if (courseStart == 2) {
    
				courseService.addCourse(course);
			} else {
				String[] studentInfo = line.split(",");
				String studentId = studentInfo[2];

				Student student = studentService.loadStudent(studentId);
				if (student == null) {
					student = new Student(studentInfo[2], studentInfo[1], studentInfo[0]);
					studentService.addStudent(student);
				}

                                student = studentService.loadStudent(student.getId());
                                course = courseService.loadCourse(course.getId());
				float score = Float.parseFloat(studentInfo[3]);
				Learning learning = new Learning(studentId, course.getCourseName(), score);
				learning.setStudent(student);
				learning.setCourse(course);
				learningService.addLearning(learning);
			}
		}
	}
}
