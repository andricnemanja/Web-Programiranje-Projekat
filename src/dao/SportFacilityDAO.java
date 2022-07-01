package dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import beans.Customer;
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
	
	private void parseJSONObject(JSONObject facilityJSONObject) {
		
		String name = (String) facilityJSONObject.get("name");
		FacilityType facilityType = FacilityType.valueOf((String) facilityJSONObject.get("facilityType"));
		double longtitude = (Double) facilityJSONObject.get("longitude");
		double latitude = (Double) facilityJSONObject.get("latitude");	
		String streetName = (String) facilityJSONObject.get("street");
		String streetNumber = (String) facilityJSONObject.get("streetNumber");
		String postCode = (String) facilityJSONObject.get("postCode");
		String city = (String) facilityJSONObject.get("city");
		Double averageRating = (Double) facilityJSONObject.get("averageRating");	
		String imageName = (String) facilityJSONObject.get("imageName");

		
		Location newLocation = new Location(longtitude, latitude, streetName, streetNumber, postCode, city);
		 
		
		SportFacility newFacility = new SportFacility(name, facilityType, newLocation, averageRating, imageName);

		
		facilities.put(name, newFacility);


	}
	
	public SportFacility saveFacility(SportFacility newFacility) {
		JSONObject facilityJSONObject = new JSONObject();
		JSONArray facilityJSONArray = new JSONArray();

		for(SportFacility sportFacility : facilities.values()) {
			facilityJSONObject.put("name",sportFacility.getName());
			facilityJSONObject.put("facilityType", sportFacility.getFacilityType());
			facilityJSONObject.put("location", sportFacility.getLocation());
			//za dodavanje loga
			facilityJSONObject.put("imageName",sportFacility.getImageName());
			
			facilityJSONArray.add(facilityJSONObject);
			facilityJSONObject = new JSONObject();
		}
		facilityJSONObject.put("name",newFacility.getName());
		facilityJSONObject.put("facilityType", newFacility.getFacilityType());
		facilityJSONObject.put("location", newFacility.getLocation());
		//za dodavanje loga
		facilityJSONObject.put("imageName",newFacility.getImageName());

		facilityJSONArray.add(facilityJSONObject);
		facilities.put(newFacility.getName(), newFacility);

		
		 try {
	         FileWriter file = new FileWriter(contextPath + "/facilities.json");
	         file.write(facilityJSONArray.toJSONString());
	         file.close();
	      } catch (IOException e) {
	         e.printStackTrace();
	         return null;
	      }
		 
		 return newFacility;
 
	}

	public Collection<SportFacility> search(String search) {
		ArrayList<SportFacility> list = new ArrayList<SportFacility>();
		for(SportFacility sportFacility : facilities.values()) {
			if(sportFacility.getName().toLowerCase().contains(search.toLowerCase())) {
				list.add(sportFacility);
			}
		}
		return list;
	}

	public Collection<SportFacility> searchCity(String search) {
		ArrayList<SportFacility> list = new ArrayList<SportFacility>();
		for(SportFacility sportFacility : facilities.values()) {
			if(sportFacility.getLocation().getCity().equals(search)) {
				list.add(sportFacility);
			}
		}
		return list;
	}

	public Collection<SportFacility> searchType(String search) {
		ArrayList<SportFacility> list = new ArrayList<SportFacility>();
		for(SportFacility sportFacility : facilities.values()) {
			if(sportFacility.getFacilityType().toString().equals(search)) {
				list.add(sportFacility);
			}
		}
		return list;
	}

	public Collection<SportFacility> searchRating(String search) {
		ArrayList<SportFacility> list = new ArrayList<SportFacility>();
		for(SportFacility sportFacility : facilities.values()) {
			if(sportFacility.getAverageRating() >= Double.parseDouble(search)) {
				list.add(sportFacility);
			}
		}
		return list;
	}


}
