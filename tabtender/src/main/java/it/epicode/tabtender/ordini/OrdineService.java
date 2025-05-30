package it.epicode.tabtender.ordini;

import it.epicode.tabtender.common.CommonResponse;
import it.epicode.tabtender.prodotti.Prodotto;
import it.epicode.tabtender.tavoli.Tavolo;
import it.epicode.tabtender.tavoli.TavoloRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public class OrdineService {
    @Autowired
    private OrdineRepository ordineRepository;
    @Autowired
    private TavoloRepository tavoloRepository;

    public CommonResponse saveOrdine(OrdineRequest request) {
        Ordine ordine = new Ordine();
        List<Prodotto> prodotti = request.getProdotti();
        ordine.setProdotti(prodotti);
        double prezzoTotale = prodotti.stream()
                .mapToDouble(p -> {
                    double variantiPrezzo = p.getVarianti() != null
                            ? p.getVarianti().stream().mapToDouble(v -> v.getPrezzo()).sum()
                            : 0;
                    return p.getPrezzo() + variantiPrezzo;
                })
                .sum();
        Tavolo tavolo = tavoloRepository.findById(request.getTavoloId()).orElseThrow(() -> new EntityNotFoundException("Tavolo non trovato con id: " + request.getTavoloId()));
        ordineRepository.save(ordine);

        tavolo.setOrdine(ordine);
        tavoloRepository.save(tavolo);
        return new CommonResponse(ordine.getId());
    }

    public void updateOrdine(Long id, OrdineRequest request) {
        Ordine ordine = ordineRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ordine non trovato "));
        List<Prodotto> prodotti = request.getProdotti();
        ordine.setProdotti(prodotti);
        ordine.setPrezzoTotale(prodotti.stream().mapToDouble(Prodotto::getPrezzo).sum());
        Tavolo tavolo = tavoloRepository.findById(request.getTavoloId()).orElseThrow(() -> new EntityNotFoundException("Tavolo non trovato con id: " + request.getTavoloId()));;
        ordine.setTavolo(tavolo);
        ordineRepository.save(ordine);
    }

    public void deleteOrdine(Long id) {
        Ordine ordine = ordineRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ordine non trovato "));
        ordineRepository.delete(ordine);
    }

    public OrdineResponse findOrdineById(Long id) {
        Ordine ordine = ordineRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ordine non trovato "));
        return new OrdineResponse(
                ordine.getId(),
                ordine.getProdotti(),
                ordine.getPrezzoTotale(),
                ordine.getTavolo().getId());
    }

    public Page<OrdineResponse> findAllOrdini(int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        Page<Ordine> ordinePage = ordineRepository.findAll(pageable);
        return ordinePage.map(ordine -> new OrdineResponse(
                ordine.getId(),
                ordine.getProdotti(),
                ordine.getPrezzoTotale(),
                ordine.getTavolo().getId()));
    }

    @Transactional
    public void spostaOrdine(Long ordineId, Long nuovoTavoloId) {
        Ordine ordine = ordineRepository.findById(ordineId)
                .orElseThrow(() -> new EntityNotFoundException("Ordine non trovato"));

        Tavolo tavoloCorrente = ordine.getTavolo();
        if (tavoloCorrente != null) {
            tavoloCorrente.setOrdine(null);
        }
        Tavolo nuovoTavolo = tavoloRepository.findById(nuovoTavoloId)
                .orElseThrow(() -> new EntityNotFoundException("Tavolo non trovato"));

        nuovoTavolo.setOrdine(ordine);
        ordine.setTavolo(nuovoTavolo);

//        tavoloRepository.save(tavoloCorrente); // solo se necessario
        tavoloRepository.save(nuovoTavolo);
//        ordineRepository.save(ordine); // opzionale se cascade presente
    }
}
