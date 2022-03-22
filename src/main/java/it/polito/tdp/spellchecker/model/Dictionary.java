package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Dictionary {
	Set<String> dizionario; 
	long start, end; 
	long tempo=0; 
	
	public long getTempo() {
		return tempo;
	}

	public void loadDictionary(String language) {
		 dizionario= new HashSet <String>(); 
		
		//usiamo il set perchè è il più veloce
		
		String l; 
			if(language.compareTo("English")==0) {
				l="C:\\Users\\rober\\Desktop\\TDP\\eclipse\\Lab03\\src\\main\\resources\\English.txt"; 	
			}
		
		else {
			l="C:\\Users\\rober\\Desktop\\TDP\\eclipse\\Lab03\\src\\main\\resources\\Italian.txt"; 	
		}
		
		try {
			
			FileReader fr = new FileReader(l); 
			//se è english leggo da inglese altrimenti da italiano
			BufferedReader br = new BufferedReader(fr);
			String word;
				while ((word = br.readLine()) != null) {
					// Aggiungere parola alla struttura dati
					dizionario.add(word); 
				}
			br.close();
			} catch (IOException e){
			System.out.println("Errore nella lettura del file");
			}
	}
	
	public List<String> spellCheckText(String testo) {
		
		List <String> paroleErr = new LinkedList<String>(); 
		testo=testo.toLowerCase(); //metti tutto in minuscolo per controllare
		testo.replaceAll("[.,\\/#!$%\\^&\\*;:{}=\\-_`~()\\[\\]\"]", "");
		String[] parole = testo.split(" "); //per controllare ogni parola, perchè separata da spazio
		
		start= System.nanoTime(); 
		for (String s:parole) {
			if (!dizionario.contains(s)) {
				paroleErr.add(s); 
			}
		}
		end= System.nanoTime(); 
	    tempo= end-start;
		return paroleErr; 
		}
			
	}

