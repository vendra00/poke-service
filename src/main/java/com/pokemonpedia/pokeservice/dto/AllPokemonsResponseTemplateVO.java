package com.pokemonpedia.pokeservice.dto;

import com.pokemonpedia.pokeservice.model.PokemonUrl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllPokemonsResponseTemplateVO {

	private PokemonUrl pokemon;
}
