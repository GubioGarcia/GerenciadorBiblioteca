# Sistema de Gerenciamento de Biblioteca

Este projeto é um **Sistema de Gerenciamento de Biblioteca** desenvolvido em Java que permite o cadastro de **clientes**, **livros** e o gerenciamento de **empréstimos**. O sistema oferece um menu interativo para facilitar o gerenciamento das operações relacionadas a clientes, livros e empréstimos.

## Funcionalidades Principais

### 1. Gerenciamento de Clientes
O sistema permite as seguintes operações com clientes:
- **Cadastro de Clientes:** Adiciona um novo cliente informando nome, CPF, endereço e telefone.
- **Listagem de Clientes:** Exibe todos os clientes cadastrados no sistema.
- **Edição de Clientes:** Permite modificar as informações de um cliente existente.
- **Remoção de Clientes:** Remove um cliente com base no CPF.

### 2. Gerenciamento de Livros
As operações de livros incluem:
- **Cadastro de Livros:** Adiciona novos livros, incluindo título, autor, ano de publicação e quantidade disponível.
- **Listagem de Livros:** Exibe todos os livros cadastrados.
- **Edição de Livros:** Permite editar as informações de um livro existente.
- **Remoção de Livros:** Remove um livro com base no título.

### 3. Gerenciamento de Empréstimos
O sistema também permite controlar os empréstimos de livros:
- **Realizar Empréstimos:** Permite emprestar um livro a um cliente, reduzindo a quantidade disponível.
- **Devolução de Livros:** Realiza a devolução de um livro, aumentando a quantidade disponível.
- **Listagem de Empréstimos:** Exibe todos os empréstimos em andamento.

## Como Usar o Sistema

1. **Iniciar o Sistema:** 
   Ao iniciar o sistema, um menu principal será exibido, permitindo que o usuário escolha entre as opções de **Clientes**, **Livros** e **Empréstimos**.

2. **Gerenciar Clientes:**
   Ao acessar o menu de clientes, você pode cadastrar, listar, editar ou remover clientes. Basta seguir as instruções no terminal.

3. **Gerenciar Livros:**
   Através do menu de livros, você pode realizar as mesmas operações que no menu de clientes, porém relacionadas aos livros da biblioteca.

4. **Gerenciar Empréstimos:**
   O menu de empréstimos permite realizar um empréstimo, devolver um livro ou listar todos os empréstimos registrados.

5. **Sair do Sistema:**
   Para sair do sistema, escolha a opção "4" no menu principal. O sistema salvará automaticamente todas as informações de clientes, livros e empréstimos.

## Estrutura do Código

### Pacotes
- **models:** Contém as classes modelo principais como `Cliente`, `Livro` e `Emprestimo`.
- **services:** Contém as classes de serviço como `ClienteService`, `LivroService` e `EmprestimoService`, responsáveis pela lógica de negócios.
- **utils:** Contém as classes de utilidades como `ClienteDatabase`, `LivroDatabase` e `EmprestimoDatabase`, responsáveis pela persistência dos dados em arquivos CSV.

### Classes Principais
- **Main.java:** Ponto de entrada do sistema, onde o menu principal é exibido.
- **ClienteService.java:** Lida com as operações relacionadas aos clientes.
- **LivroService.java:** Gerencia as operações de livros.
- **EmprestimoService.java:** Controla as operações de empréstimos.

### Persistência
Os dados de clientes, livros e empréstimos são salvos em arquivos CSV e podem ser carregados novamente quando o sistema é reiniciado. Estes arquivos são gerenciados pelas classes `ClienteDatabase`, `LivroDatabase` e `EmprestimoDatabase`.

## Requisitos do Sistema

- **Java 8 ou superior**
- **Bibliotecas padrão da linguagem Java**

## Como Executar

1. **Clone o repositório:**
   git clone https://github.com/seuprojeto/biblioteca

2. **Compile o projeto:**
   javac -d bin src/*.java
   
3. **Execute o sistema:**
   java -cp bin Main
   
## Este arquivo `README.md` descreve de forma detalhada o sistema, suas funcionalidades, como utilizá-lo, e como contribuir.
