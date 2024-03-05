package main;

import java.util.HashSet;
import java.util.Set;

import dao.BrandsDAO;
import dao.OwnersDAO;
import dao.VehiclesDAO;
import managers.DAOManager;
import managers.ErrorManager;
import pojos.Brands;
import pojos.Owners;
import pojos.Vehicles;

public class MainOperations {

	public static void main(String[] args) {
		BrandsDAO bDAO = DAOManager.getBrandsDAO();
		OwnersDAO oDAO = DAOManager.getOwnersDAO();
		VehiclesDAO vDAO = DAOManager.getVehiclesDAO();

		// Add brand without vehicles
		System.out.println("==============================================");
		System.out.println("Adding new Brand...");
		Brands addingBrand = new Brands(1, "Brand1", "Country1", null);
		int resAddBrand = bDAO.addBrand(addingBrand);
		if (resAddBrand > 0) {
			System.out.println(resAddBrand + " Brand added.");
		} else {
			System.out.println(ErrorManager.getMessage(resAddBrand, "This Brand"));
		}

		// Add brand with vehicles
		System.out.println("==============================================");
		System.out.println("Adding new Brand...");
		addingBrand = new Brands(2, "Brand2", "Country2", null);
		Set<Vehicles> vs = new HashSet<>();
		vs.add(new Vehicles(1, addingBrand, new Owners(1, "Name1", "Surname1", "Phone1", "Email1", null), "Model1",
				"License1"));
		addingBrand.setVehicleses(vs);
		resAddBrand = bDAO.addBrand(addingBrand);
		if (resAddBrand > 0) {
			System.out.println(resAddBrand + " Brand added.");
		} else {
			System.out.println(ErrorManager.getMessage(resAddBrand, "This Brand"));
		}

		// Add existing brand (causes error)
		System.out.println("==============================================");
		System.out.println("Adding new Brand...");
		addingBrand = new Brands(1, "Brand1", "Country1", null);
		resAddBrand = bDAO.addBrand(addingBrand);
		if (resAddBrand > 0) {
			System.out.println(resAddBrand + " Brand added.");
		} else {
			System.out.println(ErrorManager.getMessage(resAddBrand, "This Brand"));
		}

		// Get brand by id (vehicles not included)
		System.out.println("==============================================");
		int idReadingBrand = 2;
		System.out.println("Retrieving Brand " + idReadingBrand + "...");
		Brands readingBrand = bDAO.getBrandById(idReadingBrand, false);
		if (readingBrand.getBrandId() != null) {
			System.out.println("Brand found. (Vehicles not included)");
			System.out.println(readingBrand.toString());
			if (readingBrand.getVehicleses() != null) {
				System.out.println(readingBrand.getVehicleses().toString());
			}
		} else {
			System.out.println(ErrorManager.getMessage(-1, "This Brand"));
		}

		// Get brand by id (vehicles included)
		System.out.println("==============================================");
		System.out.println("Retrieving Brand " + idReadingBrand + "...");
		readingBrand = bDAO.getBrandById(idReadingBrand, true);
		if (readingBrand.getBrandId() != null) {
			System.out.println("Brand found. (Vehicles included)");
			System.out.println(readingBrand.toString());
			if (readingBrand.getVehicleses() != null) {
				for (Vehicles v : readingBrand.getVehicleses()) {
					System.out.println(v.toString());
				}
			}
		} else {
			System.out.println(ErrorManager.getMessage(-1, "This Brand"));
		}

		// Get brand by non-existing id (causes error)
		System.out.println("==============================================");
		int errorBrand = 99;
		System.out.println("Retrieving Brand " + errorBrand + "...");
		readingBrand = bDAO.getBrandById(errorBrand, false);
		if (readingBrand.getBrandId() != null) {
			System.out.println("Brand found. (Vehicles not included)");
			System.out.println(readingBrand.toString());
			if (readingBrand.getVehicleses() != null) {
				System.out.println(readingBrand.getVehicleses().toString());
			}
		} else {
			System.out.println(ErrorManager.getMessage(-1, "This Brand"));
		}

		// Update vehicle (change owner)
		System.out.println("===============================================");
		Integer idUpdateVehicle = 1;
		System.out.println("Updating Vehicle with  Id " + idUpdateVehicle + "...");
		Vehicles updateVehicle = vDAO.getVehicleById(idUpdateVehicle);
		if (updateVehicle != null) {
			updateVehicle.setOwners(oDAO.getOwnerById(3, false));
			idUpdateVehicle = vDAO.updateVehicle(updateVehicle);
			if (idUpdateVehicle.equals(updateVehicle.getVehicleId()))
				System.out.println("Vehicle updated: " + updateVehicle.toString());
		} else {
			System.out.println(ErrorManager.getMessage(-1, "This Vehicle"));
		}

		// Delete brand
		System.out.println("==============================================");
		int idDeletingBrand = 2;
		System.out.println("Deleting Brand " + idDeletingBrand + "...");
		int resDeleteBrand = bDAO.deleteBrand(idDeletingBrand);
		if (resDeleteBrand > 0) {
			System.out.println(resDeleteBrand + " Brand deleted.");
		} else {
			System.out.println(ErrorManager.getMessage(resDeleteBrand, "This Brand"));
		}

		// Add owner
		System.out.println("===============================================");
		System.out.println("Adding new Owner...");
		Owners addOwner = new Owners(3, "Name3", "Surname3", "Phone3", "Email3", null);
		Integer resIdAddOwner = oDAO.addOwner(addOwner);
		if (resIdAddOwner > 0) {
			System.out.println("Owner with Id " + resIdAddOwner + " added.");
		} else {
			System.out.println(ErrorManager.getMessage(resIdAddOwner, "This Owner"));
		}

		// Add owner with vehicles
		System.out.println("===============================================");
		System.out.println("Adding new Owner...");
		addOwner = new Owners(4, "Name4", "Surname4", "Phone4", "Email4", null);
		Set<Vehicles> vs2 = new HashSet<>();
		vs2.add(new Vehicles(5, addingBrand, addOwner, "Model5", "License5"));
		addOwner.setVehicleses(vs2);
		resIdAddOwner = oDAO.addOwner(addOwner);
		if (resIdAddOwner > 0) {
			System.out.println("Owner with Id " + resIdAddOwner + " added.");
		} else {
			System.out.println(ErrorManager.getMessage(resIdAddOwner, "This Owner"));
		}

		// Get owner by Id
		System.out.println("===============================================");
		Integer idGetOwner = 4;
		System.out.println("Retrieving Owner with  Id " + idGetOwner + "...");
		Owners resIdGetOwner = oDAO.getOwnerById(idGetOwner, true);
		if (resIdGetOwner != null) {
			System.out.println("Owner found: " + resIdGetOwner.toString());
			if (resIdGetOwner.getVehicleses() != null) {
				for (Vehicles v : resIdGetOwner.getVehicleses()) {
					System.out.println(v.toString());
				}
			}
		} else {
			System.out.println(ErrorManager.getMessage(-1, "This Owner"));
		}

		// Get vehicle by Id
		System.out.println("===============================================");
		Integer idGetVehicle = 5;
		System.out.println("Retrieving Vehicle with  Id " + idGetVehicle + "...");
		Vehicles resIdGetVehicle = vDAO.getVehicleById(idGetVehicle);
		if (resIdGetVehicle != null) {
			System.out.println("Vehicle found: " + resIdGetVehicle.toString());
		} else {
			System.out.println(ErrorManager.getMessage(-1, String.valueOf(idGetVehicle)));
		}

		// Get vehicle by non-existing Id (causes error)
		System.out.println("===============================================");
		idGetVehicle = 99;
		System.out.println("Retrieving Vehicle with  Id " + idGetVehicle + "...");
		resIdGetVehicle = vDAO.getVehicleById(idGetVehicle);
		if (resIdGetVehicle != null) {
			System.out.println("Vehicle found: " + resIdGetVehicle.toString());
		} else {
			System.out.println(ErrorManager.getMessage(-1, "This Vehicle"));
		}

		// Delete brand
		System.out.println("==============================================");
		int idDeletingOwner = 4;
		System.out.println("Deleting Owner " + idDeletingOwner + "...");
		int resDeleteOwner = oDAO.deleteOwner(idDeletingOwner);
		if (resDeleteOwner > 0) {
			System.out.println("Owner with Id " + resDeleteOwner + " deleted.");
		} else {
			System.out.println(ErrorManager.getMessage(resDeleteOwner, "This Owner"));
		}
	}

}
