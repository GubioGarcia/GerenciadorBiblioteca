package utils;

import models.Livro;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LivroDatabase {
    private static final String FILE_NAME = "livros.csv";
    private static final String CSV_HEADER = "Título,Autor,Ano de Publicação,Quantidade Disponível";

    public static void salvarLivros(List<Livro> livros) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            writer.write(CSV_HEADER);
            writer.newLine();

            for (Livro livro : livros) {
                writer.write(livro.getTitulo() + "," + livro.getAutor() + "," +
                        livro.getAnoPublicacao() + "," + livro.getQuantidadeDisponivel());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar os dados: " + e.getMessage());
        }
    }

    public static List<Livro> carregarLivros() {
        List<Livro> livros = new ArrayList<>();

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
                    livros.add(new Livro(dados[0], dados[1], Integer.parseInt(dados[2]), Integer.parseInt(dados[3])));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado. Nenhum livro será carregado.");
        } catch (IOException e) {
            System.out.println("Erro ao carregar os dados: " + e.getMessage());
        }

        return livros;
    }
}
