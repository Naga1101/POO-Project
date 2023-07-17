import java.util.ArrayList;
import java.io.*;
import java.util.List;
import java.util.Scanner;
import java.util.Objects;
import java.time.LocalDate;

/**
 * Escreva uma descrição da classe VintageMain aqui.
 * 
 * @author (seu nome) 
 * @version (um número da versão ou uma data)
 */
public class VintageMain implements Serializable
{
    private static final String nomeFicheiro = "./estado/loja.ser";
    private ArrayList<User> listaUsersRegistados;
    private ArrayList<Transportadoras> listaTransportadoraRegistadas;
    private ArrayList<Artigos> artigosPublicados;
    private LocalDate data;

    public VintageMain(){
        this.listaUsersRegistados = new ArrayList<>();
        this.listaTransportadoraRegistadas = new ArrayList<>();
        this.artigosPublicados = new ArrayList<>();
        this.data = LocalDate.now();
    }

    public ArrayList<User> getlistaUsersRegistados() {
        return new ArrayList<>(listaUsersRegistados);
    }

    public ArrayList<Transportadoras> getlistaTransportadoraRegistadas() {
        return new ArrayList<>(listaTransportadoraRegistadas);
    }

    public ArrayList<Artigos> getartigosPublicados() {
        return new ArrayList<>(artigosPublicados);
    }
    
    public LocalDate getData() {
        return data;
    }
    
    public void setData(int numSemanas) {
        this.data = calcularData(numSemanas);
    }

    public void addUsers(User utilizador){
        User novoUser = new User(utilizador);
        listaUsersRegistados.add(novoUser);
    }

    public void addTransportadora(Transportadoras transportadora){
        Transportadoras novatransportadora = new Transportadoras(transportadora);
        listaTransportadoraRegistadas.add(novatransportadora);
    }

    public void addArtigos(Artigos artigo){
        artigosPublicados.add(artigo);
    }

    public Artigos procurarArtigo(int codigo){
        for (Artigos artigo : artigosPublicados) {
            if (artigo.getCodigo() == codigo) {
                return artigo;
            }
        }
        return null;
    }

    public Transportadoras procurarTransportadora(int id){
        for (Transportadoras transportadora : listaTransportadoraRegistadas) {
            if (transportadora.getId() == id) {
                return transportadora;
            }
        }
        return null;
    }
    public void loadArtigosPublicados(){
        for(User user : listaUsersRegistados){
            List<Artigos> listaVendas = user.getListaVendas();
            for (Artigos artigo : listaVendas) {
                artigosPublicados.add(artigo);
            }
        }
    }

    public int imprimirListaUsers() {
        if(listaUsersRegistados.isEmpty()){
            return 1;
        }
        else{
            System.out.println("Lista de users: ");
            for (User user : listaUsersRegistados) {
                System.out.println(user.toString());
            }
            return 0;
        }
    }

    public int imprimirListaTransportadoras() {
        if(listaTransportadoraRegistadas.isEmpty()){
            return 1;
        }
        else{
            System.out.println("Lista de transportadoras: ");
            for (Transportadoras transportadora : listaTransportadoraRegistadas) {
                System.out.println(transportadora.toString());
            }
            return 0;
        }
    }

    public int imprimirListaArtigosPublicados() {
        if(artigosPublicados.isEmpty()){
            return 1;
        }
        else{
            System.out.println("Lista de artigos: ");
            for (Artigos artigo : artigosPublicados) {
                System.out.println(artigo.toString());
            }
            return 0;
        }
    }
    
    public void encomendaChegou(ArrayList<Artigos> listaArtigos, int idComp, LocalDate dataEntrega) {    
        for (Artigos artigo : listaArtigos) {
            for (User user : listaUsersRegistados) {
                ArrayList<Artigos> listaVendas = user.getListaVendas();
                for (Artigos artigoRem : listaVendas) {
                    if (artigoRem.getCodigo() == artigo.getCodigo()) {
                        user.encomendaEntregue(artigoRem.getCodigo(), idComp, dataEntrega);
                    }
                }
            }
        }
    }
    
    public void verificarEncomenda() { 
        LocalDate dataAtual = getData();
        for (User user : listaUsersRegistados) {
                ArrayList<Encomenda> encomendas = user.getListaEncomendas();
                for (Encomenda encomenda : encomendas) {
                    LocalDate entrega = encomenda.getDataEntrega();
                    if (entrega.isBefore(dataAtual)) {
                        user.registarEncomenda(encomenda, entrega);
                        encomendaChegou(encomenda.getArtigos(), user.getId(), entrega);
                    }
                }
        }
    }
    
    public void pagarVendedor(ArrayList<Artigos> listaArtigos){
        for (Artigos artigo : listaArtigos) {
            for (User user : listaUsersRegistados) {
                ArrayList<Artigos> listaVendas = user.getListaVendas();
                for (Artigos artigoRem : listaVendas) {
                    if (artigoRem.equals(artigo)) {
                        Artigos elemPreco = procurarArtigo(artigo.getCodigo());
                        double ganhou = elemPreco.calcularPreco();
                        double possui = user.getValorRecebido();
                        ganhou = ganhou + possui;
                        user.setValorRecebido(ganhou);
                    }
                }
            }
        }
    }
    
    public LocalDate calcularData(int numSemanas){
        LocalDate novaData = this.data.plusWeeks(numSemanas);
        return novaData;
    }


    public void removerArtEncomenda(ArrayList<Artigos> listaEncomendada) {
        for(Artigos artigo : listaEncomendada){
            removerAnuncio(artigo, 1);
        }
    }
    
    public void removerAnuncio(Artigos artigo, int tipo){
        if(tipo == 0){  /// foi removido o anuncio
            if(artigosPublicados.contains(artigo)){
                artigosPublicados.remove(artigo);
            }
        
            for(User user : listaUsersRegistados){
                ArrayList<Artigos> listaVendas = user.getListaVendas();
                ArrayList<Artigos> novaListaVendas = new ArrayList<>(listaVendas);
                for (Artigos artigoRem : listaVendas){
                    if(artigoRem.equals(artigo)){
                        novaListaVendas.remove(artigo);
                    }
                }
                user.setListaVendas(novaListaVendas);
            }
        }
        else if(tipo == 1){  /// Foi comprado
            if(artigosPublicados.contains(artigo)){
                artigosPublicados.remove(artigo);
            }
            
            int codigo;
            for(User user : listaUsersRegistados){
                if(user.getListaVendas().contains(artigo)){
                    codigo = artigo.getCodigo();
                    user.getArtigo(codigo).setEstadoEnc();
                }
            }
            
            for(User user : listaUsersRegistados){
                if(user.getListaCompras().contains(artigo)){
                    ArrayList<Artigos> listaCompras = user.getListaVendas();
                    ArrayList<Artigos> novaListaCompras = new ArrayList<>(listaCompras);
                    for (Artigos artigoRem : listaCompras){
                        if(artigoRem.equals(artigo)){
                            novaListaCompras.remove(artigo);
                        }
                    }
                    user.setListaCompras(novaListaCompras);
                }
            }
        }
    }

    public void guardarEstado(){
        File folder = new File("estado");
        folder.mkdirs();
        try{
            FileOutputStream ficheiroOut = new FileOutputStream(nomeFicheiro);
            ObjectOutputStream out = new ObjectOutputStream(ficheiroOut);
            out.writeObject(this);
            out.close();
            ficheiroOut.close();
            System.out.println("Estado salvo corretamente");
        }
        catch(IOException e){
            e.printStackTrace();
            System.out.println("Erro a guardar o estado da loja");
        }
    }

    public void carregarEstado() throws ClassNotFoundException {
        try{
            FileInputStream ficheiroIn = new FileInputStream(nomeFicheiro);
            ObjectInputStream in = new ObjectInputStream(ficheiroIn);
            VintageMain carregado = (VintageMain) in.readObject();
            this.listaUsersRegistados = carregado.listaUsersRegistados;
            this.listaTransportadoraRegistadas = carregado.listaTransportadoraRegistadas;
            this.artigosPublicados = carregado.artigosPublicados;
            System.out.println("A loja foi carregada corretamente");
        }
        catch (IOException e){
            e.printStackTrace();
            System.out.println("Erro ao carregar o estado da loja");
        }
    }

    public static void main(String[] args) throws ClassNotFoundException {
        VintageMain main = new VintageMain();
        boolean respostaValida = false;
        Scanner sc= new Scanner(System.in);

        System.out.println("Pretende carregar o estado da loja guardado?");
        while (!respostaValida) {
            System.out.println("Responda: S | N");
            char aux = sc.next().charAt(0);
            if (aux == 'S') {
                respostaValida = true;
                main.carregarEstado();
            } else if (aux == 'N') {
                respostaValida = true;
                ArrayList<Artigos> vazia = new ArrayList<>();
                ArrayList<Artigos> vaziaV = new ArrayList<>();
                ArrayList<Encomenda> vaziaE = new ArrayList<>();
                ArrayList<Historico> vaziaH = new ArrayList<>();
                User user1 = new User(1, 0,"joao","João","Rua Banana",100000001, 0, 0,vazia,vaziaV, vaziaE, vaziaH);
                User user2 = new User(2, 0,"maria","Maria","Rua Melancia",100024001, 0, 10,vazia, vaziaV, vaziaE, vaziaH);
                User user3 = new User(3, 0,"zubyo","Zubyo","Rua Melão",113024001, 0, 20.5,vazia, vaziaV, vaziaE, vaziaH);
                User user4 = new User(4, 0,"zulnae","Zulnae","Rua Abóbora",100928301, 0, 0,vazia, vaziaV, vaziaE, vaziaH);
                User user5 = new User(5, 0,"gaia","Gaia","Rua Maça",390928301, 0, 13.2,vazia, vaziaV, vaziaE, vaziaH);
                Transportadoras transportadora1 = new Transportadoras(1, "CTT", false, 7, 0.5, 0);
                Transportadoras transportadora2 = new Transportadoras(2, "vip", true, 10, 2, 0);

                main.addUsers(user1);
                main.addUsers(user2);
                main.addUsers(user3);
                main.addUsers(user4);
                main.addUsers(user5);

                main.addTransportadora(transportadora1);
                main.addTransportadora(transportadora2);

            } else {
                System.out.println("Caracter inválido.");
            }
        }
        new Interface(main).run();
        
        main.encontrarMaiorVendedor();
        
        System.out.println("Pretende guardar o estado da loja atual?");
        respostaValida = false;
        while (!respostaValida) {
            System.out.println("Responda: S | N");
            char aux = sc.next().charAt(0);
            if (aux == 'S') {
                respostaValida = true;
                main.guardarEstado();
            } else if (aux == 'N') {
                respostaValida = true;
            } else {
                System.out.println("Caracter inválido.");
            }
        }

    }
    
    public void encontrarMaiorVendedor() {
        User Maiorvendedor = null;
        double valorTotal = 0.0;

        for (User user : getlistaUsersRegistados()) {
            double valorRecebido = user.getValorRecebido();
            if (valorRecebido > valorTotal) {
                valorTotal = valorRecebido;
                Maiorvendedor = user;
            }
        }

        if (Maiorvendedor != null) {
            System.out.println("O vendedor que recebeu mais dinheiro no total foi: " + Maiorvendedor.getNome() + "Valor recebido: " + valorTotal);
        } else {
            System.out.println("Não há vendedores registados.");
        }
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VintageMain main = (VintageMain) o;
        return Objects.equals(listaUsersRegistados, main.listaUsersRegistados) &&
                Objects.equals(listaTransportadoraRegistadas, main.listaTransportadoraRegistadas) &&
                Objects.equals(artigosPublicados, main.artigosPublicados);
    }

    @Override
    public int hashCode() {
        return Objects.hash(listaUsersRegistados, listaTransportadoraRegistadas, artigosPublicados);
    }
}
