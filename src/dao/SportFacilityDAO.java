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
		FacilityType facilityType = FacilityType.valueOf((String) customerJSONObject.get("facilityType"));
		double longtitude = (Double) customerJSONObject.get("longitude");
		double latitude = (Double) customerJSONObject.get("latitude");	
		String streetName = (String) customerJSONObject.get("street");
		String streetNumber = (String) customerJSONObject.get("streetNumber");
		String postCode = (String) customerJSONObject.get("postCode");
		Double averageRating = (Double) customerJSONObject.get("averageRating");	
		
		Location newLocation = new Location(longtitude, latitude, streetName, streetNumber, postCode);
		 
		
		SportFacility newFacility = new SportFacility(name, facilityType, newLocation, averageRating);

		
		facilities.put(name, newFacility);


	}


}
