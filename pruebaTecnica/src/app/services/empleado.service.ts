import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from "../../environments/environment";
import 'rxjs/add/operator/map';

@Injectable({
  providedIn: 'root'
})
export class EmpleadoService {

  private servicesEmpleados = environment.domain + "/empleado";
  private servicesSequence = environment.domain + "/sequence";

  constructor(private http:HttpClient) { }

  getEmpleados = () => {
      return this.http.get<any>(this.servicesEmpleados);
  }

  getSequence = () => {
    return this.http.get<any>(this.servicesSequence);
  }

  setEmpleados = (params: any) => {
    let headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Accept': 'application/json',
      'Access-Control-Allow-Origin': '*',
      'Access-Control-Allow-Methods': 'POST',
      'Access-Control-Allow-Headers': '*'
    });
    return this.http.post<any>(this.servicesEmpleados,JSON.stringify(params), {headers});
  }

  verifyEmail = (params: any) => {
    let headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Accept': 'application/json',
      'Access-Control-Allow-Origin': '*',
      'Access-Control-Allow-Methods': 'POST',
      'Access-Control-Allow-Headers': '*'
    });
    return this.http.post<any>(this.servicesEmpleados + '/findUser', JSON.stringify(params), {headers});
  }




}
