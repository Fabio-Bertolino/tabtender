package it.epicode.tabtender.prodotti_ordinati;

import it.epicode.tabtender.prodotti.Prodotto;
import it.epicode.tabtender.prodotti.ProdottoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public class ProdottoOrdinatoService {
    @Autowired
    private ProdottoOrdinatoRepository prodottoOrdinatoRepository;
    @Autowired
    private ProdottoRepository prodottoRepository;

//    public ProdottoOrdinato fromRequest(ProdottoOrdinatoRequest request) {
//        Prodotto prodotto = prodottoRepository
//                .findById(request.getProdottoId())
//                .orElseThrow(() -> new EntityNotFoundException("Prodotto non trovato con ID: " + request.getProdottoId()));
//        return new ProdottoOrdinato(null, prodotto, request.getQuantita());
//    }
//
//    public List<ProdottoOrdinato> fromRequestList(List<ProdottoOrdinatoRequest> requests) {
//        return requests.stream().map(this::fromRequest).toList();
//    }
//
//    public double calcolaTotale(List<ProdottoOrdinato> prodottiOrdinati) {
//        return prodottiOrdinati.stream()
//                .mapToDouble(po -> po.getProdotto().getPrezzo() * po.getQuantita())
//                .sum();
//    }


    public ProdottoOrdinato fromRequest(ProdottoOrdinatoRequest request) {
        Long id = request.getProdotto().getId();

        Prodotto prodotto = prodottoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Prodotto non trovato con ID: " + id));

        return new ProdottoOrdinato(null, prodotto, request.getQuantita());
    }

    public List<ProdottoOrdinato> fromRequestList(List<ProdottoOrdinatoRequest> requests) {
        return requests.stream().map(this::fromRequest).toList();
    }

    public double calcolaTotale(List<ProdottoOrdinato> prodottiOrdinati) {
        return prodottiOrdinati.stream()
                .mapToDouble(po -> po.getProdotto().getPrezzo() * po.getQuantita())
                .sum();
    }
}
