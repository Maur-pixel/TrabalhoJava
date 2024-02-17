/*
NOME: MAURICIO FONSECA DA SILVA
PROFESSORA: VIRGÍNIA MOTA
TERCEIRA TRIMESTRAL
*/

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class TesteRestaurante {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in); //scanner responsável pela leitura de todos os dados digitados pelo usuário
        List<Cliente> clientes = new ArrayList<>(); //clientes que podem entrar no restaurante
        Cliente clienteAtual; //objeto que serve como variável temporária
        Cliente[] clientesAux; //array de clientes que também serve como uma "variável temporária"
        Mesa[] mesas = new Mesa[10]; //mesas que o restaurante pode ter
        //comandas que haverão no restaurante, sendo que cada comanda está associada a uma mesa específica
        ComandaBebida[] comandasB = new ComandaBebida[10]; 
        ComandaComida[] comandasC = new ComandaComida[10];

        //variáveis para facilitar a leitura de dados
        String nome; 
        String email;
        String data;
        int acompanhantes;
        int numeroMesa;
        int numClientes = 0;
        int pedido;
        int numBebidas;
        int numRefeicoes;
        double valor;
        
        //"construindo" os objetos
        for (int i = 0; i < comandasC.length; i++) {
            comandasB[i] = new ComandaBebida();
            comandasC[i] = new ComandaComida();
            mesas[i] = new Mesa();
            mesas[i].setNumeroMesa(i+1);
            mesas[i].setComandaBebida(comandasB[i]);
            mesas[i].setComandaComida(comandasC[i]);
        }
        Restaurante buchinhoCheio = new Restaurante("Rua dos Bobos, 0", mesas);

        //rodar a iteração a partir do número de clientes cadastrados por mesa
        while (numClientes < 20) {
            //lógica para adicionar os clientes no restaurante
            System.out.println("Boas-vindas ao "+buchinhoCheio.getNome()+". Por gentileza, informe-nos o número de acompanhantes no restaurante. Se não houver, digite '0'. ");
            acompanhantes = sc.nextInt();
            System.out.println("Em seguida, digite o(s) nome(s) e e-mail(s) correspondentes.");
            sc.nextLine();
            clientesAux = new Cliente[acompanhantes+1];
            for (int i=0; i <= acompanhantes; i++) {
                clienteAtual = new Cliente();
                clientesAux[i] = new Cliente();
                System.out.print("Nome do cliente "+(i+1)+": ");
                nome = sc.nextLine();
                clienteAtual.setNome(nome);
                System.out.print("E-mail do cliente "+(i+1)+": ");
                email = sc.nextLine();
                clienteAtual.setEmail(email);
                numClientes++;
                clientes.add(clienteAtual);
                clientesAux[i].setNome(nome);
                clientesAux[i].setEmail(email);
            }

            //lógica para exibir as mesas disponíveis e reservar a mesa que o usuário pedir
            System.out.println("Qual mesa deseja reservar?");
            System.out.println("Mesas disponíveis:");
            for (int i = 0; i < mesas.length; i++) {
                if (mesas[i].getReserva() == false)
                    System.out.print(mesas[i].getNumeroMesa()+",  ");        
            }
            System.out.println();
            numeroMesa = sc.nextInt();
            sc.nextLine();
            if (numeroMesa < 0 || numeroMesa > 10) {
                System.out.println("Número da mesa inválido.");
                break;
            }
            else {
                for (int i = 0; i < mesas.length; i++) {
                    if (numeroMesa - 1 == i) {
                        mesas[i].setReserva(mesas[i].reservar());
                        mesas[i].setClientes(clientesAux);
                    }
                }
            }
            System.out.println("Digite a data em que deseja reservar a mesa. Ex: DD/MM/AAAA");
            data = sc.nextLine();
            mesas[numeroMesa-1].setData(data);

            //implementação dos pedidos (com a modificação de ComandaComida e ComandaBebida)
            mesas[numeroMesa-1].getComandaBebida().setClientes(clientesAux);
            mesas[numeroMesa-1].getComandaComida().setClientes(clientesAux);
            System.out.print("Digite a quantidade de refeições que você quer pedir: ");
            numRefeicoes = sc.nextInt();
            mesas[numeroMesa-1].getComandaComida().imprimirMenu();
            for (int i = 0; i < numRefeicoes; i++) {
                pedido = sc.nextInt();
                mesas[numeroMesa-1].getComandaComida().calcularPedidos(pedido);
            }
            System.out.print("Digite a quantidade de bebidas que você quer pedir: ");
            numBebidas = sc.nextInt();
            mesas[numeroMesa-1].getComandaBebida().imprimirMenu();
             for (int i = 0; i < numBebidas; i++) {
                pedido = sc.nextInt();
                mesas[numeroMesa-1].getComandaBebida().calcularPedidos(pedido);
            }
            valor = mesas[numeroMesa-1].getComandaBebida().calcular10porcento() + mesas[numeroMesa-1].getComandaComida().calcular10porcento();
            System.out.printf("Valor da comanda: R$ %.2f\n", valor);
            System.out.printf("Conta dividida: R$ %.2f para cada.\n", (mesas[numeroMesa-1].getComandaComida().dividirConta(clientesAux) + mesas[numeroMesa-1].getComandaBebida().dividirConta(clientesAux)));
        }
        sc.close();
    }
}
