package services;

import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import beans.Customer;
import beans.CustomerLevel;
import beans.Product;
import beans.CustomerLevel.CustomerType;
import dao.CustomerDAO;
import dao.MembershipDAO;
import dao.ProductDAO;

@Path("/customers")
public class CustomerService {
	
	@Context
	ServletContext ctx;
	
	@Context
	HttpServletRequest request;
	
	public CustomerService() {
	}
	
	@PostConstruct
	public void init() {
		if (ctx.getAttribute("membershipDAO") == null) {
	    	String contextPath = ctx.getRealPath("");
			ctx.setAttribute("membershipDAO", new MembershipDAO (contextPath));
		}
		if (ctx.getAttribute("customerDAO") == null) {
	    	String contextPath = ctx.getRealPath("");
	    	MembershipDAO membershipDAO = (MembershipDAO) ctx.getAttribute("membershipDAO");
			ctx.setAttribute("customerDAO", new CustomerDAO(contextPath, membershipDAO));
		}
	}
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Customer> getCustomers() {
		CustomerDAO dao = (CustomerDAO) ctx.getAttribute("customerDAO");
		return dao.getAll();
	}
	
	@POST
	@Path("/register")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Customer newCustomer(Customer customer) {
		CustomerDAO dao = (CustomerDAO) ctx.getAttribute("customerDAO");
		customer.setCustomerLevel(new CustomerLevel(CustomerType.BRONZE, 2, 1000));
		request.getSession().setAttribute("user", customer);
		
		return dao.saveCustomer(customer);
	}
	
	@POST
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Customer login(Customer customer) {
		CustomerDAO dao = (CustomerDAO) ctx.getAttribute("customerDAO");
		return dao.login(customer);
	}
	
}
