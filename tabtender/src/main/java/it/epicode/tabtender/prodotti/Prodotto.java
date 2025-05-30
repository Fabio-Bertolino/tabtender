package it.epicode.tabtender.prodotti;

import it.epicode.tabtender.varianti.Variante;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "prodotti")
public class Prodotto {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private Double prezzo;

    private String immagine;

    @ManyToMany
    private List<Variante> varianti;

    private String note;
}
