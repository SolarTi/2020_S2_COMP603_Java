package pdc_project2.ui;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import pdc_project2.model.Course;
import pdc_project2.model.Learning;
import pdc_project2.model.Student;
import pdc_project2.service.CourseService;
import pdc_project2.service.CourseServiceImpl;
import pdc_project2.service.LearningService;
import pdc_project2.service.LearningServiceImpl;
import pdc_project2.service.StudentService;
import pdc_project2.service.StudentServiceImpl;
import pdc_project2.util.ScoreUtil;

public class StudentPanel extends JPanel implements ActionListener {

	private JTable studentTable;

	private JLabel studentNameLabel;
	private JTable courseTable;
	private JButton coursesButton;
	private JButton addScoreButton;

	private List<Student> students = new ArrayList<Student>();

	private StudentService studentService = new StudentServiceImpl();
	private LearningService learningService = new LearningServiceImpl();
	private CourseService courseService = new CourseServiceImpl();

	public StudentPanel() {
		setLayout(new BorderLayout());

		studentTable = new JTable();
		studentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		studentTable.setAutoCreateRowSorter(true);
		studentNameLabel = new JLabel();
		studentNameLabel.setHorizontalAlignment(JLabel.CENTER);
		courseTable = new JTable();
		courseTable.setAutoCreateRowSorter(true);
		courseTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		addScoreButton = new JButton("Add Score");
		coursesButton = new JButton("Courses");

		JPanel centerPanel = new JPanel();
		JScrollPane scrollPane = new JScrollPane(studentTable);
		centerPanel.add(scrollPane);

		add(centerPanel);

		JPanel bottomPanel = new JPanel();
		bottomPanel.add(addScoreButton);
		bottomPanel.add(coursesButton);
		add(bottomPanel, BorderLayout.SOUTH);

		addScoreButton.addActionListener(this);
		coursesButton.addActionListener(this);

		loadStudentData();
	}

	/**
	 * load student data from database.
	 */
	public void loadStudentData() {

		students = studentService.browseStudent();

		String[] columnNames = { "Student Id", "Given Name", "Surname", "Num. of Courses", "GPA" };
		Object[][] data = new Object[students.size()][columnNames.length];

		for (int i = 0; i < students.size(); i++) {
			Student student = students.get(i);
			data[i][0] = student.getStudentId();
			data[i][1] = student.getGivenName();
			data[i][2] = student.getSurname();
			data[i][3] = student.getStudentLearnings().size();
			data[i][4] = String.format("%.2f", student.getGpa());
		}

		TableModel studenTableModel = new DefaultTableModel(data, columnNames);
		studentTable.setModel(studenTableModel);
	}

	/**
	 * show courses of a student.
	 */
	private void showCourses(Student student) {
		studentNameLabel.setText(student.getGivenName() + " " + student.getSurname());

		String[] columnNames = { "Course Name", "Course Credit", "Score", "Grade" };
		Object[][] data = new Object[student.getStudentLearnings().size()][columnNames.length];

		int i = 0;
		for (Learning learning : student.getStudentLearnings()) {
			Course course = learning.getCourse();

			data[i][0] = course.getCourseName();
			data[i][1] = course.getCredit();
			data[i][2] = learning.getScore();
			data[i][3] = String.format("%s(%.1f)", ScoreUtil.getGrade(learning.getScore()),
					ScoreUtil.getCredit(learning.getScore()));
			i++;
		}

		TableModel studenTableModel = new DefaultTableModel(data, columnNames);
		courseTable.setModel(studenTableModel);

		JPanel rightPanel = new JPanel(new BorderLayout());
		rightPanel.add(studentNameLabel, BorderLayout.NORTH);
		JScrollPane scrollPane = new JScrollPane(courseTable);
		rightPanel.add(scrollPane);

		// pop up a window
		JButton okButton = new JButton("Close");
		JDialog dialog = new JDialog();
		dialog.add(rightPanel);
		dialog.add(okButton, BorderLayout.SOUTH);
		dialog.setLocation(0, 0);
		dialog.pack();
		dialog.setVisible(true);
		dialog.setLocationRelativeTo(this);
		okButton.addActionListener(e -> {
			dialog.dispose();
		});
		dialog.setModal(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == addScoreButton) {
			int index = studentTable.getSelectedRow();
			if (index >= 0) {
				Student student = students.get(index);

				String courseName = JOptionPane.showInputDialog(this, "Enter the course name:");
				if (courseName == null) {
					return;
				}

				Course course = courseService.loadCourse(courseName);

				if (course != null) {
					try {
						float score = Float.parseFloat(JOptionPane.showInputDialog(this, "Enter the course score:"));
						if (score >= 0 && score <= 100) {
							Learning learning = student.getLearning(courseName);
							if (learning == null) {
								learning = new Learning(student.getStudentId(), courseName, score);

								student = studentService.loadStudent(student.getId());

								learning.setStudent(student);
								learning.setCourse(course);

								learningService.addLearning(learning);
								JOptionPane.showMessageDialog(this, "score added.");
							} else {
								learning = learningService.loadLearning(learning.getId());
								learning.setScore(score);
								learningService.updateLearning(learning);

								student = studentService.loadStudent(student.getId());
								learning.setStudent(student);
								learning.setCourse(course);

								learningService.updateLearning(learning);
								JOptionPane.showMessageDialog(this, "score updated.");
							}

							loadStudentData();
						} else {
							throw new NumberFormatException();
						}
					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(this, "Invalid score number.");
					}
				} else {
					JOptionPane.showMessageDialog(this, "No course has name " + courseName);
				}
			} else {
				JOptionPane.showMessageDialog(this, "No student selected.");
			}
		}

		if (e.getSource() == coursesButton) {
			int index = studentTable.getSelectedRow();
			if (index >= 0) {
				showCourses(students.get(index));
			} else {
				JOptionPane.showMessageDialog(this, "No student selected.");
			}
		}
	}
}
