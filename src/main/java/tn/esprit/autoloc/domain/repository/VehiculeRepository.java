package tn.esprit.autoloc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.autoloc.domain.CategorieVehicule;
import tn.esprit.autoloc.domain.StatutVehicule;
import tn.esprit.autoloc.domain.Vehicule;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface VehiculeRepository extends JpaRepository<Vehicule, Long> {

    // 1. Rechercher par immatriculation
    Optional<Vehicule> findByImmatriculation(String immatriculation);

    // 2. Rechercher les véhicules par statut (ex: DISPONIBLE)
    List<Vehicule> findByStatut(StatutVehicule statut);

    // 3. Rechercher par catégorie
    List<Vehicule> findByCategorie(CategorieVehicule categorie);

    // 4. Rechercher par marque et modèle
    List<Vehicule> findByMarqueAndModele(String marque, String modele);

    // 5. Rechercher les véhicules avec un tarif journalier inférieur ou égal à un montant
    List<Vehicule> findByTarifJournalierLessThanEqual(BigDecimal tarifMax);

    // 6. Rechercher les véhicules d'une agence
    List<Vehicule> findByAgenceIdAgence(Long idAgence);

    // 7. Requête JPQL : Rechercher les véhicules disponibles pour une période donnée
    @Query("SELECT v FROM Vehicule v WHERE v.statut = 'DISPONIBLE' " +
            "AND v.idVehicule NOT IN (" +
            "   SELECT r.vehicule.idVehicule FROM Reservation r " +
            "   WHERE r.statut <> 'ANNULEE' " +
            "   AND (r.dateDebut <= :dateFin AND r.dateFin >= :dateDebut)" +
            ")")
    List<Vehicule> findVehiculesDisponiblesPourPeriode(
            @Param("dateDebut") LocalDate dateDebut,
            @Param("dateFin") LocalDate dateFin
    );

    // 8. Requête JPQL : Statistiques du nombre de véhicules par catégorie
    @Query("SELECT v.categorie, COUNT(v) FROM Vehicule v GROUP BY v.categorie")
    List<Object[]> countVehiculesParCategorie();
}