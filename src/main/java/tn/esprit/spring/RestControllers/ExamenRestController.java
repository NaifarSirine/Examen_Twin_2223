package tn.esprit.spring.RestControllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.DAO.Entities.Banque;
import tn.esprit.spring.DAO.Entities.Compte;
import tn.esprit.spring.DAO.Entities.Transaction;
import tn.esprit.spring.DAO.Entities.TypeTransaction;
import tn.esprit.spring.Services.IExamenService;

import java.util.List;

@RestController
@AllArgsConstructor
public class ExamenRestController {
    IExamenService iExamenService;

    @PostMapping("ajouterBank")
    public Banque ajouterBank(@RequestBody Banque bank) {
        return iExamenService.ajouterBank(bank);
    }

    @PostMapping("ajouterCompteEtAffecterAAgence/{agenceBank}")
    public Compte ajouterCompteEtAffecterAAgence(@RequestBody Compte compte, @PathVariable String agenceBank) {
        return iExamenService.ajouterCompteEtAffecterAAgence(compte, agenceBank);
    }

    @GetMapping("getAllTransactionByBankId/{idBank}")
    public List<Transaction> getAllTransactionByBankId(@PathVariable long idBank){
        return iExamenService.getAllTransactionByBankId(idBank);
    }
    @PostMapping("addVirement")
    public String ajouterVirement(@RequestBody Transaction transaction){
        return iExamenService.ajouterVirement(transaction);
    }
    @PostMapping("addRetrait")
    public String ajouterRetrait(@RequestBody Transaction transaction){
        return iExamenService.ajouterRetrait(transaction);
    }
    @PostMapping("addVersement")
    public String ajouterVersement(@RequestBody Transaction transaction){
        return iExamenService.ajouterVersement(transaction);
    }
    @GetMapping("getTransactionByBank")
    public List<Transaction> transactionByBank(@RequestParam long idBank){
        return iExamenService.getAllTransactionByBankId(idBank);
    }
    @GetMapping("getTransactionByCompteAndType")
    public List<Transaction> transactionByCompteAndType(@RequestParam long idCompte, @RequestParam TypeTransaction type){
        return iExamenService.getAllTransactionByTypeAndCompte(type, idCompte);
    }
}