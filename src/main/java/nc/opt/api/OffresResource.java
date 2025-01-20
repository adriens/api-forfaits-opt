package nc.opt.api;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.ws.rs.core.Response;


import java.util.List;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.media.ExampleObject;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("/offres") //endpoint /offres
@Produces(MediaType.APPLICATION_JSON)
public class OffresResource {

    @Inject
    EntityManager entityManager;

    @GET
    @Tag(name = "Gammes de Forfaits", description = "Liste les différentes gammes d'offres télécoms de l'OPT") 
    public List<Forfait> getAllOffres() {
        return entityManager.createQuery("SELECT f FROM Forfait f", Forfait.class).getResultList();
    }

    @GET
    @Path("/forfait-m")
    @Operation(
        summary = "Liste des forfaits de la gamme forfait-m",
        description = "Retourne la liste complète des forfaits disponibles dans la gamme forfait-m."
    )
    @APIResponse(
        responseCode = "200",
        description = "Liste des forfaits de la gamme forfait-m",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = ForfaitM.class),
            examples = @ExampleObject(
                name = "Exemple de réponse",
                value = "[{\"id\":\"forfait-m-1\",\"volumetrie\":\"1 Go\",\"vocal\":\"1 H\",\"sms\":\"SMS illimités\",\"prix\":1000.0,\"url\":\"https://www.opt.nc/particuliers/mobile/quel-forfait-choisir/forfait-m-1-go\"}, {\"id\":\"forfait-m-100\",\"volumetrie\":\"100 Go\",\"vocal\":\"Appels illimités\",\"sms\":\"SMS illimités\",\"prix\":10000.0,\"url\":\"https://www.opt.nc/particuliers/mobile/quel-forfait-choisir/forfait-m-100-go\"}]"
            )
        )
    )
    @Tag(name = "Forfait-m", description = "Liste de tous les forfaits de la gamme forfait-m")
    public List<ForfaitM> getForfaitsGammeM() {
        return entityManager.createQuery("SELECT fm FROM ForfaitM fm", ForfaitM.class).getResultList();
    }

    @GET
    @Path("/forfait-m/{id}")
    @Operation(
        summary = "Détails d'un forfait spécifique de la gamme forfait-m",
        description = "Retourne les détails d'un forfait spécifique de la gamme forfait-m en fonction de l'ID fourni."
    )
    @Parameter(
        name = "id",
        description = "ID du forfait",
        required = true,
        example = "forfait-m-1"
    )
    @APIResponses({
        @APIResponse(
            responseCode = "200",
            description = "Détails du forfait",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ForfaitM.class),
                examples = @ExampleObject(
                    name = "Exemple de réponse",
                    value = "{\"id\":\"forfait-m-1\",\"volumetrie\":\"1 Go\",\"vocal\":\"1 H\",\"sms\":\"Illimité\",\"prix\":1000.0,\"url\":\"https://www.opt.nc/particuliers/mobile/quel-forfait-choisir/forfait-m-1-go\"}"
                )
            )
        ),
        @APIResponse(
            responseCode = "404",
            description = "Forfait non trouvé"
        )
    })
    @Tag(name = "Forfait-m", description = "Détails d'un forfait spécifique de la gamme forfait-m")
    public Response getForfaitMById(@PathParam("id") String id) {
        ForfaitM forfaitM;
        try {
            forfaitM = entityManager.createQuery("SELECT fm FROM ForfaitM fm WHERE fm.id = :id", ForfaitM.class)
                                    .setParameter("id", id)
                                    .getSingleResult();
            return Response.ok(forfaitM).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND)
                           .entity("Forfait avec ID '" + id + "' non trouvé")
                           .type(MediaType.TEXT_PLAIN)
                           .build();
        }
    }
    
    @GET
    @Path("/abonnement-data-seul")
    @Operation(
        summary = "Liste des abonnements de données",
        description = "Retourne la liste des abonnements de données (IMV et IM4G)."
    )
    @APIResponse(
        responseCode = "200",
        description = "Liste des abonnements de données (IMV et IM4G)",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = AbonnementDataSeul.class),
            examples = @ExampleObject(
                name = "Exemple de réponse",
                value = "[{\"id\":\"IMV-10\",\"volumetrie\":\"1 Mo\",\"debit\":\"256 Ko/s\",\"prix\":530,\"url\":\"https://www.opt.nc/sites/serviciel/files/media/file/FI_Internet%20Mobile%20au%20Volume.pdf\",\"type_forfait\":\"IMV\"}, {\"id\":\"IM4G-1\",\"volumetrie\":\"1 Go\",\"debit\":\"150 Mb/s\",\"prix\":1908,\"url\":\"https://www.opt.nc/sites/serviciel/files/media/file/FI_ForfaitInternetMobile4G%202022_1.pdf\",\"type_forfait\":\"IM4G\"}]"
            )
        )
    )
    @Tag(name = "Abonnement-data-seul", description = "Liste de tous les abonnements de données (IMV et IM4G)")
    public List<AbonnementDataSeul> getAbonnementsDataSeul() {
        return entityManager.createQuery("SELECT ads FROM AbonnementDataSeul ads", AbonnementDataSeul.class).getResultList();
    }

    @GET
    @Path("/abonnement-data-seul/imv")
    @Operation(
        summary = "Liste des abonnements IMV",
        description = "Retourne la liste des abonnements IMV."
    )
    @APIResponse(
        responseCode = "200",
        description = "Liste des abonnements IMV",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = AbonnementDataSeul.class),
            examples = @ExampleObject(
                name = "Exemple de réponse",
                value = "[{\"id\":\"IMV-10\",\"volumetrie\":\"1 Mo\",\"debit\":\"256 Ko/s\",\"prix\":530,\"url\":\"https://www.opt.nc/sites/serviciel/files/media/file/FI_Internet%20Mobile%20au%20Volume.pdf\",\"type_forfait\":\"IMV\"}]"
            )
        )
    )
    @Tag(name = "Abonnement-data-seul", description = "Liste de tous les abonnements IMV")
    public List<AbonnementDataSeul> getAbonnementsIMV() {
        return entityManager.createQuery("SELECT ads FROM AbonnementDataSeul ads WHERE ads.type_forfait = :type_forfait", AbonnementDataSeul.class)
        .setParameter("type_forfait", "IMV")
        .getResultList();

    }

    @GET
    @Path("/abonnement-data-seul/im4g")
    @Operation(
        summary = "Liste des abonnements IM4G",
        description = "Retourne la liste des abonnements IM4G."
    )
    @APIResponse(
        responseCode = "200",
        description = "Liste des abonnements IM4G",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = AbonnementDataSeul.class),
            examples = @ExampleObject(
                name = "Exemple de réponse",
                value = "[{\"id\":\"IM4G-1\",\"volumetrie\":\"1 Go\",\"debit\":\"150 Mb/s\",\"prix\":1908,\"url\":\"https://www.opt.nc/sites/serviciel/files/media/file/FI_ForfaitInternetMobile4G%202022_1.pdf\",\"type_forfait\":\"IM4G\"}]"
            )
        )
    )
    @Tag(name = "Abonnement-data-seul", description = "Liste de tous les abonnements IM4G")
    public List<AbonnementDataSeul> getAbonnementsIM4G() {
        return entityManager.createQuery("SELECT ads FROM AbonnementDataSeul ads WHERE ads.type_forfait = :type", AbonnementDataSeul.class)
        .setParameter("type", "IM4G")
        .getResultList();
    }

    @GET
    @Path("/abonnement-data-seul/{id}")
    @Operation(
        summary = "Détails d'un abonnement spécifique",
        description = "Retourne les détails d'un abonnement spécifique de données en fonction de l'ID fourni."
    )
    @Parameter(
        name = "id",
        description = "ID de l'abonnement",
        required = true,
        example = "IMV-10"
    )
    @APIResponses({
        @APIResponse(
            responseCode = "200",
            description = "Détails de l'abonnement",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = AbonnementDataSeul.class),
                examples = @ExampleObject(
                    name = "Exemple de réponse",
                    value = "{\"id\":\"IMV-10\",\"volumetrie\":\"1 Mo\",\"debit\":\"256 Ko/s\",\"prix\":530,\"url\":\"https://www.opt.nc/sites/serviciel/files/media/file/FI_Internet%20Mobile%20au%20Volume.pdf\",\"type_forfait\":\"IMV\"}"
                )
            )
        ),
        @APIResponse(
            responseCode = "404",
            description = "Abonnement non trouvé"
        )
    })
    @Tag(name = "Abonnement-data-seul", description = "Accès à un abonnement spécifique")
    public Response getAbonnementDataSeulById(@PathParam("id") String id) {
        AbonnementDataSeul abonnementDataSeul;
        try {
            abonnementDataSeul = entityManager.createQuery("SELECT ads FROM AbonnementDataSeul ads WHERE ads.id = :id", AbonnementDataSeul.class)
                                            .setParameter("id", id)
                                            .getSingleResult();
            return Response.ok(abonnementDataSeul).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND)
                        .entity("Abonnement avec ID '" + id + "' non trouvé")
                        .type(MediaType.TEXT_PLAIN)
                        .build();
        }
    }
}