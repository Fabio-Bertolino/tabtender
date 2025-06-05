package it.epicode.tabtender.prodotti;

import com.fasterxml.jackson.annotation.JsonBackReference;
import it.epicode.tabtender.reparti.Reparto;
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

//    @ManyToMany
//    private List<Variante> varianti;

//    private String note;

    @ManyToOne
    @JoinColumn(name = "reparto_id", nullable = false)
    @JsonBackReference
    private Reparto reparto;
}
