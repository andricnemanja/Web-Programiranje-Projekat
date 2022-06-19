package services;

import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
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
import beans.Product;
import dao.CustomerDAO;
import dao.ProductDAO;

@Path("/customers")
public class CustomerService {
	
	@Context
	ServletContext ctx;
	
	public CustomerService() {
	}
	
	@PostConstruct
	// ctx polje je null u konstruktoru, mora se pozvati nakon konstruktora (@PostConstruct anotacija)
	public void init() {
		// Ovaj objekat se instancira više puta u toku rada aplikacije
		// Inicijalizacija treba da se obavi samo jednom
		if (ctx.getAttribute("customerDAO") == null) {
	    	String contextPath = ctx.getRealPath("");
			ctx.setAttribute("customerDAO", new CustomerDAO(contextPath));
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
		return dao.saveCustomer(customer);
	}
	
}
