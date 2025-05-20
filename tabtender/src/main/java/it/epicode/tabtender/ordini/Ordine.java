package it.epicode.tabtender.ordini;

import it.epicode.tabtender.prodotti.Prodotto;
import it.epicode.tabtender.tavoli.Tavolo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ordini")
public class Ordine {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToMany
    private List<Prodotto> prodotti;

    double prezzoTotale;

//    @OneToOne
//    private Tavolo tavolo;
}
