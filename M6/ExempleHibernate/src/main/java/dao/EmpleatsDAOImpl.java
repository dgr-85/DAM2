package dao;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import managers.SessionFactoryUtil;
import pojos.Empleats;

public class EmpleatsDAOImpl implements EmpleatsDAO {

	SessionFactory factory = SessionFactoryUtil.getSessionFactory();

	@Override
	public Integer addEmpleat(Empleats empleats) {
		Session session = factory.openSession();
		Transaction tx = null;
		Integer idEmpleat = null;
		try {
			tx = session.beginTransaction();

			idEmpleat = (Integer) session.save(empleats);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			if (e.getCause().getCause() instanceof SQLIntegrityConstraintViolationException) {
				SQLIntegrityConstraintViolationException ex = (SQLIntegrityConstraintViolationException) e.getCause()
						.getCause();
				return ex.getErrorCode() * -1;
			}
		} finally {
			session.close();
		}
		return idEmpleat;
	}

	@Override
	public Empleats getEmpleatById(int id) {
		Session session = factory.openSession();
		Transaction tx = null;
		Empleats getEmpleat = null;
		try {
			tx = session.beginTransaction();
			getEmpleat = session.get(Empleats.class, id);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return getEmpleat;
	}

	@Override
	public Integer updateEmpleat(Empleats empleats) {
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.update(empleats);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			if (e.getCause().getCause() instanceof SQLIntegrityConstraintViolationException) {
				SQLIntegrityConstraintViolationException ex = (SQLIntegrityConstraintViolationException) e.getCause()
						.getCause();
				return ex.getErrorCode() * -1;
			}
		} finally {
			session.close();
		}
		return empleats.getEmpNo();
	}

	@Override
	public Integer deleteEmpleat(int id) {
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Empleats doomedEmpleat = getEmpleatById(id);
			session.delete(doomedEmpleat);
			tx.commit();
		} catch (IllegalArgumentException e) {
			if (tx != null) {
				tx.rollback();
			}
			return -1;
		} finally {
			session.close();
		}
		return id;
	}

	@Override
	public ArrayList<Empleats> listAllEmpleats() {
		Session session = factory.openSession();
		Transaction tx = null;
		List<Empleats> emps = new ArrayList<>();
		try {
			tx = session.beginTransaction();
			emps = session.createQuery("from Empleats", Empleats.class).getResultList();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return (ArrayList<Empleats>) emps;
	}

}
