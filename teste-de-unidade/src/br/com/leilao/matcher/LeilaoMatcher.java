package br.com.leilao.matcher;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import br.com.leilao.dominio.Lance;
import br.com.leilao.dominio.Leilao;

public class LeilaoMatcher extends TypeSafeMatcher<Leilao> {
	private final Lance lance;

    public LeilaoMatcher(Lance lance) {
        this.lance = lance;
    }
    
    /*Vamos agora começar pelo método matchesSafely 
     que retorna verdadeiro caso o lance exista
	 ou falso caso não exista.*/
    @Override
    protected boolean matchesSafely(Leilao item) {
        return item.getLances().contains(lance);
    }
    
    /*Agora adicionaremos a descrição desse matcher:*/
    public void describeTo(Description description) {
        description.appendText("leilao com lance " + lance.getValor());
    }
    
    /*Por fim, vamos criar o método que instanciará nosso matcher nos testes:*/
    public static Matcher<Leilao> temUmLance(Lance lance) {
        return new LeilaoMatcher(lance);
    }
}
