package tn.esprit.autoloc.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Employe;
import tn.esprit.autoloc.service.EmployeService;

import java.util.List;

@RestController
@RequestMapping("/api/employes")
@RequiredArgsConstructor
@CrossOrigin("*")
public class EmployeController {

    private final EmployeService employeService;

    @PostMapping("/agence/{idAgence}")
    public ResponseEntity<Employe> ajouterEmploye(@RequestBody Employe employe, @PathVariable Long idAgence) {
        return new ResponseEntity<>(employeService.ajouterEmploye(employe, idAgence), HttpStatus.CREATED);
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

    @GetMapping("/{id}")
    public ResponseEntity<Employe> obtenirEmployeParId(@PathVariable Long id) {
        return ResponseEntity.ok(employeService.obtenirEmployeParId(id));
    }

    @GetMapping("/agence/{idAgence}")
    public ResponseEntity<List<Employe>> obtenirEmployesParAgence(@PathVariable Long idAgence) {
        return ResponseEntity.ok(employeService.obtenirEmployesParAgence(idAgence));
    }

    @GetMapping
    public ResponseEntity<List<Employe>> obtenirTousEmployes() {
        return ResponseEntity.ok(employeService.obtenirTousEmployes());
    }
}