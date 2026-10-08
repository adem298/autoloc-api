package tn.esprit.autoloc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.esprit.autoloc.domain.Contrat;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ContratRepository extends JpaRepository<Contrat, Long> {

    // 1. Trouver un contrat à partir de sa réservation
    Optional<Contrat> findByReservationIdReservation(Long idReservation);

    // 2. Contrats validés
    List<Contrat> findByValideTrue();

    // 3. Contrats non validés
    List<Contrat> findByValideFalse();

    // 4. Requête JPQL : Calculer le Chiffre d'Affaires total (Contrats validés)
    @Query("SELECT COALESCE(SUM(c.montantTotal), 0) FROM Contrat c WHERE c.valide = true")
    BigDecimal totalChiffreAffaires();
}