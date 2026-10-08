package tn.esprit.autoloc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.autoloc.domain.Client;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    // 1. Rechercher par email
    Optional<Client> findByEmail(String email);

    // 2. Rechercher par numéro de permis
    Optional<Client> findByNumPermis(String numPermis);

    // 3. Recherche partielle par nom (case-insensitive)
    List<Client> findByNomContainingIgnoreCase(String nom);

    // 4. Vérifier l'existence d'un email
    boolean existsByEmail(String email);

    // 5. Vérifier l'existence d'un numéro de permis
    boolean existsByNumPermis(String numPermis);
}