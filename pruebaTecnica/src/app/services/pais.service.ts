import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from "../../environments/environment";
import 'rxjs/add/operator/map';

@Injectable({
  providedIn: 'root'
})
export class PaisService {

  private servicesPaises = environment.domain + "/pais";

  constructor(private http:HttpClient) { }

  getPaises = () => {
    return this.http.get<any>(this.servicesPaises);
  }
}
