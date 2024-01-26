package demo.test.lucas.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import org.springframework.validation.annotation.Validated;

@Validated
@Builder
public record VehicleDto(
        @NotBlank( message = "Plate can not to be blank" )
        @Pattern( regexp = "^[a-zA-Z0-9]+$", message = "Invalid Vehicle Plate" )
        String plate,
        @NotBlank( message = "Model can not to be blank" )
        String model,
        @NotBlank( message = "Manufacturer can not to be blank" )
        String manufacturer,
        @NotBlank( message = "Color can not to be blank" )
        String color,
        @NotNull
        boolean status) {


}
