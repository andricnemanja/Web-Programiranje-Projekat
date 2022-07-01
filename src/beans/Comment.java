package beans;

public class Comment {
	private int id;
	private Customer customer;
	private SportFacility sportFacility;
	private String commentText;
	private int rating;
	
	public Comment(int id, Customer customer, SportFacility sportFacility, String commentText, int rating) {
		super();
		this.id = id;
		this.customer = customer;
		this.sportFacility = sportFacility;
		this.commentText = commentText;
		this.rating = rating;
	}

	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public SportFacility getSportFacility() {
		return sportFacility;
	}
	public void setSportFacility(SportFacility sportFacility) {
		this.sportFacility = sportFacility;
	}
	public String getCommentText() {
		return commentText;
	}
	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
}
