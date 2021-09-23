package com.prueba.tecnica.controller;

import com.prueba.tecnica.exception.ResourceNotFoundException;
import com.prueba.tecnica.model.Identificacion;
import com.prueba.tecnica.repository.IdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class TipoIdRestController {
    @Autowired
    IdRepository idRepository;

    @GetMapping("/tipoId")
    public List<Identificacion> getAllTipoId() {
        return idRepository.findAll();
    }

    @PostMapping("/tipoId")
    public Identificacion createtipoId(@Valid @RequestBody Identificacion identificacion) {
        return idRepository.save(identificacion);
    }

    @PutMapping("/tipoId/{id}")
    public Identificacion updatetipoId(@PathVariable(value = "id") Long identificacionId,
                           @Valid @RequestBody Identificacion identificacionDetails) {

        Identificacion identificacion = idRepository.findById(identificacionId)
                .orElseThrow(() -> new ResourceNotFoundException("Tipo_identificacion", "id", identificacionId));

        identificacionDetails.setTipo_id(identificacionDetails.getTipo_id());
        Identificacion updatedidentificacion = idRepository.save(identificacion);
        return updatedidentificacion;
    }
}
