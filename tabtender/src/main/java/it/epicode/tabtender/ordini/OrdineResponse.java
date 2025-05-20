package it.epicode.tabtender.ordini;

import it.epicode.tabtender.prodotti.Prodotto;
import it.epicode.tabtender.tavoli.Tavolo;
import jakarta.validation.Valid;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdineResponse {
    private Long id;
    private List<Prodotto> prodotti;
    double prezzoTotale;
//    private Tavolo tavolo;
}
