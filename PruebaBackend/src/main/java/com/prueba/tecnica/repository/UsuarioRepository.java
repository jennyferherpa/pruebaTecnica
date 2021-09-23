package com.prueba.tecnica.repository;

import com.prueba.tecnica.model.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuarios,Long> {
    Usuarios findByEmail(String email);
}
