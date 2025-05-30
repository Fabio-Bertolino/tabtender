package it.epicode.tabtender.tavoli;

import it.epicode.tabtender.common.CommonResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tavoli")
public class TavoloController {
    @Autowired
    private TavoloService tavoloService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public CommonResponse saveTavolo(@RequestBody @Valid TavoloPostRequest request) {
        return tavoloService.saveTavolo(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
//    @PreAuthorize("hasRole('ADMIN')")
    public void deleteTavolo(@PathVariable(name = "id") Long id) {
        tavoloService.deleteTavolo(id);
    }

    @PutMapping("/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
    public void updateTavolo(@PathVariable(name = "id") Long id, @RequestBody @Valid TavoloRequest request) {
        tavoloService.updateTavolo(id, request);
    }

    @GetMapping("/{id}")
//    @PreAuthorize("isAuthenticated()")
    public TavoloResponse getTavoloById(@PathVariable(name = "id") Long id) {
        return tavoloService.findTavoloById(id);
    }

    @GetMapping
//    @PreAuthorize("isAuthenticated()")
    public Page<TavoloResponse> getAllTavoli(@RequestParam(name = "page", defaultValue = "0") int page,
                                             @RequestParam(name = "size", defaultValue = "20") int size,
                                             @RequestParam(name = "sort", defaultValue = "id") String sort) {
        return tavoloService.findAllTavoli(page, size, sort);
                                             }
}
