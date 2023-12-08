package tn.esprit.spring.Services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.spring.DAO.Entities.*;
import tn.esprit.spring.DAO.Repositories.BanqueRepo;
import tn.esprit.spring.DAO.Repositories.CompteRepo;
import tn.esprit.spring.DAO.Repositories.TransactionRepo;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ExamenService implements IExamenService {


    BanqueRepo banqueRepo;
    TransactionRepo transactionRepo;
    CompteRepo compteRepo;
    @Override
    public Banque ajouterBank(Banque Banque) {
        return banqueRepo.save(Banque);
    }

    @Override
    public Compte ajouterCompteEtAffecterAAgence(Compte compte, String agenceBank) {
        Banque banque= banqueRepo.findByAgence(agenceBank);
        // Banque parent + Ajout Cascade / Compte child
        banque.getComptes().add(compte);
        banqueRepo.save(banque);
        return compte;
    }

    @Override
    public void getAllTransactionByDate() {
        List<Transaction> transactions= transactionRepo.findByDateTransaction(LocalDate.now());
        log.info("La liste des transactions");
        for (Transaction  transaction: transactions) {
            log.info(transaction.getIdTransaction()+"");
        }
    }

    @Override
    public List<Transaction> getAllTransactionByBankId(long idBank) {
        return transactionRepo.findByBankIdBank(idBank);
    }

    @Override
    public String ajouterVirement(Transaction transaction) {
        transaction.setExpediteur(compteRepo.findByCode(transaction.getExpediteur().getCode()));
        transaction.setDestinataire(compteRepo.findByCode(transaction.getDestinataire().getCode()));
        if (transaction.getTypeTransaction().equals(TypeTransaction.VIREMENT) && (transaction.getExpediteur().getTypeCompte().equals(TypeCompte.EPARGNE))) {
            return "On ne peut pas faire un virement à partir d’un compte épargne";
        } else {
            if ( !(transaction.getExpediteur().getTypeCompte().equals(TypeCompte.EPARGNE)) && (transaction.getExpediteur().getSolde() < (transaction.getMontant() + 3))||(transaction.getExpediteur().getTypeCompte().equals(TypeCompte.EPARGNE)) && (transaction.getExpediteur().getSolde() < transaction.getMontant())) {

                return "On ne peut pas faire un virement : Solde insuffisant";
            } else {
                Compte expediteur = compteRepo.findByCode(transaction.getExpediteur().getCode());
                Compte destinataire = compteRepo.findByCode(transaction.getDestinataire().getCode());
                expediteur.setSolde(expediteur.getSolde() - (transaction.getMontant() + 3));
                destinataire.setSolde(destinataire.getSolde() + transaction.getMontant());
                transaction.setDateTransaction(LocalDate.now());
                compteRepo.save(expediteur);
                compteRepo.save(destinataire);
                transactionRepo.save(transaction);
                return "VIREMENT de "+transaction.getMontant()+" DT de compte "+expediteur.getIdCompte()+" vers le compte "+destinataire.getIdCompte()+" approuvée avec succès";
            }
        }
    }

    @Override
    public String ajouterRetrait(Transaction transaction) {
        transaction.setExpediteur(compteRepo.findByCode(transaction.getExpediteur().getCode()));
        if ((transaction.getTypeTransaction().equals(TypeTransaction.RETRAIT)&&(transaction.getExpediteur().getSolde() < transaction.getMontant()+2))){
            return "On ne peut pas faire un retrait : Solde insuffisant";
        } else {
            Compte expediteur = compteRepo.findByCode(transaction.getExpediteur().getCode());
            expediteur.setSolde(expediteur.getSolde() - (transaction.getMontant() + 2));
            compteRepo.save(expediteur);
            transactionRepo.save(transaction);
            return "RETRAIT de "+transaction.getMontant()+" DT de compte "+expediteur.getIdCompte()+" approuvée avec succès.";
        }
    }

    @Override
    public String ajouterVersement(Transaction transaction) {
        transaction.setDestinataire(compteRepo.findByCode(transaction.getDestinataire().getCode()));
        Compte destinataire = compteRepo.findByCode(transaction.getDestinataire().getCode());
        if (destinataire.getTypeCompte().equals(TypeCompte.EPARGNE)){
            destinataire.setSolde(destinataire.getSolde() + transaction.getMontant());
        }else {
            destinataire.setSolde(destinataire.getSolde() + (transaction.getMontant()-2));
        }
        compteRepo.save(destinataire);
        transactionRepo.save(transaction);
        return "Versement de "+transaction.getMontant()+" DT vers le compte "+destinataire.getIdCompte()+" approuvée avec succès.";
    }

    @Override
    public List<Transaction> getAllTransactionByTypeAndCompte(TypeTransaction type, long idCompte) {
        return transactionRepo.findByDestinataireIdCompteOrExpediteurIdCompteAndType(idCompte,idCompte,type);
    }
}
