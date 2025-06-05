package it.epicode.tabtender.prodotti_ordinati;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdottoOrdinatoResponse {

    private Long id;
    private Long prodottoId;
    private int quantita;
}
