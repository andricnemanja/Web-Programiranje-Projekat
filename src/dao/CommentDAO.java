package dao;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import beans.Comment;
import beans.Customer;
import beans.SportFacility;
import dto.SaveCommentDTO;

public class CommentDAO extends DAO {

	private HashMap<Integer, Comment> comments = new HashMap<Integer, Comment>();
	private CustomerDAO customerDAO;
	private SportFacilityDAO sportFacilityDAO;
	private String contextPath;
	private int maxID = 100;
	
	public CommentDAO() {
		super();
	}
	
	public CommentDAO(String contextPath, CustomerDAO customerDAO, SportFacilityDAO sportFacilityDAO) {
		super();
		this.customerDAO = customerDAO;
		this.sportFacilityDAO = sportFacilityDAO;
		this.contextPath = contextPath;
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
		
		if(id > maxID) maxID = id;
	

		Comment newComment = new Comment(id, customer, sportFacility, commentText, rating);
		comments.put(id, newComment);

	}
	
	
	private Comment saveComment(Comment newComment) {
		JSONObject customerJSONObject = new JSONObject();
		JSONArray customerJSONArray = new JSONArray();

		for(Comment comment : comments.values()) {
			customerJSONObject.put("id",comment.getId());
			customerJSONObject.put("customerUsername", comment.getCustomer().getUsername());
			customerJSONObject.put("sportFacilityName", comment.getSportFacility().getName());
			customerJSONObject.put("comment",comment.getCommentText());
			customerJSONObject.put("rating", comment.getRating());
			customerJSONObject.put("isApproved", comment.isApproved());
			customerJSONArray.add(customerJSONObject);
			customerJSONObject = new JSONObject();
		}
		customerJSONObject.put("id",newComment.getId());
		customerJSONObject.put("customerUsername", newComment.getCustomer().getUsername());
		customerJSONObject.put("sportFacilityName", newComment.getSportFacility().getName());
		customerJSONObject.put("comment",newComment.getCommentText());
		customerJSONObject.put("rating", newComment.getRating());
		customerJSONObject.put("isApproved", newComment.isApproved());

		customerJSONArray.add(customerJSONObject);		
		comments.put(newComment.getId(), newComment);

		
		 try {
	         FileWriter file = new FileWriter(contextPath + "/customers.json");
	         file.write(customerJSONArray.toJSONString());
	         file.close();
	      } catch (IOException e) {
	         e.printStackTrace();
	         return null;
	      }
		 
		 return newComment;
 
	}
	
	public ArrayList<Comment> getCommentsForSportFacility(String facilityName) {
		ArrayList<Comment> commentsForFacility = new ArrayList<>();
		for(Comment comment : comments.values()) {
			if(comment.getSportFacility().getName().equals(facilityName))
				commentsForFacility.add(comment);
		}
		return commentsForFacility;
	}
	
	public boolean canCustomerLeaveComment(Customer customer, String facilityName) {
		ArrayList<Comment> commentsForFacility = getCommentsForSportFacility(facilityName);
		
		for(Comment comment : commentsForFacility) {
			if(comment.getCustomer().getUsername().equals(customer.getUsername())) {
				return false;
			}
		}
		
		for(SportFacility facility: customer.getVisitedFacilities()) {
			if(facility.getName().equals(facilityName))
				return true;
		}
		return false;
	}
	
	public Comment saveComment(SaveCommentDTO dto, Customer customer) {
		Comment newComment = new Comment(++maxID, customer, sportFacilityDAO.getSportFacility(dto.getFacilityName()) ,dto.getText(), dto.getRating());	
		return saveComment(newComment);
	}




}
