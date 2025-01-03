package nc.opt.api;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.util.List;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("/offres") //endpoint /offres
@Produces(MediaType.APPLICATION_JSON)

public class OffresResource {

    @Inject
    EntityManager entityManager;

    @GET
    @Tag(name = "Forfaits", description = "Liste les différentes gammes d'offres télécoms de l'OPT") 
    public List<Forfait> getAllOffres() {
        return entityManager.createQuery("SELECT f FROM Forfait f", Forfait.class).getResultList();
    }
}
