package nc.opt.api;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
public class OffresResourceTest {

    @Test
    public void testGetAllOffres() {
        given()
          .when().get("/offres")
          .then()
             .statusCode(200)
             .body("$", notNullValue())
             .body("$", hasSize(5));
    }

    @Test
    public void testGetAllForfaitsGammeM() {
        given()
          .when().get("/offres/forfait-m")
          .then()
             .statusCode(200)
             .body("$", notNullValue())
             .body("$", hasSize(4))
             .body("[0].id", equalTo("forfait-m-1"));
    }

    @Test
    public void testGetForfaitMById() {
        given()
          .pathParam("id", "forfait-m-1")  // Spécifier l'ID du forfait à rechercher
          .when().get("/offres/forfait-m/{id}")
          .then()
             .statusCode(200)
             .body("id", equalTo("forfait-m-1"))  // Vérifier l'ID du forfait renvoyé
             .body("prix", equalTo(1000.0f));  // Vérifier le prix du forfait
    }
    @Test
    public void testGetForfaitMById_NotFound() {
        given()
          .pathParam("id", "forfait-m-unknown")  // ID qui n'existe pas
          .when().get("/offres/forfait-m/{id}")
          .then()
             .statusCode(404)  // Vérifier que le code d'état est 404
             .body(equalTo("Forfait avec ID 'forfait-m-unknown' non trouvé"));  // Vérifier le message d'erreur retourné
    }
    @Test
    public void testGetAllAbonnementsDataSeul() {
        given()
          .when().get("/offres/abonnement-data-seul")
          .then()
             .statusCode(200)  // Vérifier que la réponse HTTP est 200 OK
             .body("$", notNullValue())  // Vérifier que la réponse n'est pas vide
             .body("$", hasSize(9));  // Vérifier que la liste contient bien 10 abonnements (basé sur vos données)
    }

    @Test
    public void testGetAbonnementsIMV() {
        given()
          .when().get("/offres/abonnement-data-seul/imv")
          .then()
             .statusCode(200)
             .body("$", notNullValue())
             .body("$", hasSize(4))
             .body("[0].id", equalTo("IMV-10"));
    }
    
    @Test
    public void testGetAbonnementsIM4G() {
        given()
          .when().get("/offres/abonnement-data-seul/im4g")
          .then()
             .statusCode(200)
             .body("$", notNullValue())
             .body("$", hasSize(5))
             .body("[0].id", equalTo("IM4G-1"));
    }

    @Test
    public void testGetAbonnementDataSeulById() {
        given()
          .pathParam("id", "IMV-10")  // Spécifier l'ID du forfait à rechercher
          .when().get("/offres/abonnement-data-seul/{id}")
          .then()
             .statusCode(200)  // Vérifier que la réponse HTTP est 200 OK
             .body("id", equalTo("IMV-10"))  // Vérifier l'ID de l'abonnement renvoyé
             .body("prix", equalTo(530.0f));  // Vérifier le prix de l'abonnement
    }

    @Test
    public void testGetAbonnementDataSeulById_NotFound() {
        given()
          .pathParam("id", "IMV-unknown")  // ID qui n'existe pas
          .when().get("/offres/abonnement-data-seul/{id}")
          .then()
             .statusCode(404)  // Vérifier que la réponse HTTP est 404
             .body(equalTo("Abonnement avec ID 'IMV-unknown' non trouvé"));  // Vérifier le message d'erreur retourné
    }
}