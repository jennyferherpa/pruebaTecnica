package com.prueba.tecnica.controller;

import com.prueba.tecnica.exception.ResourceNotFoundException;
import com.prueba.tecnica.model.Pais;
import com.prueba.tecnica.repository.PaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class PaisRestController {
    @Autowired
    PaisRepository paisRepository;

    @GetMapping("/pais")
    public List<Pais> getAllPais() {
        return paisRepository.findAll();
    }

    @PostMapping("/pais")
    public Pais createPais(@Valid @RequestBody Pais pais) {
        return paisRepository.save(pais);
    }

    @PutMapping("/pais/{id}")
    public Pais updatePais(@PathVariable(value = "id") Long paisId,
                           @Valid @RequestBody Pais paisDetails) {

        Pais pais = paisRepository.findById(paisId)
                .orElseThrow(() -> new ResourceNotFoundException("pais", "id", paisId));

        pais.setPais(paisDetails.getPais());
        Pais updatedPais = paisRepository.save(pais);
        return updatedPais;
    }
}
