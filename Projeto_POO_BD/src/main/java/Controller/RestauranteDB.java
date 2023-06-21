package Controller;
import Model.Restaurante;

import java.sql.SQLException;
import java.util.ArrayList;

public class RestauranteDB extends ConnectionDB{
    boolean sucesso = false; //Para saber se funcionou

    public ArrayList<Restaurante> selectRestaurante() {
        ArrayList<Restaurante> res = new ArrayList<>();
        connectToDB();
        String sql = "SELECT * FROM Restaurante";
        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                Restaurante resAux = new Restaurante(rs.getInt("CNPJ"),rs.getString("nome"),rs.getString("endereco"));
                System.out.println("nome = " + resAux.getNome());
                System.out.println("CNPJ = " + resAux.getCNPJ());
                System.out.println("Endereço = "+ resAux.getEndereco());
                System.out.println("--------------------------------");
                res.add(resAux);
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
        return res;
    }

}
