package services;

import models.Emprestimo;
import utils.EmprestimoDatabase;
import java.util.ArrayList;
import java.util.List;

public class EmprestimoService {
    private List<Emprestimo> emprestimos = new ArrayList<>();

    public void setEmprestimos(List<Emprestimo> emprestimos) {
        this.emprestimos.clear();
        this.emprestimos.addAll(emprestimos);
    }

    public boolean emprestarLivro(Emprestimo emprestimo) {
        if (emprestimo.getLivro().getQuantidadeDisponivel() > 0) {
            emprestimos.add(emprestimo);
            emprestimo.getLivro().setQuantidadeDisponivel(emprestimo.getLivro().getQuantidadeDisponivel() - 1);
            EmprestimoDatabase.salvarEmprestimos(emprestimos);
            return true;
        }
        return false;
    }

    public void devolverLivro(Emprestimo emprestimo) {
        emprestimos.remove(emprestimo);
        emprestimo.getLivro().setQuantidadeDisponivel(emprestimo.getLivro().getQuantidadeDisponivel() + 1);
        EmprestimoDatabase.salvarEmprestimos(emprestimos);
    }

    public List<Emprestimo> listarEmprestimos() {
        return emprestimos;
    }
}
