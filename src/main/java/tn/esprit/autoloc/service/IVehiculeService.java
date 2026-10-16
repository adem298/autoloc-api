package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.StatutVehicule;
import tn.esprit.autoloc.domain.Vehicule;
import java.time.LocalDate;
import java.util.List;

public interface IVehiculeService {
    Vehicule ajouterVehicule(Vehicule vehicule, Long idAgence);
    Vehicule modifierVehicule(Long id, Vehicule vehicule);
    void supprimerVehicule(Long id);
    Vehicule obtenirVehiculeParId(Long id);
    List<Vehicule> obtenirTousVehicules();
    Vehicule changerStatutVehicule(Long idVehicule, StatutVehicule nouveauStatut);
    Vehicule affecterEquipementsAuVehicule(Long idVehicule, List<Long> idsEquipements);
    List<Vehicule> rechercherVehiculesDisponibles(LocalDate dateDebut, LocalDate dateFin);
}