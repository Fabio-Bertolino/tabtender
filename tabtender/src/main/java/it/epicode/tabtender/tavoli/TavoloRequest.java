package it.epicode.tabtender.tavoli;

import it.epicode.tabtender.ordini.Ordine;
import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TavoloRequest {
    @PositiveOrZero
    private int numeroPosti;

    private boolean disponibile;

    private Ordine ordine;
}
