package com.api.combodigital.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "suscripciones")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Suscripcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String correo;

    private String password;

    private Boolean estado;

    private LocalDate fechaInicio;

    private LocalDate fechaFinal;

    private String perfil;

    private String pin;

    private Integer precio;

    private String proveedor;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "cuenta_id")
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    private Cuenta cuenta;


}
