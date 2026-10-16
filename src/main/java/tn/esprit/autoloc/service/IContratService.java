package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Contrat;
import tn.esprit.autoloc.domain.ModePaiement;
import java.math.BigDecimal;
import java.util.List;

public interface IContratService {
    Contrat genererContrat(Long idReservation);
    Contrat validerContrat(Long idContrat);
    void supprimerContrat(Long id);
    Contrat obtenirContratParId(Long id);
    List<Contrat> obtenirTousContrats();
    Contrat ajouterPaiementAuContrat(Long idContrat, BigDecimal montant, ModePaiement modePaiement);
}