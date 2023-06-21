package Controller;

import Model.Atendente;

import java.sql.SQLException;
import java.util.ArrayList;

public class AtendenteDB extends ConnectionDB{
    boolean sucesso = false; //Para saber se funcionou
    public boolean insertAtendente(Atendente at) {
        connectToDB();
        String sql = "INSERT INTO Atendente (nome, Restaurante_CNPJ) values(?,?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, at.getNome());
            pst.setInt(2, at.getRestaurante_CNPJ());
            pst.execute();
            sucesso = true;
            System.out.println("Atendente cadastrado com sucesso!");
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

    public ArrayList<Atendente> selectAtendente() {
        ArrayList<Atendente> at = new ArrayList<>();
        connectToDB();
        String sql = "SELECT * FROM Atendente";
        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);
            System.out.println("Lista de Atendentes: ");
            while (rs.next()) {
                Atendente atAux= new Atendente(rs.getString("nome"),rs.getInt("Restaurante_CNPJ"));
                atAux.setIdFuncionario(rs.getInt("idFuncionario"));
                System.out.println("nome = " + atAux.getNome());
                System.out.println("Id do Atendente = " + atAux.getIdFuncionario());
                System.out.println("--------------------------------");
                at.add(atAux);
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
        return at;
    }

}
