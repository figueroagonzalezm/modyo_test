package com.modyotest.pokedex.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.modyotest.pokedex.model.PokemonInfo;

public interface IPokedexService {
	public Page<PokemonInfo> findAll(Pageable pageable);
	public PokemonInfo findById(int pokemonId);
}