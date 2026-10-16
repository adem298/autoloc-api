package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Equipement;
import java.util.List;

public interface IEquipementService {
    Equipement ajouterEquipement(Equipement equipement);
    Equipement modifierEquipement(Long id, Equipement equipement);
    void supprimerEquipement(Long id);
    Equipement obtenirEquipementParId(Long id);
    List<Equipement> obtenirTousEquipements();
}