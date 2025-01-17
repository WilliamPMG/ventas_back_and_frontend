package com.api.combodigital.test.controllers.suscripcion;

import com.api.combodigital.entities.Cliente;
import com.api.combodigital.entities.Cuenta;

import java.time.LocalDate;

public class SuscripcionTestDataBuilder {

    private Long id;

    private String correo;

    private String password;

    private Boolean estado;

    private Integer precio;

    private String proveedor;

    private Cuenta cuenta;

    private Cliente cliente;

    private LocalDate fechaInicio;

    private LocalDate fechaFinal;

    private String perfil;

    private String pin;

    public SuscripcionTestDataBuilder suscripcionPorDefecto() {

        this.correo = "giorman@gmail.com";
        this.password = "11111111";
        this.estado = true;
        this.fechaInicio = LocalDate.now();
        this.fechaFinal = fechaInicio.plusDays(30);
        this.perfil = "perfil 1";
        this.pin = "5487";
        this.precio = 10000;
        this.proveedor = "My Pantalla";
        this.cuenta = new Cuenta();
        cuenta.setId(1L);
        this.cliente = new Cliente();
        cliente.setId(1L);
        return this;
    }

    public SuscripcionTestDataBuilder suscripcionEditar(Long id) {

        this.id= id;
        this.correo = "cambio@gmail.com";
        this.password = "11111111";
        this.estado = true;
        this.fechaInicio = LocalDate.now();
        this.fechaFinal = fechaInicio.plusDays(30);
        this.perfil = "cambio 1";
        this.pin = "5487";
        this.precio = 10000;
        this.proveedor = "My Pantalla";
        this.cuenta = new Cuenta();
        cuenta.setId(1L);
        this.cliente = new Cliente();
        cliente.setId(1L);
        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(LocalDate fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

}
