package tn.esprit.autoloc.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Reservation;
import tn.esprit.autoloc.service.ReservationService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping
    public ResponseEntity<Reservation> ajouterReservation(
            @RequestParam Long idClient,
            @RequestParam Long idVehicule,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateDebut,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFin) {
        return new ResponseEntity<>(reservationService.ajouterReservation(idClient, idVehicule, dateDebut, dateFin), HttpStatus.CREATED);
    }

    @PutMapping("/{id}/confirmer")
    public ResponseEntity<Reservation> confirmerReservation(@PathVariable Long id) {
        return ResponseEntity.ok(reservationService.confirmerReservation(id));
    }

    @PutMapping("/{id}/annuler")
    public ResponseEntity<Reservation> annulerReservation(@PathVariable Long id) {
        return ResponseEntity.ok(reservationService.annulerReservation(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerReservation(@PathVariable Long id) {
        reservationService.supprimerReservation(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> obtenirReservationParId(@PathVariable Long id) {
        return ResponseEntity.ok(reservationService.obtenirReservationParId(id));
    }

    @GetMapping("/client/{idClient}")
    public ResponseEntity<List<Reservation>> obtenirReservationsParClient(@PathVariable Long idClient) {
        return ResponseEntity.ok(reservationService.obtenirReservationsParClient(idClient));
    }

    @GetMapping
    public ResponseEntity<List<Reservation>> obtenirToutesReservations() {
        return ResponseEntity.ok(reservationService.obtenirToutesReservations());
    }
}