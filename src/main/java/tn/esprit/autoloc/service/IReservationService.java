package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Reservation;
import java.time.LocalDate;
import java.util.List;

public interface IReservationService {
    Reservation ajouterReservation(Long idClient, Long idVehicule, LocalDate dateDebut, LocalDate dateFin);
    Reservation confirmerReservation(Long idReservation);
    Reservation annulerReservation(Long idReservation);
    void supprimerReservation(Long id);
    Reservation obtenirReservationParId(Long id);
    List<Reservation> obtenirReservationsParClient(Long idClient);
    List<Reservation> obtenirToutesReservations();
}