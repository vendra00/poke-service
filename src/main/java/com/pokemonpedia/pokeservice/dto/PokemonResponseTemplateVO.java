package com.pokemonpedia.pokeservice.dto;

import com.pokemonpedia.pokeservice.model.Pokemon;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PokemonResponseTemplateVO {
	
	private Pokemon pokemon;

}
