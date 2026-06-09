package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    public static Connection getConexao() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver"); 

        Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/lavarapido?useTimezone=true&serverTimezone=UTC", 
                "leodoleo", 
                "0000"
        );

        return con;
    }
}
