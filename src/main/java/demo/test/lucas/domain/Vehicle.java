package demo.test.lucas.domain;

import demo.test.lucas.web.dto.VehicleDto;
import demo.test.lucas.web.dto.VehicleUpdateDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.Optional;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity( name = "Vehicles" )
@Table( name = "vehicles" )
public class Vehicle {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "Id" )
    private Long id;
    @Column( name = "Plate" )
    @NotNull
    private String plate;
    @Column( name = "Model" )
    private String model;
    @Column( name = "Manufacturer" )
    private String manufacturer;
    @Column( name = "Color" )
    private String color;
    @Column( name = "Status" )
    @NotNull
    private boolean status;

    public Vehicle( VehicleDto vehicleDto ) {
        this.plate = vehicleDto.plate();
        this.model = vehicleDto.model();
        this.manufacturer = vehicleDto.manufacturer();
        this.color = vehicleDto.color();
        this.status = vehicleDto.status();
    }

    public void updateVehicle( VehicleUpdateDto vehicleUpdateDto ) {

        Optional.ofNullable( vehicleUpdateDto.model() )
                .ifPresent( model -> this.model = model );
        Optional.ofNullable( vehicleUpdateDto.manufacturer() )
                .ifPresent( manufacturer -> this.manufacturer = manufacturer );
        Optional.ofNullable( vehicleUpdateDto.color() )
                .ifPresent( color -> this.color = color );
        Optional.of( vehicleUpdateDto.status() )
                .ifPresent( status -> this.status = status );
    }

}
