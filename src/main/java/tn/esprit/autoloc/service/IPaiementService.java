package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.ModePaiement;
import tn.esprit.autoloc.domain.Paiement;
import java.math.BigDecimal;
import java.util.List;

public interface IPaiementService {
    Paiement ajouterPaiement(Long idContrat, BigDecimal montant, ModePaiement modePaiement);
    void supprimerPaiement(Long id);
    Paiement obtenirPaiementParId(Long id);
    List<Paiement> obtenirPaiementsParContrat(Long idContrat);
    List<Paiement> obtenirTousPaiements();
}