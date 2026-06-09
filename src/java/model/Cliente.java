package model;

public class Cliente {

    private String cpf;
    private String nome;
    private String telefone;

    public Cliente() {
    }

    public Cliente(String cpf, String nome, String telefone) {
        this.cpf = cpf;
        this.nome = nome;
        this.telefone = telefone;
    }

    // GETTERS
    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    // SETTERS
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    // BUILDER PATTERN
    
    public static class Builder {

        private String cpf;
        private String nome;
        private String telefone;

        public Builder cpf(String cpf) {
            this.cpf = cpf;
            return this;
        }

        public Builder nome(String nome) {
            this.nome = nome;
            return this;
        }

        public Builder telefone(String telefone) {
            this.telefone = telefone;
            return this;
        }

        public Cliente build() {
            return new Cliente(cpf, nome, telefone);
        }
    }
}