package it.epicode.tabtender.tavoli;

import it.epicode.tabtender.ordini.Ordine;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tavoli")
public class Tavolo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private int numeroPosti;

    @Column(nullable = false)
    private boolean disponibile = true;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "ordine_id")
    private Ordine ordine;
}
