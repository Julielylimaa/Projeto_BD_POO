package Controller;

import java.sql.*;
public abstract class ConnectionDB {

    Connection con; //conexão
    PreparedStatement pst; // declaração(query) preparada - código em sql
    Statement st; //declaração(query) - código em sql
    ResultSet rs; //resposta do banco

    String database = "restaurantes";//nome do BD
    String user = "root";
    String password = "root140818";
    String url = "jdbc:mysql://localhost:3306/" + database + "?useTimezone=true&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";


    public void connectToDB() {
        try {
            con = DriverManager.getConnection(url, user, password);
            //System.out.println("Conexao deu certo!");
        } catch(SQLException exc) {
            System.out.println("Erro: " + exc.getMessage());
        }
    }
}
