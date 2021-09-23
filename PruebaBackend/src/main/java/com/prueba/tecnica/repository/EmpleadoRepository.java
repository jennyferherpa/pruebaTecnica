package com.prueba.tecnica.repository;

import com.prueba.tecnica.model.Empleado;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoRepository extends PagingAndSortingRepository<Empleado, Long> {
    Empleado findByEmail(String email);
}
