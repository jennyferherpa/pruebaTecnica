package com.prueba.tecnica.repository;

import com.prueba.tecnica.model.Identificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdRepository extends JpaRepository<Identificacion,Long> {
}
