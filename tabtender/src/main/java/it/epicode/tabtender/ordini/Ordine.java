package it.epicode.tabtender.ordini;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.epicode.tabtender.prodotti.Prodotto;
import it.epicode.tabtender.prodotti_ordinati.ProdottoOrdinato;
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

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "ordine_id")
    private List<ProdottoOrdinato> prodotti;

    double prezzoTotale;

    @OneToOne(mappedBy = "ordine")
    @JsonIgnore
    private Tavolo tavolo;
}
