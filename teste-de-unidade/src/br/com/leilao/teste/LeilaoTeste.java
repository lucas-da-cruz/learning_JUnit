package br.com.leilao.teste;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.leilao.builder.CriadorDeLeilao;
import br.com.leilao.dominio.Lance;
import br.com.leilao.dominio.Leilao;
import br.com.leilao.dominio.Usuario;

import static br.com.leilao.matcher.LeilaoMatcher.temUmLance;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class LeilaoTeste {
	
	@Test
	public void deveReceberUmLance(){
		Leilao leilao = new Leilao("Macbook PRO 15");
		assertEquals(0, leilao.getLances().size());
		
		leilao.propoe(new Lance(new Usuario("Steve Jobs"), 2000));
		
		assertEquals(1, leilao.getLances().size());
		assertEquals(2000, leilao.getLances().get(0).getValor(), 0.00001);
	}
	
	@Test
    public void deveReceberUmLanceComMatcher() {
        Leilao leilao = new CriadorDeLeilao().para("Macbook Pro 15").constroi();
        assertEquals(0, leilao.getLances().size());

        Lance lance = new Lance(new Usuario("Steve Jobs"), 2000);
        leilao.propoe(lance);

        assertThat(leilao.getLances().size(), equalTo(1));
        assertThat(leilao, temUmLance(lance));
    }
	
	@Test
	public void deveReceberVariosLances(){
		Leilao leilao = new Leilao("Macbook PRO 15");
		leilao.propoe(new Lance(new Usuario("Steve Jobs"), 2000));
		leilao.propoe(new Lance(new Usuario("Steve Wozniok"), 3000));
		
		assertEquals(2, leilao.getLances().size());
		assertEquals(2000, leilao.getLances().get(0).getValor(), 0.00001);
		assertEquals(3000, leilao.getLances().get(1).getValor(), 0.00001);
	}
	
	@Test
	public void naoDeveAceitarDoisLancesSeguidosDoMesmoUsuario(){
		Leilao leilao = new Leilao("Macbook PRO 15");
		leilao.propoe(new Lance(new Usuario("Steve Jobs"), 2000));

		leilao.propoe(new Lance(new Usuario("Steve Jobs"), 2000));
		leilao.propoe(new Lance(new Usuario("Steve Jobs"), 2500));
		
		assertEquals(1, leilao.getLances().size());
		assertEquals(2000, leilao.getLances().get(0).getValor(), 0.00001);
	}
	
	@Test
	public void naoDeveAceitarMaisQue5LancesDoMesmoUsuario(){
		Leilao leilao = new Leilao("Macbook PRO 15");
		Usuario steveJobs = new Usuario("Steve Jobs");
		Usuario bilGates = new Usuario("Bil Gates");
		
		leilao.propoe(new Lance(steveJobs, 2000));
		leilao.propoe(new Lance(bilGates, 3000));
		
		leilao.propoe(new Lance(steveJobs, 4000));
		leilao.propoe(new Lance(bilGates, 5000));		
		
		leilao.propoe(new Lance(steveJobs, 6000));
		leilao.propoe(new Lance(bilGates, 7000));		
		
		leilao.propoe(new Lance(steveJobs, 8000));
		leilao.propoe(new Lance(bilGates, 9000));		
		
		leilao.propoe(new Lance(steveJobs, 10000));
		leilao.propoe(new Lance(bilGates, 11000));
	
		//Deve ser ignorado
		leilao.propoe(new Lance(steveJobs, 12000));
		
		assertEquals(10, leilao.getLances().size());
		assertEquals(11000, leilao.getLances().get(leilao.getLances().size()-1).getValor(), 0.0001);

	}
	
	@Test
    public void deveDobrarOUltimoLanceDado() {
        Leilao leilao = new Leilao("Macbook Pro 15");
        Usuario steveJobs = new Usuario("Steve Jobs");
        Usuario billGates = new Usuario("Bill Gates");

        leilao.propoe(new Lance(steveJobs, 2000));
        leilao.propoe(new Lance(billGates, 3000));
        leilao.dobraLance(steveJobs);

        assertEquals(4000, leilao.getLances().get(2).getValor(), 0.00001);
    }
	
    @Test
    public void naoDeveDobrarCasoNaoHajaLanceAnterior() {
        Leilao leilao = new Leilao("Macbook Pro 15");
        Usuario steveJobs = new Usuario("Steve Jobs");

        leilao.dobraLance(steveJobs);

        assertEquals(0, leilao.getLances().size());
    }
	
}
