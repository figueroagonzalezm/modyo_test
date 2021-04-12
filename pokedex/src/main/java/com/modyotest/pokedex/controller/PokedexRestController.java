package com.modyotest.pokedex.controller;

import java.lang.reflect.UndeclaredThrowableException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<?> getPokemons(@PathVariable int page) {
		logger.info("Executing method: getPokemons");
		Map<String, Object> response = new HashMap<>();		
		 Page<PokemonInfo> resultPage = pokedexService.findAll(PageRequest.of(page, LINES_PER_PAGE));
		 if(page > resultPage.getTotalPages()) {
				response.put("message", "Error en la petición");
				response.put("error", "El recurso solicitado no existe");
				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);			 
		 }
		 return new ResponseEntity<>(resultPage, HttpStatus.OK);
	}
	
	@GetMapping("/pokemons/{id}")
	public ResponseEntity<?> getPokemon(@PathVariable int id) {
		logger.info("Executing method: getPokemons");
		Map<String, Object> response = new HashMap<>();
		PokemonInfo pokemoninfo;
		try {
			pokemoninfo = pokedexService.findById(id);
		} catch (UndeclaredThrowableException e) {
			response.put("message", "Error procesando la solicitud");
			response.put("error", "El recurso solicitado no existe");
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(pokemoninfo, HttpStatus.OK); 
	}
}
