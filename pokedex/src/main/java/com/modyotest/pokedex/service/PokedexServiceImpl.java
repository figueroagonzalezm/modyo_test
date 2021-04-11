package com.modyotest.pokedex.service;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.modyotest.pokedex.dao.PokedexDAO;
import com.modyotest.pokedex.model.PokemonInfo;

@Service
public class PokedexServiceImpl implements IPokedexService {
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(PokedexServiceImpl.class);
	
	@Autowired
	PokedexDAO pokedexDAO;
	
	/**
	 * Retrieve Pokemons paged information from PokeApi
	 * @param {@link Pageable} pagination parameters
	 * @return List of Pokemon information
	 */
	@Override
	@Cacheable("pokemonPages")
	public Page<PokemonInfo> findAll(Pageable pageable) {
		logger.info("Executing method: findAll");
		return pokedexDAO.findAll(pageable);
	}
	/**
	 * Retrieve Pokemon information from PokeApi
	 * @param pokemonId
	 * @return {@link PokemonInfo} With the Pokemon information
	 */
	@Override
	@Cacheable("pokemons")
	public PokemonInfo findById(int pokemonId) {
		return pokedexDAO.findById(pokemonId);
	}


}
