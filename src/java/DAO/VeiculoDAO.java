package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Veiculo;
import model.Cliente;
import util.Conexao;

public class VeiculoDAO {

    public void cadastrar(Veiculo v) throws ClassNotFoundException, SQLException {
        String sql = "INSERT INTO veiculo (placa, modelo, cor, cpf_cliente) VALUES (?, ?, ?, ?)";

        try (Connection con = Conexao.getConexao();
             PreparedStatement comando = con.prepareStatement(sql)) {

            comando.setString(1, v.getPlaca());
            comando.setString(2, v.getModelo());
            comando.setString(3, v.getCor());
            comando.setString(4, v.getCliente().getCpf());

            comando.executeUpdate();
        }
    }

    public void excluir(int idVeiculo) throws ClassNotFoundException, SQLException {
        String sql = "DELETE FROM veiculo WHERE id_veiculo = ?";

        try (Connection con = Conexao.getConexao();
             PreparedStatement comando = con.prepareStatement(sql)) {

            comando.setInt(1, idVeiculo);
            comando.executeUpdate();
        }
    }

    public List<Veiculo> listar() throws ClassNotFoundException, SQLException {
        List<Veiculo> lista = new ArrayList<>();

        String sql = "SELECT * FROM veiculo";

        try (Connection con = Conexao.getConexao();
             PreparedStatement comando = con.prepareStatement(sql);
             ResultSet rs = comando.executeQuery()) {

            while (rs.next()) {
                Veiculo v = new Veiculo();
                v.setId(rs.getInt("id_veiculo"));
                v.setPlaca(rs.getString("placa"));
                v.setModelo(rs.getString("modelo"));
                v.setCor(rs.getString("cor"));

                Cliente c = new Cliente();
                c.setCpf(rs.getString("cpf_cliente"));
                v.setCliente(c);

                lista.add(v);
            }
        }

        return lista;
    }

    public Veiculo buscarPorId(int idVeiculo) throws ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM veiculo WHERE id_veiculo = ?";
        Veiculo v = null;

        try (Connection con = Conexao.getConexao();
             PreparedStatement comando = con.prepareStatement(sql)) {

            comando.setInt(1, idVeiculo);

            try (ResultSet rs = comando.executeQuery()) {
                if (rs.next()) {
                    v = new Veiculo();
                    v.setId(rs.getInt("id_veiculo"));
                    v.setPlaca(rs.getString("placa"));
                    v.setModelo(rs.getString("modelo"));
                    v.setCor(rs.getString("cor"));

                    Cliente c = new Cliente();
                    c.setCpf(rs.getString("cpf_cliente"));
                    v.setCliente(c);
                }
            }
        }

        return v;
    }

    public List<Veiculo> listarPorCliente(String cpfCliente) throws ClassNotFoundException, SQLException {
        List<Veiculo> lista = new ArrayList<>();

        String sql = "SELECT * FROM veiculo WHERE cpf_cliente = ?";

        try (Connection con = Conexao.getConexao();
             PreparedStatement comando = con.prepareStatement(sql)) {

            comando.setString(1, cpfCliente);

            try (ResultSet rs = comando.executeQuery()) {
                while (rs.next()) {
                    Veiculo v = new Veiculo();
                    v.setId(rs.getInt("id_veiculo"));
                    v.setPlaca(rs.getString("placa"));
                    v.setModelo(rs.getString("modelo"));
                    v.setCor(rs.getString("cor"));

                    Cliente c = new Cliente();
                    c.setCpf(cpfCliente);
                    v.setCliente(c);

                    lista.add(v);
                }
            }
        }

        return lista;
    }

 
    public void atualizar(Veiculo v) throws ClassNotFoundException, SQLException {
        String sql = "UPDATE veiculo SET placa = ?, modelo = ?, cor = ?, cpf_cliente = ? WHERE id_veiculo = ?";

        try (Connection con = Conexao.getConexao();
             PreparedStatement comando = con.prepareStatement(sql)) {

            comando.setString(1, v.getPlaca());
            comando.setString(2, v.getModelo());
            comando.setString(3, v.getCor());
            comando.setString(4, v.getCliente().getCpf());
            comando.setInt(5, v.getId());

            comando.executeUpdate();
        }
    }
}