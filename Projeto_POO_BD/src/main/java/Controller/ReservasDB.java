package Controller;

import Model.Reservas;

import java.sql.SQLException;
import java.util.ArrayList;

public class ReservasDB extends ConnectionDB{
    boolean sucesso = false; //Para saber se funcionou
    public boolean insertReserva(Reservas reserva) {
        connectToDB();
        String sql = "INSERT INTO Reserva (Cliente_CPF, Mesa_idMesa, data, hora) values(?,?,?,?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, reserva.getCpfCliente());
            pst.setInt(2, reserva.getIdMesa());
            pst.setDate(3, reserva.getData());
            pst.setTime(4,reserva.getHora());

            pst.execute();
            sucesso = true;
            MesaDB mesa = new MesaDB();
            mesa.updateVaziaNao(reserva.getIdMesa());
            System.out.println("Mesa reservada com sucesso!");
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


    public ArrayList<Reservas> selectRerservas() {
        ArrayList<Reservas> reservas = new ArrayList<>();
        connectToDB();
        String sql = "SELECT * FROM Reserva";
        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);
            System.out.println("Reservas: ");
            while (rs.next()) {
                Reservas resAux = new Reservas(rs.getInt("Cliente_CPF"),rs.getInt("Mesa_idMesa"),rs.getDate("data"), rs.getTime("hora"));
                System.out.println("CPF do Cliente = " + resAux.getCpfCliente());
                System.out.println("Mesa reservada = " + resAux.getIdMesa());
                System.out.println("Data da reserva = "+ resAux.getData());
                System.out.println("Hora da reserva = " + resAux.getHora());
                System.out.println("--------------------------------");
                reservas.add(resAux);
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
        return reservas;
    }
}
