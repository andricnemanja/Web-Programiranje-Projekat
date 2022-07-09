package beans;

import java.util.Date;

public class WorkoutHistory {

	private int id;
	private Date checkInDateTime;
	private Workout workout;
	private Customer customer;
	private Coach coach;

	public WorkoutHistory() {
	}
	

	public WorkoutHistory(int id, Date checkInDateTime, Workout workout, Customer customer, Coach coach) {
		super();
		this.id = id;
		this.checkInDateTime = checkInDateTime;
		this.workout = workout;
		this.customer = customer;
		this.coach = coach;
	}

	public WorkoutHistory(Date checkInDateTime, Workout workout, Customer customer, Coach coach) {
		super();
		this.id = 0;
		this.checkInDateTime = checkInDateTime;
		this.workout = workout;
		this.customer = customer;
		this.coach = coach;
	}

	public Date getCheckInDateTime() {
		return checkInDateTime;
	}


	public void setCheckInDateTime(Date checkInDateTime) {
		this.checkInDateTime = checkInDateTime;
	}

	public Workout getWorkout() {
		return workout;
	}

	public void setWorkout(Workout workout) {
		this.workout = workout;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Coach getCoach() {
		return coach;
	}

	public void setCoach(Coach coach) {
		this.coach = coach;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}
	
}
