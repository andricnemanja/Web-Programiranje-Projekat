package beans;

public class Location {
	private double longitude;
	private double latitude;
	private String streetName;
	private String houseNumber;
	private String postCode;
	
	
	public Location() {
		// TODO Auto-generated constructor stub
	}


	public Location(double longitude, double latitude, String streetName, String houseNumber, String postCode) {
		super();
		this.longitude = longitude;
		this.latitude = latitude;
		this.streetName = streetName;
		this.houseNumber = houseNumber;
		this.postCode = postCode;
	}


	public double getLongitude() {
		return longitude;
	}


	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}


	public double getLatitude() {
		return latitude;
	}


	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}


	public String getStreetName() {
		return streetName;
	}


	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}


	public String getHouseNumber() {
		return houseNumber;
	}


	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}


	public String getPostCode() {
		return postCode;
	}


	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	
	
}
