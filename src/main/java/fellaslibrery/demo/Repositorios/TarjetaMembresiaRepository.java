package com.spring.repositorios;

import com.spring.modelos.TarjetaMembresia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarjetaMembresiaRepository extends JpaRepository<TarjetaMembresia, Long> {
    // Agrega métodos personalizados aquí si necesitas consultas específicas
}
