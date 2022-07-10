package dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import beans.Location;
import beans.SportFacility;
import beans.SportFacility.FacilityType;
import dto.SportFacilitySearchDTO;
import sort.SportFacilitySort;

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
		String city = (String) customerJSONObject.get("city");
		Double averageRating = (Double) customerJSONObject.get("averageRating");	
		String imageName = (String) customerJSONObject.get("imageName");

		
		Location newLocation = new Location(longtitude, latitude, streetName, streetNumber, postCode, city);
		 
		
		SportFacility newFacility = new SportFacility(name, facilityType, newLocation, averageRating, imageName);

		
		facilities.put(name, newFacility);


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
	
	public SportFacility getSportFacility(String name) {
		return facilities.get(name);
	}
	
	public Collection<SportFacility> search(SportFacilitySearchDTO dto) {
		ArrayList<SportFacility> list = new ArrayList<SportFacility>();
		
		for(SportFacility sportFacility : facilities.values()) {
			if(dto.getRating() != -1 && sportFacility.getAverageRating() < dto.getRating()) 
				continue;
			if(!dto.getName().equals("") && !sportFacility.getName().toLowerCase().contains(dto.getName().toLowerCase()))
				continue;
			if(!dto.getCity().equals("") && !sportFacility.getLocation().getCity().toLowerCase().contains(dto.getCity().toLowerCase()))
				continue;
			if(dto.getFacilityType() != FacilityType.NULL && !(sportFacility.getFacilityType() == dto.getFacilityType()))
				continue;
			
			list.add(sportFacility);
			
		}
		return SportFacilitySort.sort(dto.getSortingStrategy(), list);
	}
	
	


}
