package br.com.itau.unittestingworkshop.controller;

import br.com.itau.unittestingworkshop.core.domain.Empresa;
import br.com.itau.unittestingworkshop.core.domain.TipoCanal;
import br.com.itau.unittestingworkshop.core.service.WorkshopService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/workshop")
@AllArgsConstructor
public class WorkshopController {

    private WorkshopService workshopService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Empresa>> getWorkshop(@RequestParam(value = "tipoCanal", required = false)TipoCanal tipoCanal) {
        return ResponseEntity.ok(workshopService.getEmpresas(tipoCanal));
    }
}
