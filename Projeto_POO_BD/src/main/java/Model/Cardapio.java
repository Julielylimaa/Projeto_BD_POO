package Model;

public class Cardapio {
    //todos as opçoes do restaurante ficam registrados aqui

    private int idOpcao;
    private String nomeOpcao;
    private double valor;

    public Cardapio(int idOpcao, String nomeOpcao, double valor) {
        this.idOpcao = idOpcao;
        this.nomeOpcao = nomeOpcao;
        this.valor = valor;
    }

    public int getIdOpcao() {
        return idOpcao;
    }

    public String getNomeOpcao() {
        return nomeOpcao;
    }

    public double getValor() {
        return valor;
    }
}
