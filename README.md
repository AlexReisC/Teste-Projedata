# Teste Prático Projedata
Este repositório contém o código solução da etapa de teste prático para a vaga de Desenvolvedor de Software Júnior na Projedata.

## Como instalar e executar
- Clone o repositório: `git clone https://github.com/AlexReisC/Teste-Projedata.git`
- Certifique-se de ter o Java instalado na sua máquina
- Acesse a pasta do projeto `Teste-Projedata`
- Use a IDE/editor de código da sua preferência para executar o método `main` da classe `Principal.java`

### Tecnologias utilizadas
- JDK 21
- IntelliJ IDEA

## Requisitos Feitos
- [x] [Pessoa.java](src/Pessoa.java): Classe Pessoa com nome (String) e data de nascimento (LocalDate)
```java
import java.time.LocalDate;

public abstract class Pessoa {
    private String nome;
    private LocalDate dataNascimento;
    
    // construtor, getters e setters ...
}
```

- [x] [Funcionario.java](src/Funcionario.java): Classe Funcionário que estende a classe Pessoa, com salário (BigDecimal) e função (String).
```java
import java.math.BigDecimal;

public class Funcionario extends Pessoa {
    private BigDecimal salario;
    private String funcao;
    
    // construtor, getters e setters
}
```

- [x] [Principal.java](src/Principal.java): classe Principal que executa as ações:
  - [x] Inserir todos os funcionários, na mesma ordem e informações da tabela acima. 
  - [x] Remover o funcionário “João” da lista.
  - [x] Imprimir todos os funcionários com todas suas informações, sendo que:
  - [x] Os funcionários receberam 10% de aumento de salário, atualizar a lista de funcionários com novo valor.
  - [x] Agrupar os funcionários por função em um MAP, sendo a chave a “função” e o valor a “lista de funcionários”.
  - [x] Imprimir os funcionários, agrupados por função.
  - [x] Imprimir os funcionários que fazem aniversário no mês 10 e 12.
  - [x] Imprimir o funcionário com a maior idade, exibir os atributos: nome e idade.
  - [x] Imprimir a lista de funcionários por ordem alfabética.
  - [x] Imprimir o total dos salários dos funcionários.
  - [x] Imprimir quantos salários mínimos ganha cada funcionário, considerando que o salário mínimo é R$1212.00.

## Considerações finais
Agradeço a oportunidade de participar do processo seletivo e chegar a esta etapa. Espero que poder conversar e demonstrar minhas habilidades. Fico a disposição!

Alex Reis Cavalcante 
- [LinkedIn](https://linkedin.com/in/alex-reis-cavalcante) 
- [reisc2018@gamil.com](reisc2018@gmail.com)