package utils;

import models.Cliente;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDatabase {
    private static final String FILE_NAME = "clientes.csv";
    private static final String CSV_HEADER = "Nome Completo,CPF,Endereço,Telefone";

    public static void salvarClientes(List<Cliente> clientes) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            writer.write(CSV_HEADER);
            writer.newLine();

            for (Cliente cliente : clientes) {
                writer.write(cliente.getNomeCompleto() + "," + cliente.getCpf() + "," +
                        cliente.getEndereco() + "," + cliente.getTelefone());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar os dados: " + e.getMessage());
        }
    }

    public static List<Cliente> carregarClientes() {
        List<Cliente> clientes = new ArrayList<>();

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
                    clientes.add(new Cliente(dados[0], dados[1], dados[2], dados[3]));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado. Nenhum cliente será carregado.");
        } catch (IOException e) {
            System.out.println("Erro ao carregar os dados: " + e.getMessage());
        }
        
        return clientes;
    }
}
