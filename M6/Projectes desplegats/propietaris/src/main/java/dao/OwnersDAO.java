package dao;

import java.util.List;

import pojos.Owners;

public interface OwnersDAO {

	public Integer addOwner(Owners owner);

	public Owners getOwnerById(Integer id, Boolean includeVehicles);

	public Integer updateOwner(Owners owner);

	public Integer deleteOwner(Integer id);

	public List<Owners> listAllOwners(Boolean includeVehicles);
}
