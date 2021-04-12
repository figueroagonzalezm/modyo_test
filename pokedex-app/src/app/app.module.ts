import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { PokemonsComponent } from './components/pokemons/pokemons.component';
import { RouterModule, Routes } from '@angular/router';
import { DetailComponent } from './components/detail/detail.component';
import { PaginatorComponent } from './components/paginator/paginator.component';


const routes: Routes = [
  {path: '', redirectTo: '/pokemons', pathMatch: 'full'},
  {path: 'pokemons', component: PokemonsComponent},
  {path: 'pokemons/page/:page', component: PokemonsComponent},
  {path: 'detail/:id', component: DetailComponent},

];

@NgModule({
  declarations: [
    AppComponent,
    PokemonsComponent,
    DetailComponent,
    PaginatorComponent,

  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    RouterModule.forRoot(routes)
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
