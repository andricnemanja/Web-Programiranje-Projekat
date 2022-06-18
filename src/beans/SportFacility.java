package beans;

public class SportFacility {
	
	private String name;
	private FacilityType facilityType;
	private Location location;
	private double averageRating;
	//radno vreme
	//slika - logo
	
	
	
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
}
