package tn.esprit.autoloc.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.service.AgenceService;

import java.util.List;

@RestController
@RequestMapping("/api/agences")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AgenceController {

    private final AgenceService agenceService;

    @PostMapping
    public ResponseEntity<Agence> ajouterAgence(@RequestBody Agence agence) {
        return new ResponseEntity<>(agenceService.ajouterAgence(agence), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Agence> modifierAgence(@PathVariable Long id, @RequestBody Agence agence) {
        return ResponseEntity.ok(agenceService.modifierAgence(id, agence));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerAgence(@PathVariable Long id) {
        agenceService.supprimerAgence(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Agence> obtenirAgenceParId(@PathVariable Long id) {
        return ResponseEntity.ok(agenceService.obtenirAgenceParId(id));
    }

    @GetMapping
    public ResponseEntity<List<Agence>> obtenirToutesAgences() {
        return ResponseEntity.ok(agenceService.obtenirToutesAgences());
    }
}