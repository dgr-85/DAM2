package dao;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import managers.SessionFactoryUtil;
import pojos.Usuaris;

public class UsuarisDAOImpl implements UsuarisDAO {

	SessionFactory factory = SessionFactoryUtil.getSessionFactory();

	@Override
	public Integer addUsuari(Usuaris usuaris) {
		Session session = factory.openSession();
		Transaction tx = null;
		Integer idUsuari = null;
		try {
			tx = session.beginTransaction();
			idUsuari = (Integer) session.save(usuaris);
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
		return idUsuari;
	}

	@Override
	public Usuaris getUsuarisById(int id) {
		Session session = factory.openSession();
		Transaction tx = null;
		Usuaris getUsuari = null;
		try {
			tx = session.beginTransaction();
			getUsuari = session.get(Usuaris.class, id);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			// session.get no llença excepcions, si la id no existeix retorna null
			// session.load retorna un placeholder i no llença la query fins que busques una
			// propietat; llença ObjectNotFoundException si la id no existeix
			e.printStackTrace();
		} finally {
			session.close();
		}
		return getUsuari;
	}

	@Override
	public void updateUsuaris(Usuaris usuaris) {
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.update(usuaris);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	@Override
	public void deleteUsuaris(int id) {
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.delete(id);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	@Override
	public ArrayList<Usuaris> listAllUsuaris() {
		Session session = factory.openSession();
		Transaction tx = null;
		List<Usuaris> users = new ArrayList<>();
		try {
			tx = session.beginTransaction();
			users = session.createQuery("select * from usuaris", Usuaris.class).getResultList();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return (ArrayList<Usuaris>) users;
	}

}
