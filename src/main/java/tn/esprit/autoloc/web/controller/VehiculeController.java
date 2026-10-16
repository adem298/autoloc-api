package tn.esprit.autoloc.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.StatutVehicule;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.service.VehiculeService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/vehicules")
@RequiredArgsConstructor
@CrossOrigin("*")
public class VehiculeController {

    private final VehiculeService vehiculeService;

    @PostMapping("/agence/{idAgence}")
    public ResponseEntity<Vehicule> ajouterVehicule(@RequestBody Vehicule vehicule, @PathVariable Long idAgence) {
        return new ResponseEntity<>(vehiculeService.ajouterVehicule(vehicule, idAgence), HttpStatus.CREATED);
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

    @GetMapping("/{id}")
    public ResponseEntity<Vehicule> obtenirVehiculeParId(@PathVariable Long id) {
        return ResponseEntity.ok(vehiculeService.obtenirVehiculeParId(id));
    }

    @GetMapping
    public ResponseEntity<List<Vehicule>> obtenirTousVehicules() {
        return ResponseEntity.ok(vehiculeService.obtenirTousVehicules());
    }

    @PatchMapping("/{id}/statut")
    public ResponseEntity<Vehicule> changerStatutVehicule(@PathVariable Long id, @RequestParam StatutVehicule nouveauStatut) {
        return ResponseEntity.ok(vehiculeService.changerStatutVehicule(id, nouveauStatut));
    }

    @PostMapping("/{id}/equipements")
    public ResponseEntity<Vehicule> affecterEquipementsAuVehicule(@PathVariable Long id, @RequestBody List<Long> idsEquipements) {
        return ResponseEntity.ok(vehiculeService.affecterEquipementsAuVehicule(id, idsEquipements));
    }

    @GetMapping("/disponibles")
    public ResponseEntity<List<Vehicule>> rechercherVehiculesDisponibles(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateDebut,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFin) {
        return ResponseEntity.ok(vehiculeService.rechercherVehiculesDisponibles(dateDebut, dateFin));
    }
}