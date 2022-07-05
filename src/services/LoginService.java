package services;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import beans.Customer;
import beans.User;
import dao.CoachDAO;
import dao.CustomerDAO;
import dao.UserDAO;

@Path("")
public class LoginService {
	
	@Context
	ServletContext ctx;
	
	@Context
	HttpServletRequest request;
	
	public LoginService() {
		
	}
	
	@PostConstruct
	public void init() {
		if (ctx.getAttribute("customerDAO") == null) {
	    	String contextPath = ctx.getRealPath("");
			ctx.setAttribute("customerDAO", new CustomerDAO(contextPath));
		}
		if (ctx.getAttribute("coachDAO") == null) {
	    	String contextPath = ctx.getRealPath("");
			ctx.setAttribute("coachDAO", new CoachDAO(contextPath));
		}
	}
	
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public User login(User user) {
		CustomerDAO dao = (CustomerDAO) ctx.getAttribute("customerDAO");
		User loggedUser = dao.login(user);
		if(loggedUser != null) {
			request.getSession().setAttribute("user", loggedUser);
			return loggedUser;
		}
		
		CoachDAO coachDAO = (CoachDAO) ctx.getAttribute("coachDAO");
		
		loggedUser = coachDAO.login(user);
		if(loggedUser != null) {
			request.getSession().setAttribute("user", loggedUser);
			return loggedUser;
		}
		
		
		return null;

	}
	
	
	@POST
	@Path("/logout")
	public void logout(@Context HttpServletRequest request) {
		request.getSession().invalidate();
	}
	
	@GET
	@Path("/currentUser")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public User login(@Context HttpServletRequest request) {
		return (User) request.getSession().getAttribute("user");
	}
}
