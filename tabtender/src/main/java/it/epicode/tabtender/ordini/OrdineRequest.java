package it.epicode.tabtender.ordini;

import it.epicode.tabtender.prodotti_ordinati.ProdottoOrdinatoRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdineRequest {
    @Valid
    private List<ProdottoOrdinatoRequest> prodotti;
    private Long tavoloId;
    private String nomeUtente;
}
