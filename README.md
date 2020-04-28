# learning_JUnit
 Test learning project in JUnit and Hamcrest
TDD = Test driven Development

TDD é uma prática de desenvolvimento de software na qual o programador escreve um teste antes do código. TDD nos traz segurança e feedback constante sobre a qualidade do nosso código.

É uma boa prática para todo desenvolvedor de software!

A ideia principal de TDD é escrever o teste antes do código de produção.
Vantagens:
	- O teste já nasce testado
	- Segurança na refatoração
	- A classe de teste é a primeira a ser testada, ou seja, o código deve ser claro e limpo;
	
Teste Data Builder é a ideia de diminuir o acoplamento da classe de teste;

Dar passos pequenos pode ser muito benéfico para a sua implementação. Começar pelo teste mais simples nos possibilita evoluir o código aos poucos (geralmente gostamos de começar pelo caso mais difícil, o que pode dificultar).

Além disso, ao implementar códigos simples, aumentamos a facilidade de manutenção do nosso código. Afinal, código simples é muito mais fácil de manter do que códigos complexos. Muitas vezes nós programadores escrevemos códigos complicados desnecessariamente. TDD nos lembra o tempo todo de ser simples.

Muitas pessoas, no entanto, dizem que tomar passos de bebê o tempo todo pode ser contraproducente. Segundo o próprio autor da prática, Kent Beck, você deve tomar passos pequenos sempre que sua "confiança sobre o código" estiver baixa. Se você está trabalhando em um trecho de código e está bastante confiante sobre ele, você pode dar passos um pouco maiores. Mas cuidado, passos grandes não devem ser a regra, mas sim a exceção.

Devemos ver o teste falhar, pois é uma das maneiras que temos para garantir que nosso teste foi implementado corretamente. Afinal, um teste automatizado é código, e podemos implementá-lo incorretamente.
Ao ver que o teste que esperamos falha, realmente falha, temos a primeira garantia de que implementamos ele de maneira correta. Imagine se o teste que esperamos que falhe, na prática não falha. O que aconteceu?
Para completar o "teste" do teste, podemos escrever o código de produção mais simples que o faz passar. Dessa forma, garantimos que o teste fica vermelho quando deve, e fica verde quando deve.


//Essa anotação diz ao JUnit para que ele execute o método antes de cada método de testes da classe
@Before

//métodos anotados com @After são executados após a
//execução dos métodos de testes da classe
@After

//São anotações para declarar para os métodos serem executados antes e depois de todos os métodos de teste.
Eles podem ser bastante úteis quando temos algum recurso que precisa ser inicializado apenas uma vez e que pode ser consumido por todos os métodos de teste sem a necessidade de ser reinicializado.
@BeforeClass
@BeforeClass

Nosso código de teste é altamente acoplado ao nosso código de produção. Isso significa que uma mudança no código de produção pode impactar profundamente em nosso código de testes.

Se não cuidarmos dos nossos testes, uma simples mudança pode impactar em MUITAS mudanças no código de testes.

É por isso que neste capítulo discutimos métodos auxiliares e test data builders. Todas elas são maneiras para fazer com que nosso código de testes evolua mais facilmente.

Assert.fail(); 
É um método que faz falhar nosso teste
