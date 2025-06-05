package it.epicode.tabtender.common;

import com.github.javafaker.Faker;
import it.epicode.tabtender.ordini.Ordine;
import it.epicode.tabtender.ordini.OrdineRepository;
import it.epicode.tabtender.ordini.OrdineService;
import it.epicode.tabtender.prodotti.Prodotto;
import it.epicode.tabtender.prodotti.ProdottoRepository;
import it.epicode.tabtender.prodotti.ProdottoService;
import it.epicode.tabtender.prodotti_ordinati.ProdottoOrdinato;
import it.epicode.tabtender.reparti.Reparto;
import it.epicode.tabtender.reparti.RepartoRepository;
import it.epicode.tabtender.reparti.RepartoService;
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
    @Autowired
    private RepartoService repartoService;
    @Autowired
    private RepartoRepository repartoRepository;

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

        Reparto reparto1 = new Reparto();
        reparto1.setNome("Caffetteria");
        repartoRepository.save(reparto1);

        Reparto reparto2 = new Reparto();
        reparto2.setNome("Cibo");
        repartoRepository.save(reparto2);

        Prodotto prodotto1 = new Prodotto();
        prodotto1.setNome("Espresso");
        prodotto1.setPrezzo(1.2);
        List<Variante> espressoVarianti = List.of(variante, variante2, variante3);
//        prodotto1.setVarianti(espressoVarianti);
        prodotto1.setReparto(reparto1);
        prodottoRepository.save(prodotto1);

        Prodotto prodotto2 = new Prodotto();
        prodotto2.setNome("Cappuccino");
        prodotto2.setPrezzo(1.5);
        List<Variante> cappuccinoVarianti = List.of(variante2);
//        prodotto2.setVarianti(cappuccinoVarianti);
        prodotto2.setReparto(reparto1);
        prodottoRepository.save(prodotto2);

        Prodotto prodotto3 = new Prodotto();
        prodotto3.setNome("Maritozzo");
        prodotto3.setPrezzo(1.8);
        List<Variante> maritozzoVarianti = List.of(variante3);
//        prodotto3.setVarianti(maritozzoVarianti);
        prodotto3.setReparto(reparto2);
        prodottoRepository.save(prodotto3);

        Tavolo tavolo1 = new Tavolo();
        tavolo1.setNumeroPosti(4);
        tavoloRepository.save(tavolo1);

        Tavolo tavolo2 = new Tavolo();
        tavolo2.setNumeroPosti(2);
        tavoloRepository.save(tavolo2);

        Tavolo tavolo3 = new Tavolo();
        tavolo3.setNumeroPosti(2);
        tavoloRepository.save(tavolo3);

        ProdottoOrdinato po1 = new ProdottoOrdinato(null, prodotto1, 2);
        ProdottoOrdinato po2 = new ProdottoOrdinato(null, prodotto2, 1);

        Ordine ordine1 = new Ordine();
        ordine1.setProdotti(List.of(po1, po2));
        ordine1.setPrezzoTotale(
                po1.getProdotto()
                        .getPrezzo() * po1.getQuantita() + po2.getProdotto().getPrezzo() * po2.getQuantita());
        ordine1.setTavolo(tavolo1);
        ordineRepository.save(ordine1);

        tavolo1.setDisponibile(false);
        tavolo1.setOrdine(ordine1);
        tavoloRepository.save(tavolo1);

        ProdottoOrdinato po3 = new ProdottoOrdinato(null, prodotto1, 1);

        Ordine ordine2 = new Ordine();
        ordine2.setProdotti(List.of(po3));
        ordine2.setPrezzoTotale(po3.getProdotto().getPrezzo() * po3.getQuantita());
        ordine2.setTavolo(tavolo2);
        ordineRepository.save(ordine2);

        tavolo2.setDisponibile(false);
        tavolo2.setOrdine(ordine2);
        tavoloRepository.save(tavolo2);

        ProdottoOrdinato po4 = new ProdottoOrdinato(null, prodotto1, 1);
    }
}
