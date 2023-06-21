package Model;

public class Conta {
    private int idConta;
    private double valorTotal;
    private int idMesa;

    public Conta(double valorTotal, int idMesa) {
        this.valorTotal = valorTotal;
        this.idMesa = idMesa;
    }

    public void setIdConta(int idConta) {
        this.idConta = idConta;
    }

    public int getIdConta() {
        return idConta;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public int getIdMesa() {
        return idMesa;
    }
//vai puxar o arrayList de pedidos do cliente e somar todos os valores



}
