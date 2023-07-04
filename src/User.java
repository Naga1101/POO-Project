import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;
import java.text.DecimalFormat;

/**
 * Escreva uma descrição da classe User aqui.
 * 
 * @author (seu nome) 
 * @version (um número da versão ou uma data)
 */
public class User implements Serializable{
    private int id;
    private String email;
    private String nome;
    private String morada;
    private int nif;
    private double valorRecebido;
    private ArrayList<Artigos> listaCompras;
    private ArrayList<Tuple<Artigos, String>> listaVendas;
    private ArrayList<Encomenda> listaEncomendas;
    
    /**
     * Construtor para objetos da classe User
     */
    public User() {
        this.id = 0;
        this.email = "n/a";
        this.nome = "n/a";
        this.morada  = "n/a";
        this.nif = 0;
        this.valorRecebido = 0;
        this.listaCompras = new ArrayList<>();
        this.listaVendas = new ArrayList<>();
        this.listaEncomendas = new ArrayList<>();
    }

    public User(int id, String email, String nome, String morada, int nif,double valorRecebido,
                ArrayList<Artigos> listaCompras, ArrayList<Tuple<Artigos, String>> listaVendas, ArrayList<Encomenda> listaEncomendas){
        this.id = id;
        this.email = email;
        this.nome = nome;
        this.morada = morada;
        this.nif = nif;
        this.valorRecebido = valorRecebido;
        this.listaCompras = new ArrayList<>(listaCompras);
        this.listaVendas = new ArrayList<>(listaVendas);
        this.listaEncomendas = new ArrayList<>(listaEncomendas);
    }

    public User(User u){
        this.id = u.getId();
        this.email = u.getEmail();
        this.nome = u.getNome();
        this.morada = u.getMorada();
        this.nif = u.getNif();
        this.valorRecebido = u.getValorRecebido();
        this.listaCompras = u.getListaCompras();
        this.listaVendas = u.getListaVendas();
        this.listaEncomendas = u.getListaEncomendas();
    }

    public int getId(){
        return id;
    }

    public String getEmail(){
        return email;
    }

    public String getNome(){
        return nome;
    }

    public String getMorada(){
        return morada;
    }

    public int getNif(){
        return nif;
    }
    
    public double getValorRecebido(){
        return valorRecebido;
    }

    public Artigos getArtigo(int codigo){
        for (Artigos artigo : listaCompras) {
            if (artigo.getCodigo() == codigo) {
                return artigo;
            }
        }
        for (Tuple<Artigos, String> elem : listaVendas) {
            Artigos artigo = elem.getFirst();
            if (artigo.getCodigo() == codigo) {
                return artigo;
            }
        }
        return null;
    }

    public ArrayList<Artigos> getListaCompras() {
        return new ArrayList<>(listaCompras);
    }

    public ArrayList<Tuple<Artigos, String>> getListaVendas() {
    return new ArrayList<>(listaVendas);
    }

    public ArrayList<Encomenda> getListaEncomendas() {
        return new ArrayList<>(listaEncomendas);
    }

    public void setId(int id){
        this.id = id;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public void setMorada(String morada){
        this.morada = morada;
    }

    public void setNif(int nif){
        this.nif = nif;
    }
    
    public void setValorRecebido(double valorRecebido){
        this.valorRecebido = valorRecebido;
    }
    
    public void setListaCompras( ArrayList<Artigos> listaCompras) {
        this.listaCompras = listaCompras;
    }

    public User clone(){
        return new User(this);
    }

    public Artigos procurarArtigo(int codigo){
        for (Tuple<Artigos, String> elem : listaVendas) {
            Artigos artigo = elem.getFirst();
            if (artigo.getCodigo() == codigo) {
                return artigo;
            }
        }
        return null;
    }

    public void adicionarArtigoCompra(Artigos artigo) {
          Artigos novoArtigo = new Artigos(artigo);
          listaCompras.add(novoArtigo);
    }

    public void adicionarArtigoVender(Artigos artigo) {
        Artigos novoArtigo = new Artigos(artigo);
        listaVendas.add(new Tuple<>(novoArtigo,"Listado"));
    }

    public void adicionarEncomenda (Encomenda e){
        Encomenda novaEncomenda = new Encomenda(e);
        listaEncomendas.add(novaEncomenda);
    }

    public int imprimirListaVendas() {
        if(listaVendas.isEmpty()){
            return 1;
        }
        else{
            System.out.println("Lista de vendas: ");
            for (Tuple<Artigos, String> elem : listaVendas) {
                Artigos artigo = elem.getFirst();
                String status = elem.getSecond();

                System.out.println(artigo.toString() + " | Status: " + status);
            }
            return 0;
        }
    }

    public int imprimirListaCompras() {
        if(listaCompras.isEmpty()){
            return 1;
        }
        else{
            System.out.println("O seu carrinho: ");
            for (Artigos artigo : listaCompras) {
                System.out.println(artigo.toString());
            }
            return 0;
        }
    }

    public void removerArtigoVender(int codigo) {
        for (Tuple<Artigos, String> elem : listaVendas) {
            Artigos artigo = elem.getFirst();
            if (artigo.getCodigo() == codigo){
                elem.setSecond("em viagem");
                break;
            }
        }
    }
    
    public void encomendaEntregue(int codigo) {
        for (Tuple<Artigos, String> elem : listaVendas) {
            Artigos artigo = elem.getFirst();
            if (artigo.getCodigo() == codigo){
                elem.setSecond("Entregue");
                break;
            }
        }
    }

    public void removerArtigoComprar(int codigo) {
        for (Artigos artigo : listaCompras) {
            if (artigo.getCodigo() == codigo) {
                listaCompras.remove(artigo);
                break;
            }
        }
    }

    public boolean existeArtigoComCodigoLV(int codigo) {
        for (Tuple<Artigos, String> elem : listaVendas) {
            Artigos artigo = elem.getFirst();
            if (artigo.getCodigo() == codigo) {
                return true;
            }
        }
        return false;
    }

    public boolean existeArtigoComCodigoLC(int codigo) {
        for (Artigos artigo : listaCompras) {
            if (artigo.getCodigo() == codigo) {
                return true;
            }
        }
        return false;
    }

    public String toString(){
        DecimalFormat df = new DecimalFormat("#.##");
        return  "Email: " + this.email + "| Nome: " + this.nome + " | Morada: " + this.morada + " | Nif: " + this.nif + " | Valor recebido: " + df.format(this.valorRecebido) + "€ ";
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                nif == user.nif &&
                Objects.equals(email, user.email) &&
                Objects.equals(nome, user.nome) &&
                Objects.equals(morada, user.morada) &&
                Objects.equals(listaCompras, user.listaCompras) &&
                Objects.equals(listaVendas, user.listaVendas) &&
                Objects.equals(listaEncomendas, user.listaEncomendas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, nome, morada, nif, listaCompras, listaVendas, listaEncomendas);
    }
}

