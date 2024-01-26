package demo.test.lucas.integration.config;

import demo.test.lucas.ApiApplication;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import jakarta.annotation.PostConstruct;
import org.apache.commons.lang3.SystemProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(classes = { ApiApplication.class }, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public abstract class RestAssuredConfiguration {

    @LocalServerPort
    int localServerPort;
    private String BASE_PATH = "/vehicle";
    @PostConstruct
    public void setup( ) {
        RestAssured.port = localServerPort;
        RestAssured.basePath = BASE_PATH;
        RestAssured.requestSpecification = new RequestSpecBuilder().setContentType( ContentType.JSON ).build();
        RestAssured.responseSpecification = new ResponseSpecBuilder().build();
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

}
