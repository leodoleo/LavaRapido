<%-- 
    Document   : resultadocadastrar
    Created on : 30 de mai. de 2025, 21:43:48
    Author     : luish
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
    <head>
        <meta charset="UTF-8">
        <title>Resultado do Cadastro</title>
        <link rel="stylesheet" href="Modelocss/resultadocadastrar.css">
    </head>
    <body>

        <div class="card">
            <h1>Cadastro Realizado com Sucesso!</h1>

            <p><strong>CPF:</strong> <%= request.getParameter("txtcpf")%></p>
            <p><strong>Nome:</strong> <%= request.getParameter("txtnome")%></p>
            <p><strong>Telefone:</strong> <%= request.getParameter("txttelefone")%></p>



            <a href="index.html" class="btn">Voltar ao Início</a>
            <a href="cliente.html" class="btn"> Novo Cadastro</a>


        </div>

    </body>
</html>

