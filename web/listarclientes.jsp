<%@page import="java.util.List"%>
<%@page import="model.Cliente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Lista de Clientes</title>
        <link rel="stylesheet" href="Modelocss/listarclientes.css" />
    </head>

    <body>

        <div class="container">
            <h1>Lista de Clientes</h1>

            <form action="ControleCliente" method="get" class="form-busca">
                <input type="hidden" name="op" value="CONSULTAR" />
                <input type="text" name="cpf" placeholder="Digite o CPF" required />
                <button type="submit">Buscar</button>
                <a href="ControleCliente?op=LISTAR">Mostrar Todos</a>
            </form>

            <table>
                <tr>
                    <th>CPF</th>
                    <th>Nome</th>
                    <th>Telefone</th>
                    <th>Editar</th>
                    <th>Excluir</th>
                </tr>

                <%
                    List<Cliente> lista = (List<Cliente>) request.getAttribute("lista");
                    if (lista != null && !lista.isEmpty()) {
                        for (Cliente cliente : lista) {
                %>

                <tr>
                    <td><%= cliente.getCpf()%></td>
                    <td><%= cliente.getNome()%></td>
                    <td><%= cliente.getTelefone()%></td>

                    <td>
                        <form action="ControleCliente" method="get">
                            <input type="hidden" name="op" value="EDITAR" />
                            <input type="hidden" name="cpf" value="<%= cliente.getCpf()%>" />
                            <button class="btn-editar">✏️ Editar</button>
                        </form>
                    </td>

                    <td>
                        <form action="ControleCliente" method="get"> <input type="hidden" name="op" value="EXCLUIR" />
                            <input type="hidden" name="cpf" value="<%= cliente.getCpf()%>" />
                            <button type="submit" class="btn-deletar">🗑️ Excluir</button>
                        </form>
                    </td>
                </tr>

                <%
                    }
                } else {
                %>

                <tr>
                    <td colspan="5">Nenhum cliente encontrado.</td>
                </tr>

                <%
                    }
                %>

            </table>

            <a href="cliente.html">
                <button class="btn-voltar">Voltar</button>
            </a>

        </div>

    </body>
</html>