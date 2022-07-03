package dao;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import beans.Coach;
import beans.User.Gender;
import beans.User.UserType;

public class CoachDAO extends DAO {

	private HashMap<String, Coach> coaches = new HashMap<String, Coach>();
	
	public CoachDAO() {
		super();
	}
	
	public CoachDAO(String contextPath) {
		super();
		loadCoaches(contextPath);
	}
	
	public Collection<Coach> getAll() {
		return coaches.values();
	}
	
	
	private void loadCoaches(String contextPath) {
		
        JSONArray coachesList = super.load(contextPath, "coaches.json");    
        for(Object obj : coachesList) {
        	parseJSONObject((JSONObject)obj);
        }
	}
	
	@Override
	protected void parseJSONObject(JSONObject customerJSONObject) {
		
		
		String firstName = (String) customerJSONObject.get("firstName");
		String lastName = (String) customerJSONObject.get("lastName");
		String email = (String) customerJSONObject.get("email");	
		String username = (String) customerJSONObject.get("username");	
		String password = (String) customerJSONObject.get("password");	
		Gender gender = Gender.valueOf((String) customerJSONObject.get("gender"));
		String dateOfBirthString = (String) customerJSONObject.get("dateOfBirth");
		
		Date date = DateParser.parseDate(dateOfBirthString);
	

		Coach coach = new Coach(firstName, lastName, email, username, password, gender, date, UserType.COACH);
		coaches.put(username, coach);

	}
	
	public Coach getCoach(String username) {
		return coaches.get(username);
	}



}
