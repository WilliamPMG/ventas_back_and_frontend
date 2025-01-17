package com.api.combodigital.test.services.cuenta;


import com.api.combodigital.entities.Cuenta;

public class CuentaTestDataBuilder {

    private Long id;

    private String nombre;

    private Double precio;

    private Integer dia;


    public CuentaTestDataBuilder cuentaPorDefecto() {

        this.id = 1L;
        this.nombre = "Disney";
        this.precio = 7000.0;
        this.dia = 30;
        return this;
    }

    public Cuenta crear() {
        return new Cuenta(id,nombre,precio,dia,null);
    }

    public CuentaTestDataBuilder ConId(Long id) {
        this.id = id;
        return this;
    }

    public CuentaTestDataBuilder ConNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public CuentaTestDataBuilder conPrecio(Double precio) {
        this.precio = precio;
        return this;
    }

    public CuentaTestDataBuilder conDia(String dia) {
        this.precio = precio;
        return this;
    }


}
