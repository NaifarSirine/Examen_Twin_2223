package tn.esprit.spring.DAO.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.DAO.Entities.Banque;

public interface BanqueRepo extends JpaRepository<Banque,Long> {
    Banque findByAgence(String agence);
}
