package tn.esprit.autoloc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.domain.exception.ResourceNotFoundException;
import tn.esprit.autoloc.repository.AgenceRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AgenceService implements IAgenceService {

    private final AgenceRepository agenceRepository;

    @Override
    public Agence ajouterAgence(Agence agence) {
        return agenceRepository.save(agence);
    }

    @Override
    public Agence modifierAgence(Long id, Agence agenceModifiee) {
        Agence agence = obtenirAgenceParId(id);
        agence.setNom(agenceModifiee.getNom());
        agence.setVille(agenceModifiee.getVille());
        agence.setAdresse(agenceModifiee.getAdresse());
        agence.setTelephone(agenceModifiee.getTelephone());
        return agenceRepository.save(agence);
    }

    @Override
    public void supprimerAgence(Long id) {
        Agence agence = obtenirAgenceParId(id);
        agenceRepository.delete(agence);
    }

    @Override
    @Transactional(readOnly = true)
    public Agence obtenirAgenceParId(Long id) {
        return agenceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Agence introuvable avec l'ID : " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Agence> obtenirToutesAgences() {
        return agenceRepository.findAll();
    }
}