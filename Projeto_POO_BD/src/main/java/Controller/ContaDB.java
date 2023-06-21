package Controller;

import Model.Conta;

import java.sql.SQLException;
import java.util.ArrayList;

//CRUD COMPLETO
public class ContaDB extends ConnectionDB{
    PedidoDB p = new PedidoDB();
    MesaDB mesa = new MesaDB();
    boolean sucesso = false; //Para saber se funcionou
    public boolean insertConta(Conta conta) {
        connectToDB();
        String sql = "INSERT INTO Conta (valorTotal, Mesa_idMesa) values(?,?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setDouble(1, conta.getValorTotal());
            pst.setInt(2, conta.getIdMesa());
            pst.execute();
            sucesso = true;
            System.out.println("Conta criada.");
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
    public boolean updateTotalConta(int idMesa) {
        connectToDB();
        String sql = "UPDATE Conta SET  valorTotal = calcularTotalPedidoPorMesa(?) WHERE Mesa_idMesa=" +idMesa;
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1,idMesa);
            pst.execute();
            sucesso = true;
            //System.out.println("Conta atualizada!");
        } catch (SQLException ex) {
            System.out.println("Erro = " + ex.getMessage());
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


    public ArrayList<Conta> selectContaFinal(int idMesa) {
        ArrayList<Conta> conta = new ArrayList<>();
        connectToDB();
        String sql = "SELECT * FROM Conta WHERE Mesa_idMesa =" +idMesa;
        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);
            System.out.println("Conta finalizada: ");
            while (rs.next()) {
                Conta cAux= new Conta(rs.getDouble("valorTotal"),rs.getInt("Mesa_idMesa"));
                cAux.setIdConta(rs.getInt("idConta"));
                System.out.println("Conta " + cAux.getIdConta() + ": ");
                p.selectPedidos(idMesa);
                System.out.println("Valor Final: " +cAux.getValorTotal());

                conta.add(cAux);
            }
            System.out.println("\n--------------------------------");
            sucesso = true;
            mesa.updateVaziaSim(idMesa);
            System.out.println("Pagamento realizado! A mesa foi esvaziada.");
            System.out.println("\n--------------------------------");
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
        return conta;
    }
    public boolean limparConta(int idMesa) {
        connectToDB();
        String sql = "DELETE FROM Conta where Mesa_idMesa=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, idMesa);
            pst.execute();
            sucesso = true;
        } catch (SQLException ex) {
            System.out.println("Erro = " + ex.getMessage());
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


}
