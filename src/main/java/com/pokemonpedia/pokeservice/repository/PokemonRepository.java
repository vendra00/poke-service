package com.pokemonpedia.pokeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pokemonpedia.pokeservice.model.Pokemon;


@Repository
public interface PokemonRepository extends JpaRepository<Pokemon, Long>{

	Pokemon findPokemonById(Long id);
}
