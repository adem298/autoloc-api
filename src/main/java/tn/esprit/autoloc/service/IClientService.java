package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Client;
import java.util.List;

public interface IClientService {
    Client ajouterClient(Client client);
    Client modifierClient(Long id, Client client);
    void supprimerClient(Long id);
    Client obtenirClientParId(Long id);
    Client obtenirClientParEmail(String email);
    List<Client> obtenirTousClients();
}