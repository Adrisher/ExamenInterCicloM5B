package com.ista.backend.apirest.controllers;


import com.ista.backend.apirest.model.Producto;
import com.ista.backend.apirest.service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping("/ProductList")
    public ResponseEntity<List<Producto>> obtenerLista() {
        try {
            return new ResponseEntity<>(productoService.findByAll(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/createProducto")
    public ResponseEntity<?> createProduct (@Valid @RequestBody Producto pr, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }else{
            return new ResponseEntity<>(productoService.save(pr), HttpStatus.CREATED);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> readProduct (@PathVariable (value = "id") Long codigo){
        Optional<Producto> oSong = Optional.ofNullable(productoService.findById(codigo));

        if (!oSong.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(oSong);
    }

    @PutMapping("/updateProduct/{id}")
    public ResponseEntity<?> updateProduct (@RequestBody Producto pro, @PathVariable (value = "id") Long codigo) {
        Optional<Producto> pr = Optional.ofNullable(productoService.findById(codigo));
        if (!pr.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        pr.get().setDescripcion(pro.getDescripcion());
        pr.get().setPrecio(pro.getPrecio());
        pr.get().setCantidad(pro.getCantidad());
        return ResponseEntity.status(HttpStatus.CREATED).body(productoService.save(pr.get()));
    }

    @DeleteMapping("/deleteProduct/{id}")
    public ResponseEntity<?> deleteProduct (@PathVariable (value = "id") Long codigo) {
        try {
            productoService.delete(codigo);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (DataIntegrityViolationException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al elminar producto");
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
