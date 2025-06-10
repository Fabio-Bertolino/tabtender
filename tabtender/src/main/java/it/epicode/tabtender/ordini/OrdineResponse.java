package it.epicode.tabtender.ordini;

import com.fasterxml.jackson.annotation.JsonFormat;
import it.epicode.tabtender.prodotti.Prodotto;
import it.epicode.tabtender.prodotti_ordinati.ProdottoOrdinato;
import it.epicode.tabtender.tavoli.Tavolo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdineResponse {
    private Long id;
    private List<ProdottoOrdinato> prodotti;
    double prezzoTotale;
//    @JsonIgnore
    private Long tavoloId;
    private String nomeUtente;
    @JsonFormat(pattern = "yyyy-MM-dd; HH:mm")
    private LocalDateTime dataOrdine;
    @JsonFormat(pattern = "yyyy-MM-dd; HH:mm")
    private LocalDateTime ultimaModifica;
}
