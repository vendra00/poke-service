package com.pokemonpedia.pokeservice.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.pokemonpedia.pokeservice.dto.PokemonResponseTemplateVO;
import com.pokemonpedia.pokeservice.model.Pokemon;
import com.pokemonpedia.pokeservice.model.PokemonUrl;

public interface PokemonService {

	
	Pokemon savePokemon(Pokemon p);
	
	List<Pokemon> saveAllPokemons(List<Pokemon> list);
	
	Pokemon findPokemonById(Long id);
		
	PokemonResponseTemplateVO getPokemonFromApi(Long id);
	
	ResponseEntity<PokemonUrl> findAllPokemonsApi();
	
	List<Pokemon>getAllPokemonsDb();

}
