import { Component, Input, OnInit } from '@angular/core';
import { Cuenta } from 'src/app/models/cuenta';
import { UntypedFormGroup, UntypedFormBuilder, Validators } from '@angular/forms';
import { Cliente } from 'src/app/models/cliente';
import { Venta } from 'src/app/models/venta';
import { Suscripcion } from 'src/app/models/suscripcion';
import { CuentaService } from 'src/app/services/cuenta.service';
import { VentaService } from 'src/app/services/venta.service';

//componente complementario para realizar la suscripcion
//aqui se ingresan los datos especificos para la suscripcion
@Component({
  selector: 'app-venta-suscripcion-datos',
  templateUrl: './venta-suscripcion-datos.component.html',
  styleUrls: ['./venta-suscripcion-datos.component.scss'],
})
export class VentaSuscripcionDatosComponent implements OnInit {
  formulario!: UntypedFormGroup;
  valido!: boolean;
  @Input('cliente') cliente!: Cliente;
  suscripciones: Suscripcion[] = new Array<Suscripcion>();
  cuentas: Cuenta[] = new Array<Cuenta>();
  constructor(
    private cuentaServicio: CuentaService,
    private ventaServicio: VentaService,
    private fb: UntypedFormBuilder
  ) {}

  ngOnInit(): void {
    this.crearFormulario();
    this.inicio();
  }

  //me permite crear el formulario
  crearFormulario() {
    this.formulario = this.fb.group({
      cuenta: ['',Validators.required],
      correo: ['', Validators.compose([Validators.required, Validators.email])],
      password: ['', Validators.required],
      perfil: ['', Validators.required],
      pin: [''],
      proveedor: [
        '',
        [
          Validators.required,
          Validators.minLength(1),
          Validators.maxLength(20),
        ],
      ],
    });
  }

  //realiza los ajustes para ingresar los datos de la suscripcion
  inicio() {
    this.cuentaServicio.consultarLista().subscribe((resultado) => {
      this.cuentas = resultado;
      this.ordenar(this.cuentas);
    });
    if (!this.ventaServicio.venta) {
      this.ventaServicio.venta = new Venta();
      this.ventaServicio.venta.cliente = `${this.cliente.nombre} ${this.cliente.apellido}`;
    }
  }

  //ordenar array cliente asc
  ordenar(array: any[]) {
    array.sort((a, b) => {
      if (a.nombre.toLocaleLowerCase() > b.nombre.toLocaleLowerCase()) {
        return 1;
      }
      if (a.nombre.toLocaleLowerCase() < b.nombre.toLocaleLowerCase()) {
        return -1;
      }
      return 0;
    });
  }

  //agrega los datos de la suscripcion
  agregar() {
    let suscripicion: Suscripcion = this.formulario.value;
    let cuenta: Cuenta[] = this.cuentas.filter(
      (cuenta) => cuenta.id == this.formulario.value.cuenta
    );
    suscripicion.cuenta = cuenta[0];
    suscripicion.precio = cuenta[0].precio;
    let now = new Date();
    suscripicion.fechaInicio = new Date(
      Date.UTC(now.getFullYear(), now.getMonth(), now.getDate())
    );
    suscripicion.fechaInicio = suscripicion.fechaInicio
      .toISOString()
      .slice(0, 10);
    suscripicion.fechaFinal = new Date(
      Date.UTC(now.getFullYear(), now.getMonth(), now.getDate() + cuenta[0].dia)
    );
    suscripicion.fechaFinal = suscripicion.fechaFinal
      .toISOString()
      .slice(0, 10);
    suscripicion.estado = true;
    suscripicion.cliente = this.cliente;
    if (this.ventaServicio.venta) {
      this.ventaServicio.venta.suscripcion.push(suscripicion);
      this.calcularTotal();
    }

    this.formulario.reset();
  }

  // permite calcular el total de las suscripciones
  calcularTotal() {
    if (this.ventaServicio.venta) {
      let precio: number = 0;
      this.ventaServicio.venta.suscripcion.map((item) => {
        precio += item.precio;
        return precio;
      });
      this.ventaServicio.venta.total = precio;
    }
  }
}
