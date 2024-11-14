package com.spring.controladores;

import com.spring.modelos.TarjetaMembresia;
import com.spring.servicios.TarjetaMembresiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tarjetas")
public class TarjetaMembresiaController {

    @Autowired
    private TarjetaMembresiaService tarjetaMembresiaService;

    @PostMapping
    public ResponseEntity<TarjetaMembresia> crearTarjeta(@RequestBody TarjetaMembresia tarjeta) {
        return ResponseEntity.ok(tarjetaMembresiaService.guardarTarjeta(tarjeta));
    }

    @GetMapping
    public List<TarjetaMembresia> obtenerTarjetas() {
        return tarjetaMembresiaService.obtenerTodasLasTarjetas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TarjetaMembresia> obtenerTarjetaPorId(@PathVariable Long id) {
        return tarjetaMembresiaService.obtenerTarjetaPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<TarjetaMembresia> actualizarTarjeta(@PathVariable Long id,
            @RequestBody TarjetaMembresia tarjeta) {
        TarjetaMembresia tarjetaActualizada = tarjetaMembresiaService.actualizarTarjeta(id, tarjeta);
        return ResponseEntity.ok(tarjetaActualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTarjeta(@PathVariable Long id) {
        tarjetaMembresiaService.eliminarTarjeta(id);
        return ResponseEntity.noContent().build();
    }
}
