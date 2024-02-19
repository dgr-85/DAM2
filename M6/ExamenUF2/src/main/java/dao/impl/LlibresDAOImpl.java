package dao.impl;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import dao.LlibresDAO;
import managers.ConnectionManagerHibernate;
import pojos.Autors;
import pojos.Idiomes;
import pojos.Llibres;

public class LlibresDAOImpl implements LlibresDAO {

	SessionFactory factory = ConnectionManagerHibernate.getSessionFactory();

	@Override
	public Integer addLlibre(Llibres llibre) {
		Session session = factory.openSession();
		Transaction tx = null;
		Integer idLlibre = null;
		try {
			tx = session.beginTransaction();
			if (session.get(Autors.class, llibre.getAutors().getCodiAutor()) == null) {
				session.save(llibre.getAutors());
			}
			if (session.get(Idiomes.class, llibre.getIdiomes().getCodi()) == null) {
				session.save(llibre.getIdiomes());
			}
			idLlibre = (Integer) session.save(llibre);
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
		return idLlibre;
	}

	@Override
	public Llibres getLlibreById(Integer id) {
		Session session = factory.openSession();
		Transaction tx = null;
		Llibres getLlibre = null;
		try {
			tx = session.beginTransaction();
			getLlibre = session.get(Llibres.class, id);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return getLlibre;
	}

	@Override
	public Integer updateLlibre(Llibres llibre) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer deleteLlibre(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Llibres> listAllLlibres() {
		// TODO Auto-generated method stub
		return null;
	}

}
