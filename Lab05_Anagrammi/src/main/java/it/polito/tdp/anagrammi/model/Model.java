package it.polito.tdp.anagrammi.model;

import java.util.*;

import it.polito.tdp.anagrammi.db.ParolaDAO;

public class Model {
	private ParolaDAO paroladao;

	public Model() {
		paroladao = new ParolaDAO();
	}
	public List<String> getCorretti(String parola){
		List<String> anagrammi = paroladao.anagrammi(parola);
		return paroladao.getAnagrammiCorretti(anagrammi);
	}
	public List<String> getSbagliati(String parola){
		List<String> anagrammi = paroladao.anagrammi(parola);
		return paroladao.getAnagrammiSbagliati(anagrammi);
	}
	
}
