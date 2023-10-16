package tutorial;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;

public class ModificacionsJDBC {
	
	public String demanarDades(String enunciat) {
		System.out.println(enunciat);
		return Main.sc.nextLine();
	}
	
	public Date convertirData() {
		int any=Integer.parseInt(demanarDades("Introdueix el número de l'any. (4 xifres"));
		int mes=Integer.parseInt(demanarDades("Introdueix el número del mes. (gener = 01 fins desembre = 12)"));
		int dia=Integer.parseInt(demanarDades("Introdueix el número del dia. (del 01 al 31)"));
		
		String data=Integer.toString(any)+"-"+Integer.toString(mes)+"-"+Integer.toString(dia);
		
		return java.sql.Date.valueOf(data);
	}
	
	public void afegirEmpleat(Connection connexio) {
		String sql="insert into empleados values (?,?,?,?,?,?,?,?);";
		
		try {
			System.out.println("Ara afegirem un empleat nou a la base de dades.");
			
			PreparedStatement sqlPreparat=connexio.prepareStatement(sql);
			
			sqlPreparat.setInt(1, Integer.parseInt(demanarDades("Introdueix-ne el codi d'empleat.")));
			sqlPreparat.setString(2, demanarDades("Introdueix-ne el cognom."));
			sqlPreparat.setString(3, demanarDades("Introdueix-ne l'ofici."));
			sqlPreparat.setInt(4, Integer.parseInt(demanarDades("Introdueix el codi d'empleat del seu cap.")));
			System.out.println("Ara s'introdueix pas per pas la data d'incorporació de l'empleat.");
			sqlPreparat.setDate(5, convertirData());
			sqlPreparat.setFloat(6, Float.parseFloat(demanarDades("Introdueix-ne el salari.")));
			sqlPreparat.setFloat(7, Float.parseFloat(demanarDades("Introdueix-ne la comissió, si en té.")));
			sqlPreparat.setInt(7, Integer.parseInt(demanarDades("Introdueix el codi del seu departament.")));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
