package dao;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import managers.SessionFactoryUtil;
import pojos.Departaments;

public class DepartamentsDAOImpl implements DepartamentsDAO {

	SessionFactory factory = SessionFactoryUtil.getSessionFactory();

	@Override
	public Integer addDepartament(Departaments departaments) {
		Session session = factory.openSession();
		Transaction tx = null;
		Integer idDepartament = null;
		try {
			tx = session.beginTransaction();
			idDepartament = (Integer) session.save(departaments);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			if (e.getCause().getCause() instanceof SQLIntegrityConstraintViolationException) {
				SQLIntegrityConstraintViolationException ex = (SQLIntegrityConstraintViolationException) e.getCause()
						.getCause();
				return ex.getErrorCode() * -1;
			} else {
				e.printStackTrace();
			}
		} finally {
			session.close();
		}
		return idDepartament;
	}

	@Override
	public Departaments getDepartamentById(int id) {
		Session session = factory.openSession();
		Transaction tx = null;
		Departaments getDepartament = null;
		try {
			tx = session.beginTransaction();
			getDepartament = session.get(Departaments.class, id);
			if (getDepartament != null) {
				Hibernate.initialize(getDepartament.getEmpleatses());
			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return getDepartament;
	}

	@Override
	public Integer updateDepartament(Departaments departaments) {
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.update(departaments);
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
		return departaments.getDeptNo();
	}

	@Override
	public Integer deleteDepartament(int id) {
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Departaments doomedDepartament = getDepartamentById(id);
			session.delete(doomedDepartament);
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
	public ArrayList<Departaments> listAllDepartaments() {
		Session session = factory.openSession();
		Transaction tx = null;
		List<Departaments> deps = new ArrayList<>();
		try {
			tx = session.beginTransaction();
			deps = session.createQuery("from Departaments", Departaments.class).getResultList();
			for (Departaments d : deps) {
				Hibernate.initialize(d.getEmpleatses());
			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return (ArrayList<Departaments>) deps;
	}

}
