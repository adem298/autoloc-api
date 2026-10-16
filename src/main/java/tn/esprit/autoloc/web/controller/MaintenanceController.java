package tn.esprit.autoloc.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Maintenance;
import tn.esprit.autoloc.service.MaintenanceService;

import java.util.List;

@RestController
@RequestMapping("/api/maintenances")
@RequiredArgsConstructor
@CrossOrigin("*")
public class MaintenanceController {

    private final MaintenanceService maintenanceService;

    @PostMapping("/vehicule/{idVehicule}")
    public ResponseEntity<Maintenance> ajouterMaintenance(@RequestBody Maintenance maintenance, @PathVariable Long idVehicule) {
        return new ResponseEntity<>(maintenanceService.ajouterMaintenance(maintenance, idVehicule), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Maintenance> modifierMaintenance(@PathVariable Long id, @RequestBody Maintenance maintenance) {
        return ResponseEntity.ok(maintenanceService.modifierMaintenance(id, maintenance));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerMaintenance(@PathVariable Long id) {
        maintenanceService.supprimerMaintenance(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Maintenance> obtenirMaintenanceParId(@PathVariable Long id) {
        return ResponseEntity.ok(maintenanceService.obtenirMaintenanceParId(id));
    }

    @GetMapping("/vehicule/{idVehicule}")
    public ResponseEntity<List<Maintenance>> obtenirMaintenancesParVehicule(@PathVariable Long idVehicule) {
        return ResponseEntity.ok(maintenanceService.obtenirMaintenancesParVehicule(idVehicule));
    }

    @GetMapping
    public ResponseEntity<List<Maintenance>> obtenirToutesMaintenances() {
        return ResponseEntity.ok(maintenanceService.obtenirToutesMaintenances());
    }
}