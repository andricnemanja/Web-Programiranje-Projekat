package dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import beans.Membership;
import beans.Coupon;
import beans.Customer;
import beans.SportFacility;
import factories.MembershipFactory;
import beans.Membership.MembershipStatus;
import beans.Membership.MembershipType;


public class MembershipDAO extends DAO {

	private HashMap<String, Membership> memberships = new HashMap<String, Membership>();
	private int maxID;
	private MembershipFactory membershipFactory;

	
	public MembershipDAO() {
		super();
	}
	
	public MembershipDAO(String contextPath) {
		super();
		loadMemberships(contextPath);
		this.membershipFactory = new MembershipFactory();
	}
	
	public Collection<Membership> getAll() {
		return memberships.values();
	}
	
	
	private void loadMemberships(String contextPath) {
		
        JSONArray membershipList = super.load(contextPath, "memberships.json");    
        for(Object obj : membershipList) {
        	parseJSONObject((JSONObject)obj);
        }
	}
	
	@Override
	protected void parseJSONObject(JSONObject customerJSONObject) {
		
		
		String id = (String) customerJSONObject.get("id");
		MembershipType membershipType = MembershipType.valueOf((String) customerJSONObject.get("type"));
		Date paymentDate = DateParser.parseDate((String) customerJSONObject.get("paymentDate"));
		Date endDate = DateParser.parseDate((String) customerJSONObject.get("endDate"));
		double price = (double) customerJSONObject.get("price");
		String customerUsername = (String) customerJSONObject.get("customerUsername");
		MembershipStatus membershipStatus = MembershipStatus.valueOf((String) customerJSONObject.get("status"));
		int numberOfRemainingVisits = Integer.valueOf((String) customerJSONObject.get("numberOfRemainingVisits"));
	
		Membership membership = new Membership(id, membershipType, paymentDate, endDate, price, customerUsername, membershipStatus, numberOfRemainingVisits);
		memberships.put(id, membership);
		
		if(Integer.valueOf(id) > maxID) 
			maxID = Integer.valueOf(id);

	}
	
	public Membership getMembershipForCustomer(String customerUsername) {
		for(Membership membership : memberships.values()) {
			if(membership.getCustomerUsername().equals(customerUsername) && membership.getMembershipStatus() == MembershipStatus.ACTIVE) {
				return membership;
			}
		}
		return null;
	}
	
	public void saveMembership(Membership membership) {
		
	}

	public Membership createMembership(String membershipType, Customer customer, Coupon coupon) {
		
		Membership membership;
		if(coupon == null)
			membership = membershipFactory.getMembership(String.valueOf(++maxID), membershipType, customer);
		else
			membership = membershipFactory.getMembership(String.valueOf(++maxID), membershipType, customer, coupon);
		
		customer.setMembership(membership);
		memberships.put(String.valueOf(maxID), membership);
		//TODO save membership
		return membership;
	}
	
	public double getPrice(String membershipType, Customer customer, Coupon coupon) {
		if(coupon == null)
			return membershipFactory.calculatePrice(membershipType, customer.getCustomerLevel());
		else
			return membershipFactory.calculatePrice(membershipType, customer.getCustomerLevel(), coupon);
	}

}
