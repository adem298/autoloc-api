package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Vehicule;
import java.util.List;

public interface IVehiculeService {
    Vehicule ajouterVehicule(Vehicule vehicule);
    Vehicule modifierVehicule(Long id, Vehicule vehicule);
    void supprimerVehicule(Long id);
    Vehicule obtenirVehiculeParId(Long id);
    List<Vehicule> obtenirTousVehicules();

    // Scénario 2 : Affectation Agence (@ManyToOne)
    Vehicule affecterVehicule(Long idVehicule, Long idAgence);

    // Scénario 3 : Affectation / Désaffectation Équipement (@ManyToMany)
    Vehicule affecterEquipement(Long idVehicule, Long idEquipement);
    Vehicule desaffecterEquipement(Long idVehicule, Long idEquipement);
}