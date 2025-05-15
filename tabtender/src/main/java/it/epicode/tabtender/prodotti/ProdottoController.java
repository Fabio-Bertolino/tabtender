package it.epicode.tabtender.prodotti;

import it.epicode.tabtender.common.CommonResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/prodotti")
public class ProdottoController {
    @Autowired
    private ProdottoService prodottoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public CommonResponse saveProdotto(@RequestBody @Valid ProdottoRequest request) {
        return prodottoService.saveProdotto(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
//    @PreAuthorize("hasRole('ADMIN')")
    public void deleteProdotto(@PathVariable(name = "id") Long id) {
        prodottoService.deleteProdotto(id);
    }

    @PutMapping("/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
    public void updateProdotto(@PathVariable(name = "id") Long id, @RequestBody @Valid ProdottoRequest request) {
        prodottoService.updateProdotto(id, request);
    }

    @GetMapping("/{id}")
//    @PreAuthorize("isAuthenticated()")
    public ProdottoResponse getProdottoById(@PathVariable(name = "id") Long id) {
        return prodottoService.findProdottoById(id);
    }

    @GetMapping
//    @PreAuthorize("isAuthenticated()")
    public Page<ProdottoResponse> getAllProdotti(@RequestParam(name = "page", defaultValue = "0") int page,
                                                 @RequestParam(name = "size", defaultValue = "20") int size,
                                                 @RequestParam(name = "sort", defaultValue = "id") String sort) {
        return prodottoService.findAllProdotti(page, size, sort);
    }
}
