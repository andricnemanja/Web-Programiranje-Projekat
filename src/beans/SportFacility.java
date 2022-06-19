package beans;

public class SportFacility {
	
	private String name;
	private FacilityType facilityType;
	private Location location;
	private double averageRating;
	private String imageName;
	//radno vreme
	
	
	
	public enum FacilityType{
		GYM,
		POOL,
		SPORTS_CENTER,
		DANCE_STUDIO,
		SAUNA
	}
	
	public enum FacilityStatus{
		OPEN,
		CLOSED
	}

	public SportFacility() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SportFacility(String name, FacilityType facilityType, Location location, double averageRating, String imageName) {
		super();
		this.name = name;
		this.facilityType = facilityType;
		this.location = location;
		this.averageRating = averageRating;
		this.imageName = imageName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public FacilityType getFacilityType() {
		return facilityType;
	}

	public void setFacilityType(FacilityType facilityType) {
		this.facilityType = facilityType;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public double getAverageRating() {
		return averageRating;
	}

	public void setAverageRating(double averageRating) {
		this.averageRating = averageRating;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	
	
}
