package demo.test.lucas.integration.controller;

import demo.test.lucas.integration.config.RestAssuredConfiguration;
import demo.test.lucas.web.dto.VehicleDetailsDto;
import demo.test.lucas.web.dto.VehicleDto;
import lombok.Getter;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.hasSize;

public class AddVehicleTest extends RestAssuredConfiguration {
    @Getter
    private static VehicleDetailsDto vehicleDetailsDto;
    @Test
    public void shouldAddVehicleWithSuccessAndValidateResponseBody() {
        VehicleDto vehicleDto = vehicleDto( "TEST0000", "Automation", "Nissan", "black" );

        vehicleDetailsDto =
        given()
                .log().all()
                .body( vehicleDto )
        .when()
                .post()
        .then()
                .log().all()
                .statusCode( 201 )
                .header("Location", endsWith("/vehicle/1"))
                .extract().as( VehicleDetailsDto.class );

        assertThat(vehicleDetailsDto.id()).isEqualTo(1);
        assertThat(vehicleDetailsDto.plate()).contains("TEST0000");
        assertThat(vehicleDetailsDto.model()).contains("Automation");
        assertThat(vehicleDetailsDto.manufacturer()).contains("Nissan");
        assertThat(vehicleDetailsDto.color()).contains("black");
        assertThat(vehicleDetailsDto.status()).isTrue();
    }

    @Test
    public void shouldNotAddVehicleAndValidateMessage() {
        VehicleDto vehicleDto = vehicleDto( "", "", "", "" );
        given()
                .log().all()
                .body( vehicleDto )
        .when()
                .post()
        .then()
                .log().all()
                .statusCode( 400 )
                .body( "$", hasSize( 5 ) )
                .body( "field", hasItems( "manufacturer", "plate","color", "model" ) )
                .body( "message",
                        hasItems( "Color can not to be blank",
                                  "Plate can not to be blank",
                                  "Model can not to be blank",
                                  "Manufacturer can not to be blank",
                                  "Invalid Vehicle Plate") )
        ;

    }

    private VehicleDto vehicleDto( String plate, String model, String manufacturer, String color ) {
        return VehicleDto.builder()
                .plate( plate )
                .model( model )
                .manufacturer( manufacturer )
                .color( color )
                .status( true )
                .build();
    }

}
