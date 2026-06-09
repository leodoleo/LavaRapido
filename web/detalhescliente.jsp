<%-- 
    Document   : detalhescliente
    Created on : 31 de mai. de 2025, 22:37:13
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
    <title>Detalhes do Cliente</title>
    <link rel="stylesheet" href="Modelocss/listarclientes.css" />
</head>
<body>
    <h1>Detalhes do Cliente</h1>

    <% if (c != null) { %>
    <p><strong>CPF:</strong> <%= c.getCpf() %></p>
    <p><strong>Nome:</strong> <%= c.getNome() %></p>
    <strong>Telefone:</strong> <%= c.getTelefone() %>
    <% } else { %>
    <p>Nenhum cliente encontrado.</p>
    <% } %>

    <a href="ControleCliente?op=LISTAR" class="btn-voltar"> Voltar à lista</a>
</body>
</html>
