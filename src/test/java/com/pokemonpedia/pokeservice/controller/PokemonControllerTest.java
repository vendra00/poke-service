package com.pokemonpedia.pokeservice.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.pokemonpedia.pokeservice.AbstractTest;
import com.pokemonpedia.pokeservice.model.Pokemon;

public class PokemonControllerTest extends AbstractTest {

	
	@Override
	@Before
	public void setUp() {
		super.setUp();
	}
	
	@Test
	public void getPokemonList() throws Exception {
		String uri = "/pokemon/getAllPokemonsDb";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Pokemon[] list = super.mapFromJson(content, Pokemon[].class);
		assertTrue(list.length > 0);
	}
	
	@Test
	public void createPokemon() throws Exception {
		String uri = "/pokemon/";
		Pokemon c = new Pokemon();
		c.setId((long) 1);
		c.setName("Gabriel");
		c.setHeight(10);;
		c.setWeight(50);;
		c.setBase_experience(120);;
		String inputJson = super.mapToJson(c);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}
}
