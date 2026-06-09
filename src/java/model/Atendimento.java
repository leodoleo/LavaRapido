package model;

import java.util.Date;

public class Atendimento {

    private int id;
    private Date data;
    private Veiculo veiculo;
    private Servico servico;


    public Atendimento() {
    }


    public Atendimento(int id, Date data, Veiculo veiculo, Servico servico) {
        this.id = id;
        this.data = data;
        this.veiculo = veiculo;
        this.servico = servico;
    }

    // GETTERS
    public int getId() {
        return id;
    }

    public Date getData() {
        return data;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public Servico getServico() {
        return servico;
    }

    // SETTERS
    public void setId(int id) {
        this.id = id;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }
}