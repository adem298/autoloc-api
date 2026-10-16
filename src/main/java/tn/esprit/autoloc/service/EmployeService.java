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
@Transactional
@RequiredArgsConstructor
public class EmployeService implements IEmployeService {

    private final EmployeRepository employeRepository;
    private final AgenceRepository agenceRepository;

    @Override
    public Employe ajouterEmploye(Employe employe, Long idAgence) {
        Agence agence = agenceRepository.findById(idAgence)
                .orElseThrow(() -> new ResourceNotFoundException("Agence introuvable ID : " + idAgence));
        employe.setAgence(agence);
        return employeRepository.save(employe);
    }

    @Override
    public Employe modifierEmploye(Long id, Employe employeModifie) {
        Employe emp = obtenirEmployeParId(id);
        emp.setNom(employeModifie.getNom());
        emp.setPrenom(employeModifie.getPrenom());
        emp.setRole(employeModifie.getRole());
        return employeRepository.save(emp);
    }

    @Override
    public void supprimerEmploye(Long id) {
        Employe emp = obtenirEmployeParId(id);
        employeRepository.delete(emp);
    }

    @Override
    @Transactional(readOnly = true)
    public Employe obtenirEmployeParId(Long id) {
        return employeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employé introuvable ID : " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Employe> obtenirEmployesParAgence(Long idAgence) {
        return employeRepository.findByAgenceIdAgence(idAgence);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Employe> obtenirTousEmployes() {
        return employeRepository.findAll();
    }
}