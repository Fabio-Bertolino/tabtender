package it.epicode.tabtender.prodotti;

import it.epicode.tabtender.common.CommonResponse;
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
public class ProdottoService {
    @Autowired
    private ProdottoRepository prodottoRepository;

    public CommonResponse saveProdotto(ProdottoRequest request) {
        Prodotto prodotto = new Prodotto();
        BeanUtils.copyProperties(request, prodotto);
        prodottoRepository.save(prodotto);
        return new CommonResponse(prodotto.getId());
    }

    public void updateProdotto(Long id, ProdottoRequest request) {
        Prodotto prodotto = prodottoRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Prodotto non trovato "));
        BeanUtils.copyProperties(request, prodotto);
        prodottoRepository.save(prodotto);
    }

    public void deleteProdotto(Long id) {
        Prodotto prodotto = prodottoRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Prodotto non trovato "));
        prodottoRepository.delete(prodotto);
    }

    public ProdottoResponse findProdottoById(Long id) {
        Prodotto prodotto = prodottoRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Prodotto non trovato "));
        return new ProdottoResponse(
                prodotto.getId(),
                prodotto.getNome(),
                prodotto.getPrezzo(),
                prodotto.getImmagine(),
                prodotto.getVarianti(),
                prodotto.getNote());
    }

    public Page<ProdottoResponse> findAllProdotti(int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        Page<Prodotto> prodottoPage = prodottoRepository.findAll(pageable);
        return prodottoPage.map(prodotto -> new ProdottoResponse(
                prodotto.getId(),
                prodotto.getNome(),
                prodotto.getPrezzo(),
                prodotto.getImmagine(),
                prodotto.getVarianti(),
                prodotto.getNote()));
    }
}
