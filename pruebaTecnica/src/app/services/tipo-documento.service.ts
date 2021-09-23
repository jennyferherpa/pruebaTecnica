import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from "../../environments/environment";
import 'rxjs/add/operator/map';

@Injectable({
  providedIn: 'root'
})
export class TipoDocumentoService {

  private servicesTipeID = environment.domain + "/tipoId";

  constructor(private http:HttpClient) { }

  getTipeID = () => {
    return this.http.get<any>(this.servicesTipeID);
  }
}
