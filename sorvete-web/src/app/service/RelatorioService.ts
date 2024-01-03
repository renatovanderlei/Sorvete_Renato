import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, tap } from 'rxjs';

@Injectable({
    providedIn: 'root'
  })
  export class RealtorioService {
    url = 'http://localhost:8080/';
    headers: HttpHeaders = new HttpHeaders().set('Content-Type', 'application/json');
    constructor(private http: HttpClient) { }

    findAllSabor(data : any){
      return this.http.get<any>(this.url + "relatorio/sabor", {
        headers: this.headers,
        observe: 'response',
        params:{
            data: data
        },
      });
    }

    findAllTipo(data : any){
      return this.http.get<any>(this.url + "relatorio/tipo", {
        headers: this.headers,
        observe: 'response',
        params:{
            data: data
        },
      });
    }

  }