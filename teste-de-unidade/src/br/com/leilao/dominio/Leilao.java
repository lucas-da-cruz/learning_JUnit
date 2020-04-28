package br.com.leilao.dominio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Leilao {

	private String descricao;
	private List<Lance> lances;
	
	public Leilao(String descricao) {
		this.descricao = descricao;
		this.lances = new ArrayList<Lance>();
	}
	
	public void propoe(Lance lance) {
		//int total = qtdeDeLancesDo(lance.getUsuario());
		
		if(lances.isEmpty() || podeDarLance(lance.getUsuario())){
			lances.add(lance);
		}
	}
	
	private boolean podeDarLance(Usuario usuario){
		return !ultimoLanceDado().getUsuario().equals(usuario) && qtdeDeLancesDo(usuario) < 5;
	}
	
	private int qtdeDeLancesDo(Usuario usuario){
		int total = 0;
		for(Lance l : lances){
			if(l.getUsuario().equals(usuario)) total++;
		}
		return total;
	}

	private Lance ultimoLanceDado(){
		return lances.get(lances.size()-1);
	}
	
	/*Implemente o m�todo dobraLance(Usuario usuario) na classe Leilao. 
	 * Este m�todo deve encontrar o �ltimo lance dado por este usu�rio e 
	 * criar um novo lance com o dobro do lance anterior. 
	 * Caso ele n�o tenha dado nenhum lance anteriormente, n�o � criado um novo lance.
	 * Repare que ainda precisamos seguir as regras da propor um lance: no m�ximo 5
	 * lances por usu�rios e dois lances seguidos do mesmo usu�rio n�o � v�lido.*/
	 public void dobraLance(Usuario usuario) {
	        Lance ultimoLance = ultimoLanceDo(usuario);
	        if(ultimoLance!=null) {
	            propoe(new Lance(usuario, ultimoLance.getValor()*2));
	        }
	  }

	 private Lance ultimoLanceDo(Usuario usuario) {
		 Lance ultimo = null;
	     for(Lance lance : lances) {
	    	 if(lance.getUsuario().equals(usuario)) ultimo = lance;
	     }
	     return ultimo;
	 }

	public String getDescricao() {
		return descricao;
	}

	public List<Lance> getLances() {
		return Collections.unmodifiableList(lances);
	}

	
	
}
