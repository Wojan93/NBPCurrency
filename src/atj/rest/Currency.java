package atj.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/")
public class Currency {

	@GET
	@Produces(javax.ws.rs.core.MediaType.TEXT_PLAIN)
	public String currency() {
		return "Hello bloody student!";
	}
}
