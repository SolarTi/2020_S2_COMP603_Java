package pdc_project2.service;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import pdc_project2.model.Course;
import pdc_project2.util.BaseLog;
import pdc_project2.util.HibernateUtils;

public class CourseServiceImpl extends BaseLog implements CourseService {

	@Override
	public boolean addCourse(Course course) {
		Session session = HibernateUtils.getSession();
		Transaction tx = null;
		boolean status = false;
		try {
			tx = session.beginTransaction();
			session.save(course);
			tx.commit();
			status = true;
			session.close();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.info("ERROR Occurs in CourseServiceImpl - addCourse!");
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public List<Course> browseCourse() {
		List<Course> courseList = null;
		Session session = HibernateUtils.getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			String hql = "from Course as a order by a.id";
			Query query = session.createQuery(hql);
			courseList = query.list();
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.info("ERROR Occurs in CourseServiceImpl - browseCourse!");
			e.printStackTrace();
		}
		return courseList;
	}

	@Override
	public boolean delCourse(int courseid) {
		Session session = HibernateUtils.getSession();
		Transaction tx = null;
		boolean status = false;
		try {
			tx = session.beginTransaction();
			Course course = (Course) session.load(Course.class, courseid);
			session.delete(course);
			tx.commit();
			status = true;
			session.close();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.info("ERROR Occurs in CourseServiceImpl - delCourse!");
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public Course loadCourse(int courseid) {
		Session session = HibernateUtils.getSession();
		Transaction tx = null;
		Course course = null;
		try {
			tx = session.beginTransaction();
			course = (Course) session.load(Course.class, courseid);
			tx.commit();
                        session.close();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.info("ERROR Occurs in CourseServiceImpl - loadCourse!");
			e.printStackTrace();
		}
		return course;
	}

	@Override
	public boolean updateCourse(Course course) {
		Session session = HibernateUtils.getSession();
		Transaction tx = null;
		boolean status = false;
		try {
			tx = session.beginTransaction();
			session.update(course);
			tx.commit();
			status = true;
			session.close();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.info("ERROR Occurs in CourseServiceImpl - updateCourse!");
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public Course loadCourse(String courseName) {
		List<Course> studentList = null;
		Session session = HibernateUtils.getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			String hql = "from Course as a where a.courseName = ? order by a.id";
			Query query = session.createQuery(hql);
			query.setParameter(0, courseName);
			studentList = query.list();
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.info("ERROR Occurs in StudentServiceImpl - browseStudent!");
			e.printStackTrace();
		}
		if (studentList.size() > 0) {
			return studentList.get(0);
		}
		return null;
	}
}
