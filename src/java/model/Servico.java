package model;

public class Servico {

    private int id;
    private String tipoServico;
    private double valor;

    public Servico() {
    }

    public Servico(int id, String tipoServico, double valor) {
        this.id = id;
        this.tipoServico = tipoServico;
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public String getTipoServico() {
        return tipoServico;
    }

    public double getValor() {
        return valor;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTipoServico(String tipoServico) {
        this.tipoServico = tipoServico;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}