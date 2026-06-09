package model;

import DAO.AtendimentoDAO;

public class CadastrarAtendimentoCommand implements Command {

    private AtendimentoDAO dao;
    private Atendimento atendimento;

    public CadastrarAtendimentoCommand(
            AtendimentoDAO dao,
            Atendimento atendimento) {

        this.dao = dao;
        this.atendimento = atendimento;
    }

    @Override
    public void executar() {

        try {

            dao.cadastrar(atendimento);

        } catch (Exception e) {

            throw new RuntimeException(
                    "Erro ao cadastrar atendimento",
                    e);

        }

    }
}