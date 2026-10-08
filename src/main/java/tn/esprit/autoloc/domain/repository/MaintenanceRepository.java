package tn.esprit.autoloc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.autoloc.domain.Maintenance;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MaintenanceRepository extends JpaRepository<Maintenance, Long> {

    List<Maintenance> findByVehiculeIdVehicule(Long idVehicule);

    List<Maintenance> findByDateDebutLessThanEqualAndDateFinGreaterThanEqual(
            LocalDate dateRef1, LocalDate dateRef2
    );
}