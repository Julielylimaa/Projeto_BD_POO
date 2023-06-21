package Model;

import java.util.ArrayList;
import java.util.List;
public class Mesa {
    private int idMesa;
    private int qtdLugares;
    //quantos lugares disponiveis
    private String vazia;


    public int getIdMesa() {
        return idMesa;
    }

    public void setIdMesa(int idMesa) {
        this.idMesa = idMesa;
    }

    public int getQtdLugares() {
        return qtdLugares;
    }


    public String getVazia() {
        return vazia;
    }

    public void setVazia(String vazia) {
        this.vazia = vazia;
    }

    public Mesa(int qtdLugares, String vazia) {
        this.qtdLugares = qtdLugares;

        this.vazia = vazia;
    }
}
