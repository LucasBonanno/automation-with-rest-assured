package demo.test.lucas.repository;

import demo.test.lucas.domain.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository< Vehicle, Long > {
    Vehicle findByPlate( String plate );
    boolean existsByPlate( String plate );
    List< Vehicle> findByStatus( boolean status );
}
