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
}