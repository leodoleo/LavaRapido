package controller;

import DAO.VeiculoDAO;
import DAO.ClienteDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import model.Veiculo;
import model.Cliente;

@WebServlet(name = "ControleVeiculo", urlPatterns = {"/ControleVeiculo"})
public class ControleVeiculo extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String op = request.getParameter("op");
        String mensagem = "";

        try {
            VeiculoDAO vdao = new VeiculoDAO();
            ClienteDAO cdao = new ClienteDAO();

            switch (op) {
/*CADASTRAR VEÍCULO
Endpoint: /ControleVeiculo
Método: POST
Parâmetros:

op=CADASTRAR
txtplaca
txtmodelo
txtcor
txtcpf*/
                case "CADASTRAR": {
                    String placa = request.getParameter("txtplaca");
                    String modelo = request.getParameter("txtmodelo");
                    String cor = request.getParameter("txtcor");
                    String cpf = request.getParameter("txtcpf");

                    Cliente cliente = cdao.buscarPorCpf(cpf);

                    Veiculo v = new Veiculo();
                    v.setPlaca(placa);
                    v.setModelo(modelo);
                    v.setCor(cor);
                    v.setCliente(cliente);

                    vdao.cadastrar(v);

                    mensagem = "Veículo cadastrado com sucesso.";
                    request.setAttribute("msg", mensagem);
                    request.getRequestDispatcher("sucesso.jsp").forward(request, response);
                    break;
                }
/*LISTAR VEÍCULOS
Endpoint: /ControleVeiculo
Método: GET
Parâmetro: op=LISTAR
Retorno: listarveiculos.jsp*/
                case "LISTAR": {
                    List<Veiculo> lista = vdao.listar();
                    request.setAttribute("lista", lista);
                    request.getRequestDispatcher("listarveiculos.jsp").forward(request, response);
                    break;
                }

                case "LISTAR_POR_CLIENTE": {
                    String cpf = request.getParameter("cpf");

                    List<Veiculo> lista = vdao.listarPorCliente(cpf);
                    request.setAttribute("lista", lista);
                    request.getRequestDispatcher("listarveiculos.jsp").forward(request, response);
                    break;
                }
/*EXCLUIR VEÍCULO
Endpoint: /ControleVeiculo
Método: POST
Parâmetros:

op=EXCLUIR
id*/
                case "EXCLUIR": {
                    String idString = request.getParameter("id");
                    if (idString != null) {
                        int id = Integer.parseInt(idString);
                        System.out.println("Tentando excluir veículo ID: " + id);
                        vdao.excluir(id);
                    }
                    response.sendRedirect("ControleVeiculo?op=LISTAR");
                    break;
                }
/*EDITAR VEÍCULO
Endpoint: /ControleVeiculo
Método: GET
Parâmetros:

op=EDITAR
id
Retorno: editarveiculo.jsp*/
                case "EDITAR": {
                    int id = Integer.parseInt(request.getParameter("id"));
                    Veiculo v = vdao.buscarPorId(id);

                    request.setAttribute("veiculo", v);
                    request.getRequestDispatcher("editarveiculo.jsp").forward(request, response);
                    break;
                }
/*ATUALIZAR VEÍCULO
Endpoint: /ControleVeiculo
Método: POST
Parâmetros:

op=ATUALIZAR
id
placa
modelo
cor
cpf*/
                case "ATUALIZAR": {
                    int id = Integer.parseInt(request.getParameter("txtid"));
                    String placa = request.getParameter("txtplaca");
                    String modelo = request.getParameter("txtmodelo");
                    String cor = request.getParameter("txtcor");
                    String cpf = request.getParameter("txtcpf");

                    Cliente cliente = cdao.buscarPorCpf(cpf);

                    Veiculo v = new Veiculo();
                    v.setId(id);
                    v.setPlaca(placa);
                    v.setModelo(modelo);
                    v.setCor(cor);
                    v.setCliente(cliente);

                    vdao.atualizar(v);

                    mensagem = "Veículo atualizado com sucesso.";
                    request.setAttribute("msg", mensagem);
                    request.getRequestDispatcher("sucessoeditarveiculo.jsp").forward(request, response);
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
