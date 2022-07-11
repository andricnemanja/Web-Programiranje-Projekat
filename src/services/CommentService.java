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

import beans.Comment;
import beans.Customer;
import beans.Product;
import beans.SportFacility;
import beans.User;
import dao.CommentDAO;
import dao.CustomerDAO;
import dao.MembershipDAO;
import dao.ProductDAO;
import dao.SportFacilityDAO;
import dto.SaveCommentDTO;
import dto.SportFacilitySearchDTO;

@Path("/comments")
public class CommentService {
	
	@Context
	ServletContext ctx;
	
	@Context
	HttpServletRequest request;
	
	public CommentService() {
	}
	
	@PostConstruct
	public void init() {
		if (ctx.getAttribute("membershipDAO") == null) {
	    	String contextPath = ctx.getRealPath("");
			ctx.setAttribute("membershipDAO", new MembershipDAO (contextPath));
		}
		if (ctx.getAttribute("SportFacilityDAO") == null) {
	    	String contextPath = ctx.getRealPath("");
			ctx.setAttribute("SportFacilityDAO", new SportFacilityDAO (contextPath));
		}
		if (ctx.getAttribute("customerDAO") == null) {
	    	String contextPath = ctx.getRealPath("");
	    	MembershipDAO membershipDAO = (MembershipDAO) ctx.getAttribute("membershipDAO");
			ctx.setAttribute("customerDAO", new CustomerDAO(contextPath, membershipDAO));
		}
		if (ctx.getAttribute("commentDAO") == null) {
	    	String contextPath = ctx.getRealPath("");
			CustomerDAO customerDAO = (CustomerDAO) ctx.getAttribute("customerDAO");
			SportFacilityDAO sportFacilityDAO = (SportFacilityDAO) ctx.getAttribute("SportFacilityDAO");
			ctx.setAttribute("commentDAO", new CommentDAO(contextPath, customerDAO, sportFacilityDAO));
		}
	}
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Comment> getComments() {
		CommentDAO dao = (CommentDAO) ctx.getAttribute("commentDAO");
		return dao.getAll();
	}
	
	
	@GET
	@Path("/{facilityName}")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Comment> getCommentsForFacility(@PathParam("facilityName") String facilityName) {
		CommentDAO dao = (CommentDAO) ctx.getAttribute("commentDAO");
		return dao.getCommentsForSportFacility(facilityName);
	}
	
	@GET
	@Path("/canUserLeaveComment/{facilityName}")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean canUserLeaveComment(@PathParam("facilityName") String facilityName) {
		CommentDAO dao = (CommentDAO) ctx.getAttribute("commentDAO");
		User user = (User) request.getSession().getAttribute("user");

		return dao.canCustomerLeaveComment((Customer)user, facilityName);
	}
	
	
	@POST
	@Path("/saveComment")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Comment saveComment(SaveCommentDTO dto) {
		CommentDAO dao = (CommentDAO) ctx.getAttribute("commentDAO");
		User user = (User) request.getSession().getAttribute("user");

		return dao.saveComment(dto, (Customer)user);
	}
}
