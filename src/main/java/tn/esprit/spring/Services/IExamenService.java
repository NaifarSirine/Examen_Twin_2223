package tn.esprit.spring.Services;

import tn.esprit.spring.DAO.Entities.Banque;
import tn.esprit.spring.DAO.Entities.Compte;
import tn.esprit.spring.DAO.Entities.Transaction;
import tn.esprit.spring.DAO.Entities.TypeTransaction;

import java.util.List;

public interface IExamenService {
    public Banque ajouterBank(Banque Banque);
    public Compte ajouterCompteEtAffecterAAgence(Compte compte, String agenceBank);
    public void getAllTransactionByDate();
    public List<Transaction> getAllTransactionByBankId(long idBank);
    String ajouterVirement(Transaction transaction);
    String ajouterRetrait(Transaction transaction);
    String ajouterVersement(Transaction transaction);
    List<Transaction> getAllTransactionByTypeAndCompte(TypeTransaction type, long idCompte);

}
