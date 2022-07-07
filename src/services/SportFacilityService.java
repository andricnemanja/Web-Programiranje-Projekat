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

import beans.Product;
import beans.SportFacility;
import dao.ProductDAO;
import dao.SportFacilityDAO;

@Path("/facilities")
public class SportFacilityService {

	@Context
	ServletContext ctx;
	
	public SportFacilityService() {
	}
	
	@PostConstruct
	public void init() {
		if (ctx.getAttribute("SportFacilityDAO") == null) {
	    	String contextPath = ctx.getRealPath("");
			ctx.setAttribute("SportFacilityDAO", new SportFacilityDAO (contextPath));
		}
	}
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<SportFacility> getSportFacility() {
		SportFacilityDAO dao = (SportFacilityDAO) ctx.getAttribute("SportFacilityDAO");
		return dao.getAll();
	}
	
	@GET
	@Path("/name/{search}")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<SportFacility> searchSportFacilitiesByName(@PathParam("search") String search) {
		SportFacilityDAO dao = (SportFacilityDAO) ctx.getAttribute("SportFacilityDAO");
		return dao.search(search);
	}
	
	@GET
	@Path("/city/{search}")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<SportFacility> searchSportFacilitiesByCity(@PathParam("search") String search) {
		SportFacilityDAO dao = (SportFacilityDAO) ctx.getAttribute("SportFacilityDAO");
		return dao.searchCity(search);
	}
	
	@GET
	@Path("/type/{search}")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<SportFacility> searchSportFacilitiesByType(@PathParam("search") String search) {
		SportFacilityDAO dao = (SportFacilityDAO) ctx.getAttribute("SportFacilityDAO");
		return dao.searchType(search);
	}
	
	@GET
	@Path("/rating/{search}")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<SportFacility> searchSportFacilitiesByRating(@PathParam("search") String search) {
		SportFacilityDAO dao = (SportFacilityDAO) ctx.getAttribute("SportFacilityDAO");
		return dao.searchRating(search);
	}
	
	
}
