package it.epicode.tabtender.reparti;

import it.epicode.tabtender.prodotti.Prodotto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RepartoResponse {
    private Long id;
    private String nome;
    private List<Prodotto> prodotti;
}
