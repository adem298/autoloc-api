package tn.esprit.autoloc.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Contrat;
import tn.esprit.autoloc.domain.ModePaiement;
import tn.esprit.autoloc.service.ContratService;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/contrats")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ContratController {

    private final ContratService contratService;

    @PostMapping("/generer/{idReservation}")
    public ResponseEntity<Contrat> genererContrat(@PathVariable Long idReservation) {
        return new ResponseEntity<>(contratService.genererContrat(idReservation), HttpStatus.CREATED);
    }

    @PutMapping("/{id}/valider")
    public ResponseEntity<Contrat> validerContrat(@PathVariable Long id) {
        return ResponseEntity.ok(contratService.validerContrat(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerContrat(@PathVariable Long id) {
        contratService.supprimerContrat(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contrat> obtenirContratParId(@PathVariable Long id) {
        return ResponseEntity.ok(contratService.obtenirContratParId(id));
    }

    @GetMapping
    public ResponseEntity<List<Contrat>> obtenirTousContrats() {
        return ResponseEntity.ok(contratService.obtenirTousContrats());
    }

    @PostMapping("/{id}/paiements")
    public ResponseEntity<Contrat> ajouterPaiementAuContrat(
            @PathVariable Long id,
            @RequestParam BigDecimal montant,
            @RequestParam ModePaiement modePaiement) {
        return ResponseEntity.ok(contratService.ajouterPaiementAuContrat(id, montant, modePaiement));
    }
}