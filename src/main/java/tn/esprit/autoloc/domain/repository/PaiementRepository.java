package tn.esprit.autoloc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.autoloc.domain.ModePaiement;
import tn.esprit.autoloc.domain.Paiement;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface PaiementRepository extends JpaRepository<Paiement, Long> {

    List<Paiement> findByContratIdContrat(Long idContrat);

    List<Paiement> findByModePaiement(ModePaiement modePaiement);

    @Query("SELECT COALESCE(SUM(p.montant), 0) FROM Paiement p WHERE p.contrat.idContrat = :idContrat")
    BigDecimal sumMontantByContratId(@Param("idContrat") Long idContrat);
}