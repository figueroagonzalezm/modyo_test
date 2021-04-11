import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Pokemon } from 'src/app/models/pokemon';
import { DetailService } from 'src/app/services/detail.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-detail',
  templateUrl: './detail.component.html',
  styleUrls: ['./detail.component.css']
})
export class DetailComponent implements OnInit {
  pokemon:Pokemon;
  imageUrl:string;
  constructor(private detailService: DetailService,
    private activatedRoute: ActivatedRoute,
    private location:Location) { }
  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe(params=>{
      let id = +params.get('id');
      this.imageUrl = `https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/dream-world/${id}.svg`;
      this.detailService.getPokemon(id).subscribe(pokemon=> this.pokemon = pokemon);
    });
  }
  public back(): void {
    this.location.back();
  }
}
