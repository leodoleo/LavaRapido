package model;

public class PolimentoDecorator extends ServicoDecorator {

    private static final double VALOR_POLIMENTO = 50.0;

    public PolimentoDecorator(IServico servico) {
        super(servico);
    }

    @Override
    public String getDescricao() {
        return servico.getDescricao() + " + Polimento";
    }

    @Override
    public double getValor() {
        return servico.getValor() + VALOR_POLIMENTO;
    }
}