<%@page import="java.util.List"%>
<%@page import="model.Veiculo"%>
<%@page import="model.Cliente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="pt-BR">
    <head>
        <meta charset="UTF-8">
        <title>Lista de Veículos</title>

        <link rel="stylesheet" href="Modelocss/listarclientes.css">
    </head>

    <body>

        <jsp:include page="menu.jsp"/>

        <div class="container">
            <h1>Lista de Veículos</h1>

            <table>
                <tr>
                    <th>ID</th>
                    <th>Placa</th>
                    <th>Modelo</th>
                    <th>Cor</th>
                    <th>CPF Cliente</th>
                    <th>Editar</th>
                    <th>Excluir</th>
                </tr>

                <%
                    List<Veiculo> lista = (List<Veiculo>) request.getAttribute("lista");

                    if (lista != null && !lista.isEmpty()) {
                        for (Veiculo v : lista) {
                %>

                <tr>
                    <td><%= v.getId()%></td>
                    <td><%= v.getPlaca()%></td>
                    <td><%= v.getModelo()%></td>
                    <td><%= v.getCor()%></td>
                    <td><%= v.getCliente().getCpf()%></td>

                    <!-- EDITAR -->
                    <td>
                        <form action="<%= request.getContextPath()%>/ControleVeiculo" method="get">
                            <input type="hidden" name="op" value="EDITAR">
                            <input type="hidden" name="id" value="<%= v.getId()%>">
                            <button class="btn-editar">✏️ Editar</button>
                        </form>
                    </td>

                    <!-- EXCLUIR -->
                    <td>
                        <form action="ControleVeiculo" method="get"> <input type="hidden" name="op" value="EXCLUIR">
                            <input type="hidden" name="id" value="<%= v.getId()%>">
                            <button type="submit" class="btn-deletar" onclick="return confirm('Deseja realmente excluir este veículo?')">🗑️ Excluir</button>
                        </form>
                    </td>   
                </tr>

                <%
                    }
                } else {
                %>

                <tr>
                    <td colspan="7" style="text-align:center;">Nenhum veículo encontrado.</td>
                </tr>

                <%
                    }
                %>

            </table>

            <br>

            <a href="veiculo.jsp" class="botao-listar">Cadastrar Veículo</a>
            <a href="cliente.html" class="botao-listar">Clientes</a>
            <a href="atendimento.jsp" class="botao-listar">Atendimento</a>

        </div>

    </body>
</html>