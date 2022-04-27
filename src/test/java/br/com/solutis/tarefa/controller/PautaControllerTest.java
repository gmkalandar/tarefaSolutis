package br.com.solutis.tarefa.controller;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;
import java.net.URI;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WebAppConfiguration
class PautaControllerTest {
	

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	void deveriaBuscarTodasAsPautasERetornar200() throws Exception {
		mockMvc
		.perform(MockMvcRequestBuilders
			      .get("/pauta")
			      .accept(MediaType.APPLICATION_JSON))
			      .andExpect(MockMvcResultMatchers
							.status().isOk());
	}

	@Test
	void deveriaBuscarTodasPorIdLongERetornar200() throws Exception {
		mockMvc
		.perform(MockMvcRequestBuilders
			      .get("/pauta/id/{idDaPauta}", 1)
			      .accept(MediaType.APPLICATION_JSON))
			      .andExpect(MockMvcResultMatchers
							.status().isOk());
		
		
	}

	@Test
	void deveriaBuscarEleitorNasPautaERetornar200() throws Exception {
		mockMvc
		.perform(MockMvcRequestBuilders
			      .get("/pauta/poreleitor/{idDoEleitor}", 1)
			      .accept(MediaType.APPLICATION_JSON))
			      .andExpect(MockMvcResultMatchers
							.status().isOk());
		
	}

	@Test
	void deveriaBuscarTodasPorIdPautasNomeFormERetornar200() throws Exception {
		
		URI uri = new URI("/pauta/nome");
		String json = "{\"nome\":\"teste\"}";
		
		mockMvc
		.perform(MockMvcRequestBuilders
				.post(uri)
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(200));
		
	}

	@Test
	void naoDeveriaAbrirUmaPautaERetornar200() throws Exception {
		
		mockMvc
		.perform(MockMvcRequestBuilders
			      .get("/pauta/abrir/{idDaPauta}", 2)
			      .accept(MediaType.APPLICATION_JSON))
			      .andExpect(MockMvcResultMatchers
							.status().isOk());
	}

	
	@Test
	void deveriaAbrirUmaPautaERetornar201() throws Exception {
		
		mockMvc
		.perform(MockMvcRequestBuilders
			      .get("/pauta/abrir/{idDaPauta}", 1)
			      .accept(MediaType.APPLICATION_JSON))
			      .andExpect(MockMvcResultMatchers
							.status().isCreated());
	}
	
	@Test
	void naoDeveriaUmaAnularPautaERetornar200() throws Exception{
		
		mockMvc
		.perform(MockMvcRequestBuilders
			      .get("/pauta/anular/{idDaPauta}", 200)
			      .accept(MediaType.APPLICATION_JSON))
			      .andExpect(MockMvcResultMatchers
							.status().isOk());
		
	}

	@Test
	void deveriaUmaAnularPautaERetornar201() throws Exception{
		
		mockMvc
		.perform(MockMvcRequestBuilders
			      .get("/pauta/anular/{idDaPauta}", 2)
			      .accept(MediaType.APPLICATION_JSON))
			      .andExpect(MockMvcResultMatchers
							.status().isCreated());
		
	}
	
	@Test
	void deveriaCriarPautaERetornar201() throws Exception{
		
		URI uri = new URI("/pauta/salvar");
		String json = "{\"id\":\"\",\"nome\":\"teste\",\"duracao\":\"10\"}";
		
		mockMvc
		.perform(MockMvcRequestBuilders
				.post(uri)
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(201));
		
	}

	
	@Test
	void deveriaEditarPautaERetornar201() throws Exception{
	
		URI uri = new URI("/pauta/salvar");
		String json = "{\"id\":\"1\",\"nome\":\"teste\",\"duracao\":\"10\"}";
		
		mockMvc
		.perform(MockMvcRequestBuilders
				.post(uri)
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(201));
	}

	@Test
	void deveriaExcluirPautaERetornar201() throws Exception{
		
		mockMvc
		.perform(MockMvcRequestBuilders
			      .delete("/pauta/excluir/{idDaPauta}", 4)
			      .accept(MediaType.APPLICATION_JSON))
			      .andExpect(MockMvcResultMatchers
							.status().isCreated());
		
	} 

	@Test
	void naoDeveriaExcluirPautaERetornar200() throws Exception{
		
		mockMvc
		.perform(MockMvcRequestBuilders
			      .delete("/pauta/excluir/{idDaPauta}", 2)
			      .accept(MediaType.APPLICATION_JSON))
			      .andExpect(MockMvcResultMatchers
							.status().isOk());
		
	} 
}
