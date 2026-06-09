<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Registrar Atendimento</title>

    <link rel="stylesheet" href="Modelocss/cliente.css">
</head>

<body>


<form action="/LavaRapidu/ControleAtendimento" method="post">

    <div>
        <h2>Registrar Atendimento</h2>
    </div>
<jsp:include page="menu.jsp"/>
    <div>
        <label>ID do Veículo:</label>
        <input type="number" name="txtveiculo" placeholder="Digite o ID do veículo" required>
    </div>

    <div>
        <label>Serviço:</label>
        <select name="txtservico" required>
            <option value="">Selecione...</option>
            <option value="1">Lavagem Simples - R$30</option>
            <option value="2">Lavagem Completa - R$50</option>
            <option value="3">Lavagem Motor - R$80</option>
            <option value="4">Higienização Interna - R$150</option>
            <option value="5">Polimento - R$300</option>
            <option value="6">Cristalização de Vidros - R$70</option>
            <option value="7">Aspiração Interna - R$25</option>
        </select>
    </div>

    <div>
    <label>Serviços Adicionais:</label><br>

    <input type="checkbox" name="cera" value="true">
    Aplicação de Cera (+ R$20)

    <br>

    <input type="checkbox" name="polimentoExtra" value="true">
    Polimento Premium (+ R$50)

</div>

<input type="hidden" name="op" value="CADASTRAR">

    <input type="submit" value="Registrar Atendimento">

    <a href="ControleAtendimento?op=LISTAR" class="botao-listar">
        LISTAR ATENDIMENTOS
    </a>

    <a href="veiculo.jsp" class="botao-listar">
        IR PARA VEÍCULOS
    </a>

    <a href="cliente.html" class="botao-listar">
        IR PARA CLIENTES
    </a>

</form>

</body>
</html>