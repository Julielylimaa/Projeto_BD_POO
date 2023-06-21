package Controller;

import Model.Pedido;
import Model.Cardapio;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.ArrayList;

public class PedidoDB extends ConnectionDB{
    boolean sucesso = false; //Para saber se funcionou

    //inserir pedido
    public boolean insertPedido(Pedido pedido) {
        connectToDB();
        String sql = "INSERT INTO Pedido (valor, Mesa_idMesa, Cardapio_idOpcao) values(?,?,?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setDouble(1, pedido.getValor());
            pst.setInt(2, pedido.getIdMesa());
            pst.setInt(3,pedido.getIdOpcao());
            pst.execute();
            sucesso = true;
            System.out.println("Pedido adicionado!");
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
    public boolean deletePedido(int idPedido, int idMesa) {
        connectToDB();
        String sql = "DELETE FROM Pedido where idPedido=? AND Mesa_idMesa = " +idMesa;
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, idPedido);
            pst.execute();
            sucesso = true;
            System.out.println("Pedido removido!");
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


    //select de todos os pedidos de determinada mesa
    public ArrayList<Pedido> selectPedidos(int idMesa) {
        ArrayList<Pedido> pedido = new ArrayList<>();
        connectToDB();
        String sql = "SELECT * FROM Pedido WHERE Mesa_idMesa ="+ idMesa;
        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            String nomePedido= null;
            System.out.println("Pedidos da mesa " + idMesa + ":");
            while (rs.next()) {
                Pedido peAux = new Pedido(rs.getDouble("valor"),rs.getInt("Mesa_idMesa"),rs.getInt("Cardapio_idOpcao"));
                peAux.setIdPedido(rs.getInt("idPedido"));
                CardapioDB cardapio = new CardapioDB();
                ArrayList<Cardapio> c = cardapio.salvarCardapio();
                for(Cardapio aux : c){
                    if (aux.getIdOpcao() == peAux.getIdOpcao())
                        nomePedido = aux.getNomeOpcao();
                }
                System.out.println("Id: "+ peAux.getIdPedido() + "   " +nomePedido +"    R$ " + peAux.getValor());

                pedido.add(peAux);
            }
            sucesso = true;
            System.out.println("--------------------------------");
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
        return pedido;
    }
    public double calcularTotalPedido(int idMesa) {
        connectToDB();
        double total = 0;
        String sql = "select calcularTotalPedidoPorMesa(?)";
        try {
            pst = con.prepareStatement(sql);

            pst.setDouble(1, idMesa);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                total = rs.getDouble(1); // Obtém o valor retornado pelo SELECT
            }

            //System.out.println("Conta finalizada!");
        } catch (SQLException exc) {
            System.out.println("Erro: " + exc.getMessage());
        } finally {
            try {
                con.close();
                pst.close();
            } catch (SQLException exc) {
                System.out.println("Erro: " + exc.getMessage());
            }
        }
        return total;
    }

    public boolean limparPedidos(int idMesa) {
        connectToDB();
        String sql = "DELETE FROM Pedido where Mesa_idMesa=?";
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
