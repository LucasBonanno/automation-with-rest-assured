package demo.test.lucas.web.dto;

import demo.test.lucas.domain.Vehicle;

public record VehicleDetailsDto(
        Long id,
        String plate,
        String model,
        String manufacturer,
        String color,
        boolean status) {

    public VehicleDetailsDto( Vehicle vehicle ) {
        this( vehicle.getId(),
                vehicle.getPlate(),
                vehicle.getModel(),
                vehicle.getManufacturer(),
                vehicle.getColor(),
                vehicle.isStatus() );
    }

}