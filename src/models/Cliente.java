package models;

public class Cliente {
    private String nomeCompleto;
    private String cpf;
    private String endereco;
    private String telefone;

    public Cliente(String nomeCompleto, String cpf, String endereco, String telefone) {
        this.nomeCompleto = nomeCompleto;
        this.cpf = cpf;
        this.endereco = endereco;
        this.telefone = telefone;
    }

    public String getNomeCompleto() { return nomeCompleto; }
    public void setNomeCompleto(String nomeCompleto) { this.nomeCompleto = nomeCompleto; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    @Override
    public String toString() {
        return "Nome: " + nomeCompleto + ", CPF: " + cpf + ", Endereço: " + endereco + ", Telefone: " + telefone;
    }
}
