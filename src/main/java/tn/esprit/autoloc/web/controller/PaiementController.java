package tn.esprit.autoloc.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.ModePaiement;
import tn.esprit.autoloc.domain.Paiement;
import tn.esprit.autoloc.service.PaiementService;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/paiements")
@RequiredArgsConstructor
@CrossOrigin("*")
public class PaiementController {

    private final PaiementService paiementService;

    @PostMapping("/contrat/{idContrat}")
    public ResponseEntity<Paiement> ajouterPaiement(
            @PathVariable Long idContrat,
            @RequestParam BigDecimal montant,
            @RequestParam ModePaiement modePaiement) {
        return new ResponseEntity<>(paiementService.ajouterPaiement(idContrat, montant, modePaiement), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerPaiement(@PathVariable Long id) {
        paiementService.supprimerPaiement(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paiement> obtenirPaiementParId(@PathVariable Long id) {
        return ResponseEntity.ok(paiementService.obtenirPaiementParId(id));
    }

    @GetMapping("/contrat/{idContrat}")
    public ResponseEntity<List<Paiement>> obtenirPaiementsParContrat(@PathVariable Long idContrat) {
        return ResponseEntity.ok(paiementService.obtenirPaiementsParContrat(idContrat));
    }

    @GetMapping
    public ResponseEntity<List<Paiement>> obtenirTousPaiements() {
        return ResponseEntity.ok(paiementService.obtenirTousPaiements());
    }
}