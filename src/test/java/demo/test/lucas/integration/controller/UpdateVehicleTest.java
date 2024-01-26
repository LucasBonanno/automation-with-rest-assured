package demo.test.lucas.integration.controller;

import demo.test.lucas.integration.config.RestAssuredConfiguration;
import demo.test.lucas.web.dto.VehicleUpdateDto;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

public class UpdateVehicleTest extends RestAssuredConfiguration {

    @Test
    public void shouldUpdateVehicleWithSuccessAndValidateResponseBody() {

        VehicleUpdateDto vehicleUpdateDto = mockVehicleUpdateDto( 1L );

        given()
                .log().all()
                .body( vehicleUpdateDto )
        .when()
                .put()
        .then()
                .log().all()
                .statusCode( 200 )
                .body( "id", is( vehicleUpdateDto.id().intValue() ) )
                .body( "model", is( vehicleUpdateDto.model() ) )
                .body( "manufacturer", is( vehicleUpdateDto.manufacturer() ) )
                .body( "color", is( vehicleUpdateDto.color() ) )
                .body( "status", is( vehicleUpdateDto.status() ) )
        ;

    }

    @Test
    public void shouldNotUpdateVehicleAndValidateNotFoundException() {

        given()
                .log().all()
                .body( mockVehicleUpdateDto( 1234L ) )
        .when()
                .put()
        .then()
                .log().all()
                .statusCode( 404 )
        ;

    }

    private VehicleUpdateDto mockVehicleUpdateDto( Long id ) {
        return VehicleUpdateDto.builder()
                .id( id )
                .model( "Automation Update" )
                .manufacturer( "Nissan Update" )
                .color( "green update" )
                .status( false )
                .build();

    }

}