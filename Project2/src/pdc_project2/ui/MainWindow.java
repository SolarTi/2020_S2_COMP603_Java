package pdc_project2.ui;

import java.awt.BorderLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import pdc_project2.util.HibernateUtils;

public class MainWindow extends JFrame {

	public MainWindow() {
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		// close database if window is closed
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				HibernateUtils.shutdown();
			}
		});

		// setup panels
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		StudentPanel studentPanel = new StudentPanel();
		tabbedPane.addTab("Students", studentPanel);
		tabbedPane.addTab("Courses", new CoursesPanel());
		add(tabbedPane);

		JLabel label = new JLabel("Student Information Management System");
		label.setHorizontalAlignment(JLabel.CENTER);
		add(label, BorderLayout.NORTH);
		
		setTitle("Student Information Management System");
		setVisible(true);
		
		tabbedPane.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				System.out.println("Tag Changed.");
				studentPanel.loadStudentData();
			}
		});
	}
}
