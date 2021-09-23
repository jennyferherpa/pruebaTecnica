import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from "../../environments/environment";
import 'rxjs/add/operator/map';

@Injectable({
  providedIn: 'root'
})
export class AreaService {

  private servicesAreas = environment.domain + "/area";

  constructor(private http:HttpClient) { }

  getAreas = () => {
    return this.http.get<any>(this.servicesAreas);
  }
}
