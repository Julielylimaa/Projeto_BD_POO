package Controller;

import Model.Cozinheiro;

import java.sql.SQLException;
import java.util.ArrayList;

public class CozinheiroDB extends ConnectionDB{
    boolean sucesso = false; //Para saber se funcionou
    public boolean insertCozinheiro(Cozinheiro co) {
        connectToDB();
        String sql = "INSERT INTO Cozinheiro (nome, Restaurante_CNPJ) values(?,?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, co.getNome());
            pst.setInt(2, co.getRestaurante_CNPJ());
            pst.execute();
            sucesso = true;
            System.out.println("Cozinheiro cadastrado com sucesso!");
        } catch (SQLException exc) {
            System.out.println("Erro: " + exc.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                pst.close();
            } catch (SQLException exc) {
                System.out.println("Erro: " + exc.getMessage());
            }
        }
        return sucesso;
    }

    public ArrayList<Cozinheiro> selectCozinheiro() {
        ArrayList<Cozinheiro> coz = new ArrayList<>();
        connectToDB();
        String sql = "SELECT * FROM Cozinheiro";
        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);
            System.out.println("Lista de Cozinheiros: ");
            while (rs.next()) {
                Cozinheiro coAux= new Cozinheiro(rs.getString("nome"),rs.getInt("Restaurante_CNPJ"));
                coAux.setIdFuncionario(rs.getInt("idFuncionario"));
                System.out.println("nome = " + coAux.getNome());
                System.out.println("Id do Cozinheiro = " + coAux.getIdFuncionario());
                System.out.println("--------------------------------");
                coz.add(coAux);
            }
            sucesso = true;
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                st.close();
            } catch (SQLException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
        return coz;
    }

}
