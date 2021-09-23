package com.prueba.tecnica.controller;

import com.prueba.tecnica.exception.ResourceNotFoundException;
import com.prueba.tecnica.model.Area;
import com.prueba.tecnica.repository.AreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class AreaRestController {
    @Autowired
    AreaRepository areaRepository;

    @GetMapping("/area")
    public List<Area> getAllAreas() {
        return areaRepository.findAll();
    }

    @PostMapping("/area")
    public Area createArea(@Valid @RequestBody Area area) {
        return areaRepository.save(area);
    }

    @PutMapping("/area/{id}")
    public Area updateArea(@PathVariable(value = "id") Long areaId,
                                   @Valid @RequestBody Area areaDetails) {
        Area area = areaRepository.findById(areaId)
                .orElseThrow(() -> new ResourceNotFoundException("area", "id", areaId));
        area.setArea(areaDetails.getArea());
        Area updatedAreas = areaRepository.save(area);
        return updatedAreas;
    }
}
