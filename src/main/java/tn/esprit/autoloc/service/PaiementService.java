package tn.esprit.autoloc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Contrat;
import tn.esprit.autoloc.domain.ModePaiement;
import tn.esprit.autoloc.domain.Paiement;
import tn.esprit.autoloc.domain.exception.ResourceNotFoundException;
import tn.esprit.autoloc.repository.ContratRepository;
import tn.esprit.autoloc.repository.PaiementRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PaiementService implements IPaiementService {

    private final PaiementRepository paiementRepository;
    private final ContratRepository contratRepository;

    @Override
    public Paiement ajouterPaiement(Long idContrat, BigDecimal montant, ModePaiement modePaiement) {
        Contrat contrat = contratRepository.findById(idContrat)
                .orElseThrow(() -> new ResourceNotFoundException("Contrat introuvable ID : " + idContrat));

        Paiement p = new Paiement();
        p.setMontant(montant);
        p.setDatePaiement(LocalDate.now());
        p.setModePaiement(modePaiement);
        p.setContrat(contrat);

        return paiementRepository.save(p);
    }

    @Override
    public void supprimerPaiement(Long id) {
        Paiement p = obtenirPaiementParId(id);
        paiementRepository.delete(p);
    }

    @Override
    @Transactional(readOnly = true)
    public Paiement obtenirPaiementParId(Long id) {
        return paiementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paiement introuvable ID : " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Paiement> obtenirPaiementsParContrat(Long idContrat) {
        return paiementRepository.findByContratIdContrat(idContrat);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Paiement> obtenirTousPaiements() {
        return paiementRepository.findAll();
    }
}