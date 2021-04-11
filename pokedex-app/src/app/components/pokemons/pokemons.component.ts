import { CompileShallowModuleMetadata } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { tap } from 'rxjs/operators';
import { Pokemon } from 'src/app/models/pokemon';
import { PokemonsService } from 'src/app/services/pokemons.service';

@Component({
  selector: 'app-pokemons',
  templateUrl: './pokemons.component.html',
  styleUrls: ['./pokemons.component.css']
})
export class PokemonsComponent implements OnInit {
  pokemons:Pokemon[];

  constructor(private pokemonService: PokemonsService,
    private activatedRout: ActivatedRoute) { }

  ngOnInit(): void {
    this.activatedRout.paramMap.subscribe(params=>{
      let page: number = +params.get('page');
      if(!page){
        page = 0;
      }
      this.pokemonService.getPokemons(page).pipe(
        tap(response=> {
          console.log('pokemon tap 3');
          (response.content as Pokemon[]).forEach(pokemon => console.log(pokemon.name))
        })
        ).subscribe(response => {
          this.pokemons = response.content as Pokemon[];
        });
    });
  }

}
