import {AfterViewInit, Component, ViewChild} from '@angular/core';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';
import { EmpleadoDTO } from '../../models/Empleado.model';
import {EmpleadoService} from '../../services/empleado.service'



@Component({
  selector: 'app-consulta-empleados',
  templateUrl: './consulta-empleados.component.html',
  styleUrls: ['./consulta-empleados.component.css']
})
export class ConsultaEmpleadosComponent implements AfterViewInit {
  displayedColumns: string[] = ['id', 'nombre completo', 'pais', 'area', 'editar'];
  dataSource: MatTableDataSource<EmpleadoDTO>;
  empleados : EmpleadoDTO [] = [];
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(private empleadoService : EmpleadoService) {
    // Assign the data to the data source for the table to render.
    this.llenarTabla();
    this.dataSource = new MatTableDataSource();
  }

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  llenarTabla(){
    const empleados = [];
    this.empleadoService.getEmpleados()
    .subscribe(
      response => {
        Object.keys(response).map((key) => {
          console.log(key);
          console.log(response[key]);
        });

      },
        error => {
          console.log(error);
          if (!error.ok) {

          }
        }
    );
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  editarEmpleado(empleados:string){
    console.log(empleados)
  }
}

