package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Employe;
import java.util.List;

public interface IEmployeService {
    Employe ajouterEmploye(Employe employe);
    Employe ajouterEmploye(Employe employe, Long idAgence); // Surcharge avec ID agence
    Employe modifierEmploye(Long id, Employe employe);
    void supprimerEmploye(Long id);
    Employe obtenirEmployeParId(Long id);
    List<Employe> obtenirTousEmployes();
    List<Employe> obtenirEmployesParAgence(Long idAgence);

    // Méthodes Métier de Mouvement RH (@ManyToOne)
    Employe desaffecterEmploye(Long idEmploye);
    Employe affecterEmploye(Long idEmploye, Long idAgence);
}