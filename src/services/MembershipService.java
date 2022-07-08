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
import beans.Membership;
import beans.Product;
import beans.SportFacility;
import beans.User;
import dao.CommentDAO;
import dao.CouponDAO;
import dao.CustomerDAO;
import dao.MembershipDAO;
import dao.ProductDAO;
import dao.SportFacilityDAO;
import factories.MembershipFactory;

@Path("/membership")
public class MembershipService {
	
	@Context
	ServletContext ctx;
	
	@Context
	HttpServletRequest request;
	
	private MembershipFactory membershipFactory;
	
	public MembershipService() {
		this.membershipFactory = new MembershipFactory();
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
		if (ctx.getAttribute("couponDAO") == null) {
	    	String contextPath = ctx.getRealPath("");
			ctx.setAttribute("couponDAO", new CouponDAO (contextPath));
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
	@Path("/createMembership/{membershipName}/{couponCode}")
	@Produces(MediaType.APPLICATION_JSON)
	public Membership createMembership(@PathParam("membershipName") String membershipName, @PathParam("couponCode") String couponCode) {
		CouponDAO couponDAO = (CouponDAO) ctx.getAttribute("couponDAO");
		MembershipDAO membershipDAO = (MembershipDAO) ctx.getAttribute("membershipDAO");
		
		User user = (User) request.getSession().getAttribute("user");
		Membership membership = membershipDAO.createMembership(membershipName, (Customer)user, couponDAO.getCoupon(couponCode));
		return membership;
	}
	
	@GET
	@Path("/createMembership/{membershipName}")
	@Produces(MediaType.APPLICATION_JSON)
	public Membership createMembershipWithoutCoupon(@PathParam("membershipName") String membershipName) {
		MembershipDAO membershipDAO = (MembershipDAO) ctx.getAttribute("membershipDAO");
		
		User user = (User) request.getSession().getAttribute("user");
		Membership membership = membershipDAO.createMembership(membershipName, (Customer)user, null);
		return membership;
	}
	
	
	@GET
	@Path("/isCodeValid/{couponCode}")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean isCouponValid(@PathParam("couponCode") String couponCode) {
		CouponDAO couponDAO = (CouponDAO) ctx.getAttribute("couponDAO");
		
		return couponDAO.isCouponValid(couponCode);
	}
	
	@GET
	@Path("/getPrice/{membershipName}/{couponCode}")
	@Produces(MediaType.APPLICATION_JSON)
	public double getPrice(@PathParam("membershipName") String membershipName, @PathParam("couponCode") String couponCode) {
		CouponDAO couponDAO = (CouponDAO) ctx.getAttribute("couponDAO");
		MembershipDAO membershipDAO = (MembershipDAO) ctx.getAttribute("membershipDAO");
		
		User user = (User) request.getSession().getAttribute("user");
		return membershipDAO.getPrice(membershipName, (Customer)user, couponDAO.getCoupon(couponCode));
	}
	
	@GET
	@Path("/getPrice/{membershipName}")
	@Produces(MediaType.APPLICATION_JSON)
	public double getPriceWithoutCoupon(@PathParam("membershipName") String membershipName) {
		MembershipDAO membershipDAO = (MembershipDAO) ctx.getAttribute("membershipDAO");
		
		User user = (User) request.getSession().getAttribute("user");
		return membershipDAO.getPrice(membershipName, (Customer)user, null);
	}
	
	
	@GET
	@Path("/getCouponCodes")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<String> getAllCouponCodes() {
		CouponDAO couponDAO = (CouponDAO) ctx.getAttribute("couponDAO");
		return couponDAO.getAllCouponCodes();
	}
	
}
