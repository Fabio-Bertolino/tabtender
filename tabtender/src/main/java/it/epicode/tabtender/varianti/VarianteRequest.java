package it.epicode.tabtender.varianti;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VarianteRequest {
    @NotBlank(message = "Inserire il nome")
    private String nome;
    @PositiveOrZero(message = "Inserire il prezzo")
    private double prezzo;
}
