package demo.test.lucas.web;

import demo.test.lucas.domain.Vehicle;
import demo.test.lucas.repository.VehicleRepository;
import demo.test.lucas.web.dto.VehicleDetailsDto;
import demo.test.lucas.web.dto.VehicleDto;
import demo.test.lucas.web.dto.VehicleUpdateDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping( "/vehicle" )
public class VehicleController {
    @Autowired
    private VehicleRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity addVehicle( @Valid @RequestBody VehicleDto vehicleDto, UriComponentsBuilder uriBuilder ) {
        Vehicle newVehicle = repository.save( new Vehicle( vehicleDto ) );
        var uri = uriBuilder.path( "/vehicle/{id}" ).buildAndExpand( newVehicle.getId() ).toUri();
        return ResponseEntity.created( uri ).body( new VehicleDetailsDto( newVehicle ) );

    }

    @GetMapping( "/{id}" )
    public ResponseEntity getVehicle( @PathVariable Long id ) {
        Vehicle vehicle = repository.getReferenceById( id );
        return ResponseEntity.ok( new VehicleDetailsDto( vehicle ) );
    }

    @PutMapping()
    @Transactional
    public ResponseEntity updateVehicle( @Valid @RequestBody VehicleUpdateDto vehicleUpdateDto ) {
        Vehicle vehicle = repository.getReferenceById( vehicleUpdateDto.id() );
        vehicle.updateVehicle( vehicleUpdateDto );

        return ResponseEntity.ok( new VehicleDetailsDto( vehicle ) );
    }

    @DeleteMapping( "/{id}" )
    @Transactional
    public ResponseEntity< Vehicle > deleteVehicle( @PathVariable Long id ) {
        repository.deleteById( id );
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity< Page< VehicleDetailsDto > > getAllVehicles( @PageableDefault( size = 10, sort = { "id" } ) Pageable pageable ) {
        Page< VehicleDetailsDto > vehicles = repository.findAll( pageable ).map( VehicleDetailsDto :: new );
        return ResponseEntity.ok( vehicles );
    }

}
