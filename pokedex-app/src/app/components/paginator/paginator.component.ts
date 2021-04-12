import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-paginator',
  templateUrl: './paginator.component.html'
})
export class PaginatorComponent implements OnInit {

  @Input() paginator:any;
  pages: number[];

  constructor() { }

  ngOnInit(): void {
    this.pages = new Array(this.paginator.totalPages).fill(0).map((valor,indice)=> indice+1);
    console.log(this.pages);
  }

}
