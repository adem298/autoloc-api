package tn.esprit.autoloc.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Equipement;
import tn.esprit.autoloc.service.EquipementService;

import java.util.List;

@RestController
@RequestMapping("/api/equipements")
@RequiredArgsConstructor
@CrossOrigin("*")
public class EquipementController {

    private final EquipementService equipementService;

    @PostMapping
    public ResponseEntity<Equipement> ajouterEquipement(@RequestBody Equipement equipement) {
        return new ResponseEntity<>(equipementService.ajouterEquipement(equipement), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Equipement> modifierEquipement(@PathVariable Long id, @RequestBody Equipement equipement) {
        return ResponseEntity.ok(equipementService.modifierEquipement(id, equipement));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerEquipement(@PathVariable Long id) {
        equipementService.supprimerEquipement(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Equipement> obtenirEquipementParId(@PathVariable Long id) {
        return ResponseEntity.ok(equipementService.obtenirEquipementParId(id));
    }

    @GetMapping
    public ResponseEntity<List<Equipement>> obtenirTousEquipements() {
        return ResponseEntity.ok(equipementService.obtenirTousEquipements());
    }
}