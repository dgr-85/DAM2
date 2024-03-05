package dao;

import java.util.List;

import pojos.Vehicles;

public interface VehiclesDAO {

	public Integer addVehicle(Vehicles vehicle);

	public Vehicles getVehicleById(Integer id);

	public Integer updateVehicle(Vehicles vehicle);

	public Integer deleteVehicle(Integer id);

	public List<Vehicles> listallVehicles();
}
