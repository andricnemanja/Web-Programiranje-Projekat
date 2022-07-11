package dao;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import beans.Coach;
import beans.Comment;
import beans.Customer;
import beans.SportFacility;
import beans.Workout;
import beans.Workout.WorkoutType;

public class WorkoutDAO extends DAO {

	private HashMap<Integer, Workout> workouts = new HashMap<Integer, Workout>();
	private CoachDAO coachDAO;
	private SportFacilityDAO sportFacilityDAO;
	
	public WorkoutDAO() {
		super();
	}
	
	public WorkoutDAO(String contextPath, CoachDAO coachDAO, SportFacilityDAO sportFacilityDAO) {
		super();
		this.coachDAO = coachDAO;
		this.sportFacilityDAO = sportFacilityDAO;
		loadWorkouts(contextPath);
	}
	
	public Collection<Workout> getAll() {
		return workouts.values();
	}
	
	
	private void loadWorkouts(String contextPath) {
		
        JSONArray workoutsList = super.load(contextPath, "workout.json");    
        for(Object obj : workoutsList) {
        	parseJSONObject((JSONObject)obj);
        }
	}
	
	@Override
	protected void parseJSONObject(JSONObject customerJSONObject) {
		
		
		int id = Integer.valueOf((String) customerJSONObject.get("id"));
		String name = (String) customerJSONObject.get("name");
		WorkoutType workoutType = WorkoutType.valueOf((String)customerJSONObject.get("workoutType"));
		SportFacility sportFacility = sportFacilityDAO.getSportFacility((String) customerJSONObject.get("sportFacilityName"));
		double duration = (Double) customerJSONObject.get("duration");
		Coach coach = coachDAO.getCoach((String) customerJSONObject.get("coachUsername"));
		String description = (String) customerJSONObject.get("description");
		String imageName = (String) customerJSONObject.get("imageName");
		double additionalPayment = (Double) customerJSONObject.get("additionalPayment");


		Workout newWorkout = new Workout(id, name, workoutType, sportFacility, duration, coach, description, imageName, additionalPayment);

		workouts.put(id, newWorkout);
	}
	

	public Workout getWorkout(int id) {
		return workouts.get(id);
	}
	
	public Collection<Workout> getWorkoutsForFacility(String facilityName){
		ArrayList<Workout> workoutsForFacility = new ArrayList<>();
		for(Workout workout : workouts.values()) {
			if(workout.getSportFacility().getName().equals(facilityName)) {
				workoutsForFacility.add(workout);
			}
		}
		return workoutsForFacility;
	}


}
