package tn.esprit.autoloc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.domain.Equipement;
import tn.esprit.autoloc.domain.StatutVehicule;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.exception.BusinessException;
import tn.esprit.autoloc.domain.exception.ResourceNotFoundException;
import tn.esprit.autoloc.repository.AgenceRepository;
import tn.esprit.autoloc.repository.EquipementRepository;
import tn.esprit.autoloc.repository.VehiculeRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class VehiculeService implements IVehiculeService {

    private final VehiculeRepository vehiculeRepository;
    private final AgenceRepository agenceRepository;
    private final EquipementRepository equipementRepository;

    @Override
    public Vehicule ajouterVehicule(Vehicule vehicule, Long idAgence) {
        if (vehiculeRepository.findByImmatriculation(vehicule.getImmatriculation()).isPresent()) {
            throw new BusinessException("Immatriculation déjà existante : " + vehicule.getImmatriculation());
        }
        Agence agence = agenceRepository.findById(idAgence)
                .orElseThrow(() -> new ResourceNotFoundException("Agence introuvable avec l'ID : " + idAgence));

        vehicule.setAgence(agence);
        if (vehicule.getStatut() == null) {
            vehicule.setStatut(StatutVehicule.DISPONIBLE);
        }
        return vehiculeRepository.save(vehicule);
    }

    @Override
    public Vehicule modifierVehicule(Long id, Vehicule vehiculeModifie) {
        Vehicule v = obtenirVehiculeParId(id);
        v.setMarque(vehiculeModifie.getMarque());
        v.setModele(vehiculeModifie.getModele());
        v.setCategorie(vehiculeModifie.getCategorie());
        v.setTarifJournalier(vehiculeModifie.getTarifJournalier());
        return vehiculeRepository.save(v);
    }

    @Override
    public void supprimerVehicule(Long id) {
        Vehicule v = obtenirVehiculeParId(id);
        vehiculeRepository.delete(v);
    }

    @Override
    @Transactional(readOnly = true)
    public Vehicule obtenirVehiculeParId(Long id) {
        return vehiculeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Véhicule introuvable avec l'ID : " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Vehicule> obtenirTousVehicules() {
        return vehiculeRepository.findAll();
    }

    @Override
    public Vehicule changerStatutVehicule(Long idVehicule, StatutVehicule nouveauStatut) {
        Vehicule v = obtenirVehiculeParId(idVehicule);
        v.setStatut(nouveauStatut);
        return vehiculeRepository.save(v);
    }

    @Override
    public Vehicule affecterEquipementsAuVehicule(Long idVehicule, List<Long> idsEquipements) {
        Vehicule v = obtenirVehiculeParId(idVehicule);
        List<Equipement> equipements = equipementRepository.findAllById(idsEquipements);
        v.getEquipements().addAll(equipements);
        return vehiculeRepository.save(v);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Vehicule> rechercherVehiculesDisponibles(LocalDate dateDebut, LocalDate dateFin) {
        if (dateDebut.isAfter(dateFin)) {
            throw new BusinessException("La date de début doit être antérieure à la date de fin.");
        }
        return vehiculeRepository.findVehiculesDisponiblesPourPeriode(dateDebut, dateFin);
    }
}