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
import javax.ws.rs.core.Response;

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
	// ctx polje je null u konstruktoru, mora se pozvati nakon konstruktora (@PostConstruct anotacija)
	public void init() {
		// Ovaj objekat se instancira više puta u toku rada aplikacije
		// Inicijalizacija treba da se obavi samo jednom
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
	
	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public SportFacility newFacility(SportFacility sportFacility) {
		SportFacilityDAO dao = (SportFacilityDAO) ctx.getAttribute("sportFacilityDAO");
		return dao.saveFacility(sportFacility);
	}
	
	
}
