import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Pokemon } from '../models/pokemon';

@Injectable({
  providedIn: 'root'
})
export class DetailService {
  private urlEndPoint: string = "http://localhost:8080/api/pokemons";
  constructor(private http: HttpClient ) { }

  getPokemon(id:number): Observable<Pokemon>{
    return this.http.get<Pokemon>(`${this.urlEndPoint}/${id}`);
  }
}
