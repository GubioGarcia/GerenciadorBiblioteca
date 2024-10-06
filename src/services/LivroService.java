package services;

import models.Livro;
import java.util.ArrayList;
import java.util.List;

public class LivroService {
    private List<Livro> livros;

    public LivroService() {
        this.livros = new ArrayList<>();
    }

    public void setLivros(List<Livro> livros) {
        this.livros.clear();
        this.livros.addAll(livros);
    }

    public boolean adicionarLivro(Livro livro) {
        livros.add(livro);
        return true;
    }

    public List<Livro> listarLivros() {
        return livros;
    }

    public Livro buscarLivroPorTitulo(String titulo) {
        return livros.stream().filter(l -> l.getTitulo().equalsIgnoreCase(titulo)).findFirst().orElse(null);
    }

    public boolean removerLivro(String titulo) {
        Livro livro = buscarLivroPorTitulo(titulo);
        if (livro != null) {
            livros.remove(livro);
            return true;
        }
        return false;
    }

    public boolean editarLivro(String titulo, Livro novoLivro) {
        Livro livro = buscarLivroPorTitulo(titulo);
        if (livro != null) {
            livro.setTitulo(novoLivro.getTitulo());
            livro.setAutor(novoLivro.getAutor());
            livro.setAnoPublicacao(novoLivro.getAnoPublicacao());
            livro.setQuantidadeDisponivel(novoLivro.getQuantidadeDisponivel());
            return true;
        }
        return false;
    }
}
