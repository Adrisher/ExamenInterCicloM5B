package com.ista.backend.apirest.repository;

import com.ista.backend.apirest.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {


}
