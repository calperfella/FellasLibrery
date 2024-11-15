package com.tu_proyecto.controller;

import com.tu_proyecto.model.Libro;
import com.tu_proyecto.service.GoogleBooksService;
import com.tu_proyecto.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/libros")
public class LibroController {

    @Autowired
    private LibroService libroService;

    @Autowired
    private GoogleBooksService googleBooksService;

    /**
     * Obtener todos los libros en la base de datos
     */
    @GetMapping
    public ResponseEntity<List<Libro>> obtenerTodosLosLibros() {
        List<Libro> libros = libroService.obtenerTodosLosLibros();
        return ResponseEntity.ok(libros);
    }

    /**
     * Buscar libros por nombre o ISBN. Si no se encuentra en la base de datos,
     * busca en Google Books y actualiza la base de datos con los resultados.
     */
    @GetMapping("/buscar")
    public ResponseEntity<List<Libro>> buscarLibros(@RequestParam(required = false) String nombre,
            @RequestParam(required = false) String isbn) {
        List<Libro> libros;

        if (nombre != null) {
            libros = libroService.buscarPorNombre(nombre);
        } else if (isbn != null) {
            libros = libroService.buscarPorIsbn(isbn);
        } else {
            return ResponseEntity.badRequest().build(); // Debe proporcionar al menos un parámetro
        }

        // Si no hay resultados en la base de datos local, buscar en Google Books API
        if (libros.isEmpty()) {
            String consulta = (nombre != null) ? nombre : isbn;
            libros = googleBooksService.buscarLibrosEnGoogle(consulta);
        }

        return ResponseEntity.ok(libros);
    }

    /**
     * Crear un nuevo libro en la base de datos
     */
    @PostMapping
    public ResponseEntity<Libro> crearLibro(@RequestBody Libro libro) {
        Libro nuevoLibro = libroService.guardarLibro(libro);
        return ResponseEntity.ok(nuevoLibro);
    }

    /**
     * Obtener un libro por su ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Libro> obtenerLibroPorId(@PathVariable Long id) {
        return libroService.obtenerLibroPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Actualizar un libro existente
     */
    @PutMapping("/{id}")
    public ResponseEntity<Libro> actualizarLibro(@PathVariable Long id, @RequestBody Libro libroDetalles) {
        Libro libroActualizado = libroService.actualizarLibro(id, libroDetalles);
        return ResponseEntity.ok(libroActualizado);
    }

    /**
     * Eliminar un libro por su ID
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarLibro(@PathVariable Long id) {
        libroService.eliminarLibro(id);
        return ResponseEntity.noContent().build();
    }
}
