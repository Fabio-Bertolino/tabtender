package it.epicode.tabtender.prodotti_ordinati;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.epicode.tabtender.prodotti.Prodotto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "prodotti_ordinati")
public class ProdottoOrdinato {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    private Prodotto prodotto;

    private int quantita;
}
