package com.tu_proyecto.repository;

import com.tu_proyecto.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Integer> {

    List<Libro> findByTituloContainingIgnoreCase(String titulo);

    List<Libro> findByIsbn(String isbn);

    boolean existsByIsbn(String isbn);
}
