package dao.impl;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import dao.AutorsDAO;
import managers.ConnectionManagerHibernate;
import pojos.Autors;

public class AutorsDAOImpl implements AutorsDAO {

	SessionFactory factory = ConnectionManagerHibernate.getSessionFactory();

	@Override
	public Integer addAutor(Autors autor) {
		Session session = factory.openSession();
		Transaction tx = null;
		Integer idAutor = null;
		try {
			tx = session.beginTransaction();
			idAutor = (Integer) session.save(autor);
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
		return idAutor;
	}

	@Override
	public Autors getAutorById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer updateAutor(Autors autor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer deleteAutor(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Autors> listAllAutors(Boolean includeLlibres) {
		Session session = factory.openSession();
		Transaction tx = null;
		List<Autors> autors = new ArrayList<>();
		try {
			tx = session.beginTransaction();
			autors = session.createQuery("from autors", Autors.class).getResultList();
			if (includeLlibres) {
				for (Autors a : autors) {
					Hibernate.initialize(a.getLlibreses());
				}
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
		return autors;
	}

}
