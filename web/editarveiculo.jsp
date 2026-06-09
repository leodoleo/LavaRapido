<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Veiculo"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Editar Veículo</title>
        <link rel="stylesheet" href="Modelocss/editarcliente.css"/>
        <style>
            body {
                font-family: Arial;
                background: #f4f4f4;
                padding: 20px;
            }
            .form-container {
                background: white;
                padding: 20px;
                width: 400px;
                margin: auto;
                border-radius: 10px;
            }
            input {
                width: 100%;
                padding: 8px;
                margin: 8px 0;
            }
            button {
                width: 100%;
                padding: 10px;
                background: #4CAF50;
                color: white;
                border: none;
            }
        </style>
    </head>
    <body>

        <%
            Veiculo v = (Veiculo) request.getAttribute("veiculo");
        %>
        <%
            if (v == null) {
                out.println("Erro: veículo não carregado");
                return;
            }
        %>
        <div class="form-container">
            <h2>Editar Veículo</h2>
            <link rel="stylesheet" href="Modelocss/global.css" />
            <form action="<%= request.getContextPath()%>/ControleVeiculo" method="post">

                <input type="hidden" name="op" value="ATUALIZAR">
                <input type="hidden" name="txtid" value="<%= v.getId()%>">

                <input type="text" name="txtplaca" value="<%= v.getPlaca()%>">
                <input type="text" name="txtmodelo" value="<%= v.getModelo()%>">
                <input type="text" name="txtcor" value="<%= v.getCor()%>">
                <input type="text" name="txtcpf" value="<%= v.getCliente().getCpf()%>">

                <button type="submit">Salvar Alterações</button>
            </form>
        </div>

    </body>
</html>