package models;

import org.bson.codecs.pojo.annotations.BsonProperty;

public class Office {
	private String description;
	private String address1;
	private String address2;
	@BsonProperty(value = "zip_code")
	private String zipCode;
	private String city;
	@BsonProperty(value = "state_code")
	private String stateCode;
	@BsonProperty(value = "country_code")
	private String countryCode;
	private Double latitude;
	private Double longitude;

	public Office() {
		super();
	}

	public Office(String description, String address1, String address2, String zipCode, String city, String stateCode,
			String countryCode, Double latitude, Double longitude) {
		super();
		this.description = description;
		this.address1 = address1;
		this.address2 = address2;
		this.zipCode = zipCode;
		this.city = city;
		this.stateCode = stateCode;
		this.countryCode = countryCode;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return "Office [description=" + description + ", address1=" + address1 + ", address2=" + address2 + ", zipCode="
				+ zipCode + ", city=" + city + ", stateCode=" + stateCode + ", countryCode=" + countryCode
				+ ", latitude=" + latitude + ", longitude=" + longitude + "]";
	}
}
