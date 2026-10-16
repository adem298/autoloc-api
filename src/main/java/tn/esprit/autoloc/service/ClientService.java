package tn.esprit.autoloc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Client;
import tn.esprit.autoloc.domain.exception.BusinessException;
import tn.esprit.autoloc.domain.exception.ResourceNotFoundException;
import tn.esprit.autoloc.repository.ClientRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ClientService implements IClientService {

    private final ClientRepository clientRepository;

    @Override
    public Client ajouterClient(Client client) {
        if (clientRepository.existsByEmail(client.getEmail())) {
            throw new BusinessException("Un client existe déjà avec cet email : " + client.getEmail());
        }
        if (clientRepository.existsByNumPermis(client.getNumPermis())) {
            throw new BusinessException("Un client existe déjà avec ce numéro de permis : " + client.getNumPermis());
        }
        client.setDateInscription(LocalDate.now());
        return clientRepository.save(client);
    }

    @Override
    public Client modifierClient(Long id, Client clientModifie) {
        Client c = obtenirClientParId(id);
        c.setNom(clientModifie.getNom());
        c.setPrenom(clientModifie.getPrenom());
        c.setTelephone(clientModifie.getTelephone());
        return clientRepository.save(c);
    }

    @Override
    public void supprimerClient(Long id) {
        Client client = obtenirClientParId(id);
        clientRepository.delete(client);
    }

    @Override
    @Transactional(readOnly = true)
    public Client obtenirClientParId(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client introuvable avec l'ID : " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public Client obtenirClientParEmail(String email) {
        return clientRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Client introuvable avec l'email : " + email));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Client> obtenirTousClients() {
        return clientRepository.findAll();
    }
}