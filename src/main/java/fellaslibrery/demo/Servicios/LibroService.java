package com.spring.servicios;

import com.spring.modelos.Libro;
import com.spring.repositorios.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibroService {

    @Autowired
    private LibroRepository libroRepository;

    // Crear o guardar un libro
    public Libro saveLibro(Libro libro) {
        return libroRepository.save(libro);
    }

    // Obtener un libro por ID
    public Optional<Libro> getLibroById(Long id) {
        return libroRepository.findById(id);
    }

    // Obtener todos los libros
    public List<Libro> getAllLibros() {
        return libroRepository.findAll();
    }

    // Actualizar un libro
    public Libro updateLibro(Long id, Libro libroDetalles) {
        Libro libro = libroRepository.findById(id).orElseThrow(() -> new RuntimeException("Libro no encontrado"));
        libro.setTitulo(libroDetalles.getTitulo());
        libro.setAutor(libroDetalles.getAutor());
        libro.setPrecio(libroDetalles.getPrecio());
        libro.setStock(libroDetalles.getStock());
        return libroRepository.save(libro);
    }

    // Eliminar un libro
    public void deleteLibro(Long id) {
        libroRepository.deleteById(id);
    }
}
