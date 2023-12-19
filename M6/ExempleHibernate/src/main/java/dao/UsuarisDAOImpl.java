package dao;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import managers.SessionFactoryUtil;
import pojos.Usuaris;

public class UsuarisDAOImpl implements UsuarisDAO {

	SessionFactory factory = SessionFactoryUtil.getSessionFactory();

	@Override
	public Integer addUsuari(Usuaris usuaris) {
		Session session = factory.openSession();
		return null;
	}

	@Override
	public Usuaris getUsuarisById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer updateUsuaris(Usuaris usuaris) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer deleteUsuaris(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Usuaris> listAllUsuaris() {
		// TODO Auto-generated method stub
		return null;
	}

}
