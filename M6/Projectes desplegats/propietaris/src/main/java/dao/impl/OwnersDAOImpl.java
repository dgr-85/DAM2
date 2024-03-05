package dao.impl;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import dao.OwnersDAO;
import managers.ConnectionManagerHibernate;
import pojos.Owners;

public class OwnersDAOImpl implements OwnersDAO {

	SessionFactory factory = ConnectionManagerHibernate.getSessionFactory();

	@Override
	public Integer addOwner(Owners owner) {
		Session session = factory.openSession();
		Transaction tx = null;
		Integer idOwner = null;
		try {
			tx = session.beginTransaction();
			idOwner = (Integer) session.save(owner);
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
		return idOwner;
	}

	@Override
	public Owners getOwnerById(Integer id, Boolean includeVehicles) {
		Session session = factory.openSession();
		Transaction tx = null;
		Owners getOwner = null;
		try {
			tx = session.beginTransaction();
			getOwner = session.get(Owners.class, id);
			if (getOwner != null && includeVehicles) {
				Hibernate.initialize(getOwner.getVehicleses());
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
		return getOwner;
	}

	@Override
	public Integer updateOwner(Owners owner) {
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			if (getOwnerById(owner.getOwnerId(), false) != null) {
				session.update(owner);
			} else {
				return -1;
			}
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
		return owner.getOwnerId();
	}

	@Override
	public Integer deleteOwner(Integer id) {
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Owners deleteOwner = getOwnerById(id, false);
			session.delete(deleteOwner);
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
	public List<Owners> listAllOwners(Boolean includeVehicles) {
		Session session = factory.openSession();
		Transaction tx = null;
		List<Owners> owners = new ArrayList<>();
		try {
			tx = session.beginTransaction();
			owners = session.createQuery("from Owners", Owners.class).getResultList();
			if (includeVehicles) {
				for (Owners o : owners) {
					Hibernate.initialize(o.getVehicleses());
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
		return owners;
	}

}
