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
          .pathParam("id", "forfait-m-1")
          .when().get("/offres/forfait-m/{id}")
          .then()
             .statusCode(200)
             .body("id", equalTo("forfait-m-1"))
             .body("prix", equalTo(1000.0f)); 
    }
    @Test
    public void testGetForfaitMById_NotFound() {
        given()
          .pathParam("id", "forfait-m-unknown")
          .when().get("/offres/forfait-m/{id}")
          .then()
             .statusCode(404)
             .body(equalTo("Forfait avec ID 'forfait-m-unknown' non trouvé"));
    }
    @Test
    public void testGetAllAbonnementsDataSeul() {
        given()
          .when().get("/offres/abonnement-data-seul")
          .then()
             .statusCode(200)
             .body("$", notNullValue())
             .body("$", hasSize(9));
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
          .pathParam("id", "IMV-10")
          .when().get("/offres/abonnement-data-seul/{id}")
          .then()
             .statusCode(200)
             .body("id", equalTo("IMV-10"))
             .body("prix", equalTo(530.0f));
    }

    @Test
    public void testGetAbonnementDataSeulById_NotFound() {
        given()
          .pathParam("id", "IMV-unknown")
          .when().get("/offres/abonnement-data-seul/{id}")
          .then()
             .statusCode(404)
             .body(equalTo("Abonnement avec ID 'IMV-unknown' non trouvé"));
    }
    @Test
    public void testGetAllKitsPrepaye() {
        given()
          .when().get("/offres/prepaye")
          .then()
             .statusCode(200)
             .body("$", notNullValue())
             .body("$", hasSize(3))
             .body("[0].id", equalTo("kit-prepaye-1000"))
             .body("[1].id", equalTo("kit-prepaye-3000"))
             .body("[2].id", equalTo("kit-prepaye-5000"));
    }

    @Test
    public void testGetKitPrepayeById() {
        given()
          .pathParam("id", "kit-prepaye-1000")
          .when().get("/offres/prepaye/{id}")
          .then()
             .statusCode(200)
             .body("id", equalTo("kit-prepaye-1000"))
             .body("credit", equalTo(1000))
             .body("prix", equalTo(1050))
             .body("sms_offert", equalTo(10))
             .body("duree_validite", equalTo(120));
    }

    @Test
    public void testGetKitPrepayeById_NotFound() {
        given()
          .pathParam("id", "kit-prepaye-unknown")
          .when().get("/offres/prepaye/{id}")
          .then()
             .statusCode(404)
             .body(equalTo("Forfait prépayé avec ID 'kit-prepaye-unknown' non trouvé"));
    }
    @Test
    public void testGetForfaitsBloques() {
      given().when().get("/offres/forfait-bloque").then().statusCode(200)
      .body("$",notNullValue()).body("$", hasSize(4))
      .body("[0].id", equalTo("forfait-bloque-1000"))
      .body("[1].id", equalTo("forfait-bloque-2000"))
      .body("[2].id", equalTo("forfait-bloque-3000"))
      .body("[3].id", equalTo("forfait-bloque-5000"));
    }

    @Test
    public void testGetForfaitBloqueById() {
        given()
          .pathParam("id", "forfait-bloque-1000")
          .when().get("/offres/forfait-bloque/{id}")
          .then()
             .statusCode(200)
             .body("id", equalTo("forfait-bloque-1000"))
             .body("prix", equalTo(1060))
             .body("credit", equalTo(1000))
             .body("sms_offert", equalTo(20))
              .body("url", equalTo("https://www.opt.nc/particuliers/mobile/quel-forfait-choisir/forfait-bloque-1000"));
    }
    @Test
    public void testGetForfaitBloqueById_NotFound() {
        given()
          .pathParam("id", "forfait-bloque-unknown")
          .when().get("/offres/forfait-bloque/{id}")
          .then()
             .statusCode(404)
             .body(equalTo("Forfait bloqué avec ID 'forfait-bloque-unknown' non trouvé"));
    }
}