package Model;

public class Restaurante {
    private String nome;
    private int CNPJ;
    private String endereco;


    public Restaurante(int CNPJ, String nome, String endereco) {
        this.nome = nome;
        this.CNPJ = CNPJ;
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public int getCNPJ() {
        return CNPJ;
    }


}
