package Controller;

import Model.Cliente;

import java.sql.SQLException;
import java.util.ArrayList;

public class ClienteDB extends ConnectionDB{
    boolean sucesso = false; //Para saber se funcionou

    //cadastrar cliente
    public boolean insertCliente(Cliente c) {
        connectToDB();
        String sql = "INSERT INTO Cliente (CPF, nome) values(?,?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, c.getCpf());
            pst.setString(2, c.getNome());
            pst.execute();
            sucesso = true;
            System.out.println("Cliente cadastrado com sucesso!");
            System.out.println("-------------------------------------");
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

    //mostrar clientes
    public ArrayList<Cliente> selectCliente() {
        ArrayList<Cliente> c = new ArrayList<>();
        connectToDB();
        String sql = "SELECT * FROM Cliente";
        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);
            System.out.println("Lista de Cliente: ");
            while (rs.next()) {
                Cliente cAux= new Cliente(rs.getString("nome"),rs.getInt("CPF"));
                System.out.println("nome = " + cAux.getNome());
                System.out.println("CPF = " + cAux.getCpf());
                System.out.println("--------------------------------");
                c.add(cAux);
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
