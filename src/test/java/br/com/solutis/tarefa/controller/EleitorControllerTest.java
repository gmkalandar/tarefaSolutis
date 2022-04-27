package br.com.solutis.tarefa.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

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
class EleitorControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	void deveriaBuscarTodosERetornar200()  throws Exception {
		mockMvc
		.perform(MockMvcRequestBuilders
			      .get("/eleitor")
			      .accept(MediaType.APPLICATION_JSON))
			      .andExpect(MockMvcResultMatchers
							.status().isOk());
	}

	@Test
	void deveriaBuscarEleitoorPorIdERetornar200()  throws Exception {
		mockMvc
		.perform(MockMvcRequestBuilders
			      .get("/eleitor/id/{idDoEleitor}", 1)
			      .accept(MediaType.APPLICATION_JSON))
			      .andExpect(MockMvcResultMatchers
							.status().isOk());
	}

	@Test
	void deveriaBuscarPeloIdDaPautaERetornar200()  throws Exception {
		mockMvc
		.perform(MockMvcRequestBuilders
			      .get("/eleitor/idpauta/{idDaPauta}", 2)
			      .accept(MediaType.APPLICATION_JSON))
			      .andExpect(MockMvcResultMatchers
							.status().isOk());
	}

	@Test
	void deveriaBuscarEleitorPorNomeERetornar200()  throws Exception {
		
		URI uri = new URI("/eleitor/nome");
		String json = "{\"nome\":\"jair\"}";
		
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
	void deveriaBuscarEleitorPorCpfERetornar200()  throws Exception {

		URI uri = new URI("/eleitor/cpf");
		String json = "{\"cpf\":\"12345678901\"}";
		
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
	void deveriaIncluirEleitorERetornar201()  throws Exception {
	
		URI uri = new URI("/eleitor/salvar");
		String json = "{\"id\":\"\",\"nome\":\"teste\",\"cpf\":\"99999999999\"}";
		
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
	void deveriaAtualizarEleitorERetornar201()  throws Exception {
		

		URI uri = new URI("/eleitor/salvar");
		String json = "{\"id\":\"2\",\"nome\":\"teste\",\"cpf\":\"99999999\"}";
		
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
	void naoDeveriaAtualizarEleitorERetornar200()  throws Exception {
		

		URI uri = new URI("/eleitor/salvar");
		String json = "{\"id\":\"2\",\"nome\":\"teste\",\"cpf\":\"12345678901\"}";
		
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
	void naodeveriaExcluirEleitorERetornar200()  throws Exception {
		mockMvc
		.perform(MockMvcRequestBuilders
			      .delete("/eleitor/excluir/{idDoEleitor}", 60)
			      .accept(MediaType.APPLICATION_JSON))
			      .andExpect(MockMvcResultMatchers
							.status().isOk());
	}

	
	@Test
	void deveriaExcluirEleitorERetornar201()  throws Exception {
		mockMvc
		.perform(MockMvcRequestBuilders
			      .delete("/eleitor/excluir/{idDoEleitor}", 6)
			      .accept(MediaType.APPLICATION_JSON))
			      .andExpect(MockMvcResultMatchers
							.status().isCreated());
	}
	
}
