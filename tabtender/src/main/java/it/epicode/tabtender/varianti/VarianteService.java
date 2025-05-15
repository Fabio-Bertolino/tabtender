package it.epicode.tabtender.varianti;

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
public class VarianteService {
    @Autowired
    private VarianteRepository varianteRepository;

    public CommonResponse saveVariante(VarianteRequest request) {
        Variante variante = new Variante();
        variante.setNome(request.getNome());
        variante.setPrezzo(request.getPrezzo());
        varianteRepository.save(variante);
        return new CommonResponse(variante.getId());
    }

    public void updateVariante(Long id, VarianteRequest request) {
        Variante variante = varianteRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Variante non trovata "));
        BeanUtils.copyProperties(request, variante);
        varianteRepository.save(variante);
    }

    public void deleteVariante(Long id) {
        Variante variante = varianteRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Variante non trovata "));
        varianteRepository.delete(variante);
    }

    public VarianteResponse findVarianteById(Long id) {
        Variante variante = varianteRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Variante non trovata "));
        return new VarianteResponse(
                variante.getId(),
                variante.getNome(),
                variante.getPrezzo());
    }

    public Page<VarianteResponse> findAllVarianti(int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        Page<Variante> variantePage = varianteRepository.findAll(pageable);
        return variantePage.map(variante -> new VarianteResponse(
                variante.getId(),
                variante.getNome(),
                variante.getPrezzo()));
    }
}
