package dao.impl;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.FindOneAndReplaceOptions;
import com.mongodb.client.model.ReturnDocument;
import com.mongodb.client.result.InsertOneResult;

import dao.CompaniesDAO;
import models.Company;

public class CompaniesDAOImpl implements CompaniesDAO {

	@Override
	public InsertOneResult addCompany(Company company) {
		try {
			MongoClient mongoClient = managers.ConnectionManager.getConnection();
			MongoDatabase db = mongoClient.getDatabase("companies");
			MongoCollection<Company> companies = db.getCollection("companies", Company.class);
			return companies.insertOne(company);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Company getCompanyByName(String name) {
		try {
			MongoClient mongoClient = managers.ConnectionManager.getConnection();
			MongoDatabase db = mongoClient.getDatabase("companies");
			MongoCollection<Company> companies = db.getCollection("companies", Company.class);
			return companies.find(Filters.eq("name", name)).first();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Company updateCompany(Company company) {
		try {
			MongoClient mongoClient = managers.ConnectionManager.getConnection();
			MongoDatabase db = mongoClient.getDatabase("companies");
			MongoCollection<Company> companies = db.getCollection("companies", Company.class);
			Document filterByCompanyname = new Document("_id", company.getId());
			FindOneAndReplaceOptions returnDocAfterReplace = new FindOneAndReplaceOptions()
					.returnDocument(ReturnDocument.AFTER);
			return companies.findOneAndReplace(filterByCompanyname, company, returnDocAfterReplace);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public void deleteCompany(String name) {
		try {
			MongoClient mongoClient = managers.ConnectionManager.getConnection();
			MongoDatabase db = mongoClient.getDatabase("companies");
			MongoCollection<Company> companies = db.getCollection("companies", Company.class);
			Document filterByCompanyName = new Document("name", name);
			System.out.println(companies.deleteOne(filterByCompanyName));
		} catch (Exception e) {
			System.out.println("Error deleting Company " + name + ".");
		}
	}

}
