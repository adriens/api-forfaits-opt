package nc.opt.api;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.util.List;

@Path("/offres") //endpoint /offres
@Produces(MediaType.APPLICATION_JSON)
public class OffresResource {

    @Inject
    EntityManager entityManager;

    @GET
    public List<Forfait> getAllOffres() {
        return entityManager.createQuery("SELECT f FROM Forfait f", Forfait.class).getResultList();
    }
}
