package models;

public class Livro {
    private String titulo;
    private String autor;
    private int anoPublicacao;
    private int quantidadeDisponivel;

    public Livro(String titulo, String autor, int anoPublicacao, int quantidadeDisponivel) {
        this.titulo = titulo;
        this.autor = autor;
        this.anoPublicacao = anoPublicacao;
        this.quantidadeDisponivel = quantidadeDisponivel;
    }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }

    public int getAnoPublicacao() { return anoPublicacao; }
    public void setAnoPublicacao(int anoPublicacao) { this.anoPublicacao = anoPublicacao; }

    public int getQuantidadeDisponivel() { return quantidadeDisponivel; }
    public void setQuantidadeDisponivel(int quantidadeDisponivel) { this.quantidadeDisponivel = quantidadeDisponivel; }

    @Override
    public String toString() {
        return "Título: " + titulo + ", Autor: " + autor + ", Ano de Publicação: " + anoPublicacao + ", Quantidade Disponível: " + quantidadeDisponivel;
    }
}
