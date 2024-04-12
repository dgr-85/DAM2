package main;

import java.util.Arrays;

import com.mongodb.client.result.InsertOneResult;

import dao.CompaniesDAO;
import managers.ConnectionManager;
import managers.DAOManager;
import models.Company;
import models.Competition;
import models.Competitor;
import models.Person;
import models.Product;
import models.Relationship;

public class MainOperations {

	public static void main(String[] args) {
		CompaniesDAO cDAO = DAOManager.getCompaniesDAO();

		// Add new Company
		System.out.println("Adding new Company...");
		Company newCompany = new Company();
		newCompany.setName("Mongo6");
		newCompany.setHomepageUrl("https://www.m6.com");
		newCompany.setTwitterUsername("Mongo_Six");
		newCompany.setCategoryCode("pain_and_suffering");
		newCompany.setPhoneNumber("666-666-666");
		newCompany.setTotalMoneyRaised("None");
		newCompany.setFoundedDay(31);
		newCompany.setFoundedMonth(2);

		Product newProduct = new Product();
		newProduct.setName("Mongo Product 6");
		newProduct.setPermalink("mongo-product-6");
		newCompany.setProducts(Arrays.asList(newProduct));

		Relationship newRelationship = new Relationship();
		newRelationship.setIsPast(false);
		newRelationship.setTitle("CEO");

		Person newPerson = new Person();
		newPerson.setFirstName("Christine");
		newPerson.setLastName("Now");
		newPerson.setPermalink("christine-now");
		newRelationship.setPerson(newPerson);
		newCompany.setRelationships(Arrays.asList(newRelationship));

		Competition newCompetition = new Competition();

		Competitor newCompetitor = new Competitor();
		newCompetitor.setName("Albalate SL");
		newCompetitor.setPermalink("albalate-sl");

		Competitor newCompetitor2 = new Competitor();
		newCompetitor2.setName("Sobel Networks");
		newCompetitor2.setPermalink("sobel-networks");
		newCompetition.setCompetitors(Arrays.asList(newCompetitor, newCompetitor2));
		newCompany.setCompetitions(Arrays.asList(newCompetition));

		InsertOneResult addResult = cDAO.addCompany(newCompany);
		if (addResult != null) {
			System.out.println("New Company added succesfully.");
		} else {
			System.out.println("Error adding Company.");
		}

		// Get existing Company by name
		String getByExistingName = "Stack Exchange";
		System.out.println("Retrieving Company with name " + getByExistingName + "...");
		Company existingCompany = cDAO.getCompanyByName(getByExistingName);
		if (existingCompany != null) {
			System.out.println("Company found: " + existingCompany.toString());
		} else {
			System.out.println("Company not found.");
		}

		// Get added Company by name
		String getByName = "Mongo6";
		System.out.println("Retrieving Company with name " + getByName + "...");
		Company retrievedCompany = cDAO.getCompanyByName(getByName);
		if (retrievedCompany != null) {
			System.out.println("Company found: " + retrievedCompany.toString());
		} else {
			System.out.println("Company not found.");
		}

		// Get non-existing Company by name (causes error)
		String getByWrongName = "Banano6";
		System.out.println("Retrieving Company with name " + getByWrongName + "...");
		Company retrievedWrongCompany = cDAO.getCompanyByName(getByWrongName);
		if (retrievedWrongCompany != null) {
			System.out.println("Company found: " + retrievedWrongCompany.toString());
		} else {
			System.out.println("Company not found.");
		}

		// Update Company
		System.out.println("Updating overview of Company " + retrievedCompany.getName() + "...");
		System.out.println("Current overview: " + retrievedCompany.getOverview());
		retrievedCompany.setOverview("We must find out if the updateCompany method works correctly.");
		retrievedCompany = cDAO.updateCompany(retrievedCompany);
		if (retrievedCompany != null) {
			System.out.println("Company updated. New overview: " + retrievedCompany.getOverview());
			System.out.println("Full Company data, for reference: " + retrievedCompany.toString());
		} else {
			System.out.println("Error updating Company. (maybe it doesn't exist?)");
		}

		// Update non-existing Company (causes error)
		System.out.println("Updating overview of Company " + getByWrongName + "...");
		retrievedWrongCompany = cDAO.updateCompany(retrievedWrongCompany);
		if (retrievedWrongCompany != null) {
			System.out.println("Company updated. New overview: " + retrievedWrongCompany.getOverview());
		} else {
			System.out.println("Error updating Company. (maybe it doesn't exist?)");
		}

		// Delete Company by name
		System.out.println("Deleting Company " + retrievedCompany.getName() + "...");
		cDAO.deleteCompany(getByName);

		// Delete non-existing Company by name (nothing happens)
		System.out.println("Deleting Company " + getByWrongName + "...");
		cDAO.deleteCompany(getByWrongName);

		ConnectionManager.closeConnection();
	}

}
