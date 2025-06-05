package it.epicode.tabtender.prodotti;

import it.epicode.tabtender.varianti.Variante;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdottoResponse {
    private Long id;
    private String nome;
    private Double prezzo;
//    private String immagine;
//    private List<Variante> varianti;
//    private String note;

    private Long repartoId;
    private String repartoNome;
}
