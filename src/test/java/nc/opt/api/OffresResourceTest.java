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
}