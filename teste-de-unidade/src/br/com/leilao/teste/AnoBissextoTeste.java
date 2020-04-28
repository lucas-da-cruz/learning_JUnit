package br.com.leilao.teste;

import org.junit.Test;

import br.com.anoBissexto.AnoBissexto;
import static org.junit.Assert.assertEquals;


public class AnoBissextoTeste {
	@Test
	public void deveRetornarAnoBissexto() {        
	    AnoBissexto anoBissexto = new AnoBissexto();

	    assertEquals(true, anoBissexto.isAnoBissexto(2020));
	    assertEquals(true, anoBissexto.isAnoBissexto(2016));
	}

	@Test
	public void naoDeveRetornarAnoBissexto() {        
	    AnoBissexto anoBissexto = new AnoBissexto();        

	    assertEquals(false, anoBissexto.isAnoBissexto(2015));
	    assertEquals(false, anoBissexto.isAnoBissexto(2011));
	}
}
