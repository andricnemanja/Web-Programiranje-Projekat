package dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public abstract class DAO {
	
	public JSONArray load(String contextPath, String JSONFileName) {
        JSONParser jsonParser = new JSONParser();
        
        try (FileReader reader = new FileReader(contextPath + "/" + JSONFileName))
        {
        	Object obj = jsonParser.parse(reader);
            JSONArray jsonArray = (JSONArray) obj;  
            
            return jsonArray;
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
			e.printStackTrace();
		} 
        return null;		
	}
	
	protected abstract void parseJSONObject(JSONObject customerJSONObject);
}
