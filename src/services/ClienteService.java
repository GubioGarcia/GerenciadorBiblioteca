package services;

import models.Cliente;
import utils.ClienteDatabase;
import java.util.ArrayList;
import java.util.List;

public class ClienteService {
    private List<Cliente> clientes;

    public ClienteService() {
        this.clientes = new ArrayList<>();
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes.clear();
        this.clientes.addAll(clientes);
    }

    private boolean validarNomeEndereco(String nome, String endereco) {
        if (nome.isEmpty()) {
            System.out.println("\nErro: Nome não pode ser vazio.");
            return false;
        }
        if (endereco.isEmpty()) {
            System.out.println("\nErro: Endereço não pode ser vazio.");
            return false;
        }
        return true;
    }

    private boolean validarCpf(String cpf) {
        if (!cpf.matches("\\d{11}")) {
            System.out.println("\nErro: CPF deve conter 11 dígitos numéricos.");
            return false;
        }
        return true;
    }

    private boolean validarTelefone(String telefone) {
        if (!telefone.matches("\\d{8,13}")) {
            System.out.println("\nErro: Telefone deve conter entre 8 e 13 dígitos numéricos.");
            return false;
        }
        return true;
    }

    private boolean cpfJaCadastrado(String cpf) {
        if (clientes.stream().anyMatch(c -> c.getCpf().equals(cpf))) {
            System.out.println("\nErro: CPF já cadastrado.");
            return true;
        }
        return false;
    }

    public boolean adicionarCliente(Cliente cliente) {
        if (!validarNomeEndereco(cliente.getNomeCompleto(), cliente.getEndereco())) {
            return false;
        }
        if (!validarCpf(cliente.getCpf())) {
            return false;
        }
        if (cpfJaCadastrado(cliente.getCpf())) {
            return false;
        }
        if (!validarTelefone(cliente.getTelefone())) {
            return false;
        }

        clientes.add(cliente);
        ClienteDatabase.salvarClientes(clientes);
        return true;
    }

    public List<Cliente> listarClientes() {
        return clientes;
    }

    public Cliente buscarClientePorCpf(String cpf) {
        return clientes.stream().filter(c -> c.getCpf().equals(cpf)).findFirst().orElse(null);
    }

    public boolean removerCliente(String cpf) {
        Cliente cliente = buscarClientePorCpf(cpf);
        if (cliente != null) {
            clientes.remove(cliente);
            ClienteDatabase.salvarClientes(clientes);
            return true;
        }
        return false;
    }

    public boolean editarCliente(String cpf, Cliente novoCliente) {
        Cliente cliente = buscarClientePorCpf(cpf);
        if (cliente != null
            && validarNomeEndereco(novoCliente.getNomeCompleto(), novoCliente.getEndereco())
            && validarTelefone(novoCliente.getTelefone())) {
            cliente.setNomeCompleto(novoCliente.getNomeCompleto());
            cliente.setEndereco(novoCliente.getEndereco());
            cliente.setTelefone(novoCliente.getTelefone());
            ClienteDatabase.salvarClientes(clientes);
            return true;
        }
        return false;
    }
}
