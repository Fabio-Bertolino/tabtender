package it.epicode.tabtender.tavoli;

import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TavoloPutRequest {
    @PositiveOrZero
    private int numeroPosti;
}
