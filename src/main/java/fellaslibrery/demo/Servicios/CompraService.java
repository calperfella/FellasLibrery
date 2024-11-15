package com.tu_proyecto.service;

import com.tu_proyecto.dto.CompraRequest;
import com.tu_proyecto.model.Compra;
import com.tu_proyecto.model.Libro;
import com.tu_proyecto.model.Usuario;
import com.tu_proyecto.repository.CompraRepository;
import com.tu_proyecto.repository.LibroRepository;
import com.tu_proyecto.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompraService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private CompraRepository compraRepository;

    @Transactional
    public List<Compra> realizarCompra(CompraRequest request) {
        Usuario usuario = usuarioRepository.findById(request.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        double totalCompra = 0;
        List<Compra> comprasRealizadas = new ArrayList<>();

        for (CompraRequest.LibroCompra libroCompra : request.getLibros()) {
            Libro libro = libroRepository.findById(libroCompra.getLibroId())
                    .orElseThrow(() -> new RuntimeException("Libro no encontrado"));

            if (libro.getStock() < libroCompra.getCantidad()) {
                throw new RuntimeException("Stock insuficiente para el libro: " + libro.getTitulo());
            }

            double totalLibro = libro.getPrecioFlcoin() * libroCompra.getCantidad();
            totalCompra += totalLibro;

            Compra compra = new Compra();
            compra.setUsuario(usuario);
            compra.setLibro(libro);
            compra.setCantidad(libroCompra.getCantidad());
            compra.setTotal(totalLibro);
            compraRepository.save(compra);
            comprasRealizadas.add(compra);

            libro.setStock(libro.getStock() - libroCompra.getCantidad());
            libroRepository.save(libro);
        }

        if (usuario.getTarjeta().getSaldo() < totalCompra) {
            throw new RuntimeException("Saldo insuficiente en la tarjeta de membresía");
        }

        usuario.getTarjeta().setSaldo(usuario.getTarjeta().getSaldo() - totalCompra);
        usuarioRepository.save(usuario);

        return comprasRealizadas;
    }
}
