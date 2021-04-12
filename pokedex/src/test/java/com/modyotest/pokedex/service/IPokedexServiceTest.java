package com.modyotest.pokedex.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.modyotest.pokedex.dao.PokedexDAO;
import com.modyotest.pokedex.model.PokemonInfo;

@SpringBootTest
class IPokedexServiceTest extends MockitoExtension{

	
    private static final int RIGHT_POKEMON_ID = 1;
    private static final int RIGHT_PAGE_NUMBER = 1;
    private static final int PAGE_SIZE = 10;
    private static final int ALL_ITEMS_AVAILABLE = 1000;
    
    Page<PokemonInfo> rightPageResponse;
    private List<PokemonInfo> pokemonInfoList;
    private PageRequest rightPageRequest;

	@Mock
    private PokedexDAO pokedexDAO;

    @InjectMocks // auto inject helloRepository
	IPokedexService pokedexService = new PokedexServiceImpl();
    

	
    @BeforeEach
    void setMockOutput() {
    	pokemonInfoList = new ArrayList<>();
		rightPageRequest = PageRequest.of(RIGHT_PAGE_NUMBER, PAGE_SIZE);
		rightPageResponse = new PageImpl<PokemonInfo>(pokemonInfoList, rightPageRequest,
				ALL_ITEMS_AVAILABLE);
		
        Mockito.when(pokedexDAO.findById(RIGHT_POKEMON_ID)).thenReturn(new PokemonInfo(RIGHT_POKEMON_ID, "Pokemon Name"));
        Mockito.when(pokedexDAO.findAll(PageRequest.of(RIGHT_PAGE_NUMBER, PAGE_SIZE))).thenReturn(rightPageResponse);
    }
	
	
	@Test
	void givenAvailablePokemons_WhenCallingMethodFindByIdWithRightID_thenReturnPokemonInfo() {
		PokemonInfo pokemon = pokedexService.findById(RIGHT_POKEMON_ID);
		assertEquals(RIGHT_POKEMON_ID, pokemon.getId());
	}

	@Test
	void givenAvailablePokemons_WhenCallingMethodFindByIdWithRightID_thenCallMethodFindByIdinPokedexDAO() {
		pokedexService.findById(RIGHT_POKEMON_ID);
		verify(pokedexDAO).findById(RIGHT_POKEMON_ID);
	}
	
	@Test
	void givenAvailablePokemons_WhenCallingMethodFindAllWithRightPageNumber_thenCallMethodFindAllinPokedexDAO() {
		pokedexService.findAll(rightPageRequest);
		verify(pokedexDAO).findAll(rightPageRequest);
	}

	@Test
	void givenAvailablePokemons_WhenCallingMethodFindAllWithRightPageRequest_thenReturnRightPokemonPageInfo() {
		Page<PokemonInfo> pageResponse = pokedexService.findAll(rightPageRequest);
		assertEquals(rightPageResponse.getTotalElements(), pageResponse.getTotalElements() );
	}
	
}
