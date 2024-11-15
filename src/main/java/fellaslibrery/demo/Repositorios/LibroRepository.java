package com.tu_proyecto.repository;

import com.tu_proyecto.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {

    /**
     * Buscar libros cuyo nombre contenga un texto dado (ignora
     * mayúsculas/minúsculas)
     */
    List<Libro> findByNombreContainingIgnoreCase(String nombre);

    /**
     * Buscar libros por su ISBN
     */
    List<Libro> findByIsbn(String isbn);

    /**
     * Verificar si un libro ya existe por su ISBN
     */
    boolean existsByIsbn(String isbn);
}
