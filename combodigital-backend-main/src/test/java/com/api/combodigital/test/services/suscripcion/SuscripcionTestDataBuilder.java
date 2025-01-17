package com.api.combodigital.test.services.suscripcion;

import com.api.combodigital.entities.Suscripcion;

import java.time.LocalDate;

public class SuscripcionTestDataBuilder {

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

    public SuscripcionTestDataBuilder suscripcionPorDefecto() {

        this.id=1L;
        this.correo = "giorman@gmail.com";
        this.password = "11111111";
        this.estado = true;
        this.fechaInicio = LocalDate.now();
        this.fechaFinal = fechaInicio.plusDays(30);
        this.perfil = "perfil 1";
        this.pin = "5487";
        this.precio = 10000;
        this.proveedor = "My Pantalla";
        return this;
    }

    public Suscripcion crear() {
        return new Suscripcion(id, correo, password, estado, fechaInicio, fechaFinal,perfil, pin,precio , proveedor,null , null );
    }

    public SuscripcionTestDataBuilder ConId(Long id) {
        this.id = id;
        return this;
    }

    public SuscripcionTestDataBuilder ConCorreo(String correo) {
        this.correo= correo;
        return this;
    }

    public SuscripcionTestDataBuilder conPassword(String password) {
        this.password = password;
        return this;
    }

    public SuscripcionTestDataBuilder conPrecio(Integer precio) {
        this.precio = precio;
        return this;
    }


}
