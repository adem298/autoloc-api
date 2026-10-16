package tn.esprit.autoloc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.*;
import tn.esprit.autoloc.exception.BusinessException;
import tn.esprit.autoloc.domain.exception.ResourceNotFoundException;
import tn.esprit.autoloc.repository.ContratRepository;
import tn.esprit.autoloc.repository.ReservationRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ContratService implements IContratService {

    private final ContratRepository contratRepository;
    private final ReservationRepository reservationRepository;

    @Override
    public Contrat genererContrat(Long idReservation) {
        Reservation reservation = reservationRepository.findById(idReservation)
                .orElseThrow(() -> new ResourceNotFoundException("Réservation introuvable ID : " + idReservation));

        if (contratRepository.findByReservationIdReservation(idReservation).isPresent()) {
            throw new BusinessException("Un contrat existe déjà pour cette réservation.");
        }

        long nbJours = ChronoUnit.DAYS.between(reservation.getDateDebut(), reservation.getDateFin());
        if (nbJours <= 0) nbJours = 1;

        BigDecimal montantTotal = reservation.getVehicule().getTarifJournalier().multiply(BigDecimal.valueOf(nbJours));

        Contrat contrat = new Contrat();
        contrat.setReservation(reservation);
        contrat.setDateSignature(LocalDate.now());
        contrat.setMontantTotal(montantTotal);
        contrat.setValide(false);

        return contratRepository.save(contrat);
    }

    @Override
    public Contrat validerContrat(Long idContrat) {
        Contrat contrat = obtenirContratParId(idContrat);
        contrat.setValide(true);
        return contratRepository.save(contrat);
    }

    @Override
    public void supprimerContrat(Long id) {
        Contrat contrat = obtenirContratParId(id);
        contratRepository.delete(contrat);
    }

    @Override
    @Transactional(readOnly = true)
    public Contrat obtenirContratParId(Long id) {
        return contratRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contrat introuvable ID : " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Contrat> obtenirTousContrats() {
        return contratRepository.findAll();
    }

    @Override
    public Contrat ajouterPaiementAuContrat(Long idContrat, BigDecimal montant, ModePaiement modePaiement) {
        Contrat contrat = obtenirContratParId(idContrat);
        Paiement p = new Paiement(null, montant, LocalDate.now(), modePaiement, contrat);
        contrat.getPaiements().add(p);
        return contratRepository.save(contrat);
    }
}