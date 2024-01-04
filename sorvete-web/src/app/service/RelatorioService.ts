import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, tap } from 'rxjs';

@Injectable({
    providedIn: 'root'
  })
  export class RealtorioService { //Classe sendo exportada para ser usada em outros componentes
    url = 'http://localhost:8080/';
    headers: HttpHeaders = new HttpHeaders().set('Content-Type', 'application/json'); //construtor das requisições http
    constructor(private http: HttpClient) { }

    //Faço a requisição http para o back-end, passando a data como parâmetro
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