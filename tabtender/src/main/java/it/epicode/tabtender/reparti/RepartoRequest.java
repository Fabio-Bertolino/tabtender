package it.epicode.tabtender.reparti;

import it.epicode.tabtender.prodotti.Prodotto;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RepartoRequest {
    @NotBlank
    private String nome;

    private List<Prodotto> prodotti;
}
