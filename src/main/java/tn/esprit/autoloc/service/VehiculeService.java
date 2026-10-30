package tn.esprit.autoloc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.domain.Equipement;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.domain.exception.ResourceNotFoundException;
import tn.esprit.autoloc.repository.AgenceRepository;
import tn.esprit.autoloc.repository.EquipementRepository;
import tn.esprit.autoloc.repository.VehiculeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehiculeService implements IVehiculeService {

    private final VehiculeRepository vehiculeRepository;
    private final AgenceRepository agenceRepository;
    private final EquipementRepository equipementRepository;

    @Override
    public Vehicule ajouterVehicule(Vehicule vehicule) {
        return vehiculeRepository.save(vehicule);
    }

    @Override
    public Vehicule modifierVehicule(Long id, Vehicule vehiculeDetails) {
        Vehicule vehicule = obtenirVehiculeParId(id);
        vehicule.setImmatriculation(vehiculeDetails.getImmatriculation());
        vehicule.setMarque(vehiculeDetails.getMarque());
        vehicule.setModele(vehiculeDetails.getModele());

        vehicule.setStatut(vehiculeDetails.getStatut());
        vehicule.setCategorie(vehiculeDetails.getCategorie());
        return vehiculeRepository.save(vehicule);
    }

    @Override
    public void supprimerVehicule(Long id) {
        Vehicule vehicule = obtenirVehiculeParId(id);
        vehiculeRepository.delete(vehicule);
    }

    @Override
    public Vehicule obtenirVehiculeParId(Long id) {
        return vehiculeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Véhicule non trouvé avec l'ID : " + id));
    }

    @Override
    public List<Vehicule> obtenirTousVehicules() {
        return vehiculeRepository.findAll();
    }

    @Override
    @Transactional
    public Vehicule affecterVehicule(Long idVehicule, Long idAgence) {
        Vehicule vehicule = obtenirVehiculeParId(idVehicule);
        Agence agence = agenceRepository.findById(idAgence)
                .orElseThrow(() -> new ResourceNotFoundException("Agence non trouvée avec l'ID : " + idAgence));
        vehicule.setAgence(agence);
        return vehiculeRepository.save(vehicule);
    }

    @Override
    @Transactional
    public Vehicule affecterEquipement(Long idVehicule, Long idEquipement) {
        Vehicule vehicule = obtenirVehiculeParId(idVehicule);
        Equipement equipement = equipementRepository.findById(idEquipement)
                .orElseThrow(() -> new ResourceNotFoundException("Équipement non trouvé avec l'ID : " + idEquipement));

        vehicule.getEquipements().add(equipement);
        return vehiculeRepository.save(vehicule);
    }

    @Override
    @Transactional
    public Vehicule desaffecterEquipement(Long idVehicule, Long idEquipement) {
        Vehicule vehicule = obtenirVehiculeParId(idVehicule);
        Equipement equipement = equipementRepository.findById(idEquipement)
                .orElseThrow(() -> new ResourceNotFoundException("Équipement non trouvé avec l'ID : " + idEquipement));

        vehicule.getEquipements().remove(equipement);
        return vehiculeRepository.save(vehicule);
    }
}