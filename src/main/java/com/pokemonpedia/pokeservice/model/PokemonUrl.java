package com.pokemonpedia.pokeservice.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pokemonpedia.pokeservice.dto.PokemonData;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PokemonUrl {

	
	private int count;
	private String next;
	private Object previous;
	private List<PokemonData> results;
	
	
}
