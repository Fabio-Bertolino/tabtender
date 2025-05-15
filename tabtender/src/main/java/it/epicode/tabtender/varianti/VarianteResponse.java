package it.epicode.tabtender.varianti;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VarianteResponse {
    private Long id;
    private String nome;
    private double prezzo;
}
