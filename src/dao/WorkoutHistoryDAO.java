package dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import beans.Coach;
import beans.Customer;
import beans.Workout;
import beans.WorkoutHistory;

public class WorkoutHistoryDAO extends DAO {

	private HashMap<Integer, WorkoutHistory> workoutHistory = new HashMap<Integer, WorkoutHistory>();
	private CustomerDAO customerDAO;
	private CoachDAO coachDAO;
	private WorkoutDAO workoutDAO;

	
	public WorkoutHistoryDAO() {
		super();
	}
	
	public WorkoutHistoryDAO(String contextPath, CustomerDAO customerDAO, CoachDAO coachDAO, WorkoutDAO workoutDAO) {
		super();
		this.customerDAO = customerDAO;
		this.coachDAO = coachDAO;
		this.workoutDAO = workoutDAO;
		loadWorkoutHistory(contextPath);
	}
	
	public Collection<WorkoutHistory> getAll() {
		return workoutHistory.values();
	}
	
	
	private void loadWorkoutHistory(String contextPath) {
		
        JSONArray workoutHistoryList = super.load(contextPath, "workoutHistory.json");    
        for(Object obj : workoutHistoryList) {
        	parseJSONObject((JSONObject)obj);
        }
	}
	
	@Override
	protected void parseJSONObject(JSONObject customerJSONObject) {
		
		int id = Integer.valueOf((String) customerJSONObject.get("id"));
		Customer customer = customerDAO.getCustomer((String) customerJSONObject.get("customerUsername"));
		Coach coach = coachDAO.getCoach((String) customerJSONObject.get("coachUserName"));
		int workoutID = Integer.valueOf((String) customerJSONObject.get("workoutID"));
		Workout workout = workoutDAO.getWorkout(workoutID);
		Date checkInDateTime = DateParser.parseDate((String) customerJSONObject.get("checkInDateTime"));
	
		WorkoutHistory newWorkoutHistory = new WorkoutHistory(id, checkInDateTime, workout, customer, coach);
		workoutHistory.put(id, newWorkoutHistory);
	}
	
	public Collection<WorkoutHistory> getWorkoutHistoryForUser(String username) {
		
		ArrayList<WorkoutHistory> historyForUser = new ArrayList<>();
		
		for(WorkoutHistory history : workoutHistory.values()) {
			if(history.getCustomer().getUsername().equals(username) || history.getCoach().getUsername().equals(username)){
				historyForUser.add(history);
			}
		}
		return historyForUser;
	}
}
