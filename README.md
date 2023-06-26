# Sigma-Utilizando-JPA

Olá, devs! Como vocês estão?

Venho compartilhar um projeto em JPA e Spring Boot com vocês. A ideia principal do projeto é adicionar, pesquisar, remover e consultar informações relacionadas a disciplinas, usando o JPA no dia a dia. Isso é conhecido como CRUD (Create, Read, Update e Delete em inglês). Tudo isso é feito utilizando JPA e Spring Boot, com foco em uma escola chamada Sigma. No sistema, você pode manipular as disciplinas, como mostrado no vídeo.

Utilizei o Lombok, e as principais anotações utilizadas foram:

@Entity

@Data

@AllArgsConstructor

@Table

@NamedQueries

@Id

@GeneratedValue(strategy = GenerationType.IDENTITY)

@Column

Em seguida, utilizei uma classe chamada MateriaRepositoryImpl que implementa a interface MateriaRepository. Essa classe herda todos os métodos da interface. 
Utilizei duas anotações do Spring Boot: @Component e @Transactional.

A anotação @Component significa que a classe usará o padrão de injeção de dependência e será elegível para autoconfiguração e autodetecção de beans anotados, por meio do escaneamento do classpath realizado pelo contêiner IoC do Spring.
A anotação @Transactional demarca transações, permitindo iniciar transações aninhadas, propagar transações para outras camadas, entre outras possibilidades. Essa anotação foi utilizada em alguns métodos dentro da classe MateriaRepositoryImpl, como salvar, PorId, remover e todos.

Dentro do JPA, utilizei um EntityManager para administrar o O/R (mapeamento entre uma classe de entidade e uma fonte de dados subjacente).
 A anotação @PersistenceContext foi utilizada para indicar o local onde os objetos (entidades) manipulados pelo EntityManager atual são armazenados.
Utilizei também uma anotação que não abre a porta 8080 do meu computador, apenas utiliza as funcionalidades do JPA + Spring Boot:

ApplicationContext applicationContext = new
SpringApplicationBuilder(SigmaApplication.class)
.web(WebApplicationType.NONE)
.run(args);

Principais funcionalidades do CRUD no projeto:

Alteração de Matéria

Buscar Matéria

Consultar Matéria

Exclusão de Matéria

Inclusão de Matéria

Principais tecnologias utilizadas e suas versões:

MySQL: 8.0.21

Spring Boot: 3.1.1

IntelliJ IDEA: 2023.1

Java: 17

Hibernate: 5.0

Lombok: 1.18.28

A principal ideia era praticar e utilizar o JPA. Espero que tenham gostado do meu projeto. Se tiverem alguma dúvida, fiquem à vontade para falar comigo.

