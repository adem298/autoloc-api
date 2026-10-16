package tn.esprit.autoloc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Equipement;
import tn.esprit.autoloc.domain.exception.ResourceNotFoundException;
import tn.esprit.autoloc.repository.EquipementRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class EquipementService implements IEquipementService {

    private final EquipementRepository equipementRepository;

    @Override
    public Equipement ajouterEquipement(Equipement equipement) {
        return equipementRepository.save(equipement);
    }

    @Override
    public Equipement modifierEquipement(Long id, Equipement equipement) {
        Equipement eq = obtenirEquipementParId(id);
        eq.setLibelle(equipement.getLibelle());
        return equipementRepository.save(eq);
    }

    @Override
    public void supprimerEquipement(Long id) {
        Equipement eq = obtenirEquipementParId(id);
        equipementRepository.delete(eq);
    }

    @Override
    @Transactional(readOnly = true)
    public Equipement obtenirEquipementParId(Long id) {
        return equipementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Équipement introuvable ID : " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Equipement> obtenirTousEquipements() {
        return equipementRepository.findAll();
    }
}