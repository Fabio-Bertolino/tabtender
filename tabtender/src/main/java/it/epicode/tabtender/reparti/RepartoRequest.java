package it.epicode.tabtender.reparti;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RepartoRequest {
    @NotBlank
    private String nome;
}
