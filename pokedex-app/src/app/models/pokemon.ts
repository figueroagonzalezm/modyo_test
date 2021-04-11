import { Ability } from "./ability";
import { Species } from "./species";
import { Type } from "./type";

export class Pokemon {
 	 id: number;
	 name: string;
	 types: Array<Type> = [] ;
	 weight: number;
	 abilities: Array<Ability> = [];
	 description: string;
	 evolutions: Array<Species>;
	 imageUrl: string;
}




