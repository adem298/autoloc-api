package tn.esprit.autoloc.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Employe;
import tn.esprit.autoloc.service.IEmployeService;

import java.util.List;

@RestController
@RequestMapping("/api/employes")
@RequiredArgsConstructor
public class EmployeController {

    private final IEmployeService employeService;

    @PostMapping
    public ResponseEntity<Employe> ajouterEmploye(@RequestBody Employe employe) {
        return new ResponseEntity<>(employeService.ajouterEmploye(employe), HttpStatus.CREATED);
    }

    @PostMapping("/agence/{idAgence}")
    public ResponseEntity<Employe> ajouterEmployeEtAffecter(@RequestBody Employe employe, @PathVariable Long idAgence) {
        return new ResponseEntity<>(employeService.ajouterEmploye(employe, idAgence), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Employe>> obtenirTousEmployes() {
        return ResponseEntity.ok(employeService.obtenirTousEmployes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employe> obtenirEmployeParId(@PathVariable Long id) {
        return ResponseEntity.ok(employeService.obtenirEmployeParId(id));
    }

    @GetMapping("/agence/{idAgence}")
    public ResponseEntity<List<Employe>> obtenirEmployesParAgence(@PathVariable Long idAgence) {
        return ResponseEntity.ok(employeService.obtenirEmployesParAgence(idAgence));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employe> modifierEmploye(@PathVariable Long id, @RequestBody Employe employe) {
        return ResponseEntity.ok(employeService.modifierEmploye(id, employe));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerEmploye(@PathVariable Long id) {
        employeService.supprimerEmploye(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{idEmploye}/desaffecter")
    public ResponseEntity<Employe> desaffecterEmploye(@PathVariable Long idEmploye) {
        return ResponseEntity.ok(employeService.desaffecterEmploye(idEmploye));
    }

    @PutMapping("/{idEmploye}/affecter-agence/{idAgence}")
    public ResponseEntity<Employe> affecterEmploye(@PathVariable Long idEmploye, @PathVariable Long idAgence) {
        return ResponseEntity.ok(employeService.affecterEmploye(idEmploye, idAgence));
    }
}