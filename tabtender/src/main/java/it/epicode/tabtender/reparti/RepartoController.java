package it.epicode.tabtender.reparti;

import it.epicode.tabtender.common.CommonResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reparti")
public class RepartoController {
    @Autowired
    private RepartoService repartoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public CommonResponse saveReparto(@RequestBody @Valid RepartoRequest request) {
        return repartoService.saveReparto(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
//    @PreAuthorize("hasRole('ADMIN')")
    public void deleteReparto(@PathVariable(name = "id") Long id) {
        repartoService.deleteReparto(id);
    }

    @PutMapping("/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
    public void updateReparto(@PathVariable(name = "id") Long id, @RequestBody @Valid RepartoRequest request) {
        repartoService.updateReparto(id, request);
    }

    @GetMapping("/{id}")
//    @PreAuthorize("isAuthenticated()")
    public RepartoResponse getRepartoById(@PathVariable(name = "id") Long id) {
        return repartoService.findRepartoById(id);
    }

    @GetMapping
//    @PreAuthorize("isAuthenticated()")
    public Page<RepartoResponse> getAllReparti(@RequestParam(name = "page", defaultValue = "0") int page,
                                               @RequestParam(name = "size", defaultValue = "20") int size,
                                               @RequestParam(name = "sort", defaultValue = "id") String sort) {
        return repartoService.findAllReparti(page, size, sort);
                                               }
}
