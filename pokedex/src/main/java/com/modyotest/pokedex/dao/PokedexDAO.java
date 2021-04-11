package com.modyotest.pokedex.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.modyotest.pokedex.model.AbilityInfo;
import com.modyotest.pokedex.model.PokemonInfo;
import com.modyotest.pokedex.model.SpeciesInfo;
import com.modyotest.pokedex.model.TypeInfo;

import me.sargunvohra.lib.pokekotlin.client.PokeApi;
import me.sargunvohra.lib.pokekotlin.client.PokeApiClient;
import me.sargunvohra.lib.pokekotlin.model.ChainLink;
import me.sargunvohra.lib.pokekotlin.model.EvolutionChain;
import me.sargunvohra.lib.pokekotlin.model.NamedApiResource;
import me.sargunvohra.lib.pokekotlin.model.NamedApiResourceList;
import me.sargunvohra.lib.pokekotlin.model.Pokemon;
import me.sargunvohra.lib.pokekotlin.model.PokemonAbility;
import me.sargunvohra.lib.pokekotlin.model.PokemonSpecies;
import me.sargunvohra.lib.pokekotlin.model.PokemonType;

@Component
public class PokedexDAO {
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(PokedexDAO.class);

	private PokeApi pokeApi;

	@PostConstruct
	private void init() {
		pokeApi = new PokeApiClient();
	}

	/**
	 * Retrieve Pokemon paged information from PokeApi
	 * @param Pageable pagination parameters
	 * @return List of Pokemon information
	 */
	public Page<PokemonInfo> findAll(Pageable pageable) {
		logger.info("Executing method: findAll");
		
		NamedApiResourceList pokemonList = pokeApi.getPokemonList(
				(int) pageable.getOffset(), 
				pageable.getPageSize());
		List<PokemonInfo> pokemonInfoList = new ArrayList<>();
		
		for (NamedApiResource resource : pokemonList.getResults()) {
			PokemonInfo pokemonInfo = getPokemonInfo(resource.getId());
			logger.info("Retrieving: {}", pokemonInfo);
			pokemonInfoList.add(pokemonInfo);
		}
		PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
		return new PageImpl<>(pokemonInfoList, pageRequest,
				pokemonList.getCount());
	}
	public PokemonInfo findById(int pokemonId) {
		logger.info("Executing method: findById");
		return getPokemonInfo(pokemonId);
	}

	private PokemonInfo getPokemonInfo(int pokemonId) {
		Pokemon pokemon = pokeApi.getPokemon(pokemonId);
		PokemonInfo pokemonInfo = new PokemonInfo();
		pokemonInfo.setId(pokemon.getId());
		pokemonInfo.setName(pokemon.getName());
		pokemonInfo.setWeight(pokemon.getWeight());
		pokemonInfo.setImageUrl(pokemon.getSprites().getFrontDefault());
		addTypes(pokemon, pokemonInfo);
		addAbilities(pokemon, pokemonInfo);
		addEvolutions(pokemonInfo, pokemon.getSpecies().getId());
		return pokemonInfo;
	}

	private void addEvolutions(PokemonInfo pokemonInfo, int specieId) {
		PokemonSpecies pokemonSpecies = pokeApi.getPokemonSpecies(specieId);
		EvolutionChain evolutionChain = pokeApi.getEvolutionChain(pokemonSpecies.getEvolutionChain().getId());		
		ChainLink chain = evolutionChain.getChain();
		ChainLink currentEvolution = chain;

		boolean moreElementsExists = true;
		while (moreElementsExists) {
			ChainLink nextEvolution = null;
			NamedApiResource species = currentEvolution.getSpecies();
			pokemonInfo.getEvolutions().add(new SpeciesInfo(species.getId(), species.getName()));
			if (currentEvolution.getEvolvesTo().isEmpty()) {
				moreElementsExists = false;
			} else {
				nextEvolution = currentEvolution.getEvolvesTo().get(0);
				currentEvolution = nextEvolution;
			}
		}
	}

	private void addAbilities(Pokemon pokemon, PokemonInfo pokemonInfo) {
		for (PokemonAbility ability : pokemon.getAbilities()) {
			pokemonInfo.getAbilities()
					.add(new AbilityInfo(ability.getAbility().getId(), ability.getAbility().getName()));
		}
	}

	private void addTypes(Pokemon pokemon, PokemonInfo pokemonInfo) {
		for (PokemonType pokemonType : pokemon.getTypes()) {
			pokemonInfo.getTypes().add(new TypeInfo(pokemonType.getType().getId(), pokemonType.getType().getName()));
		}
	}

}
