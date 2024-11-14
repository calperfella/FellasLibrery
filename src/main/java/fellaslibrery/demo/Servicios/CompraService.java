package com.spring.servicios;

import com.spring.modelos.Compra;
import com.spring.repositorios.CompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompraService {

    @Autowired
    private CompraRepository compraRepository;

    // Crear o guardar una compra
    public Compra saveCompra(Compra compra) {
        return compraRepository.save(compra);
    }

    // Obtener una compra por ID
    public Optional<Compra> getCompraById(Long id) {
        return compraRepository.findById(id);
    }

    // Obtener todas las compras
    public List<Compra> getAllCompras() {
        return compraRepository.findAll();
    }

    // Actualizar una compra
    public Compra updateCompra(Long id, Compra compraDetalles) {
        Compra compra = compraRepository.findById(id).orElseThrow(() -> new RuntimeException("Compra no encontrada"));
        compra.setUsuario(compraDetalles.getUsuario());
        compra.setLibros(compraDetalles.getLibros());
        compra.setTotal(compraDetalles.getTotal());
        return compraRepository.save(compra);
    }

    // Eliminar una compra
    public void deleteCompra(Long id) {
        compraRepository.deleteById(id);
    }
}
