<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="model.Atendimento"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Lista de Atendimentos</title>
        <link rel="stylesheet" href="Modelocss/listarclientes.css" />
        
    </head>
    <body>

        <h1>Lista de Atendimentos</h1>

        <table>
            <tr>
                <th>ID</th>
                <th>Data</th>
                <th>ID VEICULO</th>
                <th>ID Serviço</th>
            </tr>

            <%
                List<Atendimento> lista = (List<Atendimento>) request.getAttribute("atendimentos");

                if (lista != null && !lista.isEmpty()) {
                    for (Atendimento a : lista) {
            %>
            <tr>
                <td><%= a.getId()%></td>


                <td><%= a.getData()%></td>


                <td>
                    <%= (a.getVeiculo() != null ? a.getVeiculo().getPlaca() : "N/A")%>
                </td>

                <td>
                    <%= (a.getServico() != null ? a.getServico().getTipoServico() : "N/A")%>
                </td>

                <td>
                    <a href="<%= request.getContextPath()%>/ControleAtendimento?op=EXCLUIR&id=<%= a.getId()%>">
                        Excluir
                    </a>
                </td>
            </tr>
            <%
                }
            } else {
            %>
            <tr>
                <td colspan="4">Nenhum atendimento encontrado</td>
            </tr>
            <%
                }
            %>

        </table>

    </body>
</html>