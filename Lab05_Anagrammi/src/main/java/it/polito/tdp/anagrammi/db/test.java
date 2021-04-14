package it.polito.tdp.anagrammi.db;

import java.util.List;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ParolaDAO c = new ParolaDAO();
		List<String> ana =c.anagrammi("eta");
		System.out.println(c.getAnagrammiCorretti(ana));
		}

}
