package com.spring.controladores;

import com.spring.modelos.Compra;
import com.spring.modelos.CompraRequest;
import com.spring.servicios.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/compras")
public class CompraController {

    @Autowired
    private CompraService compraService;

    @PostMapping
    public ResponseEntity<Compra> realizarCompra(@RequestBody CompraRequest compraRequest) {
        Compra compra = compraService.realizarCompra(compraRequest);
        return ResponseEntity.ok(compra);
    }

    @GetMapping
    public List<Compra> obtenerCompras() {
        return compraService.obtenerTodasLasCompras();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Compra> obtenerCompraPorId(@PathVariable Long id) {
        return compraService.obtenerCompraPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Compra> actualizarCompra(@PathVariable Long id, @RequestBody Compra compra) {
        Compra compraActualizada = compraService.actualizarCompra(id, compra);
        return ResponseEntity.ok(compraActualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCompra(@PathVariable Long id) {
        compraService.eliminarCompra(id);
        return ResponseEntity.noContent().build();
    }
}
