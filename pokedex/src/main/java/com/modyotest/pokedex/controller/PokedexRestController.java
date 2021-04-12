package com.modyotest.pokedex.controller;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.modyotest.pokedex.model.PokemonInfo;
import com.modyotest.pokedex.service.IPokedexService;
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class PokedexRestController {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(PokedexRestController.class);
	private static final int LINES_PER_PAGE = 20;
    
	@Autowired
	private IPokedexService pokedexService;
    
	@GetMapping("/pokemons/page/{page}")
	public Page<PokemonInfo> getPokemons(@PathVariable int page) {
		logger.info("Executing method: getPokemons");
		return pokedexService.findAll(PageRequest.of(page, LINES_PER_PAGE));
	}
	
	@GetMapping("/pokemons/{id}")
	public PokemonInfo getPokemon(@PathVariable int id) {
		//TODO: check the accurate type of object to return in this method maybe ResponseEnty<?>
		logger.info("Executing method: getPokemons");
		return pokedexService.findById(id);
	}
}
