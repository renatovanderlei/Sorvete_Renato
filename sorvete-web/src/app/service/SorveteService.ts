import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, tap } from 'rxjs';

@Injectable({
    providedIn: 'root'
  })
  export class SorveteService {
    url = 'http://localhost:8080/';
    constructor(private http: HttpClient) { }

    save(sorvete : any){
      return this.http.post(this.url + 'sorvete' , JSON.stringify(sorvete),
      { headers: { 'Content-Type': 'application/json' } })
      .pipe(tap(res => {return res}));
    }

    findAll() : Observable<any[]>{
      return this.http.get<any[]>(this.url + 'sorvete', { headers: { 'Content-Type': 'application/json' } })
      .pipe(tap(res => {return res}));
    }

    delete(id : any){
      return this.http.delete(this.url + 'sorvete/' + id, { headers: { 'Content-Type': 'application/json' } })
      .pipe(tap(res => {return res}));
    }

    update(sorvete : any){
      return this.http.put(this.url + 'sorvete/' + sorvete.id, JSON.stringify(sorvete),
      { headers: { 'Content-Type': 'application/json' } })
      .pipe(tap(res => {return res}));
    }

  }