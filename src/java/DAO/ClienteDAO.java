package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Cliente;
import util.Conexao;

public class ClienteDAO {

    public void cadastrar(Cliente c) throws ClassNotFoundException, SQLException {
        String sql = "INSERT INTO cliente (cpf, nome, telefone) VALUES (?, ?, ?)";

        try (Connection con = Conexao.getConexao(); PreparedStatement comando = con.prepareStatement(sql)) {

            comando.setString(1, c.getCpf());
            comando.setString(2, c.getNome());
            comando.setString(3, c.getTelefone());

            comando.executeUpdate();
        }
    }

    public void excluir(String cpf) throws ClassNotFoundException, SQLException {
        String sql = "DELETE FROM cliente WHERE cpf = ?";
        try (Connection con = Conexao.getConexao(); PreparedStatement comando = con.prepareStatement(sql)) {
            comando.setString(1, cpf);
            int linhasAfetadas = comando.executeUpdate();
            System.out.println("Linhas excluídas: " + linhasAfetadas);
        }
    }

    public List<Cliente> listar() throws ClassNotFoundException, SQLException {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM cliente";

        try (Connection con = Conexao.getConexao(); PreparedStatement comando = con.prepareStatement(sql); ResultSet rs = comando.executeQuery()) {

            while (rs.next()) {
                Cliente c = new Cliente();
                c.setCpf(rs.getString("cpf"));
                c.setNome(rs.getString("nome"));
                c.setTelefone(rs.getString("telefone"));

                lista.add(c);
            }
        }

        return lista;
    }

    public Cliente buscarPorCpf(String cpf) throws ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM cliente WHERE cpf = ?";
        Cliente cliente = null;

        try (Connection con = Conexao.getConexao(); PreparedStatement comando = con.prepareStatement(sql)) {

            comando.setString(1, cpf);

            try (ResultSet rs = comando.executeQuery()) {
                if (rs.next()) {
                    cliente = new Cliente();
                    cliente.setCpf(rs.getString("cpf"));
                    cliente.setNome(rs.getString("nome"));
                    cliente.setTelefone(rs.getString("telefone"));
                }
            }
        }

        return cliente;
    }

    public void atualizar(Cliente c) throws ClassNotFoundException, SQLException {
        String sql = "UPDATE cliente SET nome = ?, telefone = ? WHERE cpf = ?";

        try (Connection con = Conexao.getConexao(); PreparedStatement comando = con.prepareStatement(sql)) {

            comando.setString(1, c.getNome());
            comando.setString(2, c.getTelefone());
            comando.setString(3, c.getCpf());

            comando.executeUpdate();
        }
    }
}
