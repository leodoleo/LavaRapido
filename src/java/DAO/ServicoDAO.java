package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Servico;
import util.Conexao;

public class ServicoDAO {

    public List<Servico> listar() throws ClassNotFoundException, SQLException {
        List<Servico> lista = new ArrayList<>();

        String sql = "SELECT * FROM servico";

        try (Connection con = Conexao.getConexao();
             PreparedStatement comando = con.prepareStatement(sql);
             ResultSet rs = comando.executeQuery()) {

            while (rs.next()) {
                Servico s = new Servico();
                s.setId(rs.getInt("id_servico"));
                s.setTipoServico(rs.getString("tiposervico"));
                s.setValor(rs.getDouble("valor"));

                lista.add(s);
            }
        }

        return lista;
    }

    public Servico buscarPorId(int id) throws ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM servico WHERE id_servico = ?";
        Servico s = null;

        try (Connection con = Conexao.getConexao();
             PreparedStatement comando = con.prepareStatement(sql)) {

            comando.setInt(1, id);

            try (ResultSet rs = comando.executeQuery()) {
                if (rs.next()) {
                    s = new Servico();
                    s.setId(rs.getInt("id_servico"));
                    s.setTipoServico(rs.getString("tiposervico"));
                    s.setValor(rs.getDouble("valor"));
                }
            }
        }

        return s;
    }

    public void cadastrar(Servico s) throws ClassNotFoundException, SQLException {
        String sql = "INSERT INTO servico (tiposervico, valor) VALUES (?, ?)";

        try (Connection con = Conexao.getConexao();
             PreparedStatement comando = con.prepareStatement(sql)) {

            comando.setString(1, s.getTipoServico());
            comando.setDouble(2, s.getValor());

            comando.executeUpdate();
        }
    }

    public void atualizar(Servico s) throws ClassNotFoundException, SQLException {
        String sql = "UPDATE servico SET tiposervico = ?, valor = ? WHERE id_servico = ?";

        try (Connection con = Conexao.getConexao();
             PreparedStatement comando = con.prepareStatement(sql)) {

            comando.setString(1, s.getTipoServico());
            comando.setDouble(2, s.getValor());
            comando.setInt(3, s.getId());

            comando.executeUpdate();
        }
    }

    public void excluir(int id) throws ClassNotFoundException, SQLException {
        String sql = "DELETE FROM servico WHERE id_servico = ?";

        try (Connection con = Conexao.getConexao();
             PreparedStatement comando = con.prepareStatement(sql)) {

            comando.setInt(1, id);
            comando.executeUpdate();
        }
    }
}