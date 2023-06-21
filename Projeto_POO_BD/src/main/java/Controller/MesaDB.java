package Controller;


import Model.Mesa;

import java.sql.SQLException;
import java.util.ArrayList;

public class MesaDB extends ConnectionDB{
    boolean sucesso = false; //Para saber se funcionou
    public boolean insertMesa(Mesa m) {
        connectToDB();
        String sql = "INSERT INTO Mesa (qtdLugares, vazia) values(?,?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, m.getQtdLugares());
            pst.setString(2, m.getVazia());
            pst.execute();
            sucesso = true;
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

    public ArrayList<Mesa> selectMesasOcupadas() {
        ArrayList<Mesa> m = new ArrayList<>();
        connectToDB();
        String sql = "SELECT * FROM Mesa WHERE vazia = 'nao'";
        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);
            System.out.println("Lista de Mesas Ocupadas: ");
            while (rs.next()) {
                Mesa mAux= new Mesa(rs.getInt("qtdLugares"),rs.getString("vazia"));
                mAux.setIdMesa(rs.getInt("idMesa"));
                System.out.println("Mesa " + mAux.getIdMesa());
                m.add(mAux);
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
        return m;
    }
    public ArrayList<Mesa> selectMesasVazias(int lugares) {
        ArrayList<Mesa> mVazias = new ArrayList<>();
        connectToDB();
        String sql = "SELECT * FROM Mesa WHERE vazia = 'sim' AND qtdLugares =" +lugares;
        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            System.out.println("Mesas Vazias: ");
            while (rs.next()) {
                Mesa mAux= new Mesa(rs.getInt("qtdLugares"),rs.getString("vazia"));
                mAux.setIdMesa(rs.getInt("idMesa"));
                System.out.println("Id = " + mAux.getIdMesa()+"    Lugares = " + mAux.getQtdLugares() );
                System.out.println("--------------------------------");
                mVazias.add(mAux);
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
        return mVazias;
    }

    public boolean updateVaziaNao(int idMesa) {
        connectToDB();
        String sql = "UPDATE Mesa SET  vazia='nao' WHERE idMesa=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1,idMesa);
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

    public boolean updateVaziaSim(int idMesa) {
        connectToDB();
        String sql = "UPDATE Mesa SET  vazia='sim' WHERE idMesa=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1,idMesa);
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
