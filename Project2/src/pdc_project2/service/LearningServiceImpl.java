package pdc_project2.service;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import pdc_project2.model.Learning;
import pdc_project2.util.BaseLog;
import pdc_project2.util.HibernateUtils;

public class LearningServiceImpl extends BaseLog implements LearningService {

	@Override
	public boolean addLearning(Learning learning) {
		Session session = HibernateUtils.getSession();
		Transaction tx = null;
		boolean status = false;
		try {
			tx = session.beginTransaction();
			session.save(learning);
			tx.commit();
			status = true;
			session.close();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.info("ERROR Occurs in LearningServiceImpl - addLearning!");
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public List<Learning> browseLearning() {
		List<Learning> learningList = null;
		Session session = HibernateUtils.getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			String hql = "from Learning as a order by a.id";
			Query query = session.createQuery(hql);
			learningList = query.list();
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.info("ERROR Occurs in LearningServiceImpl - browseLearning!");
			e.printStackTrace();
		}
		return learningList;
	}

	@Override
	public boolean delLearning(int learningid) {
		Session session = HibernateUtils.getSession();
		Transaction tx = null;
		boolean status = false;
		try {
			tx = session.beginTransaction();
			Learning learning = (Learning) session.load(Learning.class, learningid);
			session.delete(learning);
			tx.commit();
			status = true;
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.info("ERROR Occurs in LearningServiceImpl - delLearning!");
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public Learning loadLearning(int learningid) {
		Session session = HibernateUtils.getSession();
		Transaction tx = null;
		Learning learning = null;
		try {
			tx = session.beginTransaction();
			learning = (Learning) session.load(Learning.class, learningid);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.info("ERROR Occurs in LearningServiceImpl - loadLearning!");
			e.printStackTrace();
		}
		return learning;
	}

	@Override
	public boolean updateLearning(Learning learning) {
		Session session = HibernateUtils.getSession();
		Transaction tx = null;
		boolean status = false;
		try {
			tx = session.beginTransaction();
			session.update(learning);
			tx.commit();
			status = true;
			session.close();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.info("ERROR Occurs in LearningServiceImpl - updateLearning!");
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public List<Learning> browseLearningByStudentId(int studentId) {
		List<Learning> learningList = null;
		Session session = HibernateUtils.getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			String hql = "from Learning as l where l.student.id =" + studentId;
			Query query = session.createQuery(hql);
			learningList = query.list();
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.info("ERROR Occurs in LearningServiceImpl - browseLearningByStudentId!");
			e.printStackTrace();
		}
		return learningList;
	}

	@Override
	public List<Learning> browseLearningByCourseId(int courseId) {
		List<Learning> learningList = null;
		Session session = HibernateUtils.getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			String hql = "from Learning as l where l.course.id =" + courseId;
			Query query = session.createQuery(hql);
			learningList = query.list();
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.info("ERROR Occurs in LearningServiceImpl - browseLearningByStudentId!");
			e.printStackTrace();
		}
		return learningList;
	}
}
