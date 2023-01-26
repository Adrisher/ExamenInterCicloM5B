package com.ista.backend.apirest.service;

import com.ista.backend.apirest.model.Producto;
import com.ista.backend.apirest.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductoServiceImpl  extends GenericServiceImpl<Producto, Long> implements ProductoService{

    @Autowired
    ProductoRepository productoRepository;

    @Override
    public CrudRepository<Producto, Long> getDao() {
        return productoRepository;
    }


}
