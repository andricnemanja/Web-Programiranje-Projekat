package services;

import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import beans.Comment;
import beans.WorkoutHistory;
import dao.CoachDAO;
import dao.CommentDAO;
import dao.CustomerDAO;
import dao.SportFacilityDAO;
import dao.WorkoutDAO;
import dao.WorkoutHistoryDAO;

@Path("/workoutHistory")
public class WorkoutHistoryService {
	
	@Context
	ServletContext ctx;
	
	public WorkoutHistoryService() {
	}
	
	@PostConstruct
	public void init() {
		if (ctx.getAttribute("SportFacilityDAO") == null) {
	    	String contextPath = ctx.getRealPath("");
			ctx.setAttribute("SportFacilityDAO", new SportFacilityDAO (contextPath));
		}
		if (ctx.getAttribute("customerDAO") == null) {
	    	String contextPath = ctx.getRealPath("");
			ctx.setAttribute("customerDAO", new CustomerDAO(contextPath));
		}
		if (ctx.getAttribute("coachDAO") == null) {
	    	String contextPath = ctx.getRealPath("");
			ctx.setAttribute("coachDAO", new CoachDAO(contextPath));
		}
		if (ctx.getAttribute("workoutDAO") == null) {
			String contextPath = ctx.getRealPath("");
			CoachDAO coachDAO = (CoachDAO) ctx.getAttribute("coachDAO");
	    	SportFacilityDAO sportFacilityDAO = (SportFacilityDAO) ctx.getAttribute("SportFacilityDAO");
			ctx.setAttribute("workoutDAO", new WorkoutDAO (contextPath, coachDAO, sportFacilityDAO));
		}
		if (ctx.getAttribute("workoutHistoryDAO") == null) {
	    	String contextPath = ctx.getRealPath("");
	    	CoachDAO coachDAO = (CoachDAO) ctx.getAttribute("coachDAO");
	    	CustomerDAO customerDAO = (CustomerDAO) ctx.getAttribute("customerDAO");
	    	WorkoutDAO workoutDAO = (WorkoutDAO) ctx.getAttribute("workoutDAO");
			ctx.setAttribute("workoutHistoryDAO", new WorkoutHistoryDAO(contextPath,customerDAO ,coachDAO, workoutDAO));
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
	@Path("/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<WorkoutHistory> getWorkoutHistoryForUser(@PathParam("username") String username) {
		WorkoutHistoryDAO dao = (WorkoutHistoryDAO) ctx.getAttribute("workoutHistoryDAO");
		return dao.getWorkoutHistoryForUser(username);
	}
	
}
