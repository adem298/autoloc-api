package tn.esprit.autoloc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Maintenance;
import tn.esprit.autoloc.domain.StatutVehicule;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.domain.exception.ResourceNotFoundException;
import tn.esprit.autoloc.repository.MaintenanceRepository;
import tn.esprit.autoloc.repository.VehiculeRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MaintenanceService implements IMaintenanceService {

    private final MaintenanceRepository maintenanceRepository;
    private final VehiculeRepository vehiculeRepository;

    @Override
    public Maintenance ajouterMaintenance(Maintenance maintenance, Long idVehicule) {
        Vehicule v = vehiculeRepository.findById(idVehicule)
                .orElseThrow(() -> new ResourceNotFoundException("Véhicule introuvable ID : " + idVehicule));

        maintenance.setVehicule(v);
        v.setStatut(StatutVehicule.MAINTENANCE);
        return maintenanceRepository.save(maintenance);
    }

    @Override
    public Maintenance modifierMaintenance(Long id, Maintenance maintenance) {
        Maintenance m = obtenirMaintenanceParId(id);
        m.setDateDebut(maintenance.getDateDebut());
        m.setDateFin(maintenance.getDateFin());
        m.setDescription(maintenance.getDescription());
        return maintenanceRepository.save(m);
    }

    @Override
    public void supprimerMaintenance(Long id) {
        Maintenance m = obtenirMaintenanceParId(id);
        maintenanceRepository.delete(m);
    }

    @Override
    @Transactional(readOnly = true)
    public Maintenance obtenirMaintenanceParId(Long id) {
        return maintenanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Maintenance introuvable ID : " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Maintenance> obtenirMaintenancesParVehicule(Long idVehicule) {
        return maintenanceRepository.findByVehiculeIdVehicule(idVehicule);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Maintenance> obtenirToutesMaintenances() {
        return maintenanceRepository.findAll();
    }
}