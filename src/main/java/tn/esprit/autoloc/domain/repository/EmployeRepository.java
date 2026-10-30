package tn.esprit.autoloc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.autoloc.domain.Employe;
import tn.esprit.autoloc.domain.RoleEmploye;

import java.util.List;

@Repository
public interface EmployeRepository extends JpaRepository<Employe, Long> {

    @Query("SELECT e FROM Employe e WHERE e.agence.idAgence = :idAgence")
    List<Employe> findByAgenceIdAgence(@Param("idAgence") Long idAgence);

    List<Employe> findByRole(RoleEmploye role);
}