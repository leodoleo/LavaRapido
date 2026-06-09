<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Cadastro de Veículo</title>

    <link rel="stylesheet" href="Modelocss/cliente.css">
</head>

<body>



<form action="ControleVeiculo" method="post">

    <div>
        <h2>Cadastro de Veículo</h2>
    </div>
<jsp:include page="menu.jsp"/>
    <div>
        <label>CPF do Cliente:</label>
        <input type="text" name="txtcpf" placeholder="Digite o CPF do cliente" required>
    </div>

    <div>
        <label>Placa:</label>
        <input type="text" name="txtplaca" placeholder="Digite a placa" required>
    </div>

    <div>
        <label>Modelo:</label>
        <input type="text" name="txtmodelo" placeholder="Digite o modelo">
    </div>

    <div>
        <label>Cor:</label>
        <input type="text" name="txtcor" placeholder="Digite a cor">
    </div>

    <input type="hidden" name="op" value="CADASTRAR">

    <input type="submit" value="Cadastrar Veículo">

    <a href="ControleVeiculo?op=LISTAR" class="botao-listar">
        LISTAR VEÍCULOS
    </a>

    <a href="cliente.html" class="botao-listar">
        IR PARA CLIENTES
    </a>

    <a href="atendimento.jsp" class="botao-listar">
        IR PARA ATENDIMENTO
    </a>

</form>

</body>
</html>