package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Employe;
import java.util.List;

public interface IEmployeService {
    Employe ajouterEmploye(Employe employe, Long idAgence);
    Employe modifierEmploye(Long id, Employe employe);
    void supprimerEmploye(Long id);
    Employe obtenirEmployeParId(Long id);
    List<Employe> obtenirEmployesParAgence(Long idAgence);
    List<Employe> obtenirTousEmployes();
}