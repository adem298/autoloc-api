package tn.esprit.autoloc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.*;
import tn.esprit.autoloc.exception.BusinessException;
import tn.esprit.autoloc.domain.exception.ResourceNotFoundException;
import tn.esprit.autoloc.repository.ClientRepository;
import tn.esprit.autoloc.repository.ReservationRepository;
import tn.esprit.autoloc.repository.VehiculeRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ReservationService implements IReservationService {

    private final ReservationRepository reservationRepository;
    private final ClientRepository clientRepository;
    private final VehiculeRepository vehiculeRepository;

    @Override
    public Reservation ajouterReservation(Long idClient, Long idVehicule, LocalDate dateDebut, LocalDate dateFin) {
        if (dateDebut.isBefore(LocalDate.now())) {
            throw new BusinessException("La date de début ne peut pas être dans le passé.");
        }
        if (dateFin.isBefore(dateDebut)) {
            throw new BusinessException("La date de fin doit être >= date de début.");
        }

        Client client = clientRepository.findById(idClient)
                .orElseThrow(() -> new ResourceNotFoundException("Client introuvable ID : " + idClient));

        Vehicule vehicule = vehiculeRepository.findById(idVehicule)
                .orElseThrow(() -> new ResourceNotFoundException("Véhicule introuvable ID : " + idVehicule));

        if (vehicule.getStatut() == StatutVehicule.MAINTENANCE) {
            throw new BusinessException("Ce véhicule est en maintenance.");
        }

        List<Reservation> chevauchements = reservationRepository.findChevauchements(idVehicule, dateDebut, dateFin);
        if (!chevauchements.isEmpty()) {
            throw new BusinessException("Véhicule non disponible sur cette période.");
        }

        Reservation reservation = new Reservation();
        reservation.setClient(client);
        reservation.setVehicule(vehicule);
        reservation.setDateDebut(dateDebut);
        reservation.setDateFin(dateFin);
        reservation.setStatut(StatutReservation.EN_ATTENTE);

        return reservationRepository.save(reservation);
    }

    @Override
    public Reservation confirmerReservation(Long idReservation) {
        Reservation reservation = obtenirReservationParId(idReservation);
        if (reservation.getStatut() == StatutReservation.ANNULEE) {
            throw new BusinessException("Impossible de confirmer une réservation annulée.");
        }
        reservation.setStatut(StatutReservation.CONFIRMEE);
        reservation.getVehicule().setStatut(StatutVehicule.LOUE);
        return reservationRepository.save(reservation);
    }

    @Override
    public Reservation annulerReservation(Long idReservation) {
        Reservation reservation = obtenirReservationParId(idReservation);
        reservation.setStatut(StatutReservation.ANNULEE);
        reservation.getVehicule().setStatut(StatutVehicule.DISPONIBLE);
        return reservationRepository.save(reservation);
    }

    @Override
    public void supprimerReservation(Long id) {
        Reservation res = obtenirReservationParId(id);
        reservationRepository.delete(res);
    }

    @Override
    @Transactional(readOnly = true)
    public Reservation obtenirReservationParId(Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Réservation introuvable ID : " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Reservation> obtenirReservationsParClient(Long idClient) {
        return reservationRepository.findByClientIdClient(idClient);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Reservation> obtenirToutesReservations() {
        return reservationRepository.findAll();
    }
}