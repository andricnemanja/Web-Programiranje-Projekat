package dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import beans.Customer;
import beans.CustomerLevel;
import beans.CustomerLevel.CustomerType;
import beans.User;
import beans.User.Gender;
import factories.CustomerLevelFactory;

public class CustomerDAO {
	
	private HashMap<String, Customer> customers = new HashMap<String, Customer>();
	private String contextPath;
	private MembershipDAO membershipDAO;
	
	public CustomerDAO() {
		
	}
	
	public CustomerDAO(String contextPath, MembershipDAO membershipDAO) {
		this.membershipDAO = membershipDAO;
		this.contextPath = contextPath;
		loadCustomers(contextPath);
	}

	public Collection<Customer> getAll() {
		return customers.values();
	}


	private void loadCustomers(String contextPath) {
        JSONParser jsonParser = new JSONParser();
        
        try (FileReader reader = new FileReader(contextPath + "/customers.json"))
        {
            //Read JSON file
 
        	Object obj = jsonParser.parse(reader);
            JSONArray customerList = (JSONArray) obj;
             
            
            for(Object object : customerList) {
            	parseJSONObject((JSONObject)object);
            }
 
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
		String firstName = (String) customerJSONObject.get("firstName");
		String lastName = (String) customerJSONObject.get("lastName");
		String email = (String) customerJSONObject.get("email");	
		String username = (String) customerJSONObject.get("username");	
		String password = (String) customerJSONObject.get("password");	
		Gender gender = Gender.valueOf((String) customerJSONObject.get("gender"));
		String dateOfBirthString = (String) customerJSONObject.get("dateOfBirth");
		CustomerType customerType = CustomerType.valueOf((String)customerJSONObject.get("customerType"));
		
		CustomerLevel customerLevel = CustomerLevelFactory.getCustomerLevel(customerType);
		Date date = DateParser.parseDate(dateOfBirthString);
		
		Customer newCustomer = new Customer(firstName, lastName, email, username, password, gender, date);
		newCustomer.setCustomerLevel(customerLevel);
		newCustomer.setMembership(membershipDAO.getMembershipForCustomer(username));
		customers.put(username, newCustomer);


	}
	
	public Customer saveCustomer(Customer newCustomer) {
		JSONObject customerJSONObject = new JSONObject();
		JSONArray customerJSONArray = new JSONArray();

		for(Customer customer : customers.values()) {
			customerJSONObject.put("firstName",customer.getFirstName());
			customerJSONObject.put("lastName", customer.getLastName());
			customerJSONObject.put("email", customer.getEmail());
			customerJSONObject.put("username",customer.getUsername());
			customerJSONObject.put("password", customer.getPassword());
			customerJSONObject.put("gender", customer.getGender().name());
			customerJSONObject.put("dateOfBirth", DateParser.makeDateString(customer.getDateOfBirth()));	
			customerJSONArray.add(customerJSONObject);
			customerJSONObject = new JSONObject();
		}
		customerJSONObject.put("firstName",newCustomer.getFirstName());
		customerJSONObject.put("lastName", newCustomer.getLastName());
		customerJSONObject.put("email", newCustomer.getEmail());
		customerJSONObject.put("username",newCustomer.getUsername());
		customerJSONObject.put("password", newCustomer.getPassword());
		customerJSONObject.put("gender", newCustomer.getGender().name());
		customerJSONObject.put("dateOfBirth", DateParser.makeDateString(newCustomer.getDateOfBirth()));	

		customerJSONArray.add(customerJSONObject);		
		customers.put(newCustomer.getUsername(), newCustomer);

		
		 try {
	         FileWriter file = new FileWriter(contextPath + "/customers.json");
	         file.write(customerJSONArray.toJSONString());
	         file.close();
	      } catch (IOException e) {
	         e.printStackTrace();
	         return null;
	      }
		 
		 return newCustomer;
 
	}
	
	public Customer login(User customer) {
		 if(!customers.containsKey(customer.getUsername()))
			 return null;
		 
		 if(customers.get(customer.getUsername()).getPassword().equals(customer.getPassword()))
			 return customers.get(customer.getUsername());
		 
		 return null;
		 
	 }
	
	public Customer getCustomer(String username){
		 return customers.get(username);
	}
	

	


	
}
