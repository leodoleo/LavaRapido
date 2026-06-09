package model;

public class CeraDecorator extends ServicoDecorator {

    private static final double VALOR_CERA = 20.0;

    public CeraDecorator(IServico servico) {
        super(servico);
    }

    @Override
    public String getDescricao() {
        return servico.getDescricao() + " + Aplicação de Cera";
    }

    @Override
    public double getValor() {
        return servico.getValor() + VALOR_CERA;
    }
}