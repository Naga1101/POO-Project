import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;
import java.text.DecimalFormat;
import java.time.LocalDate;

/**
 * Escreva uma descrição da classe User aqui.
 * 
 * @author (seu nome) 
 * @version (um número da versão ou uma data)
 */
public class User implements Serializable{
    private int id;
    private int idArt;
    private String email;
    private String nome;
    private String morada;
    private int nif;
    private double valorRecebido;
    private double saldo;
    private ArrayList<Artigos> listaCompras;
    private ArrayList<Artigos> listaVendas;
    private ArrayList<Encomenda> listaEncomendas;
    private ArrayList<Historico> historicoAtividade;    
    
    public User() {
        this.id = 0;
        this.idArt = 0;
        this.email = "n/a";
        this.nome = "n/a";
        this.morada  = "n/a";
        this.nif = 0;
        this.valorRecebido = 0;
        this.saldo = 0;
        this.listaCompras = new ArrayList<>();
        this.listaVendas = new ArrayList<>();
        this.listaEncomendas = new ArrayList<>();
        this.historicoAtividade = new ArrayList<>();
    }

    public User(int id, int idArt, String email, String nome, String morada, int nif,double valorRecebido,double saldo,
                ArrayList<Artigos> listaCompras, ArrayList<Artigos> listaVendas, ArrayList<Encomenda> listaEncomendas, 
                ArrayList<Historico> historicoAtividade){
        this.id = id;
        this.idArt = idArt;
        this.email = email;
        this.nome = nome;
        this.morada = morada;
        this.nif = nif;
        this.valorRecebido = valorRecebido;
        this.saldo = saldo;
        this.listaCompras = new ArrayList<>(listaCompras);
        this.listaVendas = new ArrayList<>(listaVendas);
        this.listaEncomendas = new ArrayList<>(listaEncomendas);
        this.historicoAtividade = new ArrayList<>(historicoAtividade);
    }

    public User(User u){
        this.id = u.getId();
        this.idArt = u.getIdArt();
        this.email = u.getEmail();
        this.nome = u.getNome();
        this.morada = u.getMorada();
        this.nif = u.getNif();
        this.valorRecebido = u.getValorRecebido();
        this.saldo = u.getSaldo();
        this.listaCompras = u.getListaCompras();
        this.listaVendas = u.getListaVendas();
        this.listaEncomendas = u.getListaEncomendas();
        this.historicoAtividade = u.getHistoricoAtividade();
    }

    /////////////////////
    
    public int getId(){
        return id;
    }
    
    public int getIdArt(){
        return idArt;
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
    
    public double getSaldo(){
        return saldo;
    }

    public Artigos getArtigo(int codigo){
        for (Artigos artigo : listaCompras) {
            if (artigo.getCodigo() == codigo) {
                return artigo;
            }
        }
        for (Artigos artigo : listaVendas) {
            if (artigo.getCodigo() == codigo) {
                return artigo;
            }
        }
        return null;
    }

    public ArrayList<Artigos> getListaCompras() {
        return new ArrayList<>(listaCompras);
    }

    public ArrayList<Artigos> getListaVendas() {
        return new ArrayList<>(listaVendas);
    }

    public ArrayList<Encomenda> getListaEncomendas() {
        return new ArrayList<>(listaEncomendas);
    }
    
    public ArrayList<Historico> getHistoricoAtividade() {
        return new ArrayList<>(historicoAtividade);
    }
    
    //////////////////////////

    public void setId(int id){
        this.id = id;
    }
    
    public void setIdArt(int idArt){
        this.idArt = idArt;
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
    
    public void setSaldo(double saldo){
        this.saldo = saldo;
    }
    
    public void setListaCompras( ArrayList<Artigos> listaCompras) {
        this.listaCompras = listaCompras;
    }
    
    public void setListaVendas( ArrayList<Artigos> listaVendas) {
        this.listaVendas = listaVendas;
    }

    public User clone(){
        return new User(this);
    }

    public Artigos procurarArtigo(int codigo){
        for (Artigos artigo : listaVendas) {
                if (artigo.getCodigo() == codigo) {
                    return artigo;
            }
        }
        return null;
    }

    public void adicionarArtigoCompra(Artigos artigo) {
          Artigos novoArtigo = artigo.clone();
          listaCompras.add(novoArtigo);
    }

    public void adicionarArtigoVender(Artigos artigo) {
        Artigos novoArtigo = artigo.clone();
        listaVendas.add(novoArtigo);
    }

    public void adicionarEncomenda (Encomenda e){
        Encomenda novaEncomenda = new Encomenda(e);
        listaEncomendas.add(novaEncomenda);
    }
    
    public void adicionarSaldo(double valor, LocalDate data){
        Historico registoCarregamento = new Historico(idAtualH(), data, "Carregamento do saldo", 0, getId(), null, null, 0, valor, "Carregado");
        historicoAtividade.add(registoCarregamento);
        saldo += valor;
    }
    
    public void aumentaIdArt(){
        ++idArt;
    }

    public int imprimirListaVendas() {
        if(listaVendas.isEmpty()){
            return 1;
        }
        else{
            System.out.println("Lista de vendas: ");
            for (Artigos artigo : listaVendas) {
                System.out.println(artigo.toString() + " | Status: " + artigo.getEstado());
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
    
    public int imprimirHistoricoAtividade() {
        if(historicoAtividade.isEmpty()){
            return 1;
        }
        else{
            System.out.println("Histórico de atividade: ");
            for (Historico registo : historicoAtividade) {
                System.out.println(registo.toString());
            }
            return 0;
        }
    }
    
    private int setIdHistorico(int id){
      return ++id;
    }
    
    private int idAtualH(){
      int idM = 0;
      
      if(historicoAtividade == null){
          return setIdHistorico(idM);
      }
      
      for(Historico registo : historicoAtividade){
           idM++;
      }
      return setIdHistorico(idM);
    }
    
    public void encomendaEntregue(int codigo, int idComp, LocalDate dataEntrega) {
        for (Artigos artigo : listaVendas) {
            if (artigo.getCodigo() == codigo){
                //System.out.println("Cheguei ao remover do user: " + getNome());
                Historico registoVenda = new Historico(idAtualH(), dataEntrega, "Venda", getId(), idComp, artigo, null, 0, artigo.getPrecoFinal(), "Entregue");
                historicoAtividade.add(registoVenda);
                listaVendas.remove(artigo);
                //for(Historico registo : historicoAtividade){
                  //  System.out.println("Artigos vendidos: " + registo.toString());   
                //}
                break;
            }
        }
    }
    
    public void registarEncomenda(Encomenda Encomenda, LocalDate dataEntrega){
        //System.out.println("Cheguei ao adicionar do user: " + getNome());
        Historico registoCompra = new Historico(idAtualH(), dataEntrega, "Compra", 0, getId(), null, Encomenda, Encomenda.getPreco(), 0, "Recebido");
        historicoAtividade.add(registoCompra);
        listaEncomendas.remove(Encomenda);
        /*
        for(Historico registo : historicoAtividade){
            System.out.println("Artigos encomendados: " + registo.toString());
        }
        */
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
        for (Artigos artigo : listaVendas) {
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
        return  "Id " + this.id + "| Id lista artigos: " + this.idArt + "| Email: " + this.email + "| Nome: " + this.nome + " | Morada: " + this.morada + " | Nif: " + this.nif 
        + " | Valor recebido: " + df.format(this.valorRecebido) + "€ " + " | Carteira: " + df.format(this.saldo) + "€ ";
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                idArt == user.idArt &&
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
        return Objects.hash(id, idArt, email, nome, morada, nif, listaCompras, listaVendas, listaEncomendas);
    }
}

