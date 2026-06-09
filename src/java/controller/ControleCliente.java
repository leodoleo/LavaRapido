package controller;

import DAO.ClienteDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import model.Cliente;

@WebServlet(name = "ControleCliente", urlPatterns = {"/ControleCliente"})
public class ControleCliente extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String op = request.getParameter("op");
        String mensagem = "";

        try {
            ClienteDAO cdao = new ClienteDAO();

            switch (op) {
/*CADASTRAR CLIENTE
Endpoint: /ControleCliente
Método: POST
Parâmetros:

op=CADASTRAR
txtcpf
txtnome
txttelefone
Retorno: resultadocadastrar.jsp*/
                case "CADASTRAR": {
                    String cpf = request.getParameter("txtcpf");
                    String nome = request.getParameter("txtnome");
                    String telefone = request.getParameter("txttelefone");

                    Cliente c = new Cliente(cpf, nome, telefone);
                    cdao.cadastrar(c);

                    mensagem = "Cliente cadastrado com sucesso.";
                    request.setAttribute("msg", mensagem);
                    request.getRequestDispatcher("resultadocadastrar.jsp").forward(request, response);
                    break;
                }
                //LISTAR CLIENTES
/*Endpoint: /ControleCliente
Método: GET
Parâmetro: op=LISTAR
Exemplo: /ControleCliente?op=LISTAR
Retorno: listarclientes.jsp
Atributo: request.setAttribute("lista", lista) */
                case "LISTAR": {
                    List<Cliente> lista = cdao.listar();
                    request.setAttribute("lista", lista);
                    request.getRequestDispatcher("listarclientes.jsp").forward(request, response);
                    break;
                }
/*EXCLUIR CLIENTE
Endpoint: /ControleCliente
Método: POST
Parâmetros:

op=EXCLUIR
cpf
Retorno: redireciona para /ControleCliente?op=LISTAR*/
                case "EXCLUIR": {
                    String cpf = request.getParameter("cpf");
                    cdao.excluir(cpf);

                    response.sendRedirect("ControleCliente?op=LISTAR");
                    break;
                }
/*CONSULTAR CLIENTE
Endpoint: /ControleCliente
Método: GET
Parâmetros:

op=CONSULTAR
cpf
Exemplo: /ControleCliente?op=CONSULTAR&cpf=123
Retorno: detalhescliente.jsp
Atributo: request.setAttribute("cliente", cliente)*/
                case "CONSULTAR": {
                    String cpf = request.getParameter("cpf");
                    Cliente cliente = cdao.buscarPorCpf(cpf);

                    request.setAttribute("cliente", cliente);
                    request.getRequestDispatcher("detalhescliente.jsp").forward(request, response);
                    break;
                }
/*EDITAR CLIENTE
Endpoint: /ControleCliente
Método: GET
Parâmetros:

op=EDITAR
cpf
Retorno: editarcliente.jsp*/
                case "EDITAR": {
                    String cpf = request.getParameter("cpf");
                    Cliente cliente = cdao.buscarPorCpf(cpf);

                    request.setAttribute("cliente", cliente);
                    request.getRequestDispatcher("editarcliente.jsp").forward(request, response);
                    break;
                }
/*ATUALIZAR CLIENTE
Endpoint: /ControleCliente
Método: POST
Parâmetros:

op=ATUALIZAR
txtcpf
txtnome
txttelefone
Retorno: sucesso.jsp*/
                case "ATUALIZAR": {
                    String cpf = request.getParameter("txtcpf");
                    String nome = request.getParameter("txtnome");
                    String telefone = request.getParameter("txttelefone");

                    Cliente c = new Cliente(cpf, nome, telefone);
                    cdao.atualizar(c);

                    mensagem = "Cliente atualizado com sucesso.";
                    request.setAttribute("msg", mensagem);
                    request.getRequestDispatcher("sucesso.jsp").forward(request, response);
                    break;
                }
            }

        } catch (ClassNotFoundException | SQLException ex) {
            mensagem = "Erro: " + ex.getMessage();
            request.setAttribute("msg", mensagem);
            request.getRequestDispatcher("listarclientes.jsp").forward(request, response);
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
