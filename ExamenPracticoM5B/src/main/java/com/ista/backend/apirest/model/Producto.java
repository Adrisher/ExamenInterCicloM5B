package com.ista.backend.apirest.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity
public class Producto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo")
    private Long codigo;

    @NotBlank(message = "No puede ser nullo la descripcion")
    @NotNull(message = "Este campo esta vacio")
    @Column(name = "descripcion", length = 100)
    @Size(min = 2, max = 100)
    private String descripcion;


    @Column(name = "precio", scale = 3)
    @Min(value = 0, message = "Tiene que ser mayor a 0")
    @NotNull(message = "Este campo esta vacio")
    private Double precio;


    @Column(name = "cantidad")
    @NotNull(message = "Este campo esta vacio")
    @Min(value = 0, message = "Tiene que ser mayor a 0")
    private Integer cantidad;

}
