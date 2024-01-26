package demo.test.lucas.integration.controller;

import demo.test.lucas.integration.config.RestAssuredConfiguration;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class DeleteVechileTest extends RestAssuredConfiguration {

    @Test
    public void shouldDeleteVehicleWithSuccessByIdAndValidateStatusNoContent() {

        given()
                .log().all()
        .when()
                .delete( "/1" )
        .then()
                .log().all()
                .statusCode(204)
        ;

    }
}
