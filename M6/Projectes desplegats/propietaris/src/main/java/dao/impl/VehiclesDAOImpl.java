package dao.impl;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import dao.VehiclesDAO;
import managers.ConnectionManagerHibernate;
import pojos.Brands;
import pojos.Owners;
import pojos.Vehicles;

public class VehiclesDAOImpl implements VehiclesDAO {

	SessionFactory factory = ConnectionManagerHibernate.getSessionFactory();

	@Override
	public Integer addVehicle(Vehicles vehicle) {
		Session session = factory.openSession();
		Transaction tx = null;
		Integer idVehicle = null;
		try {
			tx = session.beginTransaction();
			if (session.get(Owners.class, vehicle.getOwners().getOwnerId()) == null) {
				session.save(vehicle.getOwners());
			}
			if (session.get(Brands.class, vehicle.getBrands().getBrandId()) == null) {
				session.save(vehicle.getBrands());
			}
			idVehicle = (Integer) session.save(vehicle);
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
		return idVehicle;
	}

	@Override
	public Vehicles getVehicleById(Integer id) {
		Session session = factory.openSession();
		Transaction tx = null;
		Vehicles getVehicle = null;
		try {
			tx = session.beginTransaction();
			getVehicle = session.get(Vehicles.class, id);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return getVehicle;
	}

	@Override
	public Integer updateVehicle(Vehicles vehicle) {
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			if (getVehicleById(vehicle.getVehicleId()) != null) {
				session.update(vehicle);
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
		return vehicle.getVehicleId();
	}

	@Override
	public Integer deleteVehicle(Integer id) {
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Vehicles deleteVehicle = getVehicleById(id);
			session.delete(deleteVehicle);
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
	public List<Vehicles> listallVehicles() {
		Session session = factory.openSession();
		Transaction tx = null;
		List<Vehicles> vehicles = new ArrayList<>();
		try {
			tx = session.beginTransaction();
			vehicles = session.createQuery("from Vehicles", Vehicles.class).getResultList();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return vehicles;
	}

}
