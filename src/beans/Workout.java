package beans;

public class Workout {
	private int id;
	private String name;
	private WorkoutType workoutType;
	private SportFacility sportFacility;
	private double duration;
	private Coach coach;
	private String description;
	private String imageName;
	
	public enum WorkoutType{
		GROUP,
		PERSONAL,
		GYM
	}
	
	public Workout(int id, String name, WorkoutType workoutType, SportFacility sportFacility, double duration,
			Coach coach, String description, String imageName) {
		super();
		this.id = id;
		this.name = name;
		this.workoutType = workoutType;
		this.sportFacility = sportFacility;
		this.duration = duration;
		this.coach = coach;
		this.description = description;
		this.imageName = imageName;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public WorkoutType getWorkoutType() {
		return workoutType;
	}
	public void setWorkoutType(WorkoutType workoutType) {
		this.workoutType = workoutType;
	}
	public SportFacility getSportFacility() {
		return sportFacility;
	}
	public void setSportFacility(SportFacility sportFacility) {
		this.sportFacility = sportFacility;
	}
	public double getDuration() {
		return duration;
	}
	public void setDuration(double duration) {
		this.duration = duration;
	}
	public Coach getCoach() {
		return coach;
	}
	public void setCoach(Coach coach) {
		this.coach = coach;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	
	
	
	
}
