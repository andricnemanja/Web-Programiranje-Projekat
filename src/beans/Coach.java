package beans;

import java.util.ArrayList;
import java.util.Date;

public class Coach extends User {
	
	private ArrayList<WorkoutHistory> workoutHistory;

	public Coach() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Coach(String firstName, String lastName, String email, String username, String password, Gender gender,
			Date dateOfBirth, UserType userType) {
		super(firstName, lastName, email, username, password, gender, dateOfBirth, userType);
		// TODO Auto-generated constructor stub
	}

	public ArrayList<WorkoutHistory> getWorkoutHistory() {
		return workoutHistory;
	}

	public void setWorkoutHistory(ArrayList<WorkoutHistory> workoutHistory) {
		this.workoutHistory = workoutHistory;
	}
	
	
	
	
}
