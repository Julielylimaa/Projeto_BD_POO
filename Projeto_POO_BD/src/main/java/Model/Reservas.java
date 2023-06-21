package Model;

import java.sql.Time;
import java.sql.Date;

public class Reservas {

    private int cpfCliente;
    private int idMesa;
    private Date data;
    private Time hora;

    public Reservas(int cpfCliente, int idMesa, Date data, Time hora) {
        this.cpfCliente = cpfCliente;
        this.idMesa = idMesa;
        this.data = data;
        this.hora = hora;
    }

    public int getCpfCliente() {
        return cpfCliente;
    }

    public int getIdMesa() {
        return idMesa;
    }

    public Date getData() {
        return data;
    }

    public Time getHora() {
        return hora;
    }
}
