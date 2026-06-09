<%-- 
    Document   : editarcliente
    Created on : 1 de jun. de 2025, 09:58:15
    Author     : luish
--%>

<%@page contentType="text/html;charset=UTF-8"%>
<%@page import="model.Cliente"%>
<%
    Cliente c = (Cliente) request.getAttribute("cliente");
%>
<!DOCTYPE html>
<html>
    <head>
        <title>Editar Cliente</title>
         <link rel="stylesheet" href="Modelocss/editarcliente.css"/>
    </head>
    <body>
        <div class="container">
            <h1>Editar Cliente</h1>
            <form action="ControleCliente" method="post">
                <input type="hidden" name="op" value="ATUALIZAR" />

                <label>CPF:</label>
                <input type="text" name="txtcpf" value="<%= c.getCpf()%>" readonly />

                <label>Nome:</label>
                <input type="text" name="txtnome" value="<%= c.getNome()%>" />

                <label>Telefone:</label>
                <input type="text" name="txttelefone" value="<%= c.getTelefone()%>" />


            <input type="submit" value="Atualizar" />
        </form>

        <a href="ControleCliente?op=LISTAR">Voltar</a>
    </body>
</html>
