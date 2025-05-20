package it.epicode.tabtender.reparti;

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
public class RepartoService {
    @Autowired
    private RepartoRepository repartoRepository;

    public CommonResponse saveReparto(RepartoRequest request) {
        Reparto reparto = new Reparto();
        BeanUtils.copyProperties(request, reparto);
        repartoRepository.save(reparto);
        return new CommonResponse(reparto.getId());
    }

    public void updateReparto(Long id, RepartoRequest request) {
        Reparto reparto = repartoRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reparto non trovato "));
        BeanUtils.copyProperties(request, reparto);
        repartoRepository.save(reparto);
    }

    public void deleteReparto(Long id) {
        Reparto reparto = repartoRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reparto non trovato "));
        repartoRepository.delete(reparto);
    }

    public RepartoResponse findRepartoById(Long id) {
        Reparto reparto = repartoRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reparto non trovato "));
        return new RepartoResponse(
                reparto.getId(),
                reparto.getNome(),
                reparto.getProdotti()
        );
    }

    public Page<RepartoResponse> findAllReparti(int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        Page<Reparto> repartoPage = repartoRepository.findAll(pageable);
        return repartoPage.map(reparto -> new RepartoResponse(
                reparto.getId(),
                reparto.getNome(),
                reparto.getProdotti()
        ));
    }
}
