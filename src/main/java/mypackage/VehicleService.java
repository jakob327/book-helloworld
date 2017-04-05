package mypackage;

import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;


@Stateless
@Path("/vehicles")
public class VehicleService extends Application{
	
	@PersistenceContext(name="JavaEESample")
	private EntityManager em;
	
	
	@GET
	@Produces("application/json")
	public Collection<Vehicle> getVehicles(){
		
		TypedQuery<Vehicle> query = em.createQuery("SELECT v FROM Vehicle v", Vehicle.class);
		return (Collection<Vehicle>) query.getResultList();
	}
	
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	public void addVehicle(String name) {
		Vehicle v = new Vehicle(name);
		
		em.persist(v);
	}
	
}
