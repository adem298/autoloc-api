package tn.esprit.autoloc.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.service.IVehiculeService;

import java.util.List;

@RestController
@RequestMapping("/api/vehicules")
@RequiredArgsConstructor
public class VehiculeController {

    private final IVehiculeService vehiculeService;

    @PostMapping
    public ResponseEntity<Vehicule> ajouterVehicule(@RequestBody Vehicule vehicule) {
        return new ResponseEntity<>(vehiculeService.ajouterVehicule(vehicule), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Vehicule>> obtenirTousVehicules() {
        return ResponseEntity.ok(vehiculeService.obtenirTousVehicules());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehicule> obtenirVehiculeParId(@PathVariable Long id) {
        return ResponseEntity.ok(vehiculeService.obtenirVehiculeParId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vehicule> modifierVehicule(@PathVariable Long id, @RequestBody Vehicule vehicule) {
        return ResponseEntity.ok(vehiculeService.modifierVehicule(id, vehicule));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerVehicule(@PathVariable Long id) {
        vehiculeService.supprimerVehicule(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{idVehicule}/affecter-agence/{idAgence}")
    public ResponseEntity<Vehicule> affecterVehicule(@PathVariable Long idVehicule, @PathVariable Long idAgence) {
        return ResponseEntity.ok(vehiculeService.affecterVehicule(idVehicule, idAgence));
    }

    @PutMapping("/{idVehicule}/affecter-equipement/{idEquipement}")
    public ResponseEntity<Vehicule> affecterEquipement(@PathVariable Long idVehicule, @PathVariable Long idEquipement) {
        return ResponseEntity.ok(vehiculeService.affecterEquipement(idVehicule, idEquipement));
    }

    @PutMapping("/{idVehicule}/desaffecter-equipement/{idEquipement}")
    public ResponseEntity<Vehicule> desaffecterEquipement(@PathVariable Long idVehicule, @PathVariable Long idEquipement) {
        return ResponseEntity.ok(vehiculeService.desaffecterEquipement(idVehicule, idEquipement));
    }
}