package it.epicode.tabtender.common;

import com.github.javafaker.Faker;
import it.epicode.tabtender.prodotti.Prodotto;
import it.epicode.tabtender.prodotti.ProdottoRepository;
import it.epicode.tabtender.prodotti.ProdottoService;
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
    }
}
