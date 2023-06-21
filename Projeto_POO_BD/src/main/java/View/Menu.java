package View;

import Controller.MesaDB;
import Controller.RestauranteDB;
import Model.Mesa;
import Model.Restaurante;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        RestauranteDB r = new RestauranteDB();
        boolean on = true;
        Scanner entrada = new Scanner(System.in);
        int op;

        System.out.println("\nInformações do Restaurante: ");
        r.selectRestaurante();
        while (on) {
            System.out.println("Menu Inicial: ");



            System.out.println("\nDigite a opção desejada: ");
            System.out.println("1 - Cadastro de funcionário");
            System.out.println("2 - Atendimento");
            System.out.println("3 - Visualizar mesas ocupadas");
            System.out.println("4 - Sair");

            try {
                op = entrada.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Entrada invalida, digite novamente a opção desejada: ");
                entrada.nextLine();
                op = entrada.nextInt();

            }
            entrada.nextLine();
            switch (op) {
                case 1:
                    System.out.println("Novo Cadastro de Funcionário: ");
                    Cadastros cadastro = new Cadastros();
                    cadastro.cadastrar();
                    break;
                case 2:
                    System.out.println("Tela de Atendimento");
                    Atendimento atendimento = new Atendimento();
                    atendimento.atender();

                    break;
                case 3:
                    MesaDB mesa = new MesaDB();
                    mesa.selectMesasOcupadas();
                    break;
                case 4:
                    System.out.println("Você Saiu do sistema!");
                    System.out.println("--------------------------------");
                    on = false;
                    break;
                default:
                    System.out.println("Opção Inválida!");
                    break;
            }

        }

    }

}
