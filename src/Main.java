import models.Cliente;
import models.Emprestimo;
import models.Livro;
import services.ClienteService;
import services.EmprestimoService;
import services.LivroService;
import utils.ClienteDatabase;
import utils.EmprestimoDatabase;
import utils.LivroDatabase;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    	ClienteService clienteService = new ClienteService();
        LivroService livroService = new LivroService();
        EmprestimoService emprestimoService = new EmprestimoService();

        List<Cliente> clientes = ClienteDatabase.carregarClientes();
        clienteService.setClientes(clientes);

        List<Livro> livros = LivroDatabase.carregarLivros();
        livroService.setLivros(livros);

        List<Emprestimo> emprestimos = EmprestimoDatabase.carregarEmprestimos(clientes, livros);
        emprestimoService.setEmprestimos(emprestimos);

        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\nSistema de Gerenciamento de Biblioteca");
            System.out.println("1. Clientes");
            System.out.println("2. Livros");
            System.out.println("3. Empréstimos");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    menuClientes(scanner, clienteService);
                    break;
                case 2:
                    menuLivros(scanner, livroService);
                    break;
                case 3:
                	menuEmprestimos(scanner, emprestimoService, clienteService, livroService);
                case 4:
                    System.out.println("\nSaindo...\nReinicie a aplicação para usar novamente.");
                    ClienteDatabase.salvarClientes(clienteService.listarClientes());
                    LivroDatabase.salvarLivros(livroService.listarLivros());
                    break;
                default:
                    System.out.println("\nOpção inválida.");
            }
        } while (opcao != 4);

        scanner.close();
    }

    private static void menuClientes(Scanner scanner, ClienteService clienteService) {
        int opcao;
        do {
            System.out.println("\nMenu de Clientes");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Listar Clientes");
            System.out.println("3. Editar Cliente");
            System.out.println("4. Remover Cliente");
            System.out.println("5. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarCliente(scanner, clienteService);
                    break;
                case 2:
                    listarClientes(clienteService);
                    break;
                case 3:
                    editarCliente(scanner, clienteService);
                    break;
                case 4:
                    removerCliente(scanner, clienteService);
                    break;
                case 5:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("\nOpção inválida.");
            }
        } while (opcao != 5);
    }

    private static void menuLivros(Scanner scanner, LivroService livroService) {
        int opcao;
        do {
            System.out.println("\nMenu de Livros");
            System.out.println("1. Cadastrar Livro");
            System.out.println("2. Listar Livros");
            System.out.println("3. Editar Livro");
            System.out.println("4. Remover Livro");
            System.out.println("5. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarLivro(scanner, livroService);
                    break;
                case 2:
                    listarLivros(livroService);
                    break;
                case 3:
                    editarLivro(scanner, livroService);
                    break;
                case 4:
                    removerLivro(scanner, livroService);
                    break;
                case 5:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("\nOpção inválida.");
            }
        } while (opcao != 5);
    }
    
    private static void menuEmprestimos(Scanner scanner, EmprestimoService emprestimoService, ClienteService clienteService, LivroService livroService) {
        int opcao;
        do {
            System.out.println("\nMenu de Empréstimos");
            System.out.println("1. Emprestar Livro");
            System.out.println("2. Devolver Livro");
            System.out.println("3. Listar Empréstimos");
            System.out.println("4. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    emprestarLivro(scanner, emprestimoService, clienteService, livroService);
                    break;
                case 2:
                    devolverLivro(scanner, emprestimoService);
                    break;
                case 3:
                    listarEmprestimos(emprestimoService);
                    break;
                case 4:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("\nOpção inválida.");
            }
        } while (opcao != 4);
    }

    public static void cadastrarCliente(Scanner scanner, ClienteService clienteService) {
        System.out.print("\nNome completo: ");
        String nome = scanner.nextLine();
        System.out.print("CPF (11 dígitos): ");
        String cpf = scanner.nextLine();
        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();
        System.out.print("Telefone (8 a 13 dígitos): ");
        String telefone = scanner.nextLine();

        Cliente cliente = new Cliente(nome, cpf, endereco, telefone);

        if (clienteService.adicionarCliente(cliente)) {
            System.out.println("\nCliente cadastrado com sucesso.");
        } else {
            System.out.println("\nErro no cadastro.");
        }
    }

    public static void listarClientes(ClienteService clienteService) {
        List<Cliente> clientes = clienteService.listarClientes();
        if (clientes.isEmpty()) {
            System.out.println("\nNenhum cliente cadastrado.");
        } else {
            clientes.forEach(System.out::println);
        }
    }

    public static void editarCliente(Scanner scanner, ClienteService clienteService) {
        System.out.print("Digite o CPF do cliente a ser editado: ");
        String cpf = scanner.nextLine();
        Cliente cliente = clienteService.buscarClientePorCpf(cpf);
        if (cliente != null) {
            System.out.print("\nNovo nome: ");
            String nome = scanner.nextLine();
            System.out.print("Novo endereço: ");
            String endereco = scanner.nextLine();
            System.out.print("Novo telefone (8 a 13 dígitos): ");
            String telefone = scanner.nextLine();

            Cliente novoCliente = new Cliente(nome, cpf, endereco, telefone);

            if (clienteService.editarCliente(cpf, novoCliente)) {
                System.out.println("\nCliente editado com sucesso.");
            } else {
                System.out.println("Erro na edição.");
            }
        } else {
            System.out.println("\nCliente não encontrado.");
        }
    }

    public static void removerCliente(Scanner scanner, ClienteService clienteService) {
        System.out.print("Digite o CPF do cliente a ser removido: ");
        String cpf = scanner.nextLine();
        if (clienteService.removerCliente(cpf)) {
            System.out.println("\nCliente removido com sucesso.");
        } else {
            System.out.println("\nCliente não encontrado.");
        }
    }

    public static void cadastrarLivro(Scanner scanner, LivroService livroService) {
        System.out.print("\nTítulo: ");
        String titulo = scanner.nextLine();
        System.out.print("Autor: ");
        String autor = scanner.nextLine();
        
        int anoPublicacao = 0;
        boolean entradaValida = false;
        while (!entradaValida) {
            System.out.print("Ano de publicação: ");
            try {
                anoPublicacao = scanner.nextInt();
                entradaValida = true;
            } catch (InputMismatchException e) {
                System.out.println("Por favor, insira um ano válido (número).");
                scanner.nextLine();
            }
        }

        int quantidadeDisponivel = 0;
        entradaValida = false;
        while (!entradaValida) {
            System.out.print("Quantidade disponível: ");
            try {
                quantidadeDisponivel = scanner.nextInt();
                entradaValida = true;
            } catch (InputMismatchException e) {
                System.out.println("Por favor, insira uma quantidade válida (número).");
                scanner.nextLine();
            }
        }

        scanner.nextLine();

        Livro livro = new Livro(titulo, autor, anoPublicacao, quantidadeDisponivel);
        if (livroService.adicionarLivro(livro)) {
            System.out.println("\nLivro cadastrado com sucesso.");
        } else {
            System.out.println("Erro no cadastro.");
        }
    }

    public static void listarLivros(LivroService livroService) {
        List<Livro> livros = livroService.listarLivros();
        if (livros.isEmpty()) {
            System.out.println("\nNenhum livro cadastrado.");
        } else {
            livros.forEach(System.out::println);
        }
    }

    public static void editarLivro(Scanner scanner, LivroService livroService) {
        System.out.print("Digite o título do livro a ser editado: ");
        String titulo = scanner.nextLine();
        Livro livro = livroService.buscarLivroPorTitulo(titulo);
        if (livro != null) {
            System.out.print("\nNovo título: ");
            String novoTitulo = scanner.nextLine();
            System.out.print("Novo autor: ");
            String novoAutor = scanner.nextLine();
            System.out.print("Novo ano de publicação: ");
            int novoAnoPublicacao = scanner.nextInt();
            System.out.print("Nova quantidade disponível: ");
            int novaQuantidadeDisponivel = scanner.nextInt();
            scanner.nextLine();

            Livro novoLivro = new Livro(novoTitulo, novoAutor, novoAnoPublicacao, novaQuantidadeDisponivel);
            if (livroService.editarLivro(titulo, novoLivro)) {
                System.out.println("\nLivro editado com sucesso.");
            } else {
                System.out.println("Erro na edição.");
            }
        } else {
            System.out.println("\nLivro não encontrado.");
        }
    }

    public static void removerLivro(Scanner scanner, LivroService livroService) {
        System.out.print("Digite o título do livro a ser removido: ");
        String titulo = scanner.nextLine();
        if (livroService.removerLivro(titulo)) {
            System.out.println("\nLivro removido com sucesso.");
        } else {
            System.out.println("\nLivro não encontrado.");
        }
    }
    
    public static void emprestarLivro(Scanner scanner, EmprestimoService emprestimoService, ClienteService clienteService, LivroService livroService) {
        System.out.print("Digite o CPF do cliente: ");
        String cpf = scanner.nextLine();
        Cliente cliente = clienteService.buscarClientePorCpf(cpf);
        
        if (cliente != null) {
            System.out.print("Digite o título do livro: ");
            String titulo = scanner.nextLine();
            Livro livro = livroService.buscarLivroPorTitulo(titulo);
            
            if (livro != null) {
                Emprestimo emprestimo = new Emprestimo(cliente, livro);
                if (emprestimoService.emprestarLivro(emprestimo)) {
                    System.out.println("Empréstimo realizado com sucesso.");
                } else {
                    System.out.println("Livro não disponível.");
                }
            } else {
                System.out.println("Livro não encontrado.");
            }
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }
    
    public static void devolverLivro(Scanner scanner, EmprestimoService emprestimoService) {
        System.out.print("Digite o CPF do cliente: ");
        String cpf = scanner.nextLine();
        System.out.print("Digite o título do livro: ");
        String titulo = scanner.nextLine();

        Emprestimo emprestimoParaDevolucao = null;

        for (Emprestimo emprestimo : emprestimoService.listarEmprestimos()) {
            if (emprestimo.getCliente().getCpf().equals(cpf) && emprestimo.getLivro().getTitulo().equalsIgnoreCase(titulo)) {
                emprestimoParaDevolucao = emprestimo;
                break;
            }
        }

        if (emprestimoParaDevolucao != null) {
            emprestimoService.devolverLivro(emprestimoParaDevolucao);
            System.out.println("Livro devolvido com sucesso.");
        } else {
            System.out.println("Empréstimo não encontrado para o cliente e livro fornecidos.");
        }
    }

    
    public static void listarEmprestimos(EmprestimoService emprestimoService) {
        List<Emprestimo> emprestimos = emprestimoService.listarEmprestimos();
        if (emprestimos.isEmpty()) {
            System.out.println("\nNenhum empréstimo realizado.");
        } else {
            emprestimos.forEach(System.out::println);
        }
    }
}
