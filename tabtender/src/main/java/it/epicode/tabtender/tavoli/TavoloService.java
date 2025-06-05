package it.epicode.tabtender.tavoli;

import it.epicode.tabtender.common.CommonResponse;
import it.epicode.tabtender.ordini.Ordine;
import it.epicode.tabtender.ordini.OrdineRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class TavoloService {
    @Autowired
    private TavoloRepository tavoloRepository;
    @Autowired
    private OrdineRepository ordineRepository;

    public CommonResponse saveTavolo(TavoloPostRequest request) {
        Tavolo tavolo = new Tavolo();
        BeanUtils.copyProperties(request, tavolo);
        tavoloRepository.save(tavolo);
        return new CommonResponse(tavolo.getId());
    }

    public Tavolo updateTavolo(Long id, TavoloPostRequest request) {
        Tavolo tavolo = tavoloRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tavolo non trovato "));
        tavolo.setNumeroPosti(request.getNumeroPosti());
//        tavolo.setDisponibile(request.isDisponibile());
//        if (request.getOrdineId() != null) {
//            Ordine ordine = ordineRepository
//                    .findById(request.getOrdineId())
//                    .orElseThrow(() -> new EntityNotFoundException("Ordine non trovato con id: " + request.getOrdineId()));
//            tavolo.setOrdine(ordine);
//        } else {
//            tavolo.setOrdine(null);
//        }
        return tavoloRepository.save(tavolo);
    }

    public void deleteTavolo(Long id) {
        Tavolo tavolo = tavoloRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tavolo non trovato "));
        tavoloRepository.delete(tavolo);
    }

    public TavoloResponse findTavoloById(Long id) {
        Tavolo tavolo = tavoloRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tavolo non trovato "));
        return new TavoloResponse(
                tavolo.getId(),
                tavolo.getNumeroPosti(),
                tavolo.isDisponibile(),
                tavolo.getOrdine());
    }

    public Page <TavoloResponse> findAllTavoli(int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        Page<Tavolo> tavoloPage = tavoloRepository.findAll(pageable);
        return tavoloPage.map(tavolo -> new TavoloResponse(
                tavolo.getId(),
                tavolo.getNumeroPosti(),
                tavolo.isDisponibile(),
                tavolo.getOrdine()));
    }

    public void changeDisponibilita(Long id) {
        Tavolo tavolo = tavoloRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tavolo non trovato "));
        tavolo.setDisponibile(!tavolo.isDisponibile());
        tavoloRepository.save(tavolo);
    }
}
