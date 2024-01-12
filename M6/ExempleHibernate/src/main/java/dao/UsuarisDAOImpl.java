package dao;

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
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out.println(e.getCause());
			// e.printStackTrace();
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
			getUsuari = (Usuaris) session.byId(String.valueOf(id));
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
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
		List<Usuaris> users = new ArrayList<Usuaris>();
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
