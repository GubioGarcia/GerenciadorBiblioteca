package utils;

import models.Emprestimo;
import models.Cliente;
import models.Livro;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class EmprestimoDatabase {
    private static final String FILE_NAME = "emprestimos.csv";
    private static final String CSV_HEADER = "CPF,TituloLivro,DataEmprestimo,DataDevolucaoPrevista";

    public static void salvarEmprestimos(List<Emprestimo> emprestimos) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            writer.write(CSV_HEADER);
            writer.newLine();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            for (Emprestimo emprestimo : emprestimos) {
                String linha = emprestimo.getCliente().getCpf() + "," +
                        emprestimo.getLivro().getTitulo() + "," +
                        emprestimo.getDataEmprestimo().format(formatter) + "," +
                        emprestimo.getDataDevolucaoPrevista().format(formatter);
                writer.write(linha);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar os empréstimos: " + e.getMessage());
        }
    }

    public static List<Emprestimo> carregarEmprestimos(List<Cliente> clientes, List<Livro> livros) {
        List<Emprestimo> emprestimos = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String linha;
            boolean primeiro = true;

            while ((linha = reader.readLine()) != null) {
                if (primeiro) {
                    primeiro = false;
                    continue;
                }
                String[] dados = linha.split(",");
                if (dados.length == 4) {
                    String cpf = dados[0];
                    String tituloLivro = dados[1];
                    LocalDate dataEmprestimo = LocalDate.parse(dados[2], formatter);
                    LocalDate dataDevolucaoPrevista = LocalDate.parse(dados[3], formatter);

                    Cliente cliente = clientes.stream()
                            .filter(c -> c.getCpf().equals(cpf))
                            .findFirst()
                            .orElse(null);

                    Livro livro = livros.stream()
                            .filter(l -> l.getTitulo().equalsIgnoreCase(tituloLivro))
                            .findFirst()
                            .orElse(null);

                    if (cliente != null && livro != null) {
                        Emprestimo emprestimo = new Emprestimo(cliente, livro, dataEmprestimo, dataDevolucaoPrevista);
                        emprestimos.add(emprestimo);
                        livro.setQuantidadeDisponivel(livro.getQuantidadeDisponivel() - 1);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de empréstimos não encontrado. Nenhum empréstimo será carregado.");
        } catch (IOException e) {
            System.out.println("Erro ao carregar os empréstimos: " + e.getMessage());
        }

        return emprestimos;
    }
}
