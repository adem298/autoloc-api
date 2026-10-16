package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Maintenance;
import java.util.List;

public interface IMaintenanceService {
    Maintenance ajouterMaintenance(Maintenance maintenance, Long idVehicule);
    Maintenance modifierMaintenance(Long id, Maintenance maintenance);
    void supprimerMaintenance(Long id);
    Maintenance obtenirMaintenanceParId(Long id);
    List<Maintenance> obtenirMaintenancesParVehicule(Long idVehicule);
    List<Maintenance> obtenirToutesMaintenances();
}