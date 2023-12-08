package tn.esprit.spring.DAO.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.DAO.Entities.Compte;

public interface CompteRepo extends JpaRepository<Compte,Long> {
    Compte findByCode(long code);
}
