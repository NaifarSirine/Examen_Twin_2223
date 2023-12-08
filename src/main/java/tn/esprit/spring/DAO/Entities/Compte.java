package tn.esprit.spring.DAO.Entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Compte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long idCompte;
    @Enumerated(EnumType.STRING)
    TypeCompte typeCompte;
    long code;
    double solde;
}
