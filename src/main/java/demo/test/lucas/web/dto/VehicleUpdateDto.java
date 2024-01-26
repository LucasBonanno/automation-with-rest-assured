package demo.test.lucas.web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record VehicleUpdateDto(
        @NotNull
        Long id,
        String model,
        String manufacturer,
        String color,
        boolean status) {
}
