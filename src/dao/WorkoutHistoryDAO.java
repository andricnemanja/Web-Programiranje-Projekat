package dao;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import beans.Coach;
import beans.Customer;
import beans.Workout;
import beans.Workout.WorkoutType;
import beans.WorkoutHistory;

public class WorkoutHistoryDAO extends DAO {

	private HashMap<Integer, WorkoutHistory> workoutHistory = new HashMap<Integer, WorkoutHistory>();
	private CustomerDAO customerDAO;
	private CoachDAO coachDAO;
	private WorkoutDAO workoutDAO;
	
	private String contextPath;

	
	public WorkoutHistoryDAO() {
		super();
	}
	
	public WorkoutHistoryDAO(String contextPath, CustomerDAO customerDAO, CoachDAO coachDAO, WorkoutDAO workoutDAO) {
		super();
		this.contextPath = contextPath;
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
		
		if(((String) customerJSONObject.get("isDeleted")).equals("true"))
			return;
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
	
	public Collection<WorkoutHistory> getCoachWorkoutHistory(String username, WorkoutType workoutType) {
		
		ArrayList<WorkoutHistory> historyForUser = new ArrayList<>();
		
		for(WorkoutHistory history : workoutHistory.values()) {
			if(history.getCoach().getUsername().equals(username) && history.getWorkout().getWorkoutType() == workoutType){
				historyForUser.add(history);
			}
		}
		return historyForUser;
	}
	
	public WorkoutHistory saveWorkout(WorkoutHistory newWorkout, boolean isDeleted) {
		JSONObject workoutHistoryJSONObject = new JSONObject();
		JSONArray workoutHistoryJSONArray = new JSONArray();

		for(WorkoutHistory workout : workoutHistory.values()) {
			workoutHistoryJSONObject.put("id",workout.getId());
			workoutHistoryJSONObject.put("customerUsername", workout.getCustomer().getUsername());
			workoutHistoryJSONObject.put("coachUserName", workout.getCoach().getUsername());
			workoutHistoryJSONObject.put("workoutID",workout.getWorkout().getId());
			workoutHistoryJSONObject.put("checkInDateTime", DateParser.makeDateString(workout.getCheckInDateTime()));
			workoutHistoryJSONObject.put("isDeleted", "false");				
			workoutHistoryJSONArray.add(workoutHistoryJSONObject);
			workoutHistoryJSONObject = new JSONObject();
		}
		workoutHistoryJSONObject.put("id",newWorkout.getId());
		workoutHistoryJSONObject.put("customerUsername", newWorkout.getCustomer().getUsername());
		workoutHistoryJSONObject.put("coachUserName", newWorkout.getCoach().getUsername());
		workoutHistoryJSONObject.put("workoutID", newWorkout.getWorkout().getId());
		workoutHistoryJSONObject.put("checkInDateTime", DateParser.makeDateString(newWorkout.getCheckInDateTime()));
		workoutHistoryJSONObject.put("isDeleted", isDeleted ? "true" : "false");				

		workoutHistoryJSONArray.add(workoutHistoryJSONObject);
		if(!isDeleted)
			workoutHistory.put(newWorkout.getId(), newWorkout);

		
		 try {
	         FileWriter file = new FileWriter(contextPath + "/workoutHistory.json");
	         file.write(workoutHistoryJSONArray.toJSONString());
	         file.close();
	      } catch (IOException e) {
	         e.printStackTrace();
	         return null;
	      }
		 
		 return newWorkout;
 
	}
	
	public void setDeletedFlag(int id) {
        JSONArray workoutHistoryList = super.load(contextPath, "workoutHistory.json");    
        for(Object obj : workoutHistoryList) {
        	String readId = (String)((JSONObject)obj).get("id");
        	if(readId.equals(String.valueOf(id))) {
        		((JSONObject)obj).put("isDeleted", "true");
        	}
        }
        
        try {
	         FileWriter file = new FileWriter(contextPath + "/workoutHistory.json");
	         file.write(workoutHistoryList.toJSONString());
	         file.close();
	      } catch (IOException e) {
	         e.printStackTrace();
	         return;
	      }
	}
	
	public WorkoutHistory deleteWorkout(int id) {
		setDeletedFlag(id);
		return workoutHistory.remove(id);
	}
	
}
