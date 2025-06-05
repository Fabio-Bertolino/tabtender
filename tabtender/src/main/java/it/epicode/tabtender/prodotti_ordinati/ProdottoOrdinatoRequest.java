package it.epicode.tabtender.prodotti_ordinati;

import it.epicode.tabtender.prodotti.Prodotto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdottoOrdinatoRequest {
    private Prodotto prodotto;
    private int quantita;
}
