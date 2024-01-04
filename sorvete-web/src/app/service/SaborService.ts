import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, tap } from 'rxjs';

@Injectable({
    providedIn: 'root'
  })

  //Defino a classe e faço a conexão com o backend
  export class SaborService {
    url = 'http://localhost:8080/';
    constructor(private http: HttpClient) { }

  //Defino o método para criar um novo sabor
    save(tipoSorvete : any){
      return this.http.post(this.url + 'sabor' , JSON.stringify(tipoSorvete),
      { headers: { 'Content-Type': 'application/json' } })
      .pipe(tap(res => {return res}));
    }

    // método para listar todos os sabores
    findAll() : Observable<any[]>{
      return this.http.get<any[]>(this.url + 'sabor', { headers: { 'Content-Type': 'application/json' } })
      .pipe(tap(res => {return res}));
    }

    // método para deletar um sabor
    delete(id : any){
      return this.http.delete(this.url + 'sabor/' + id, { headers: { 'Content-Type': 'application/json' } })
      .pipe(tap(res => {return res}));
    }

    // método para atualizar um sabor
    update(tipoSorvete : any){
      return this.http.put(this.url + 'sabor/' + tipoSorvete.id, JSON.stringify(tipoSorvete),
      { headers: { 'Content-Type': 'application/json' } })
      .pipe(tap(res => {return res}));
    }

  }