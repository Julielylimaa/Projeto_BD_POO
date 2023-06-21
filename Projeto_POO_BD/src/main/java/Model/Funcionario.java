package Model;

public abstract class Funcionario {
    protected String nome;

    public Funcionario(String nome, int restaurante_CNPJ) {
        this.nome = nome;
        this.restaurante_CNPJ = restaurante_CNPJ;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    protected int idFuncionario;
    protected int restaurante_CNPJ;

    public int getRestaurante_CNPJ() {
        return restaurante_CNPJ;
    }

    public String getNome() {
        return nome;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }



}