package Controller;

import Model.Cardapio;

import java.sql.SQLException;
import java.util.ArrayList;

public class CardapioDB extends ConnectionDB{
    boolean sucesso = false; //Para saber se funcionou

    //mostrar cardapio
    public ArrayList<Cardapio> selectCardapio() {
        ArrayList<Cardapio> c = new ArrayList<>();
        connectToDB();
        String sql = "SELECT * FROM Cardapio";
        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);
            System.out.println("Cardapio: ");
            while (rs.next()) {
                Cardapio caAux = new Cardapio(rs.getInt("idOpcao"),rs.getString("nome"),rs.getDouble("valor"));
                System.out.println(caAux.getIdOpcao() + "    " + caAux.getNomeOpcao() +"    " +caAux.getValor());

                c.add(caAux);
            }
            System.out.println("--------------------------------");
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
        return c;
    }
    public ArrayList<Cardapio> salvarCardapio() {
        ArrayList<Cardapio> c = new ArrayList<>();
        connectToDB();
        String sql = "SELECT * FROM Cardapio";
        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                Cardapio caAux = new Cardapio(rs.getInt("idOpcao"),rs.getString("nome"),rs.getDouble("valor"));
                c.add(caAux);
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
        return c;
    }
}
