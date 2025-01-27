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
    @APIResponse(
        responseCode = "200",
        description = "Liste des gammes de forfaits",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = Forfait.class),
            examples = @ExampleObject(
                name = "Exemple de réponse",
                value = "[{\"id\":\"forfaits-m\",\"desc\":\"Gamme forfaits M\",\"description\":\"Un forfait « tout compris ». Appels + SMS + Internet Mobile VoIP inclus, sans engagement\",\"url\":\"https://www.opt.nc/particuliers/mobile/choisissez-le-forfait-qui-vous-ressemble\"}, {\"id\":\"forfaits-bloques\",\"desc\":\"Gamme forfaits bloqués\",\"description\":\"Un forfait voix et SMS permettant de maîtriser son budget sans risque de hors-forfait, sans engagement\",\"url\":\"https://www.opt.nc/particuliers/mobile/choisissez-le-forfait-qui-vous-ressemble\"}, {\"id\":\"data-only\",\"desc\":\"Abonnement data seul\",\"description\":\"Un forfait Internet dédié aux objets connectés avec un débit jusqu'à 150 Mb/s, rechargeable et compatible avec des options SMS en local.\",\"url\":\"https://www.opt.nc/particuliers/mobile/quel-forfait-choisir/abonnement-data-seul\"}, {\"id\":\"prepaye\",\"desc\":\"Kit prépayé Liberté\",\"description\":\"Un forfait sans engagement, décompté pour les appels et SMS (local et international), et compatible avec Internet Mobile à la Demande. Rechargeable avec des cartes Liberté pour prolonger la validité du crédit.\",\"url\":\"https://www.opt.nc/particuliers/mobile/quel-forfait-choisir/kit-prepaye-liberte\"}, {\"id\":\"tourism-card\",\"desc\":\"Tourism Card\",\"description\":\"Une carte prépayée conçue pour les vacanciers, valable 3 mois, permettant appels, SMS et accès à Internet Mobile, aussi bien en local qu'à l'international.\",\"url\":\"https://tourismcard.nc/\"}]"
            )
        )
    )
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
    @APIResponses(
        value = {
            @APIResponse(
                responseCode = "200",
                description = "Détails du forfait",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ForfaitM.class),
                    examples = @ExampleObject(
                        name = "valid_id",
                        value = "{\"id\":\"forfait-m-1\",\"volumetrie\":\"1 Go\",\"vocal\":\"1 H\",\"sms\":\"Illimité\",\"prix\":1000.0,\"url\":\"https://www.opt.nc/particuliers/mobile/quel-forfait-choisir/forfait-m-1-go\"}"
                    )
                )
            ),
            @APIResponse(
                responseCode = "404",
                description = "Forfait M non trouvé",
                content = @Content(
                    mediaType = "text/plain",
                    examples = @ExampleObject(
                        name = "invalid_id",
                        value = "Forfait avec ID 'forfait-m-9999' non trouvé"
                    )
                )
            )
        }
    )
    @Tag(name = "Forfait-m", description = "Détails d'un forfait spécifique de la gamme forfait-m")
    public Response getForfaitMById(
        @Parameter(
            description = "ID du forfait M",
            schema = @Schema(implementation =  String.class),
            required = true,
            examples = {
                @ExampleObject(name = "valid_id", value = "forfait-m-1"),
                @ExampleObject(name = "invalid_id", value = "forfait-m-9999")
            }
        )      
    @PathParam("id") String id) {
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
    @APIResponses(
        value={
            @APIResponse(
            responseCode = "200",
            description = "Détails de l'abonnement",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = AbonnementDataSeul.class),
                examples = @ExampleObject(
                    name = "valid_id",
                    value = "{\"id\":\"IMV-10\",\"volumetrie\":\"1 Mo\",\"debit\":\"256 Ko/s\",\"prix\":530,\"url\":\"https://www.opt.nc/sites/serviciel/files/media/file/FI_Internet%20Mobile%20au%20Volume.pdf\",\"type_forfait\":\"IMV\"}"
                )
            )
        ),
            @APIResponse(
                responseCode = "404",
                description = "Abonnement non trouvé",
                content = @Content(
                    mediaType = "text/plain",
                    examples = @ExampleObject(
                        name = "invalid_id",
                        value = "Abonnement avec ID 'IMV-9999' non trouvé"
                    )
                )
            )
        }
    )
    @Tag(name = "Abonnement-data-seul", description = "Accès à un abonnement spécifique")
    public Response getAbonnementDataSeulById(
        @Parameter(
            description = "ID de l'abonnement data seul",
            schema = @Schema(implementation =  String.class),
            required = true,
            examples = {
                @ExampleObject(name = "valid_id", value = "IMV-10"),
                @ExampleObject(name = "invalid_id", value = "IMV-9999")
            }
        )  
    @PathParam("id") String id) {
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
    @GET
    @Path("/prepaye") // Spécifie le chemin pour les kits prépayés
    @Operation(
        summary = "Liste des forfaits prépayés",
        description = "Retourne la liste complète des forfaits prépayés disponibles."
    )
    @APIResponse(
        responseCode = "200",
        description = "Liste des forfaits prépayés",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = KitPrepaye.class),
            examples = @ExampleObject(
                name = "Exemple de réponse",
                value = "[{\"id\":\"kit-prepaye\",\"credit\":3000,\"prix\":6000,\"sms_offert\":0,\"duree_validite\":90,\"url\":\"https://www.opt.nc/particuliers/mobile/quel-forfait-choisir/kit-prepaye-liberte\"}, " +
                        "{\"id\":\"recharge-liberte-1000\",\"credit\":1000,\"prix\":1050,\"sms_offert\":10,\"duree_validite\":120,\"url\":\"https://www.opt.nc/particuliers/mobile/quel-forfait-choisir/kit-prepaye-liberte\"}, " +
                        "{\"id\":\"recharge-liberte-3000\",\"credit\":3000,\"prix\":3150,\"sms_offert\":30,\"duree_validite\":150,\"url\":\"https://www.opt.nc/particuliers/mobile/quel-forfait-choisir/kit-prepaye-liberte\"}, " +
                        "{\"id\":\"recharge-liberte-5000\",\"credit\":5000,\"prix\":5250,\"sms_offert\":50,\"duree_validite\":180,\"url\":\"https://www.opt.nc/particuliers/mobile/quel-forfait-choisir/kit-prepaye-liberte\"}]"
            )
        )
    )
    @Tag(name = "Kit Prépayé", description = "Liste de tous les forfaits prépayés")
    public List<KitPrepaye> getAllKitsPrepaye() {
        return entityManager.createQuery("SELECT k FROM KitPrepaye k", KitPrepaye.class).getResultList();
    }
    @GET
    @Path("/prepaye/{id}")
    @Operation(
        summary = "Détails d'un forfait prépayé par ID",
        description = "Retourne les détails d'un forfait prépayé spécifique en fonction de l'ID fourni."
    )
    @APIResponses(
        value = {
            @APIResponse(
                responseCode = "200",
                description = "Détails du forfait prépayé",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = KitPrepaye.class),
                    examples = @ExampleObject(
                        name = "valid_id",
                        value = "{\"id\":\"kit-prepaye\",\"credit\":3000,\"prix\":6000,\"sms_offert\":0,\"duree_validite\":90,\"url\":\"https://www.opt.nc/particuliers/mobile/quel-forfait-choisir/kit-prepaye-liberte\"}"
                    )
                )
            ),
            @APIResponse(
                responseCode = "200",
                description = "Détails du forfait prépayé",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = KitPrepaye.class),
                    examples = @ExampleObject(
                        name = "valid_id_2",
                        value = "{\"id\":\"recharge-liberte-1000\",\"credit\":1000,\"prix\":1050,\"sms_offert\":10,\"duree_validite\":120,\"url\":\"https://rechargetonmob.nc/\"}"
                    )
                )
            ),
            @APIResponse(
                responseCode = "404",
                description = "Forfait non trouvé",
                content = @Content(
                    mediaType = "text/plain",
                    examples = @ExampleObject(
                        name = "invalid_id",
                        value = "Forfait prépayé avec ID 'kit-prepaye-9999' non trouvé"
                    )
                )
            )
        }
    )
    @Tag(name = "Kit Prépayé", description = "Accès à un forfait prépayé spécifique par ID")
    public Response getKitPrepayeById(
        @Parameter(
            description = "ID du kit/recharge prépayé",
            schema = @Schema(implementation =  String.class),
            required = true,
            examples = {
                @ExampleObject(name = "valid_id", value = "kit-prepaye"),
                @ExampleObject(name = "valid_id_2", value = "recharge-liberte-1000"),
                @ExampleObject(name = "invalid_id", value = "kit-prepaye-1000")
            }
        ) 
    @PathParam("id") String id) {
        try {
            KitPrepaye kitPrepaye = entityManager.createQuery("SELECT k FROM KitPrepaye k WHERE k.id = :id", KitPrepaye.class)
                                                    .setParameter("id", id)
                                                    .getSingleResult();
            return Response.ok(kitPrepaye).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND)
                            .entity("Forfait prépayé avec ID '" + id + "' non trouvé")
                            .type(MediaType.TEXT_PLAIN)
                            .build();
        }
    }

    @GET
    @Path("/forfait-bloque")
    @Operation(
        summary = "Liste des forfaits bloqués",
        description = "Retourne la liste complète des forfaits bloqués disponibles."
    )

    @APIResponse(
        responseCode = "200",
        description = "Liste des forfaits bloqués",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = ForfaitBloque.class),
            examples = @ExampleObject(
                name = "Exemple de réponse",
                value = "[{\"id\":\"forfait-bloque-1000\",\"credit\":1000,\"prix\":1060,\"sms_offert\":20,\"url\":\"https://www.opt.nc/particuliers/mobile/quel-forfait-choisir/forfait-bloque-1000\"}, " +
                        "{\"id\":\"forfait-bloque-2000\",\"credit\":2000,\"prix\":2120,\"sms_offert\":40,\"url\":\"https://www.opt.nc/particuliers/mobile/quel-forfait-choisir/forfait-bloque-2000\"}, " +
                        "{\"id\":\"forfait-bloque-3000\",\"credit\":3000,\"prix\":3180,\"sms_offert\":60,\"url\":\"https://www.opt.nc/particuliers/mobile/quel-forfait-choisir/forfait-bloque-3000\"}, " +
                        "{\"id\":\"forfait-bloque-5000\",\"credit\":5000,\"prix\":5300,\"sms_offert\":100,\"url\":\"https://www.opt.nc/particuliers/mobile/quel-forfait-choisir/forfait-bloque-5000\"}]"
            )
        )
    )

    @Tag(name = "Forfaits bloqués", description = "Liste de tous les forfaits bloqués")
    public List<ForfaitBloque> getForfaitsBloques() {
        return entityManager.createQuery("SELECT fb FROM ForfaitBloque fb" , ForfaitBloque.class)
        .getResultList();
    }
    @GET
    @Path("/forfait-bloque/{id}")
    @Operation(
        summary = "Détails d'un forfait bloqué par ID",
        description = "Retourne les détails d'un forfait bloqué spécifique en fonction de l'ID fourni."
    )
    @APIResponses(
        value = {
            @APIResponse(
                responseCode = "200",
                description = "Détails du forfait bloqué",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ForfaitBloque.class),
                    examples = @ExampleObject(
                        name = "valid_id",
                        value = "{\"id\":\"forfait-bloque-1000\",\"credit\":1000,\"prix\":1060,\"sms_offert\":20,\"url\":\"https://www.opt.nc/particuliers/mobile/quel-forfait-choisir/forfait-bloque-1000\"}"
                    )
                )
            ),
            @APIResponse(
                responseCode = "404",
                description = "Forfait bloqué non trouvé",
                content = @Content(
                    mediaType = "text/plain",
                    examples = @ExampleObject(
                        name = "invalid_id",
                        value = "Forfait bloqué avec ID 'forfait-bloque-9999' non trouvé"
                    )
                )
            )
        }
    )
    @Tag(name = "Forfaits bloqués", description = "Accès à un forfait bloqué spécifique par ID")
    public Response getForfaitBloqueById(    
        @Parameter(
            description = "ID du forfait bloqué",
            schema = @Schema(implementation =  String.class),
            required = true,
            examples = {
                @ExampleObject(name = "valid_id", value = "forfait-bloque-1000"),
                @ExampleObject(name = "invalid_id", value = "forfait-bloque-9999")
            }
        ) 
    @PathParam("id") String id) {
        try {
            ForfaitBloque forfaitBloque = entityManager.createQuery("SELECT fb FROM ForfaitBloque fb WHERE fb.id = :id", ForfaitBloque.class)
                                                    .setParameter("id", id)
                                                    .getSingleResult();
            return Response.ok(forfaitBloque).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND)
                        .entity("Forfait bloqué avec ID '" + id + "' non trouvé")
                        .type(MediaType.TEXT_PLAIN)
                        .build();
        }
    }

    @GET
    @Path("/tourism-card")
    @Operation(
        summary = "Liste l'offre de tourisme"
    )

    @APIResponse(
        responseCode = "200",
        description = "Détails de la tourism card",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = TourismCard.class),
            examples = @ExampleObject(
                name = "Exemple de réponse",
                value = "{\"id\": \"tourism-card\",\"prix\":\"5000\",\"credit\":\"2000\",\"volumetrie\":\"25 Go\",\"duree_validite\":\"3 mois\",\"url\":\"https://www.opt.nc/particuliers/mobile/quel-forfait-choisir/tourism-card-1000\"}"
            )
        )
    )


    @Tag(name="Tourism Card", description = "Voir les détails de la tourism card")
    public List<TourismCard> getTourismCard(){
        return entityManager.createQuery("SELECT t FROM TourismCard t", TourismCard.class).getResultList();
    }
    @GET
    @Path("/{id}")
    @Operation(
        description = "Retourne les détails d'un forfait en fonction de l'ID fourni"
    )

    @APIResponses(
        value = {
            @APIResponse(
                responseCode = "200",
                description = "Détails du forfait m",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ForfaitM.class),
                    examples = @ExampleObject(
                        name = "valid_id",
                        value = "{\"id\":\"forfait-m-1\",\"volumetrie\":\"1 Go\",\"vocal\":\"1 H\",\"sms\":\"Illimité\",\"prix\":1000.0,\"url\":\"https://www.opt.nc/particuliers/mobile/quel-forfait-choisir/forfait-m-1-go\"}"
                    )
                )
            ),
            @APIResponse(
                responseCode = "404",
                description = "Forfait non trouvé",
                content = @Content(
                    mediaType = "text/plain",
                    examples = @ExampleObject(
                        name = "invalid_id",
                        value = "Forfait avec ID 'forfait-m-9999' non trouvé"
                    )
                )
            )
        }
    )



    @Tag(name = "Forfaits", description = "Recherche un forfait par ID dans toutes les gammes")
    public Response getForfaitById(
        @Parameter(
            description = "ID du forfait",
            schema = @Schema(implementation =  String.class),
            required = true,
            examples = {
                @ExampleObject(name = "valid_id", value = "forfait-m-1"),
                @ExampleObject(name = "invalid_id", value = "forfait-m-9999")
            }
        )
    @PathParam("id") String id) {
        try {
            ForfaitM forfaitM = entityManager.createQuery("SELECT fm FROM ForfaitM fm WHERE fm.id = :id", ForfaitM.class)
                                            .setParameter("id", id)
                                            .getSingleResult();
            return Response.ok(forfaitM).build();
        } catch (Exception e) {
            try {
                AbonnementDataSeul abonnementDataSeul = entityManager.createQuery("SELECT ads FROM AbonnementDataSeul ads WHERE ads.id = :id", AbonnementDataSeul.class)
                                                                    .setParameter("id", id)
                                                                    .getSingleResult();
                return Response.ok(abonnementDataSeul).build();
            } catch (Exception ex) {
                try {
                    KitPrepaye kitPrepaye = entityManager.createQuery("SELECT k FROM KitPrepaye k WHERE k.id = :id", KitPrepaye.class)
                                                        .setParameter("id", id)
                                                        .getSingleResult();
                    return Response.ok(kitPrepaye).build();
                } catch (Exception exc) {
                    try {
                        ForfaitBloque forfaitBloque = entityManager.createQuery("SELECT fb FROM ForfaitBloque fb WHERE fb.id = :id", ForfaitBloque.class)
                                                                .setParameter("id", id)
                                                                .getSingleResult();
                        return Response.ok(forfaitBloque).build();
                    } catch (Exception exc2) {
                        try {
                            TourismCard tourismCard = entityManager.createQuery("SELECT t FROM TourismCard t WHERE t.id = :id", TourismCard.class)
                                                                .setParameter("id", id)
                                                                .getSingleResult();
                            return Response.ok(tourismCard).build();
                        } catch (Exception exc3) {
                            return Response.status(Response.Status.NOT_FOUND)
                                        .entity("Forfait avec ID '" + id + "' non trouvé dans toutes les gammes.")
                                        .type(MediaType.TEXT_PLAIN)
                                        .build();
                        }
                    }
                }
            }
        }
    }

}