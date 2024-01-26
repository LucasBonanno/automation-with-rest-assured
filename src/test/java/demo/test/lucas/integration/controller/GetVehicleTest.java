package demo.test.lucas.integration.controller;

import demo.test.lucas.integration.config.RestAssuredConfiguration;
import demo.test.lucas.web.dto.VehicleDetailsDto;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

public class GetVehicleTest extends RestAssuredConfiguration {

    @Test
    public void shouldGetVehicleWithSuccessByIdAndValidateResponseBody() {

        VehicleDetailsDto getVehicleDetailsDto = AddVehicleTest.getVehicleDetailsDto();

        given()
                .log().all()
        .when()
                .get( "/1" )
        .then()
                .log().all()
                .statusCode( 200 )
                .body( "id", is( getVehicleDetailsDto.id().intValue() ) )
                .body( "model", is( getVehicleDetailsDto.model() ) )
                .body( "manufacturer", is( getVehicleDetailsDto.manufacturer() ) )
                .body( "color", is( getVehicleDetailsDto.color() ) )
                .body( "status", is( true ) )
        ;
    }
}
