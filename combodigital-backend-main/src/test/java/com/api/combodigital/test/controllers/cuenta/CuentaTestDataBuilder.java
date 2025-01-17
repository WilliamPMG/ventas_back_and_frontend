package com.api.combodigital.test.controllers.cuenta;

public class CuentaTestDataBuilder {

    private Long id;

    private String nombre;

    private int precio;

    private Integer dia;


    public CuentaTestDataBuilder cuentaPorDefecto() {

        this.nombre = "Disney";
        this.precio = 7000;
        this.dia = 30;

        return this;
    }

    public CuentaTestDataBuilder cuentaEditado(Long id) {

        this.id=id;
        this.nombre = "Star";
        this.precio = 8000;
        this.dia = 30;

        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public Integer getDia() {
        return dia;
    }

    public void setDia(Integer dia) {
        this.dia = dia;
    }
}
