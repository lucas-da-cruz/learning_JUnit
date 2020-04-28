package br.com.leilao.servico;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import br.com.leilao.dominio.Lance;
import br.com.leilao.dominio.Leilao;


public class Avaliador {
	
	private double maiorDeTodos = Double.NEGATIVE_INFINITY;
	private double menorDeTodos = Double.POSITIVE_INFINITY;
	private double media = 0;
	private List<Lance> maiores;
	
	public void avalia(Leilao leilao) {
		
		if(leilao.getLances().size() == 0){
			throw new RuntimeException("Não é possível avaliar um leilão sem lances");
		}

        double total = 0;
        for(Lance lance : leilao.getLances()) {
            if(lance.getValor() > maiorDeTodos) maiorDeTodos = lance.getValor();
            /*else causador de um bug*/
            if(lance.getValor() < menorDeTodos) menorDeTodos = lance.getValor();
            total += lance.getValor();
        }
        if(total == 0) {
            media = 0;
            return;
        }
        media = total / leilao.getLances().size();
        
        maiores = new ArrayList<Lance>(leilao.getLances());
        Collections.sort(maiores, new Comparator<Lance>(){
        	
        	public int compare(Lance a1, Lance a2){
        		if(a1.getValor() < a2.getValor()) return 1;
        		if(a1.getValor() > a2.getValor()) return -1;
        		return 0;
        	}
        });
        maiores = maiores.subList(0, maiores.size() > 3 ? 3 : maiores.size());
	}
	
	public List<Lance> getTresMaiores(){
		return maiores;
	}

    public double getMaiorLance() {
    	return maiorDeTodos; 
    }
    
    public double getMenorLance() {
    	return menorDeTodos;
    }
    
    public double getMedia() {
    	return media; 
    }

}
