package br.com.aula.heranca.sistema;

public class Cliente {
    private String nome;
    private String cpf;
    private String endereco;
    public Cliente(){

    }
    public Cliente(String nome, String cpf, String endereco){
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    @Override
    public String toString() {
        return "Nome: " + nome + ", Cpf: " + cpf + ", Endereco: " + endereco;
    }
}
