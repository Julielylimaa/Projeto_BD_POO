package View;

import Controller.AtendenteDB;
import Controller.ClienteDB;
import Controller.CozinheiroDB;
import Model.Atendente;
import Model.Cliente;
import Model.Cozinheiro;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Cadastros {
    AtendenteDB at = new AtendenteDB();
    CozinheiroDB cz = new CozinheiroDB();

    public void cadastrar() {
        boolean on = true;
        Scanner entrada = new Scanner(System.in);
        String nome;
        int op;
        while (on) {
            System.out.println("Digite a opção desejada: \n1 - Cadastrar Atendente \n2 - Cadastrar Cozinheiro  \n3 - Lista de Funcionários  \n4 - Voltar");
            try {
                op = entrada.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Entrada invalida, digite novamente a opção desejada: ");
                entrada.nextLine();
                op = entrada.nextInt();

            }
            entrada.nextLine();
            int cnpjRes = 555;
            switch (op) {
                case 1:
                    System.out.println("Cadastro de novo Atendente! \nDigite o nome do novo funcionário:");
                    nome = entrada.nextLine();
                    Atendente atendente = new Atendente(nome, cnpjRes);
                    at.insertAtendente(atendente);
                    System.out.println("--------------------------------");
                    break;

                case 2:
                    System.out.println("Cadastro de novo Cozinheiro! \nDigite o nome do novo funcionário: ");
                    nome = entrada.nextLine();
                    Cozinheiro cozinheiro = new Cozinheiro(nome, cnpjRes);
                    cz.insertCozinheiro(cozinheiro);
                    System.out.println("--------------------------------");
                    break;
                case 3:
                    cz.selectCozinheiro();
                    at.selectAtendente();
                    System.out.println("--------------------------------");
                    break;
                case 4:
                    System.out.println("Saiu da tela de Cadastro!");
                    System.out.println("--------------------------------");
                    on = false;
                    break;
                default:
                    System.out.println("Opção Inválida!");

            }
        }
    }
}
