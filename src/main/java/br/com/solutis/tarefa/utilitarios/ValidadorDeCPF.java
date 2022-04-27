package br.com.solutis.tarefa.utilitarios;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.solutis.tarefa.controller.dto.RespostaCpfDto;

public class ValidadorDeCPF {

	static String enderecoURI = "https://cpf-api-almfelipe.herokuapp.com/cpf/";

	public boolean validarCpf(String cpf) throws Exception {
		
		String urlParaChamada = enderecoURI + cpf;
		ObjectMapper objectMapper = new ObjectMapper();

		try {

			URL url = new URL(urlParaChamada);
			HttpURLConnection conexao = (HttpURLConnection) url.openConnection();

			if (conexao.getResponseCode() < 200 && conexao.getResponseCode() >= 300) {
				System.out.println("Serviço indisponível. Permissão excepcional concedida!");
				return true;
			}

			BufferedReader resposta = new BufferedReader(new InputStreamReader((conexao.getInputStream())));
			String jsonEmString = converteJsonEmString(resposta);

			RespostaCpfDto respostaCpfDto = objectMapper.readValue(jsonEmString, RespostaCpfDto.class);
					
			return respostaCpfDto.isValidade();

		} catch (Exception e) {
			throw new Exception("Serviço com erro. Permissão excepcional concedida! \n\n  ERRO: " + e);
		}
	}

	private String converteJsonEmString(BufferedReader buffereReader) throws IOException {
		String resposta = ""; 
		String jsonEmString = "";
		while ((resposta = buffereReader.readLine()) != null) {
			jsonEmString += resposta;
		}
		return jsonEmString;
	}

}
