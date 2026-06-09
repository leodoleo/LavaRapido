package model;

public class ServicoBase implements IServico {

    private Servico servico;

    public ServicoBase(Servico servico) {
        this.servico = servico;
    }

    @Override
    public String getDescricao() {
        return servico.getTipoServico();
    }

    @Override
    public double getValor() {
        return servico.getValor();
    }
}