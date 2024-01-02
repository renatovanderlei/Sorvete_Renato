import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, tap } from 'rxjs';

@Injectable({
    providedIn: 'root'
  })
  export class SaborService {
    url = 'http://localhost:8080/';
    constructor(private http: HttpClient) { }

    save(tipoSorvete : any){
      return this.http.post(this.url + 'sabor' , JSON.stringify(tipoSorvete),
      { headers: { 'Content-Type': 'application/json' } })
      .pipe(tap(res => {return res}));
    }

    findAll() : Observable<any[]>{
      return this.http.get<any[]>(this.url + 'sabor', { headers: { 'Content-Type': 'application/json' } })
      .pipe(tap(res => {return res}));
    }

    delete(id : any){
      return this.http.delete(this.url + 'sabor/' + id, { headers: { 'Content-Type': 'application/json' } })
      .pipe(tap(res => {return res}));
    }

    update(tipoSorvete : any){
      return this.http.put(this.url + 'sabor/' + tipoSorvete.id, JSON.stringify(tipoSorvete),
      { headers: { 'Content-Type': 'application/json' } })
      .pipe(tap(res => {return res}));
    }

  }