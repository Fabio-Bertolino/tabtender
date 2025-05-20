package it.epicode.tabtender.common;

import com.github.javafaker.Faker;
import it.epicode.tabtender.ordini.Ordine;
import it.epicode.tabtender.ordini.OrdineRepository;
import it.epicode.tabtender.ordini.OrdineService;
import it.epicode.tabtender.prodotti.Prodotto;
import it.epicode.tabtender.prodotti.ProdottoRepository;
import it.epicode.tabtender.prodotti.ProdottoService;
import it.epicode.tabtender.tavoli.Tavolo;
import it.epicode.tabtender.tavoli.TavoloRepository;
import it.epicode.tabtender.tavoli.TavoloService;
import it.epicode.tabtender.varianti.Variante;
import it.epicode.tabtender.varianti.VarianteRepository;
import it.epicode.tabtender.varianti.VarianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class CommonRunner implements CommandLineRunner {

    @Autowired
    private Faker faker;

    @Autowired
    private VarianteRepository varianteRepository;
    @Autowired
    private VarianteService varianteService;
    @Autowired
    private ProdottoRepository prodottoRepository;
    @Autowired
    private ProdottoService prodottoService;
    @Autowired
    private OrdineService ordineService;
    @Autowired
    private OrdineRepository ordineRepository;
    @Autowired
    private TavoloService tavoloService;
    @Autowired
    private TavoloRepository tavoloRepository;

    @Override
    public void run(String... args) throws Exception {

        Variante variante = new Variante();
        variante.setNome("Macchiato Caldo");
        variante.setPrezzo(0);
        varianteRepository.save(variante);

        Variante variante2 = new Variante();
        variante2.setNome("Con Panna");
        variante2.setPrezzo(0.5);
        varianteRepository.save(variante2);

        Variante variante3 = new Variante();
        variante3.setNome("Con Gelato");
        variante3.setPrezzo(0.7);
        varianteRepository.save(variante3);

        Prodotto prodotto1 = new Prodotto();
        prodotto1.setNome("Espresso");
        prodotto1.setPrezzo(1.2);
        List<Variante> espressoVarianti = List.of(variante, variante2, variante3);
        prodotto1.setVarianti(espressoVarianti);
        prodottoRepository.save(prodotto1);

        Prodotto prodotto2 = new Prodotto();
        prodotto2.setNome("Cappuccino");
        prodotto2.setPrezzo(1.5);
        List<Variante> cappuccinoVarianti = List.of(variante2);
        prodotto2.setVarianti(cappuccinoVarianti);
        prodottoRepository.save(prodotto2);

        Tavolo tavolo1 = new Tavolo();
        tavolo1.setNumeroPosti(4);

        Tavolo tavolo2 = new Tavolo();
        tavolo2.setNumeroPosti(2);

        Ordine ordine1 = new Ordine();
        List<Prodotto> prodottiOrdine1 = List.of(prodotto1, prodotto2);
        ordine1.setProdotti(prodottiOrdine1);
        ordine1.setPrezzoTotale(prodottiOrdine1.stream().map(prodotto -> prodotto.getPrezzo()).reduce(0.0, Double::sum));
        tavolo1.setDisponibile(false);
        tavolo1.setOrdine(ordine1);
        ordineRepository.save(ordine1);
        tavoloRepository.save(tavolo1);

        Ordine ordine2 = new Ordine();
        List<Prodotto> prodottiOrdine2 = List.of(prodotto1);
        ordine2.setProdotti(prodottiOrdine2);
        ordine2.setPrezzoTotale(prodottiOrdine2.stream().map(prodotto -> prodotto.getPrezzo()).reduce(0.0, Double::sum));
        tavolo2.setDisponibile(false);
        tavolo2.setOrdine(ordine2);
        ordineRepository.save(ordine2);
        tavoloRepository.save(tavolo2);
    }
}
