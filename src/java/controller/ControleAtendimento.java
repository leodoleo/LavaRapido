package controller;

import DAO.AtendimentoDAO;
import DAO.VeiculoDAO;
import DAO.ServicoDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import model.Atendimento;
import model.Veiculo;
import model.Servico;
import model.IServico;
import model.ServicoBase;
import model.CeraDecorator;
import model.PolimentoDecorator;
import model.Command;
import model.CadastrarAtendimentoCommand;

@WebServlet(name = "ControleAtendimento", urlPatterns = {"/ControleAtendimento"})
public class ControleAtendimento extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String op = request.getParameter("op");
        String mensagem = "";

        try {
            AtendimentoDAO adao = new AtendimentoDAO();
            VeiculoDAO vdao = new VeiculoDAO();
            ServicoDAO sdao = new ServicoDAO();

            switch (op) {
                /*CADASTRAR ATENDIMENTO
Endpoint: /ControleAtendimento
Método: POST
Parâmetros:

op=CADASTRAR
data
id_veiculo
id_servico*/

 /*LISTAR ATENDIMENTOS
Endpoint: /ControleAtendimento
Método: GET
Parâmetro: op=LISTAR
Retorno: listaratendimentos.jsp
Atributo: request.setAttribute("atendimentos", lista)*/
                case "CADASTRAR": {

                    int idVeiculo = Integer.parseInt(request.getParameter("txtveiculo"));
                    int idServico = Integer.parseInt(request.getParameter("txtservico"));

                    Veiculo v = vdao.buscarPorId(idVeiculo);
                    Servico s = sdao.buscarPorId(idServico);

                    IServico servicoFinal = new ServicoBase(s);

                    if (request.getParameter("cera") != null) {
                        servicoFinal = new CeraDecorator(servicoFinal);
                    }

                    if (request.getParameter("polimentoExtra") != null) {
                        servicoFinal = new PolimentoDecorator(servicoFinal);
                    }

                    System.out.println("Descrição Final: " + servicoFinal.getDescricao());
                    System.out.println("Valor Final: R$ " + servicoFinal.getValor());

                    Atendimento a = new Atendimento();
                    a.setData(new Date());
                    a.setVeiculo(v);
                    a.setServico(s);

                    Command comando
                            = new CadastrarAtendimentoCommand(
                                    adao,
                                    a);

                    comando.executar();

                    mensagem = "Atendimento registrado com sucesso. Serviço: "
                            + servicoFinal.getDescricao()
                            + " | Valor Final: R$ "
                            + servicoFinal.getValor();

                    request.setAttribute("msg", mensagem);
                    request.getRequestDispatcher("sucesso.jsp").forward(request, response);
                    break;
                }
                case "LISTAR": {

                    List<Atendimento> lista = adao.listar();

                    request.setAttribute("atendimentos", lista);

                    RequestDispatcher rd = request.getRequestDispatcher("listaratendimentos.jsp");
                    rd.forward(request, response);
                    break;
                }
                /*EXCLUIR ATENDIMENTO
Endpoint: /ControleAtendimento
Método: GET
Parâmetros:

op=EXCLUIR
id*/
                case "EXCLUIR": {
                    int id = Integer.parseInt(request.getParameter("id"));
                    adao.excluir(id);

                    response.sendRedirect("ControleAtendimento?op=LISTAR");
                    break;
                }

                case "EDITAR": {
                    int id = Integer.parseInt(request.getParameter("id"));
                    Atendimento a = adao.buscarPorId(id);

                    request.setAttribute("atendimento", a);
                    request.getRequestDispatcher("editaratendimento.jsp").forward(request, response);
                    break;
                }

                case "ATUALIZAR": {
                    int id = Integer.parseInt(request.getParameter("txtid"));
                    int idVeiculo = Integer.parseInt(request.getParameter("txtveiculo"));
                    int idServico = Integer.parseInt(request.getParameter("txtservico"));

                    Veiculo v = vdao.buscarPorId(idVeiculo);
                    Servico s = sdao.buscarPorId(idServico);

                    Atendimento a = new Atendimento();
                    a.setId(id);
                    a.setData(new Date());
                    a.setVeiculo(v);
                    a.setServico(s);

                    adao.atualizar(a);

                    mensagem = "Atendimento atualizado com sucesso.";
                    request.setAttribute("msg", mensagem);
                    request.getRequestDispatcher("sucesso.jsp").forward(request, response);
                    break;
                }
            }

        } catch (ClassNotFoundException | SQLException ex) {
            mensagem = "Erro: " + ex.getMessage();
            request.setAttribute("msg", mensagem);
            request.getRequestDispatcher("erro.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
