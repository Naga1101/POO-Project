import java.io.Serializable;
import java.util.Objects;
import java.text.DecimalFormat;
import java.time.LocalDate;

public class Historico implements Serializable{
    private int id; /// id do registo
    LocalDate dataRegisto;
    private String tipo; /// venda ou compra
    private int idVend;
    private int idComp;
    private Artigos artigo;  /// se tiver vendido
    private Encomenda Encomenda;   /// se tiver comprado
    private double valorPago;  /// se tiver comprado
    private double valorRecebido;  /// se tiver vendido
    private String estado;  /// Entregue ou Recebido
    
    public Historico(){
        this.id = 0;
        this.dataRegisto = null;
        this.tipo = "n/a";
        this.idVend = 0;
        this.idComp = 0;
        this.artigo = null;
        this.Encomenda = null;
        this.valorPago = 0;
        this.valorRecebido = 0;
        this.estado = "n/a";
    }
    
    public Historico(int id, LocalDate dataRegisto, String tipo, int idVend, int idComp, Artigos artigo, 
                     Encomenda Encomenda, double valorPago, double valorRecebido, String estado){
        this.id = id;
        this.dataRegisto = dataRegisto;
        this.tipo = tipo;
        this.idVend = idVend;
        this.idComp = idComp;
        this.artigo = artigo;
        this.Encomenda = Encomenda;
        this.valorPago = valorPago;
        this.valorRecebido = valorRecebido;
        this.estado = estado;
    }
    
    public Historico(Historico h) {
        this.id = h.getId();
        this.dataRegisto = h.getDataRegisto();
        this.tipo = h.getTipo();
        this.idVend = h.getIdVend();
        this.idComp = h.getIdComp();
        this.artigo = h.getArtigo();
        this.Encomenda = h.getEncomenda();
        this.valorPago = h.getValorPago();
        this.valorRecebido = h.getValorRecebido();
        this.estado = h.getEstado();
    }
    
    /////////////////////
    
    public int getId(){
        return id;
    }
    
    public LocalDate getDataRegisto(){
        return dataRegisto;
    }
    
    public String getTipo(){
        return tipo;
    }
    
    public int getIdVend(){
        return idVend;
    }
    
    public int getIdComp(){
        return idComp;
    }
    
    public Artigos getArtigo() {
        return artigo;
    }

    public Encomenda getEncomenda() {
        return Encomenda;
    }
    
    public double getValorPago() {
        return valorPago;
    }

    public double getValorRecebido() {
        return valorRecebido;
    }

    public String getEstado() {
        return estado;
    }
    
    /////////////////////
    
    public String toString(){
        DecimalFormat df = new DecimalFormat("#.##");
        if(getTipo().equals("Carregamento do saldo")){
            return  "Id " + this.id + "| Data do registo: " + this.dataRegisto + "| Tipo de ação: " + this.tipo + "| Id do Utilizador: " + this.idComp 
            + " | Fundos adicionados: " + df.format(this.valorRecebido) + "€ " + " | Estado: " + estado;
        }
        else if(getTipo().equals("Compra")){ 
            return  "Id " + this.id + "| Data do registo: " + this.dataRegisto + "| Tipo de ação: " + this.tipo + "| Id do Comprador: " + this.idComp + " | Encomenda: " + this.Encomenda.toStringEntregue()
            + " | Valor pago incluindo transportes: " + df.format(this.valorPago) + "€ " + " | Estado: " + estado;
        }
        else return  "Id " + this.id + "| Data do registo: " + this.dataRegisto + "| Tipo de ação: " + this.tipo + "| Id do Vendedor: " + this.idVend + "| Id do Comprador: " + this.idComp 
             + " | Artigo: " + this.artigo + " | Valor recebido: " + df.format(this.valorRecebido) + "€ " + " | Estado: " + estado;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        
        if (!(obj instanceof Historico)) {
            return false;
        }
        
        Historico other = (Historico) obj;
        return id == other.id &&
               idVend == other.idVend &&
               idComp == other.idComp &&
               Double.compare(valorPago, other.valorPago) == 0 &&
               Double.compare(valorRecebido, other.valorRecebido) == 0 &&
               tipo.equals(other.tipo) &&
               Objects.equals(artigo, other.artigo) &&
               Objects.equals(Encomenda, other.Encomenda) &&
               estado.equals(other.estado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tipo, idVend, idComp, artigo, Encomenda, valorPago, valorRecebido, estado);
    }
}
