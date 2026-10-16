package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Agence;
import java.util.List;

public interface IAgenceService {
    Agence ajouterAgence(Agence agence);
    Agence modifierAgence(Long id, Agence agence);
    void supprimerAgence(Long id);
    Agence obtenirAgenceParId(Long id);
    List<Agence> obtenirToutesAgences();
}