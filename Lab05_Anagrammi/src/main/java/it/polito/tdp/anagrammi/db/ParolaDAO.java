package it.polito.tdp.anagrammi.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import it.polito.tdp.anagrammi.model.Parola;



public class ParolaDAO {
	List<Parola> result;
	public List<Parola> getDizionario(){
		String sql ="select*"
				+ "from parola";
		result = new ArrayList<>();
		try {
			Connection conn = DBconnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				Parola c = new Parola(rs.getInt("id"), rs.getString("nome"));
				result.add(c);
			}
			rs.close();
			st.close();
			conn.close();
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return result;
	}
	public List<String> anagrammi(String parola){
		getDizionario();
		List<String> risultato = new ArrayList<>();
		permuta("", parola, 0, risultato);
		return risultato;
	}
	public List<String> getAnagrammiCorretti(List<String> anagrammi){
		List<String> risultato = new ArrayList<>();
		for(int i = 0; i < anagrammi.size(); i++) {
			String anagramma = anagrammi.get(i);
			for(int j = 0; j< result.size(); j++) {
				if(anagramma.equals(result.get(j).getParola())) {
					risultato.add(anagramma);
				}
			}
		}
		return risultato;
	}
	public List<String> getAnagrammiSbagliati(List<String> anagrammi){
		List<String> risultato = new ArrayList<>();
		for(int i = 0; i < anagrammi.size(); i++) {
			String anagramma = anagrammi.get(i);
			boolean sbagliato = true;
			for(int j = 0; j< result.size(); j++) {
				if(anagramma.equals(result.get(j).getParola())) {
					sbagliato = false;
				}
			}
			if(sbagliato) {
				risultato.add(anagramma);
			}
		}
		return risultato;
	}
	/*
	 * livello = lunghezza soluzione parziale
	 * parziale = stringa che contiene l'anagramma incompleto
	 * lettere = le lettere della parola iniziale che aancora non abbiamo utilizzato
	 */
	private void permuta(String parziale, String lettere, int livello, List<String> risultato) {
		if(lettere.length()==0) { //caso terminale
			//la soluzione parizale è anche una soluziione completa
			risultato.add(parziale);
			///System.out.println(parziale);
			
		}
		else {
			//fai ricorsione
			// sottoproblema == una lettera di 'lettere'
			for(int pos = 0; pos < lettere.length(); pos++) {
				char tentativo = lettere.charAt(pos);
				
				
				String nuovaParziale = parziale + tentativo;
				String nuovaLettere = lettere.substring(0,pos)+lettere.substring(pos+1);
				//lettere //togli carattere in pos da lettere
				permuta(nuovaParziale, nuovaLettere, livello+1,risultato);
				// back tracking(in questo caso non serve), rimetti a posto parziale, rimetti a posto lettere
			}
		}
	}
	

}
