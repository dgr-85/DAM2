package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Departament;

public class DepartamentDAOImpl implements DepartamentDAO {
	
	@Override
	public int addDepartament(Departament d) {
		Boolean isConnectionOpen=false;
		
		String sql ="INSERT INTO departamentos(dept_no,dnombre,loc) VALUES (?,?,?)";
		try {
			isConnectionOpen = GestorConnexions.isConnected();
			Connection conexio = GestorConnexions.obtenirConnexio();
			PreparedStatement sentencia = conexio.prepareStatement(sql);
			sentencia.setInt(1, d.getCodiDepartament());
			sentencia.setString(2, d.getNomDepartament());
			sentencia.setString(3, d.getLlocDepartament());
			int resultat = sentencia.executeUpdate();
			return resultat;
		} catch (SQLException e) {
			if (e.getErrorCode()==1062){
				//PK repetida
				return e.getErrorCode() * -1;
			}else{
				e.printStackTrace();
				return -1;
			}
		}finally{
			if(!isConnectionOpen) {
				GestorConnexions.tancarConnexio();
			}
		}
	}

	@Override
	public Departament getDepartamentById(Integer Id, boolean ambEmpleats) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateDepartament(Departament d) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteDepartament(Departament d, boolean cascade) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Departament> listDepartaments() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Departament> listEmpleatsByDepartament() {
		// TODO Auto-generated method stub
		return null;
	}


}
