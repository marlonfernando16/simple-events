# simple-events
### Descrição da aplicação
  A aplicação deverá permitir que seus usuários cadastrem vagas de emprego relacionadas a um determinado evento. Os candidatos irão visualizar essas vagas e poderão se candidatar, podendo ser contratado ou não. O candidato que participou do evento poderá avaliá-lo, atribuindo-lhe uma nota após o fim do evento, da mesma forma,  o promotor do evento poderá avaliar o desempenho do usuário que participou do evento, atribuindo-lhe uma nota. A aplicação possui quatro perfis de usuários: visitante, candidato, promotor de eventos  e administrador. 

### Perfis de usuários

#### Visitante
- não necessita estar cadastrado no sistema;
- capaz de consultar eventos, identificando o dono do evento pelo login;
  - A consulta poderá ser pelo título do evento ou por nome da especialidade

#### Candidato 
- realizar seu próprio cadastro no sistema
- uma vez logado, este usuário poderá:
  - consultar eventos, identificando o dono do evento pelo login;
    - A consulta poderá ser pelo título do evento ou por nome da especialidade
  - candidatar-se a um determinado evento, especificando as vagas que deseja concorrer
  - avaliar um evento que já participou, atribuindo-lhe uma nota 

#### Promotor de eventos
- realizar seu próprio cadastro no sistema
- uma vez logado, este usuário poderá:
  - consultar eventos, identificando o dono do evento pelo login;
    - A consulta poderá ser pelo título do evento ou por nome da especialidade
  - gerenciar (adicionar, remover, visualizar, finalizar) seus eventos;
    - o evento deve está associado a uma ou várias vagas
    - cada vaga deve está relacionado a uma especialidade
  - visualizar as candidaturas das vagas, identificando cada candidato pelo login
  - avaliar a candidatura de uma vaga, informando ao sistema se o candidato em questão foi aprovado;
  - avaliar o desempenho do candidato aprovado, atribuindo-lhe uma nota

#### Administrador
- capaz de realizar todas as funcionalidades dos perfís acima
- gerenciar (adicionar, remover, visualizar) especialidades do sistema

#### Outros requisitos do sistema

- No tocante ao cadastro do usuário (Promotor de eventos e Candidato).  
  - Deverão ser cadastrados : nome,data de nascimento, telefone,  login/senha (verificando se o login já existe ou não)
  - Usar senhas codificadas
- Usar mecanismo de autenticação e controle de sessão, para o perfil Promotor de Eventos , Candidato e Administrador
Ao acessar o formulário de login no sistema, o campo do login deverá estar preenchido com o login do último usuário logado, na máquina local. Permita digitar outro login, se o usuário for outro.
- Desenvolver o sistema com o framework Spring.



### Caso de uso
![Modelo Conceitual](https://github.com/Tales313/simple-events/blob/master/images/Diagrama%20de%20caso%20de%20uso%20-%20gerenciador%20de%20eventos.jpg)


### Diagrama de classes
![Modelo Conceitual](https://github.com/Tales313/simple-events/blob/master/images/Diagrama%20de%20Classes.jpg)







