package tn.esprit.autoloc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.domain.Employe;
import tn.esprit.autoloc.domain.exception.ResourceNotFoundException;
import tn.esprit.autoloc.repository.AgenceRepository;
import tn.esprit.autoloc.repository.EmployeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeService implements IEmployeService {

    private final EmployeRepository employeRepository;
    private final AgenceRepository agenceRepository;

    @Override
    public Employe ajouterEmploye(Employe employe) {
        return employeRepository.save(employe);
    }

    @Override
    @Transactional
    public Employe ajouterEmploye(Employe employe, Long idAgence) {
        if (idAgence != null) {
            Agence agence = agenceRepository.findById(idAgence)
                    .orElseThrow(() -> new ResourceNotFoundException("Agence non trouvée avec l'ID : " + idAgence));
            employe.setAgence(agence);
        }
        return employeRepository.save(employe);
    }

    @Override
    public Employe modifierEmploye(Long id, Employe employeDetails) {
        Employe employe = obtenirEmployeParId(id);
        employe.setNom(employeDetails.getNom());
        employe.setPrenom(employeDetails.getPrenom());
        employe.setRole(employeDetails.getRole());
        return employeRepository.save(employe);
    }

    @Override
    public void supprimerEmploye(Long id) {
        Employe employe = obtenirEmployeParId(id);
        employeRepository.delete(employe);
    }

    @Override
    public Employe obtenirEmployeParId(Long id) {
        return employeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employé non trouvé avec l'ID : " + id));
    }

    @Override
    public List<Employe> obtenirTousEmployes() {
        return employeRepository.findAll();
    }

    @Override
    public List<Employe> obtenirEmployesParAgence(Long idAgence) {
        return employeRepository.findByAgenceIdAgence(idAgence);
    }

    @Override
    @Transactional
    public Employe desaffecterEmploye(Long idEmploye) {
        Employe employe = obtenirEmployeParId(idEmploye);
        employe.setAgence(null);
        return employeRepository.save(employe);
    }

    @Override
    @Transactional
    public Employe affecterEmploye(Long idEmploye, Long idAgence) {
        Employe employe = obtenirEmployeParId(idEmploye);
        Agence agence = agenceRepository.findById(idAgence)
                .orElseThrow(() -> new ResourceNotFoundException("Agence non trouvée avec l'ID : " + idAgence));
        employe.setAgence(agence);
        return employeRepository.save(employe);
    }
}