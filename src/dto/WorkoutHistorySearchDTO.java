package dto;

import beans.SportFacility.FacilityType;
import beans.Workout.WorkoutType;

public class WorkoutHistorySearchDTO {
	private String facilityName;
	private double minPrice;
	private double maxPrice;
	private boolean withoutAdditionalPayment;
	private FacilityType facilityType;
	private WorkoutType workoutType;
	private String sortingStrategy;
	
	public WorkoutHistorySearchDTO() {
		// TODO Auto-generated constructor stub
	}

	public String getFacilityName() {
		return facilityName;
	}

	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
	}

	public double getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(double minPrice) {
		this.minPrice = minPrice;
	}

	public double getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(double maxPrice) {
		this.maxPrice = maxPrice;
	}

	public boolean isWithoutAdditionalPayment() {
		return withoutAdditionalPayment;
	}

	public void setWithoutAdditionalPayment(boolean withoutAdditionalPayment) {
		this.withoutAdditionalPayment = withoutAdditionalPayment;
	}

	public FacilityType getFacilityType() {
		return facilityType;
	}

	public void setFacilityType(FacilityType facilityType) {
		this.facilityType = facilityType;
	}

	public WorkoutType getWorkoutType() {
		return workoutType;
	}

	public void setWorkoutType(WorkoutType workoutType) {
		this.workoutType = workoutType;
	}

	public String getSortingStrategy() {
		return sortingStrategy;
	}

	public void setSortingStrategy(String sortingStrategy) {
		this.sortingStrategy = sortingStrategy;
	}


	
	
	
}
