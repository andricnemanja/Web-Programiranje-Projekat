package dto;

import java.util.Date;

public class WorkoutHistoryDTO {

	private Date checkInDateTime;
	private int workoutID;
	
	public WorkoutHistoryDTO() {
	}

	public WorkoutHistoryDTO(Date checkInDateTime, int workoutID) {
		super();
		this.checkInDateTime = checkInDateTime;
		this.workoutID = workoutID;
	}

	public Date getCheckInDateTime() {
		return checkInDateTime;
	}

	public void setCheckInDateTime(Date checkInDateTime) {
		this.checkInDateTime = checkInDateTime;
	}

	public int getWorkoutID() {
		return workoutID;
	}

	public void setWorkoutID(int workoutID) {
		this.workoutID = workoutID;
	}
	
}
