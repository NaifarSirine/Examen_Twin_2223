package tn.esprit.spring.DAO.Entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Banque {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long idBanque;
    String nom;
    String agence;
    String adresse;
    @OneToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    List<Compte> comptes= new ArrayList<>();
}
