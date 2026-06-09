package controller;

import DAO.ServicoDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import model.Servico;

@WebServlet(name = "ControleServico", urlPatterns = {"/ControleServico"})
public class ControleServico extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String op = request.getParameter("op");
        String mensagem = "";

        try {
            ServicoDAO sdao = new ServicoDAO();

            switch (op) {

                case "CADASTRAR": {
                    String tipo = request.getParameter("txttipo");
                    double valor = Double.parseDouble(request.getParameter("txtvalor"));

                    Servico s = new Servico();
                    s.setTipoServico(tipo);
                    s.setValor(valor);

                    sdao.cadastrar(s);

                    mensagem = "Serviço cadastrado com sucesso.";
                    request.setAttribute("msg", mensagem);
                    request.getRequestDispatcher("sucesso.jsp").forward(request, response);
                    break;
                }

                case "LISTAR": {
                    List<Servico> lista = sdao.listar();
                    request.setAttribute("lista", lista);
                    request.getRequestDispatcher("listarservicos.jsp").forward(request, response);
                    break;
                }

                case "EXCLUIR": {
                    int id = Integer.parseInt(request.getParameter("id"));
                    sdao.excluir(id);

                    response.sendRedirect("ControleServico?op=LISTAR");
                    break;
                }

                case "EDITAR": {
                    int id = Integer.parseInt(request.getParameter("id"));
                    Servico s = sdao.buscarPorId(id);

                    request.setAttribute("servico", s);
                    request.getRequestDispatcher("editarservico.jsp").forward(request, response);
                    break;
                }

                case "ATUALIZAR": {
                    int id = Integer.parseInt(request.getParameter("txtid"));
                    String tipo = request.getParameter("txttipo");
                    double valor = Double.parseDouble(request.getParameter("txtvalor"));

                    Servico s = new Servico();
                    s.setId(id);
                    s.setTipoServico(tipo);
                    s.setValor(valor);

                    sdao.atualizar(s);

                    mensagem = "Serviço atualizado com sucesso.";
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