package it.epicode.tabtender.ordini;

import it.epicode.tabtender.common.CommonRequest;
import it.epicode.tabtender.common.CommonResponse;
import it.epicode.tabtender.prodotti.Prodotto;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public class OrdineService {
    @Autowired
    private OrdineRepository ordineRepository;

    public CommonResponse saveOrdine(OrdineRequest request) {
        Ordine ordine = new Ordine();
        List<Prodotto> prodotti = request.getProdotti();
        ordine.setProdotti(prodotti);
        ordine.setPrezzoTotale(prodotti.stream().mapToDouble(Prodotto::getPrezzo).sum());
        ordineRepository.save(ordine);
        return new CommonResponse(ordine.getId());
    }

    public void updateOrdine(Long id, OrdineRequest request) {
        Ordine ordine = ordineRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ordine non trovato "));
        List<Prodotto> prodotti = request.getProdotti();
        ordine.setProdotti(prodotti);
        ordine.setPrezzoTotale(prodotti.stream().mapToDouble(Prodotto::getPrezzo).sum());
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
                ordine.getPrezzoTotale());
    }

    public Page<OrdineResponse> findAllOrdini(int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        Page<Ordine> ordinePage = ordineRepository.findAll(pageable);
        return ordinePage.map(ordine -> new OrdineResponse(
                ordine.getId(),
                ordine.getProdotti(),
                ordine.getPrezzoTotale()));
    }
}
