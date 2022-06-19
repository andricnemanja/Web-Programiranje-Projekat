package dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import beans.Location;
import beans.SportFacility;
import beans.SportFacility.FacilityType;

public class SportFacilityDAO {


	private HashMap<String, SportFacility> facilities = new HashMap<String, SportFacility>();
	private String contextPath;
	
	public SportFacilityDAO() {
		
	}
	
	public SportFacilityDAO(String contextPath) {
		loadSportFacility(contextPath);
		this.contextPath = contextPath;
	}
	
	public Collection<SportFacility> getAll() {
		return facilities.values();
	}
	
	
	private void loadSportFacility(String contextPath) {
        JSONParser jsonParser = new JSONParser();
        
        try (FileReader reader = new FileReader(contextPath + "/facilities.json"))
        {
            //Read JSON file
 
        	Object obj = jsonParser.parse(reader);
            JSONArray facilityList = (JSONArray) obj;
             
            
            facilityList.forEach( emp -> parseJSONObject( (JSONObject) emp ) );
 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
			
	}
	
	private void parseJSONObject(JSONObject customerJSONObject) {
		String name = (String) customerJSONObject.get("name");
		FacilityType facilityType = (FacilityType) customerJSONObject.get("facilityType");
		Location location = (Location) customerJSONObject.get("location");	
		Double averageRating = (Double) customerJSONObject.get("averageRating");	
		 
		
		SportFacility newFacility = new SportFacility(name,facilityType,location,averageRating);
		//provera da li moze put ime pa onda novi objekat
		//i da li nam treba ovde put ako mi samo bukvalno iscitavamo iz fajla??
		
		facilities.put(name, newFacility);


	}


}
