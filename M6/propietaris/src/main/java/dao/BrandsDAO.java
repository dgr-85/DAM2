package dao;

import java.util.List;

import pojos.Brands;

public interface BrandsDAO {

	public Integer addBrand(Brands brand);

	public Brands getBrandById(Integer id, Boolean includeVehicles);

	public Integer updateBrand(Brands brand);

	public Integer deleteBrand(Integer id);

	public List<Brands> listAllBrands(Boolean includeVehicles);
}
