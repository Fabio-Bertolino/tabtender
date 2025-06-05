package it.epicode.tabtender.reparti;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import it.epicode.tabtender.prodotti.Prodotto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "reparti")
public class Reparto {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @OneToMany(mappedBy = "reparto", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Prodotto> prodotti;
}
