package com.pokemonpedia.pokeservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pokemonpedia.pokeservice.dto.PokemonResponseTemplateVO;
import com.pokemonpedia.pokeservice.exception.PokemonException;
import com.pokemonpedia.pokeservice.model.Pokemon;
import com.pokemonpedia.pokeservice.model.PokemonUrl;
import com.pokemonpedia.pokeservice.service.PokemonService;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

@Api
@RestController
@RequestMapping("/pokemon")
@Slf4j
public class PokemonController {

	@Autowired
	private PokemonService pokemonService;

	@PostMapping("/")
	public Pokemon savePokemon(@RequestBody Pokemon p) throws PokemonException {
		log.info("Save Pokemon Controller Call");
		try {
			return pokemonService.savePokemon(p);
		} catch (Exception e) {
			throw new PokemonException("There was a problem when saving this Pokemon");
		}
	}

	@GetMapping("findById/{id}")
	public Pokemon findPokemonById(@PathVariable("id") Long id) throws PokemonException {
		log.info("Find Pokemon By Id Controller Call");
		try {
			return pokemonService.findPokemonById(id);
		} catch (Exception e) {
			throw new PokemonException("No pokemon with ID : " + id);
		}
	}

	@GetMapping("findByIdApi/{id}")
	public PokemonResponseTemplateVO getPokemonFromApi(@PathVariable("id") Long pokemonId) throws PokemonException {
		log.info("Get Pokemon From Api Controller Call");
		try {
			return pokemonService.getPokemonFromApi(pokemonId);
		} catch (Exception e) {
			throw new PokemonException("No pokemon with ID : " + pokemonId);
		}

	}

	@Cacheable(value = "listAllPokemonsFromApi")
	@GetMapping("findAllPokemons/")
	public ResponseEntity<PokemonUrl> findAllPokemonsApi() throws PokemonException {
		log.info("Get Pokemon From Api Controller Call");	
		try {
			return pokemonService.findAllPokemonsApi();
		} catch (Exception e) {
			throw new PokemonException("There was a problem at loading the Pokemons list from API");
		}
		

	}

	@Cacheable(value = "listAllPokemonsFromApi")
	@GetMapping("/getAllPokemonsDb")
	public ResponseEntity<List<Pokemon>> getAllPokemonsDb() throws PokemonException {
		try {
			return ResponseEntity.ok(pokemonService.getAllPokemonsDb());
		} catch (Exception e) {
			throw new PokemonException("There was a problem at loading the Pokemons list from DB");
		}
		
	}

}
