package it.epicode.tabtender.varianti;

import it.epicode.tabtender.common.CommonResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/varianti")
public class VarianteController {
    @Autowired
    private VarianteService varianteService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public CommonResponse saveVariante(@RequestBody @Valid VarianteRequest request) {
        return varianteService.saveVariante(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
//    @PreAuthorize("hasRole('ADMIN')")
    public void deleteVariante(@PathVariable(name = "id") Long id) {
        varianteService.deleteVariante(id);
    }

    @PutMapping("/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
    public void updateVariante(@PathVariable(name = "id") Long id, @RequestBody @Valid VarianteRequest request) {
        varianteService.updateVariante(id, request);
    }

    @GetMapping("/{id}")
//    @PreAuthorize("isAuthenticated()")
    public VarianteResponse getVarianteById(@PathVariable(name = "id") Long id) {
        return varianteService.findVarianteById(id);
    }

    @GetMapping
//    @PreAuthorize("isAuthenticated()")
    public Page<VarianteResponse> getAllVarianti(@RequestParam(name = "page", defaultValue = "0") int page,
                                                 @RequestParam(name = "size", defaultValue = "#{T(java.lang.Integer).MAX_VALUE}") int size,
                                                 @RequestParam(name = "sort", defaultValue = "id") String sort) {
        return varianteService.findAllVarianti(page, size, sort);
    }
}
