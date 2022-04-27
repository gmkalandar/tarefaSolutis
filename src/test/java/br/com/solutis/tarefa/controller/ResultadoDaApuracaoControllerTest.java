package br.com.solutis.tarefa.controller;

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
class ResultadoDaApuracaoControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	
	@Test
	void deveriaBuscarRetorno200ApuracaoPeloIdDaPauta() throws Exception {
				
		mockMvc
		.perform(MockMvcRequestBuilders
			      .get("/apuracao/consultar/{idDaPauta}", 1)
			      .accept(MediaType.APPLICATION_JSON))
			      .andExpect(MockMvcResultMatchers
							.status().isOk());
		
	}

	@Test
	void deveriaApurarVotacaoDePautaComResposta201() throws Exception {
		
		URI uri = new URI("/apuracao");
		String json = "{\"idDaPauta\":\"2\"}";
		
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
	void deveriaApurarVotacaoDePautaComResposta200() throws Exception {
		
		URI uri = new URI("/apuracao");
		String json = "{\"idDaPauta\":\"1\"}";
		
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
