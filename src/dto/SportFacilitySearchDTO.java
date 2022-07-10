package dto;

import beans.SportFacility.FacilityType;

public class SportFacilitySearchDTO {
	private String name;
	private String city;
	private double rating;
	private FacilityType facilityType;
	private String sortingStrategy;
	
	public SportFacilitySearchDTO() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public FacilityType getFacilityType() {
		return facilityType;
	}

	public void setFacilityType(FacilityType facilityType) {
		this.facilityType = facilityType;
	}

	public String getSortingStrategy() {
		return sortingStrategy;
	}

	public void setSortingStrategy(String sortingStrategy) {
		this.sortingStrategy = sortingStrategy;
	}
	
	
	
}
