package it.epicode.tabtender.ordini;

import it.epicode.tabtender.common.CommonResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ordini")
public class OrdineController {
    @Autowired
    private OrdineService ordineService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public CommonResponse saveOrdine(@RequestBody @Valid OrdineRequest request) {
        return ordineService.saveOrdine(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
//    @PreAuthorize("hasRole('ADMIN')")
    public void deleteOrdine(@PathVariable(name = "id") Long id) {
        ordineService.deleteOrdine(id);
    }

    @PutMapping("/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
    public void updateOrdine(@PathVariable(name = "id") Long id, @RequestBody @Valid OrdineRequest request) {
        ordineService.updateOrdine(id, request);
    }

    @GetMapping("/{id}")
//    @PreAuthorize("isAuthenticated()")
    public OrdineResponse getOrdineById(@PathVariable(name = "id") Long id) {
        return ordineService.findOrdineById(id);
    }

    @GetMapping
//    @PreAuthorize("isAuthenticated()")
    public Page<OrdineResponse> getAllOrdini(@RequestParam(name = "page", defaultValue = "0") int page,
                                             @RequestParam(name = "size", defaultValue = "20") int size,
                                             @RequestParam(name = "sort", defaultValue = "id") String sort) {
        return ordineService.findAllOrdini(page, size, sort);
    }

    @PutMapping("/ordini/{id}/sposta")
    public void spostaOrdine(
            @PathVariable Long id,
            @RequestParam Long nuovoTavoloId) {
        ordineService.spostaOrdine(id, nuovoTavoloId);
    }
}
