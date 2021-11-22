package com.pokemonpedia.pokeservice.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.pokemonpedia.pokeservice.dto.PokemonData;
import com.pokemonpedia.pokeservice.dto.PokemonResponseTemplateVO;
import com.pokemonpedia.pokeservice.model.Pokemon;
import com.pokemonpedia.pokeservice.model.PokemonUrl;
import com.pokemonpedia.pokeservice.repository.PokemonRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PokemonServiceImpl implements PokemonService {

	@Autowired
	private PokemonRepository pokemonRepository;

	@Autowired
	private RestTemplate restTemplate;

	private static final String GET_ALL_POKEMONS_API = "https://pokeapi.co/api/v2/pokemon?limit=151";
	private static final String GET_POKEMONS_BY_ID_API = "https://pokeapi.co/api/v2/pokemon/";

	@Override
	public Pokemon savePokemon(Pokemon p) {
		log.info("Save Pokemon Service Call");
		return pokemonRepository.save(p);
	}
	
	@Override
	public List<Pokemon> saveAllPokemons(List<Pokemon> list) {
		log.info("Save All Pokemon Service Call");
		return pokemonRepository.saveAll(list);
	}

	@Override
	public Pokemon findPokemonById(Long id) {
		log.info("Find Pokemon By Id Service Call");
		return pokemonRepository.findPokemonById(id);
	}

	@Override
	public PokemonResponseTemplateVO getPokemonFromApi(Long pokemonId) {
		log.info("Find Pokemon From Api Service Call");
		PokemonResponseTemplateVO response = new PokemonResponseTemplateVO();
		Pokemon pokemon = restTemplate.getForObject(GET_POKEMONS_BY_ID_API + pokemonId, Pokemon.class);
		response.setPokemon(pokemon);
		return response;
	}

	@Override
	public ResponseEntity<PokemonUrl> findAllPokemonsApi() {
		log.info("Find All Pokemon From Api Service Call");
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
		ResponseEntity<PokemonUrl> response = restTemplate.exchange(GET_ALL_POKEMONS_API, HttpMethod.GET, entity,
				PokemonUrl.class);
		saveAllPokemons(response);
		return response;
	}

	@Override
	public List<Pokemon> getAllPokemonsDb() {
		if(pokemonRepository.findAll().isEmpty()) {
			findAllPokemonsApi();
		}
		return pokemonRepository.findAll();
	}

	private void saveAllPokemons(ResponseEntity<PokemonUrl> response) {
		log.info("Save All Pokemon Service Call");
		List<PokemonData> list = new ArrayList<>();
		List<Pokemon> listPokeData = new ArrayList<>();
		list = response.getBody().getResults();
		for (PokemonData pokemonData : list) {
			Pokemon pokemon = restTemplate.getForObject(pokemonData.getUrl(), Pokemon.class);
			listPokeData.add(pokemon);
		}	
		saveAllPokemons(listPokeData);	
	}

}
