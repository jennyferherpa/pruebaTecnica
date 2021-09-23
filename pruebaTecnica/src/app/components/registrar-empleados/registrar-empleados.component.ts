import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { AreaService } from 'src/app/services/area.service';
import { PaisService } from 'src/app/services/pais.service';
import { TipoDocumentoService } from 'src/app/services/tipo-documento.service';
import { EmpleadoDTO } from '../../models/Empleado.model';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { EmpleadoService } from 'src/app/services/empleado.service';

@Component({
  selector: 'app-registrar-empleados',
  templateUrl: './registrar-empleados.component.html',
  styleUrls: ['./registrar-empleados.component.css']
})
export class RegistrarEmpleadosComponent implements OnInit {
  @ViewChild('primerName') primer_nombre:ElementRef;
  @ViewChild('segundoName') segundo_nombre:ElementRef;
  @ViewChild('primerApellido') primer_apellido:ElementRef;
  @ViewChild('segundoApellido') segundo_apellido:ElementRef;
  @ViewChild('pais') pais:ElementRef;
  @ViewChild('TId') identificacion:ElementRef;
  @ViewChild('ID') nid:ElementRef;
  @ViewChild('fechaIngreso') fecha_ingreso:ElementRef;
  @ViewChild('area') area:ElementRef;

  paises : any[]=[];
  areas : any[]=[];
  TipeID : any[]=[];
  email : string = '';

  empleado : EmpleadoDTO;


  constructor(private servicePaises: PaisService,private serviceAreas: AreaService,private serviceTipeID: TipoDocumentoService,private serviceEmpleado: EmpleadoService) { }

  ngOnInit(): void {
    this.listPaises();
    this.listAreas();
    this.listTipeID();
  }


  verData() {
    let emailNew = '';
    if(this.primer_nombre.nativeElement.value != ''){
      emailNew = this.primer_nombre.nativeElement.value + '.';
    }
    if(this.primer_apellido.nativeElement.value != ''){
      emailNew += this.primer_apellido.nativeElement.value + '@';
    }
    if(this.pais.nativeElement.value != '0'){
      if(this.pais.nativeElement.value == '1'){
        emailNew += 'cidenet.com.co';
      }else{
        emailNew += 'cidenet.com.us';
      }
    }
    this.email=emailNew;
    this.serviceEmpleado.verifyEmail({email:this.email})
    .subscribe(
      response => {
        if(response != null && response != undefined && response != ""){
          this.serviceEmpleado.getSequence()
          .subscribe(
            response => {
              let emailNew = '';
              if(this.primer_nombre.nativeElement.value != ''){
                emailNew = this.primer_nombre.nativeElement.value + '.';
              }
              if(this.primer_apellido.nativeElement.value != ''){
                emailNew += this.primer_apellido.nativeElement.value + '.' + response + '@';
              }
              if(this.pais.nativeElement.value != '0'){
                if(this.pais.nativeElement.value == '1'){
                  emailNew += 'cidenet.com.co';
                }else{
                  emailNew += 'cidenet.com.us';
                }
              }
              this.email=emailNew;
            },
              error => {
              }
          );
        }else{
          let emailNew = '';
          if(this.primer_nombre.nativeElement.value != ''){
            emailNew = this.primer_nombre.nativeElement.value + '.';
          }
          if(this.primer_apellido.nativeElement.value != ''){
            emailNew += this.primer_apellido.nativeElement.value + '@';
          }
          if(this.pais.nativeElement.value != '0'){
            if(this.pais.nativeElement.value == '1'){
              emailNew += 'cidenet.com.co';
            }else{
              emailNew += 'cidenet.com.us';
            }
          }
          this.email=emailNew;
        }
      },
        error => {
          //console.log(error);
          if (!error.ok) {

          }
        }
    );
  }

  listPaises(){
    this.servicePaises.getPaises()
    .subscribe(
      response => {
        for(let item in response){
          let datos={
            id:response[item].id,
            pais:response[item].pais,
          }
          this.paises.push(datos);
        }
      },
        error => {
          //console.log(error);
          if (!error.ok) {

          }
        }
    );
  }

  listAreas(){
    this.serviceAreas.getAreas()
    .subscribe(
      response => {
        for(let item in response){
          let datos={
            id:response[item].id,
            area:response[item].area,
          }
          this.areas.push(datos);
        }
      },
        error => {
          //console.log(error);
          if (!error.ok) {

          }
        }
    );
  }

  listTipeID(){
    this.serviceTipeID.getTipeID()
    .subscribe(
      response => {
        for(let item in response){
          let datos={
            id:response[item].id,
            tipo:response[item].tipo_id,
          }
          this.TipeID.push(datos);
        }
      },
        error => {
          //console.log(error);
          if (!error.ok) {

          }
        }
    );
  }

}
