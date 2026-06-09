package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Atendimento;
import model.Veiculo;
import model.Servico;
import util.Conexao;

public class AtendimentoDAO {

    public void cadastrar(Atendimento a) throws ClassNotFoundException, SQLException {
        String sql = "INSERT INTO atendimento (data, id_veiculo, id_servico) VALUES (?, ?, ?)";

        try (Connection con = Conexao.getConexao();
             PreparedStatement comando = con.prepareStatement(sql)) {

            comando.setDate(1, new java.sql.Date(a.getData().getTime()));
            comando.setInt(2, a.getVeiculo().getId());
            comando.setInt(3, a.getServico().getId());

            comando.executeUpdate();
        }
    }

    public void excluir(int idAtendimento) throws ClassNotFoundException, SQLException {
        String sql = "DELETE FROM atendimento WHERE id_atendimento = ?";

        try (Connection con = Conexao.getConexao();
             PreparedStatement comando = con.prepareStatement(sql)) {

            comando.setInt(1, idAtendimento);
            comando.executeUpdate();
        }
    }

    public List<Atendimento> listar() throws ClassNotFoundException, SQLException {
        List<Atendimento> lista = new ArrayList<>();

        String sql = """
            SELECT a.id_atendimento, a.data,
                   v.id_veiculo, v.placa,
                   s.id_servico, s.tiposervico, s.valor
            FROM atendimento a
            JOIN veiculo v ON a.id_veiculo = v.id_veiculo
            JOIN servico s ON a.id_servico = s.id_servico
        """;

        try (Connection con = Conexao.getConexao();
             PreparedStatement comando = con.prepareStatement(sql);
             ResultSet rs = comando.executeQuery()) {

            while (rs.next()) {
                Atendimento a = new Atendimento();
                a.setId(rs.getInt("id_atendimento"));
                a.setData(rs.getDate("data"));

                Veiculo v = new Veiculo();
                v.setId(rs.getInt("id_veiculo"));
                v.setPlaca(rs.getString("placa"));
                a.setVeiculo(v);

                Servico s = new Servico();
                s.setId(rs.getInt("id_servico"));
                s.setTipoServico(rs.getString("tiposervico"));
                s.setValor(rs.getDouble("valor"));
                a.setServico(s);

                lista.add(a);
            }
        }

        return lista;
    }

    public Atendimento buscarPorId(int idAtendimento) throws ClassNotFoundException, SQLException {
        String sql = """
            SELECT a.id_atendimento, a.data,
                   v.id_veiculo, v.placa,
                   s.id_servico, s.tiposervico, s.valor
            FROM atendimento a
            JOIN veiculo v ON a.id_veiculo = v.id_veiculo
            JOIN servico s ON a.id_servico = s.id_servico
            WHERE a.id_atendimento = ?
        """;

        Atendimento a = null;

        try (Connection con = Conexao.getConexao();
             PreparedStatement comando = con.prepareStatement(sql)) {

            comando.setInt(1, idAtendimento);

            try (ResultSet rs = comando.executeQuery()) {
                if (rs.next()) {
                    a = new Atendimento();
                    a.setId(rs.getInt("id_atendimento"));
                    a.setData(rs.getDate("data"));

                    Veiculo v = new Veiculo();
                    v.setId(rs.getInt("id_veiculo"));
                    v.setPlaca(rs.getString("placa"));
                    a.setVeiculo(v);

                    Servico s = new Servico();
                    s.setId(rs.getInt("id_servico"));
                    s.setTipoServico(rs.getString("tiposervico"));
                    s.setValor(rs.getDouble("valor"));
                    a.setServico(s);
                }
            }
        }

        return a;
    }

    public List<Atendimento> listarPorVeiculo(int idVeiculo) throws ClassNotFoundException, SQLException {
        List<Atendimento> lista = new ArrayList<>();

        String sql = """
            SELECT a.id_atendimento, a.data,
                   s.id_servico, s.tiposervico, s.valor
            FROM atendimento a
            JOIN servico s ON a.id_servico = s.id_servico
            WHERE a.id_veiculo = ?
        """;

        try (Connection con = Conexao.getConexao();
             PreparedStatement comando = con.prepareStatement(sql)) {

            comando.setInt(1, idVeiculo);

            try (ResultSet rs = comando.executeQuery()) {
                while (rs.next()) {
                    Atendimento a = new Atendimento();
                    a.setId(rs.getInt("id_atendimento"));
                    a.setData(rs.getDate("data"));

                    Veiculo v = new Veiculo();
                    v.setId(idVeiculo);
                    a.setVeiculo(v);

                    Servico s = new Servico();
                    s.setId(rs.getInt("id_servico"));
                    s.setTipoServico(rs.getString("tiposervico"));
                    s.setValor(rs.getDouble("valor"));
                    a.setServico(s);

                    lista.add(a);
                }
            }
        }

        return lista;
    }

    public void atualizar(Atendimento a) throws ClassNotFoundException, SQLException {
        String sql = "UPDATE atendimento SET data = ?, id_veiculo = ?, id_servico = ? WHERE id_atendimento = ?";

        try (Connection con = Conexao.getConexao();
             PreparedStatement comando = con.prepareStatement(sql)) {

            comando.setDate(1, new java.sql.Date(a.getData().getTime()));
            comando.setInt(2, a.getVeiculo().getId());
            comando.setInt(3, a.getServico().getId());
            comando.setInt(4, a.getId());

            comando.executeUpdate();
        }
    }
}