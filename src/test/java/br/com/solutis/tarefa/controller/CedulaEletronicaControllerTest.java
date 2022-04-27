package br.com.solutis.tarefa.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
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
class CedulaEletronicaControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	void deveriaBuscarTodosOsVotosERetornar200() throws Exception {
		mockMvc
		.perform(MockMvcRequestBuilders
			      .get("/votos/{idDaPauta}", 2)
			      .accept(MediaType.APPLICATION_JSON))
			      .andExpect(MockMvcResultMatchers
							.status().isOk());
	}

	@Test
	void deveriaBAuditarVotosPorIdDaPautaERetornar200() throws Exception {
	
		
		mockMvc
		.perform(MockMvcRequestBuilders
			      .get("/votos/auditar/{idDaPauta}", 2)
			      .accept(MediaType.APPLICATION_JSON))
			      .andExpect(MockMvcResultMatchers
							.status().isOk());
		
	}

	@Test
	void deveriaRegistrarVotoERetornar201() throws Exception {
		
		mockMvc
		.perform(MockMvcRequestBuilders
			      .get("/pauta/abrir/{idDaPauta}", 3)
			      .accept(MediaType.APPLICATION_JSON))
			      .andExpect(MockMvcResultMatchers
							.status().isCreated());
		
		
		URI uri = new URI("/votos/votar");
		String json = "{\"idDaPauta\":\"3\",\"idDoEleitor\":\"1\",\"valorDoVoto\":\"1\"}";
		
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
	void naoDeveriaRegistrarVotoERetornar200() throws Exception {
		
			
		URI uri = new URI("/votos/votar");
		String json = "{\"idDaPauta\":\"1\",\"idDoEleitor\":\"1\",\"valorDoVoto\":\"1\"}";
		
		mockMvc
		.perform(MockMvcRequestBuilders
				.post(uri)
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(200));
	}
	
	
}
