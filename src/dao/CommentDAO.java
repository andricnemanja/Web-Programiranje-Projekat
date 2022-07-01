package dao;

import java.io.FileNotFoundException;
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

import beans.Comment;
import beans.Customer;
import beans.Location;
import beans.SportFacility;
import beans.SportFacility.FacilityType;

public class CommentDAO extends DAO {

	private HashMap<Integer, Comment> comments = new HashMap<Integer, Comment>();
	private CustomerDAO customerDAO;
	private SportFacilityDAO sportFacilityDAO;

	
	public CommentDAO() {
		super();
	}
	
	public CommentDAO(String contextPath, CustomerDAO customerDAO, SportFacilityDAO sportFacilityDAO) {
		super();
		this.customerDAO = customerDAO;
		this.sportFacilityDAO = sportFacilityDAO;
		loadComments(contextPath);
	}
	
	public Collection<Comment> getAll() {
		return comments.values();
	}
	
	
	private void loadComments(String contextPath) {
		
        JSONArray commentsList = super.load(contextPath, "comments.json");    
        for(Object obj : commentsList) {
        	parseJSONObject((JSONObject)obj);
        }
	}
	
	@Override
	protected void parseJSONObject(JSONObject customerJSONObject) {
		
		
		int id = Integer.valueOf((String) customerJSONObject.get("id"));
		String commentText = (String) customerJSONObject.get("comment");
		Customer customer = customerDAO.getCustomer((String) customerJSONObject.get("customerUsername"));
		SportFacility sportFacility = sportFacilityDAO.getSportFacility((String) customerJSONObject.get("sportFacilityName"));
		int rating = Integer.valueOf((String) customerJSONObject.get("rating"));
	

		Comment newComment = new Comment(id, customer, sportFacility, commentText, rating);
		comments.put(id, newComment);

	}
	
	public ArrayList<Comment> getCommentsForSportFacility(String facilityName) {
		ArrayList<Comment> commentsForFacility = new ArrayList<>();
		for(Comment comment : comments.values()) {
			if(comment.getSportFacility().getName().equals(facilityName))
				commentsForFacility.add(comment);
		}
		return commentsForFacility;
	}




}
