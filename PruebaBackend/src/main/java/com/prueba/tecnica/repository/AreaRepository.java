package com.prueba.tecnica.repository;

import com.prueba.tecnica.model.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AreaRepository extends JpaRepository<Area,Long> {
}
