import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, tap } from 'rxjs';

@Injectable({
    providedIn: 'root'
  })
  export class TipoSorveteService {
    url = 'http://localhost:8080/';
    constructor(private http: HttpClient) { }

    save(tipoSorvete : any){
      return this.http.post(this.url + 'tipoSorvete' , JSON.stringify(tipoSorvete),
      { headers: { 'Content-Type': 'application/json' } })
      .pipe(tap(res => {return res}));
    }

    findAll() : Observable<any[]>{
      return this.http.get<any[]>(this.url + 'tipoSorvete', { headers: { 'Content-Type': 'application/json' } })
      .pipe(tap(res => {return res}));
    }

    delete(id : any){
      return this.http.delete(this.url + 'tipoSorvete/' + id, { headers: { 'Content-Type': 'application/json' } })
      .pipe(tap(res => {return res}));
    }

    update(tipoSorvete : any){
      return this.http.put(this.url + 'tipoSorvete/' + tipoSorvete.id, JSON.stringify(tipoSorvete),
      { headers: { 'Content-Type': 'application/json' } })
      .pipe(tap(res => {return res}));
    }

  }