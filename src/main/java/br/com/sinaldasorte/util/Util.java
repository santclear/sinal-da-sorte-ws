package br.com.sinaldasorte.util;

import java.util.Calendar;
import java.util.Random;

public class Util {
	
	private static Random rand = new Random();
	
	public static String novoHash() {
		String hash = newPassword();
		Calendar date = Calendar.getInstance();
		char timeMillisArray[] =  String.valueOf(date.getTimeInMillis()).toCharArray();
		char hashArray[] = hash.toCharArray();
		StringBuilder timeHash = new StringBuilder();
		
		for(int i = 0; i < hashArray.length; i++) {
			timeHash.append(timeMillisArray[i]);
			timeHash.append(hashArray[i]);
		}
		
		for(int i = hashArray.length; i < timeMillisArray.length; i++) {
			timeHash.append(timeMillisArray[i]);
		}
		
		return timeHash.toString();
	}
	
	public static String newPassword() {
		char[] vet = new char[10];
		for (int i=0; i<10; i++) {
			vet[i] = randomChar();
		}
		return new String(vet);
	}

	private static char randomChar() {
		int opt = rand.nextInt(3);
		if (opt == 0) { // gera um digito
			return (char) (rand.nextInt(10) + 48);
		}
		else if (opt == 1) { // gera letra maiuscula
			return (char) (rand.nextInt(26) + 65);
		}
		else { // gera letra minuscula
			return (char) (rand.nextInt(26) + 97);
		}
	}
}
