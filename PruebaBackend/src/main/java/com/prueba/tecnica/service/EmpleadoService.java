package com.prueba.tecnica.service;

import com.prueba.tecnica.model.Empleado;
import com.prueba.tecnica.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmpleadoService {
    @Autowired
    EmpleadoRepository repository;

    public List<Empleado> getAllEmployees(Integer pageNo, Integer pageSize, String sortBy)
    {
       try{
           Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
           Page<Empleado> pagedResult = repository.findAll(paging);
           if(pagedResult.hasContent()) {
               return pagedResult.getContent();
           } else {
               return new ArrayList<Empleado>();
           }
       }catch (Exception e){
           return null;
       }
    }
}
