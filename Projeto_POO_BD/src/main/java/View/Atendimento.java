package View;

import Controller.*;
import Model.*;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;

public class Atendimento {
    public void atender() {
        ReservasDB reserva = new ReservasDB();
        MesaDB mesas = new MesaDB();
        ClienteDB cliente = new ClienteDB();
        CardapioDB cardapio = new CardapioDB();
        PedidoDB pedido = new PedidoDB();
        ContaDB conta = new ContaDB();

        ArrayList<Cardapio> c = cardapio.salvarCardapio();

        boolean on = true;
        Scanner entrada = new Scanner(System.in);
        int idMesa;
        int cpfCliente;
        int idOpcao;
        double valor = 0;
        int lugares;
        String nome;
        int op;
        while (on) {
            System.out.println("Digite a opção desejada: \n1 - Reservar mesa \n2 - Cadastrar novo pedido \n3 - Remover um pedido " +
                    "\n4 - Finalizar conta \n5 - Voltar");
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
                    System.out.println("Registrar nova reserva! \nDigite o CPF do Cliente:");
                    cpfCliente = entrada.nextInt();
                    entrada.nextLine();
                    System.out.println("Digite o nome do Cliente: ");
                    nome = entrada.nextLine();
                    Cliente cl = new Cliente(nome, cpfCliente);
                    cliente.insertCliente(cl);
                    System.out.println("Digite quantos lugares serão necessários: ");
                    lugares = entrada.nextInt();
                    mesas.selectMesasVazias(lugares);
                    System.out.println("\nEscolha uma mesa vazia: ");
                    idMesa = entrada.nextInt();
                    LocalDate dataAtual = LocalDate.now();
                    Date dataConvertida = java.sql.Date.valueOf(dataAtual);
                    LocalTime horaAtual = LocalTime.now();
                    Time horaConvertida = Time.valueOf(horaAtual);
                    Reservas rn = new Reservas(cpfCliente, idMesa, dataConvertida, horaConvertida);
                    reserva.insertReserva(rn);
                    Conta cn = new Conta(pedido.calcularTotalPedido(idMesa), idMesa);
                    conta.insertConta(cn);
                    System.out.println("Todas as reservas atuais:");
                    reserva.selectRerservas();
                    break;
                case 2:
                    boolean continuar = true;
                    System.out.println("Digite o número da mesa: ");
                    idMesa = entrada.nextInt();

                    cardapio.selectCardapio();
                    while (continuar) {
                        System.out.println("Digite o id da opção desejada: ");
                        idOpcao = entrada.nextInt();
                        for (Cardapio aux : c) {
                            if (aux.getIdOpcao() == idOpcao)
                                valor = aux.getValor();
                        }
                        Pedido pn = new Pedido(valor, idMesa, idOpcao);
                        pedido.insertPedido(pn);
                        pedido.selectPedidos(idMesa);
                        System.out.println("Fazer outro pedido? (1- sim, 2- não)");
                        int aux = entrada.nextInt();

                        if (aux == 2) {
                            System.out.println("Cadastro de pedidos finalizado.");
                            continuar = false;
                        }

                        conta.updateTotalConta(idMesa);
                    }
                    break;
                case 3:
                    System.out.println("Digite o número da mesa que deseja remover um pedido: ");
                    idMesa = entrada.nextInt();
                    pedido.selectPedidos(idMesa);
                    System.out.println("Digite o id do pedido que deseja remover: ");
                    idOpcao = entrada.nextInt();
                    pedido.deletePedido(idOpcao, idMesa);
                    System.out.println("Pedidos atualizados: ");
                    pedido.selectPedidos(idMesa);
                    conta.updateTotalConta(idMesa);
                    break;
                case 4:
                    System.out.println("Deseja finalizar a conta de qual mesa? ");
                    idMesa = entrada.nextInt();
                    conta.selectContaFinal(idMesa);
                    pedido.limparPedidos(idMesa);
                    conta.limparConta(idMesa);
                    break;
                case 5:
                    System.out.println("Você Saiu da tela de Atendimento!");
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
