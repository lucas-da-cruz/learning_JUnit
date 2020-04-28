package br.com.leilao.teste;

import static org.junit.Assert.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.leilao.builder.CriadorDeLeilao;
import br.com.leilao.dominio.Lance;
import br.com.leilao.dominio.Leilao;
import br.com.leilao.dominio.Usuario;
import br.com.leilao.servico.Avaliador;

public class AvaliadorTest {
	
	private Avaliador leiloeiro;
	private Usuario joao;
	private Usuario jose;
	private Usuario maria;
	
	//Essa anotação diz ao JUnit para que ele execute
	//o método antes de cada método de testes
	@Before
	public void setUp(){
		this.leiloeiro = new Avaliador();
		
		this.joao = new Usuario("João");
		this.maria = new Usuario("Maria");
		this.jose = new Usuario("Jose");
	}
	
	//métodos anotados com @After são executados após a
	//execução do método de teste.
	@After
	public void finaliza() {
	  System.out.println("fim");
	}
	
	@BeforeClass
	public static void testandoBeforeClass() {
	  System.out.println("before class");
	}

	@AfterClass
	public static void testandoAfterClass() {
	  System.out.println("after class");
	}
	
	//Só ficará verde se o exception for lançado
	//Substitui a necessidade de utilizar Try/Catch no código
	@Test(expected=RuntimeException.class)
	public void naoDeveAvaliarleiloesSemNenhumLanceDado(){
		//try{
			Leilao leilao = new CriadorDeLeilao().para("XBOX").constroi();
			
			leiloeiro.avalia(leilao);
			//Método para fazer falhar o teste
			Assert.fail();
			
		//se exceção for lançada, teste dará certo
		/*} catch(RuntimeException e){
			//Deu Certo
		}*/
	}
	
	/*
	 * Teste agora que o Avaliador entende um Leilão cujos lances foram dados em ordem aleatória.
	   Para isso, crie um Leilao e proponha Lance com valores randômicos, como por exemplo,
	   200, 450, 120, 700, 630, 230. Ao final, verifique que o menor é 120 e o maior é 700.
	 */
	@Test
	public void avaliadorFuncionaComLancesOrdemAleatoria(){
		/*Leilao leilao = new Leilao("Playstation 4 Novo");*/
		
		/*leilao.propoe(new Lance(joao, 200.0));
		leilao.propoe(new Lance(maria, 450.0));
		leilao.propoe(new Lance(jose, 120.0));
		leilao.propoe(new Lance(maria, 700.0));
		leilao.propoe(new Lance(joao, 630.0));*/
		
		//Parte 01: montar os cenários
		Leilao leilao = new CriadorDeLeilao().para("Playstation 4 novo")
        		.lance(joao, 200.0)
        		.lance(maria, 450.0)
        		.lance(jose, 120.0)
        		.lance(maria, 700.0)
        		.lance(maria, 630.0)
        		.constroi();
		
		//Parte 02: executa ação
		leiloeiro.avalia(leilao);
		
		//Parte 3: validação
		double maiorEsperado = 700;
		double menorEsperado = 120;
		
		assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.0001);
		//assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0.0001);
		//Mesma coisa que o método acima, porem fazendo uso do
		//AssertThat, método da biblioteca do hamcrest
		assertThat(leiloeiro.getMenorLance(), equalTo(menorEsperado));
	}
	
	@Test
	public void deveEntenderLanceEmOrdemCrescente() {
		//Parte 01: montar os cenários
        Leilao leilao = new CriadorDeLeilao().para("Playstation 3 novo")
        		.lance(joao, 100.0)
        		.lance(maria, 200.0)
        		.lance(joao, 300.0)
        		.lance(maria, 400.0)
        		.constroi();

		//Parte 02: executa ação
		leiloeiro.avalia(leilao);
		
		//Parte 3: validação
		double maiorEsperado = 400;
		double menorEsperado = 100;
		
		//System.out.println(maiorEsperado == leiloeiro.getMaiorLance());
		assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.00001);
		
		//System.out.println(menorEsperado == leiloeiro.getMenorLance());
		assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0.00001);
	}
	
	@Test
	public void deveEntenderLeilaoComApenasUmLance(){
		Leilao leilao = new CriadorDeLeilao().para("Playstation 3 novo")
        		.lance(joao, 1000.0)
        		.constroi();
        
        leiloeiro.avalia(leilao);
		
		assertEquals(1000.0, leiloeiro.getMaiorLance(), 0.0001);
		assertEquals(1000.0, leiloeiro.getMenorLance(), 0.0001);
	}
	
	@Test
	public void deveEncontrarOsTresMaioresLances(){
		Leilao leilao = new CriadorDeLeilao().para("Playstation 3 novo")
        		.lance(joao, 100.0)
        		.lance(maria, 200.0)
        		.lance(joao, 300.0)
        		.lance(maria, 400.0)
        		.constroi();
        
        leiloeiro.avalia(leilao);
		
		List<Lance> maiores = leiloeiro.getTresMaiores();
		assertEquals(3, maiores.size());
		/*assertEquals(400.0, maiores.get(0).getValor(), 0.00001);
		assertEquals(300.0, maiores.get(1).getValor(), 0.00001);
		assertEquals(200.0, maiores.get(2).getValor(), 0.00001);*/
		
		//Mesma coisa que o assertEquals faz
		//Mas aqui se utiliza a lib do hamcrest
		assertThat(maiores, hasItems(
				new Lance(maria, 400),
				new Lance(joao, 300),
				new Lance(maria, 200)
		));
	}
	
	 @Test
	    public void deveCalcularAMedia() {
	       // cenario: 3 lances em ordem crescente
		 Leilao leilao = new CriadorDeLeilao().para("Playstation 3 novo")
	        		.lance(joao, 300.0)
	        		.lance(maria, 400.0)
	        		.lance(joao, 500.0)
	        		.constroi();

	        // executando a acao
	        leiloeiro.avalia(leilao);

	        // comparando a saida com o esperado
	        assertEquals(400, leiloeiro.getMedia(), 0.0001);
	    }

	    @Test(expected=RuntimeException.class)
	    public void testaMediaDeZeroLance(){

	    	Leilao leilao = new CriadorDeLeilao().para("Iphone 7")
	        		.constroi();

	        leiloeiro.avalia(leilao);
	        //validacao
	        assertEquals(0, leiloeiro.getMedia(), 0.0001);
	    }
	    
	    /*
		 * Escreva o teste para garantir que a classe Avaliador funciona caso o Leilão possua apenas um Lance.
		   Para isso, crie um Leilao com apenas um lance (com o valor de, por exemplo, 200), e invoque o Avaliador.
		   Para validar, verifique que tanto o maior quanto o menor sejam iguais a 200.
		 */
		@Test
		public void avaliadorFuncionaComUmLance(){
			Leilao leilao = new CriadorDeLeilao().para("Playstation 3 novo")
	        		.lance(joao, 200.0)
	        		.constroi();
			
			leiloeiro.avalia(leilao);
			
			assertEquals(200.0, leiloeiro.getMaiorLance(), 0.0001);
			assertEquals(200.0, leiloeiro.getMenorLance(), 0.0001);
		}

		/*
		 * Teste que o Avaliador funciona para lances dados em ordem decrescente.
		   Crie Lances com valores de 400, 300, 200, 100, por exemplo, e garanta que a 
		   saída bata com os valores da entrada.
		 */
		@Test
		public void avaliadorFuncionaComLancesOrdemDecrescente(){
			Leilao leilao = new CriadorDeLeilao().para("Playstation 4 novo")
	        		.lance(joao, 400.0)
	        		.lance(maria, 300.0)
	        		.lance(jose, 100.0)
	        		.constroi();
			
			leiloeiro.avalia(leilao);
			
			assertEquals(400.0, leiloeiro.getMaiorLance(), 0.0001);
			assertEquals(100.0, leiloeiro.getMenorLance(), 0.0001);
		}
}
