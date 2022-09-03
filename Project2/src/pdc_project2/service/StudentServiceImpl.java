package pdc_project2.service;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import pdc_project2.model.Student;
import pdc_project2.util.BaseLog;
import pdc_project2.util.HibernateUtils;

public class StudentServiceImpl extends BaseLog implements StudentService {

	@Override
	public boolean addStudent(Student student) {
		Session session = HibernateUtils.getSession();
		Transaction tx = null;
		boolean status = false;
		try {
			tx = session.beginTransaction();
			session.save(student);
			tx.commit();
			status = true;
			session.close();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.info("ERROR Occurs in StudentServiceImpl - addStudent!");
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public List<Student> browseStudent() {
		List<Student> studentList = null;
		Session session = HibernateUtils.getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			String hql = "from Student as a order by a.id";
			Query query = session.createQuery(hql);
			studentList = query.list();
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.info("ERROR Occurs in StudentServiceImpl - browseStudent!");
			e.printStackTrace();
		}
		return studentList;
	}

	@Override
	public boolean delStudent(int studentid) {
		Session session = HibernateUtils.getSession();
		Transaction tx = null;
		boolean status = false;
		try {
			tx = session.beginTransaction();
			Student student = (Student) session.load(Student.class, studentid);
			session.delete(student);
			tx.commit();
			status = true;
			session.close();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.info("ERROR Occurs in StudentServiceImpl - delStudent!");
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public Student loadStudent(String studentId) {
		List<Student> studentList = null;
		Session session = HibernateUtils.getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			String hql = "from Student as a where a.studentId = ? order by a.id";
			Query query = session.createQuery(hql);
			query.setParameter(0, studentId);
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

	@Override
	public Student loadStudent(int studentid) {
		Session session = HibernateUtils.getSession();
		Transaction tx = null;
		Student student = null;
		try {
			tx = session.beginTransaction();
			student = (Student) session.load(Student.class, studentid);
			tx.commit();
                        session.close();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.info("ERROR Occurs in StudentServiceImpl - loadStudent!");
			e.printStackTrace();
		}
		return student;
	}

	@Override
	public boolean updateStudent(Student student) {
		Session session = HibernateUtils.getSession();
		Transaction tx = null;
		boolean status = false;
		try {
			tx = session.beginTransaction();
			session.update(student);
			tx.commit();
			status = true;
			session.close();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.info("ERROR Occurs in StudentServiceImpl - updateStudent!");
			e.printStackTrace();
		}
		return status;
	}

}
