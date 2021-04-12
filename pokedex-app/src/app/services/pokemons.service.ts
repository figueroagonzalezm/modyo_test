import { Injectable } from '@angular/core';
import { of, Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Pokemon } from '../models/pokemon';
import { map, catchError, tap } from 'rxjs/operators';
@Injectable({
  providedIn: 'root'
})
export class PokemonsService {
  private urlEndPoint: string = "https://pokedexapi-pokedexapi.azuremicroservices.io/api/pokemons";

  constructor(private http: HttpClient) { }

  getPokemons(page: number): Observable<any>{
    return this.http.get(`${this.urlEndPoint}/page/${page}`).pipe(
      tap((response:any)=>{
        console.log('ClienteService: tap 1');
        (response.content as Pokemon[]).forEach(pokemon => console.log(pokemon.name));
        return response;
      }),
      map((response:any)=>{
          return response;
      }));
  }
}
