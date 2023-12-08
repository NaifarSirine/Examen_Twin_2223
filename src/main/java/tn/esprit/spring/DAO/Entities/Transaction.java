package tn.esprit.spring.DAO.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long idTransaction;
    double montant;
    @Enumerated(EnumType.STRING)
    TypeTransaction typeTransaction;
    LocalDate dateTransaction;
    @ManyToOne
    @JsonIgnore
    Compte expediteur;
    @ManyToOne
    @JsonIgnore
    Compte destinataire;


}
