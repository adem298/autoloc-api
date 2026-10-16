package tn.esprit.autoloc.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Client;
import tn.esprit.autoloc.service.ClientService;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ClientController {

    private final ClientService clientService;

    @PostMapping
    public ResponseEntity<Client> ajouterClient(@RequestBody Client client) {
        return new ResponseEntity<>(clientService.ajouterClient(client), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> modifierClient(@PathVariable Long id, @RequestBody Client client) {
        return ResponseEntity.ok(clientService.modifierClient(id, client));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerClient(@PathVariable Long id) {
        clientService.supprimerClient(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> obtenirClientParId(@PathVariable Long id) {
        return ResponseEntity.ok(clientService.obtenirClientParId(id));
    }

    @GetMapping("/email")
    public ResponseEntity<Client> obtenirClientParEmail(@RequestParam String email) {
        return ResponseEntity.ok(clientService.obtenirClientParEmail(email));
    }

    @GetMapping
    public ResponseEntity<List<Client>> obtenirTousClients() {
        return ResponseEntity.ok(clientService.obtenirTousClients());
    }
}