package services;

import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import beans.Comment;
import beans.Customer;
import beans.User;
import beans.Workout;
import beans.Workout.WorkoutType;
import beans.WorkoutHistory;
import dao.CoachDAO;
import dao.CommentDAO;
import dao.CustomerDAO;
import dao.MembershipDAO;
import dao.SportFacilityDAO;
import dao.WorkoutDAO;
import dao.WorkoutHistoryDAO;
import dto.WorkoutHistoryDTO;
import dto.WorkoutHistorySearchDTO;

@Path("/workoutHistory")
public class WorkoutHistoryService {
	
	@Context
	ServletContext ctx;
	
	@Context
	HttpServletRequest request;
	
	public WorkoutHistoryService() {
	}
	
	@PostConstruct
	public void init() {
		if (ctx.getAttribute("SportFacilityDAO") == null) {
	    	String contextPath = ctx.getRealPath("");
			ctx.setAttribute("SportFacilityDAO", new SportFacilityDAO (contextPath));
		}
		if (ctx.getAttribute("membershipDAO") == null) {
	    	String contextPath = ctx.getRealPath("");
			ctx.setAttribute("membershipDAO", new MembershipDAO (contextPath));
		}
		if (ctx.getAttribute("customerDAO") == null) {
	    	String contextPath = ctx.getRealPath("");
	    	MembershipDAO membershipDAO = (MembershipDAO) ctx.getAttribute("membershipDAO");
			ctx.setAttribute("customerDAO", new CustomerDAO(contextPath, membershipDAO));
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
	public Collection<WorkoutHistory> getWorkoutHistoryForUser() {
		User user = (User) request.getSession().getAttribute("user");
		WorkoutHistoryDAO dao = (WorkoutHistoryDAO) ctx.getAttribute("workoutHistoryDAO");
		return dao.getWorkoutHistoryForUser(user.getUsername());
	}
	
	@GET
	@Path("/coachGroupWorkoutHistory")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<WorkoutHistory> getCoachGroupWorkoutHistory() {
		User user = (User) request.getSession().getAttribute("user");
		WorkoutHistoryDAO dao = (WorkoutHistoryDAO) ctx.getAttribute("workoutHistoryDAO");
		return dao.getCoachWorkoutHistory(user.getUsername(), WorkoutType.GROUP);
	}
	
	@GET
	@Path("/coachPersonalWorkoutHistory")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<WorkoutHistory> getCoachPersonalWorkoutHistory() {
		User user = (User) request.getSession().getAttribute("user");
		WorkoutHistoryDAO dao = (WorkoutHistoryDAO) ctx.getAttribute("workoutHistoryDAO");
		return dao.getCoachWorkoutHistory(user.getUsername(), WorkoutType.PERSONAL);
	}
	
	@GET
	@Path("/deleteWorkout/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public WorkoutHistory deletePersonalWorkout(@PathParam("id") int id) {
		WorkoutHistoryDAO dao = (WorkoutHistoryDAO) ctx.getAttribute("workoutHistoryDAO");
		return dao.deleteWorkout(id);
	}
	
	@GET
	@Path("/getWorkoutsForFacility/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Workout> getWorkoutsForFacility(@PathParam("name") String name) {
    	WorkoutDAO workoutDAO = (WorkoutDAO) ctx.getAttribute("workoutDAO");
		return workoutDAO.getWorkoutsForFacility(name);
	}
	
	@POST
	@Path("/createWorkout")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public WorkoutHistory createWorkout(WorkoutHistoryDTO workoutHistoryDTO) {
		WorkoutHistoryDAO dao = (WorkoutHistoryDAO) ctx.getAttribute("workoutHistoryDAO");
    	WorkoutDAO workoutDAO = (WorkoutDAO) ctx.getAttribute("workoutDAO");
		User user = (User) request.getSession().getAttribute("user");
		
		Workout workout = workoutDAO.getWorkout(workoutHistoryDTO.getWorkoutID());
		WorkoutHistory workoutHistory = new WorkoutHistory(workoutHistoryDTO.getCheckInDateTime(), workout, (Customer)user, workout.getCoach());

		return dao.saveWorkout(workoutHistory, false);
	}
	
	@POST
	@Path("/searchCustomerWorkouts")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public  Collection<WorkoutHistory> searchCustomerWorkouts(WorkoutHistorySearchDTO workoutHistoryDTO) {
		WorkoutHistoryDAO dao = (WorkoutHistoryDAO) ctx.getAttribute("workoutHistoryDAO");
		User user = (User) request.getSession().getAttribute("user");
		
		return dao.search(workoutHistoryDTO, dao.getWorkoutHistoryForUser(user.getUsername()));

	}
	
	@POST
	@Path("/searchCoachPersonalWorkout")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public  Collection<WorkoutHistory> searchCoachPersonalWorkout(WorkoutHistorySearchDTO workoutHistoryDTO) {
		WorkoutHistoryDAO dao = (WorkoutHistoryDAO) ctx.getAttribute("workoutHistoryDAO");
		User user = (User) request.getSession().getAttribute("user");
		
		return dao.search(workoutHistoryDTO, dao.getCoachWorkoutHistory(user.getUsername(), WorkoutType.PERSONAL));

	}
	
	@POST
	@Path("/searchCoachGroupWorkout")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public  Collection<WorkoutHistory> searchCoachGroupWorkout(WorkoutHistorySearchDTO workoutHistoryDTO) {
		WorkoutHistoryDAO dao = (WorkoutHistoryDAO) ctx.getAttribute("workoutHistoryDAO");
		User user = (User) request.getSession().getAttribute("user");
		
		return dao.search(workoutHistoryDTO, dao.getCoachWorkoutHistory(user.getUsername(), WorkoutType.GROUP));

	}
	
}
