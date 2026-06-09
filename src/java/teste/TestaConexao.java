package teste;


import java.sql.Connection;
import java.sql.SQLException;
import util.Conexao;
/**
 *
 * @author luish
 */
public class TestaConexao {
    public static void main(String[] args) {
        try {
            Connection con = Conexao.getConexao();
            if (con != null) {
                System.out.println("✅ Conexão estabelecida com sucesso!");
                con.close(); // Fecha a conexão após o teste
            }
        } catch (ClassNotFoundException e) {
            System.out.println("❌ Driver não encontrado: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("❌ Erro na conexão com o banco: " + e.getMessage());
        }
    }
}