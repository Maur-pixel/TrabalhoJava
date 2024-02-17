/*
NOME: MAURICIO FONSECA DA SILVA
PROFESSORA: VIRGÍNIA MOTA
TERCEIRA TRIMESTRAL
*/

public class Restaurante {
    //pelo que foi entendido do UML, o Restaurante possui uma associação com a classe Mesa, sendo que o Restaurante possui de 1 até "n" mesas
    //dessa forma, coloquei, dentro da classe Restaurante, um array de mesas
    private String nome;
    private String endereco;
    private Mesa[] mesas;
    
    Restaurante() {

    }

    Restaurante(String endereco, Mesa[] mesas) {
        this.nome = "Restaurante Buchinho Cheio";
        this.endereco = endereco;
        this.mesas = mesas;
    }
    public String getNome() {
        return this.nome;
    }
    public String getEndereco() {
        return this.endereco;
    }
    public Mesa[] getMesas() {
        return this.mesas;
    }
    public void setMesas(Mesa[] mesas) {
        this.mesas = mesas;
    }

}
class Mesa {
/*
    pelo que foi entendido do UML, a classe Mesa possui uma associação com a classe Cliente, sendo que uma, e somente uma, mesa pode ter de 0 até "n" clientes
    além disso, há a relação entre as classes ComandaComida e ComandaBebida com as classes Mesa e Cliente
    pode-se entender que cada mesa possui uma comanda associada a ela, bem como essa mesma comanda está associada ao(s) cliente(s) da mesa
    posteriormente, o valor da comanda será dividido entre os clientes da mesa de forma igualitária, como pode ser visto no método dividirConta() da classe-mãe Comanda
*/
    private int numeroMesa;
    private String data;
    private boolean reserva;
    private Cliente[] clientes;
    private ComandaBebida comandaB;
    private ComandaComida comandaC;

    //inicialmente, todas as mesas não estarão reservadas no restaurante
    //por isso, resolvi fazer um construtor que "setasse" a reserva da mesa para false
    //assim, pode-se evitar erros de NullPointerException nos testes

    Mesa() {
        this.reserva = false;
    }

    public int getNumeroMesa() {
        return this.numeroMesa;
    }
    public void setNumeroMesa(int numeroMesa) {
        this.numeroMesa = numeroMesa;
    }
    public String getData() {
        return this.data;
    }
    public void setData(String data) {
        this.data = data;
    }
    public boolean getReserva() {
        return this.reserva;
    }
    public void setReserva(boolean reserva) {
        this.reserva = reserva;
    }
    public Cliente[] getClientes() {
        return this.clientes;
    }
    public void setClientes(Cliente[] clientes) {
        this.clientes = clientes;
    }
    public void setComandaBebida(ComandaBebida comanda) {
        this.comandaB = comanda;
    }
    public ComandaBebida getComandaBebida() {
        return this.comandaB;
    }
    public void setComandaComida(ComandaComida comanda) {
        this.comandaC = comanda;
    }
    public ComandaComida getComandaComida() {
        return this.comandaC;
    }

    public boolean reservar() {
        //se a mesa não estiver reservada, ela vai poder ser reservada
        //se a mesa estiver reservada, ela não poderá ser reservada no momento
        
        if (this.reserva == false) {
            System.out.println("A mesa está disponível para reserva. Reservada.");
            return true;
        }
        else {
            System.out.println("A mesa está indisponível para reserva.");
        }
        return true;
    }
}

class Cliente {
    private String nome;
    private String email;
    private Mesa mesaAssociada;

    public String getNome() {
        return this.nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Mesa getMesa() {
        return this.mesaAssociada;
    }
}

abstract class Comanda {
    protected String consumo=" ";
    protected double valor=0;
    protected Cliente[] clientes;

    public String getConsumo() {
        return this.consumo;
    }
    public void setConsumo(String consumo) {
        this.consumo = consumo;
    }
    public double getValor() {
        return this.valor;
    }
    public void setValor(double valor) {
        this.valor = valor;
    }
    public void setClientes(Cliente[] clientes) {
        this.clientes = clientes;
    }

    public void listarConsumo() {
        System.out.println("Pedido(s) feito(s): "+this.consumo);
    }
    public double calcular10porcento() {
        return this.valor += this.valor * 0.1;
    }
    public double dividirConta(Cliente[] clientes) {
        if (this.clientes.length > 1) {
            return this.valor / this.clientes.length;
        }
        else {
            return this.valor;
        }
    }
    public void imprimirMenu() {

    }
    public void calcularPedidos(int pedido) {
        switch (pedido) {
            case 1:
                this.consumo = this.consumo + "Almoço tradicional ";
                this.valor += 12.90;
                break;
            case 2:
                this.consumo = this.consumo + "Macarrão à bolonhesa ";
                this.valor += 19.90;
                break;
            case 3:
                this.consumo = this.consumo + "Tropeirão da casa ";
                this.valor += 22.90;
                break;
            case 4:
                this.consumo = this.consumo + "Almoço vegetariano ";
                this.valor += 19.90;
                break;
        }
    }
}
//mudei apenas o método imprimirMenu() e o calcularPedidos() na classe Comanda
class ComandaBebida extends Comanda {
    @Override
    public void imprimirMenu() {
        System.out.println("Opções de bebida:");
        System.out.println("1 - Coca-cola (500 mL)................................................................R$ 5,99");
        System.out.println("2 - Suco de uva natural (400 mL)......................................................R$ 6,99");
        System.out.println("3 - Guaraná Antarctica (500 mL).......................................................R$ 5,59");
        System.out.println("4 - Suco de laranja natural (400 mL)..................................................R$ 7,49");
    }
    public void calcularPedidos(int pedido) {
        switch (pedido) {
            case 1:
                this.consumo = this.consumo + "Coca-cola ";
                this.valor += 5.99;
                break;
            case 2:
                this.consumo = this.consumo + "Suco de uva natural ";
                this.valor += 6.99;
                break;
            case 3:
                this.consumo = this.consumo + "Guaraná Antarctica ";
                this.valor += 5.59;
                break;
            case 4:
                this.consumo = this.consumo + "Suco de laranja natural ";
                this.valor += 7.49;
                break;
        }
    }
}
class ComandaComida extends Comanda {
    public void imprimirMenu() {
        System.out.println("Opções de refeição:");
        System.out.println("1 - Almoço tradicional (Arroz, feijão, bife de boi grelhado e salada).........................R$ 12,90");
        System.out.println("2 - Macarrão à bolonhesa......................................................................R$ 19,90");
        System.out.println("3 - Tropeirão da casa (Arroz, feijão tropeiro, bife de boi grelhado, batata chips)............R$ 22,90");
        System.out.println("4 - Almoço vegetariano (Arroz, bobó vegetariano, salada de beterraba).........................R$ 19,90");
    }
}