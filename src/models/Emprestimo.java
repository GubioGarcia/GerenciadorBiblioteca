package models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Emprestimo {
    private Cliente cliente;
    private Livro livro;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucaoPrevista;

    public Emprestimo(Cliente cliente, Livro livro) {
        this.cliente = cliente;
        this.livro = livro;
        this.dataEmprestimo = LocalDate.now();
        this.dataDevolucaoPrevista = dataEmprestimo.plusDays(7);
    }
    
    public Emprestimo(Cliente cliente, Livro livro, LocalDate dataEmprestimo, LocalDate dataDevolucaoPrevista) {
        this.cliente = cliente;
        this.livro = livro;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucaoPrevista = dataDevolucaoPrevista;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Livro getLivro() {
        return livro;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public LocalDate getDataDevolucaoPrevista() {
        return dataDevolucaoPrevista;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return "Empréstimo{" +
                "cliente=" + cliente +
                ", livro=" + livro +
                ", dataEmprestimo=" + dataEmprestimo.format(formatter) +
                ", dataDevolucaoPrevista=" + dataDevolucaoPrevista.format(formatter) +
                '}';
    }
}

