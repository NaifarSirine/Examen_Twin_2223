package tn.esprit.spring.DAO.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tn.esprit.spring.DAO.Entities.Compte;
import tn.esprit.spring.DAO.Entities.Transaction;
import tn.esprit.spring.DAO.Entities.TypeTransaction;

import java.time.LocalDate;
import java.util.List;

public interface TransactionRepo extends JpaRepository<Transaction,Long> {

    List<Transaction> findByDateTransaction(LocalDate localDate);
    @Query("select transaction from Transaction transaction  join Banque bank on ((transaction.destinataire member bank.comptes) or (transaction.expediteur member bank.comptes)) where bank.idBanque=:idBank")
    List<Transaction> findByBankIdBank(long idBank);
    List<Transaction> findByDestinataireIdCompteOrExpediteurIdCompteAndType(long destintaire, long expediteur, TypeTransaction type);

}
