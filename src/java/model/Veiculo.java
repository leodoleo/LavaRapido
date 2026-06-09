package model;

public class Veiculo {

    private int id;
    private String placa;
    private String modelo;
    private String cor;
    private Cliente cliente;

    public Veiculo() {
    }

    public Veiculo(int id, String placa, String modelo, String cor, Cliente cliente) {
        this.id = id;
        this.placa = placa;
        this.modelo = modelo;
        this.cor = cor;
        this.cliente = cliente;
    }

    public int getId() {
        return id;
    }

    public String getPlaca() {
        return placa;
    }

    public String getModelo() {
        return modelo;
    }

    public String getCor() {
        return cor;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}