package com.api.combodigital.test.controllers.cliente;

public class ClienteTestDataBuilder {

    private Long id;

    private String nombre;

    private String apellido;

    private String telefono;


    public ClienteTestDataBuilder clientePorDefecto() {

        this.nombre = "Fernando";
        this.apellido = "Castillo";
        this.telefono = "3152485896";

        return this;
    }

    public ClienteTestDataBuilder clienteEditado(Long id) {

        this.id = id;
        this.nombre = "Mauricio";
        this.apellido = "Ramirez";
        this.telefono = "1111111111";

        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
