package Model;

public class Pedido {
    private int idPedido;
    private double valor;
    private int idMesa;
    private int idOpcao;

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public Pedido(double valor, int idMesa, int idOpcao) {

        this.valor = valor;
        this.idMesa = idMesa;
        this.idOpcao = idOpcao;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public double getValor() {
        return valor;
    }

    public int getIdMesa() {
        return idMesa;
    }

    public int getIdOpcao() {
        return idOpcao;
    }
}
