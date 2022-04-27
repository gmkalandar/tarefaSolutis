package br.com.solutis.tarefa.utilitarios;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.time.LocalDateTime;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class Criptografia {
	
	

	public String criptografarComPBKDF2(String entrada) throws NoSuchAlgorithmException, InvalidKeySpecException {

		SecureRandom valorAleatorio = new SecureRandom();
		int iteracoes = valorAleatorio.nextInt(250) + 1000;
		char[] chars = entrada.toCharArray();
		byte[] salt = getSalt();
		PBEKeySpec chavePBE = new PBEKeySpec(chars, salt, iteracoes, 64 * 8);
		SecretKeyFactory chaveSecreta = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		byte[] hash = chaveSecreta.generateSecret(chavePBE).getEncoded();

		return converterIteracao(iteracoes) + ":" + montarCadeiaHexDeBytes(salt) + ":" + montarCadeiaHexDeBytes(hash);

	}

	public boolean validarCriptografiaPBKDF2(String entrada, String hashArmazenado)
			throws NoSuchAlgorithmException, InvalidKeySpecException {

		String[] partesDoHash = hashArmazenado.split(":");
		int iteracoes = remontarIteracao(partesDoHash[0]);
		byte[] salt = desmontarCadeiaHexDeBytes(partesDoHash[1]);
		byte[] hashPuro = desmontarCadeiaHexDeBytes(partesDoHash[2]);
		PBEKeySpec chavePBE = new PBEKeySpec(entrada.toCharArray(), salt, iteracoes, hashPuro.length * 8);
		SecretKeyFactory chaveSecreta = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		int diferencial = hashPuro.length ^ chaveSecreta.generateSecret(chavePBE).getEncoded().length;

		for (int i = 0; i < hashPuro.length && i < chaveSecreta.generateSecret(chavePBE).getEncoded().length; i++) {
			diferencial |= hashPuro[i] ^ chaveSecreta.generateSecret(chavePBE).getEncoded()[i];
		}

		return diferencial == 0;

	}

	public String montarStringParaVoto(LocalDateTime dataDoVoto, String valorDoVoto, Long idDaPauta) {
		
		String votoHashParcial = String.valueOf(dataDoVoto.getYear()) + String.valueOf(dataDoVoto.getMonth())
		+ String.valueOf(dataDoVoto.getDayOfMonth()) + String.valueOf(dataDoVoto.getHour())
		+ String.valueOf(dataDoVoto.getMinute()) + String.valueOf(dataDoVoto.getSecond());

		votoHashParcial += valorDoVoto + idDaPauta;
		
		return votoHashParcial;
		
	}
	
	public String criptografarVoto(LocalDateTime dataDoVoto, String valorDoVoto, Long idDaPauta) throws NoSuchAlgorithmException, InvalidKeySpecException {	

		String votoHashParcial = montarStringParaVoto(dataDoVoto, valorDoVoto, idDaPauta);
		
		return this.criptografarComPBKDF2(votoHashParcial);
	}
	
	
	public String montarStringParaPauta(Long idDaPauta, String nomeDaPauta) {
		
		return String.valueOf(idDaPauta) + nomeDaPauta;
		
	}
	
	public String criptografarPauta(Long idDaPauta, String nomeDaPauta) throws NoSuchAlgorithmException, InvalidKeySpecException {
		
		String stringDeCriptografia = montarStringParaPauta(idDaPauta, nomeDaPauta);
								
		return this.criptografarComPBKDF2(stringDeCriptografia);
	}
	

	// Métodos internos

	private byte[] getSalt() throws NoSuchAlgorithmException {

		SecureRandom saltRandomico = SecureRandom.getInstance("SHA1PRNG");
		byte[] salt = new byte[16];
		saltRandomico.nextBytes(salt);
		return salt;

	}

	private String montarCadeiaHexDeBytes(byte[] cadeiaHex)  {

		String hex = new BigInteger(1, cadeiaHex).toString(16);

		int novoTamanho = (cadeiaHex.length * 2) - hex.length();
		if (novoTamanho > 0) {
			return String.format("%0" + novoTamanho + "d", 0) + hex;
		} else {
			return hex;
		}
	}

	private byte[] desmontarCadeiaHexDeBytes(String cadeiaHex) {
		
		byte[] bytes = new byte[cadeiaHex.length() / 2];
		
		for (int i = 0; i < bytes.length; i++) {
			bytes[i] = (byte) Integer.parseInt(cadeiaHex.substring(2 * i, 2 * i + 2), 16);
		}

		return bytes;
	}

	private String converterIteracao(int iteracao) {
		return Integer.toHexString(iteracao * iteracao * iteracao);
	}

	private int remontarIteracao(String iteracao) {

		int reversao = Integer.valueOf(iteracao, 16);
		return (int) Math.cbrt(reversao);

	}

}
