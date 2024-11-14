package com.spring.repositorios;

import com.spring.modelos.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Long> {
    // Agrega métodos personalizados aquí si necesitas consultas específicas
}
