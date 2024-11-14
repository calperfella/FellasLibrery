package com.spring.repositorios;

import com.spring.modelos.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {
    // Agrega métodos personalizados aquí si necesitas consultas específicas
}
