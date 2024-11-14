package com.spring.servicios;

import com.spring.modelos.TarjetaMembresia;
import com.spring.repositorios.TarjetaMembresiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TarjetaMembresiaService {

    @Autowired
    private TarjetaMembresiaRepository tarjetaMembresiaRepository;

    // Crear o guardar una tarjeta de membresía
    public TarjetaMembresia saveTarjetaMembresia(TarjetaMembresia tarjetaMembresia) {
        return tarjetaMembresiaRepository.save(tarjetaMembresia);
    }

    // Obtener una tarjeta de membresía por ID
    public Optional<TarjetaMembresia> getTarjetaMembresiaById(Long id) {
        return tarjetaMembresiaRepository.findById(id);
    }

    // Obtener todas las tarjetas de membresía
    public List<TarjetaMembresia> getAllTarjetasMembresia() {
        return tarjetaMembresiaRepository.findAll();
    }

    // Actualizar una tarjeta de membresía
    public TarjetaMembresia updateTarjetaMembresia(Long id, TarjetaMembresia tarjetaMembresiaDetalles) {
        TarjetaMembresia tarjetaMembresia = tarjetaMembresiaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarjeta de membresía no encontrada"));
        tarjetaMembresia.setCupo(tarjetaMembresiaDetalles.getCupo());
        tarjetaMembresia.setUsuario(tarjetaMembresiaDetalles.getUsuario());
        return tarjetaMembresiaRepository.save(tarjetaMembresia);
    }

    // Eliminar una tarjeta de membresía
    public void deleteTarjetaMembresia(Long id) {
        tarjetaMembresiaRepository.deleteById(id);
    }
}
