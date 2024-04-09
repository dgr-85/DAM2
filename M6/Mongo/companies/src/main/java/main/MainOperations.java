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

		// Get Company by name
		String getByName = "Mongo6";
		System.out.println("Retrieving Company with name " + getByName + "...");
		Company retrievedCompany = cDAO.getCompanyByName(getByName);
		if (retrievedCompany != null) {
			System.out.println("Company found: " + retrievedCompany.toString());
		} else {
			System.out.println("Company not found.");
		}

		// Update Company

		ConnectionManager.closeConnection();
	}

}
