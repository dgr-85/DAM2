package dao;

import com.mongodb.client.result.InsertOneResult;

import models.Company;

public interface CompaniesDAO {
	public InsertOneResult addCompany(Company company);

	public Company getCompanyByName(String name);

	public Company updateCompany(Company company);

	public void deleteCompany(String name);
}
