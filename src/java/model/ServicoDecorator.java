package model;

public abstract class ServicoDecorator implements IServico {

    protected IServico servico;

    public ServicoDecorator(IServico servico) {
        this.servico = servico;
    }

}