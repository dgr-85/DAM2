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
			// session.load retorna un placeholder i no llença la query fins que es busca
			// una propietat; llença ObjectNotFoundException si la id no existeix
			e.printStackTrace();
		} finally {
			session.close();
		}
		return getUsuari;
	}

	@Override
	public Integer updateUsuaris(Usuaris usuaris) {
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.update(usuaris); // a Hibernate, update és void
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
		return usuaris.getUserid();
	}

	@Override
	public Integer deleteUsuaris(int id) {
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Usuaris doomedUsuari = getUsuarisById(id);
			// delete amb una id que no existeix només esborra l'objecte de la caché, no
			// llença excepcions
			// l'única forma de provocar-ne és que tot l'objecte sigui null (getById amb id
			// inexistent -> null)
			session.delete(doomedUsuari); // a Hibernate, delete és void
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
	public ArrayList<Usuaris> listAllUsuaris() {
		Session session = factory.openSession();
		Transaction tx = null;
		List<Usuaris> users = new ArrayList<>();
		try {
			tx = session.beginTransaction();
			// a Hibernate, 'select' només s'escriu quan es volen certes columnes; no hi ha
			// 'select *'
			users = session.createQuery("from Usuaris", Usuaris.class).getResultList();
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
