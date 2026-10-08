package tn.esprit.autoloc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.autoloc.domain.Reservation;
import tn.esprit.autoloc.domain.StatutReservation;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    // 1. Historique des réservations d'un client
    List<Reservation> findByClientIdClient(Long idClient);

    // 2. Réservations d'un véhicule
    List<Reservation> findByVehiculeIdVehicule(Long idVehicule);

    // 3. Rechercher par statut
    List<Reservation> findByStatut(StatutReservation statut);

    // 4. Rechercher les réservations commençant dans un intervalle de dates
    List<Reservation> findByDateDebutBetween(LocalDate debut, LocalDate fin);

    // 5. Requête JPQL : Vérifier les chevauchements de réservation pour un véhicule
    @Query("SELECT r FROM Reservation r WHERE r.vehicule.idVehicule = :idVehicule " +
            "AND r.statut <> 'ANNULEE' " +
            "AND (r.dateDebut <= :dateFin AND r.dateFin >= :dateDebut)")
    List<Reservation> findChevauchements(
            @Param("idVehicule") Long idVehicule,
            @Param("dateDebut") LocalDate dateDebut,
            @Param("dateFin") LocalDate dateFin
    );
}