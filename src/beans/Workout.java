package beans;

public class Workout {
	private String name;
	private WorkoutType workoutType;
	private SportFacility sportFacility;
	private double duration;
	private Coach coach;
	private String description;
	// image
	
	public enum WorkoutType{
		GROUP,
		PERSONAL,
		GYM
	}
	public Workout(String name, WorkoutType workoutType, SportFacility sportFacility, double duration, Coach coach,
			String description) {
		super();
		this.name = name;
		this.workoutType = workoutType;
		this.sportFacility = sportFacility;
		this.duration = duration;
		this.coach = coach;
		this.description = description;
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
	
	
	
	
}