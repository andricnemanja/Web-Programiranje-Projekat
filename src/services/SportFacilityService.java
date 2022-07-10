package services;


import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import beans.Product;
import beans.SportFacility;
import beans.User;
import dao.ProductDAO;
import dao.SportFacilityDAO;
import dto.SportFacilitySearchDTO;

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
	
	
	@POST
	@Path("/search")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<SportFacility> search(SportFacilitySearchDTO dto) {
		SportFacilityDAO dao = (SportFacilityDAO) ctx.getAttribute("SportFacilityDAO");
		return dao.search(dto);
	}
	
	
	
}
