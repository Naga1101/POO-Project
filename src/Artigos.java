import java.io.Serializable;
import java.util.Objects;

public class Artigos implements Serializable{
    private String nome;
    private String descricao;
    private int  codigo;
    private String marca;
    private double precoBase;
    private boolean usado;
    private int numDonos;
    private int anoLancamento;
    private Transportadoras transportadora;

    ////////////////////

    public Artigos(){
        this.nome = "";
        this.descricao = "";
        this.codigo = 0;
        this.marca = "";
        this.precoBase = 0;
        this.usado = false;
        this.numDonos = 0;
        this.anoLancamento = 0;
        this.transportadora = null;
    }

    public Artigos(String nome, String descricao, int codigo, String marca,
                    double precoBase, boolean usado, int numDonos, int anoLancamento, Transportadoras transportadora){
        this.nome = nome;
        this.descricao = descricao;
        this.codigo = codigo;
        this.marca = marca;
        this.precoBase = precoBase;
        this.usado = usado;
        this.numDonos = numDonos;
        this.anoLancamento = anoLancamento;
        this.transportadora = transportadora;
    }

    public Artigos(Artigos a){
        this.nome = a.getNome();
        this.descricao = a.getDescricao();
        this.codigo = a.getCodigo();
        this.marca = a.getMarca();
        this.precoBase = a.getPrecoBase();
        this.usado = a.getUsado();
        this.numDonos = a.getNumDonos();
        this.anoLancamento = a.getAnoLancamento();
        this.transportadora = a.transportadora;
    }

    ////////////////////


    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getMarca() {
        return marca;
    }

    public double getPrecoBase() {
        return precoBase;
    }
    
    public double getPrecoFinal(){
        return 0;
    }
    
    public boolean getUsado(){
        return usado;
    }

    public int getNumDonos() {
        return numDonos;
    }

    public int getAnoLancamento() {
        return anoLancamento;
    }

    public Transportadoras getTransportadora() {
        return transportadora;
    }

    ////////////////////

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setPrecoBase(double precoBase) {
        this.precoBase = precoBase;
    }

    public void setUsado(boolean usado) {
        this.usado = usado;
    }

    public void setNumDonos(int numDonos) {
        this.numDonos = numDonos;
    }

    public void setAnoLancamento(int anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    public void setTransportadora(Transportadoras transportadora) {
        this.transportadora = transportadora;
    }

    ////////////////////

    public Artigos clone(){
        return new Artigos(this);
    }

    public double calcularPreco() {
        return 0;
    }
    
    @Override
    public String toString(){
        String condicao;
        String premium;
        if(usado) condicao = "Usado";
        else condicao = "Novo";;
        return  "Codigo: " + this.codigo + "| Nome: " + this.nome +
                "| Descricao: " + this.descricao + "| Marca: " + this.marca +
                "| Ano de lançamento: " + this.anoLancamento +
                "| Condição: " + condicao;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Artigos other = (Artigos) obj;
        return codigo == other.codigo &&
                Objects.equals(nome, other.nome) &&
                Objects.equals(descricao, other.descricao) &&
                Objects.equals(marca, other.marca) &&
                Double.compare(precoBase, other.precoBase) == 0 &&
                usado == other.usado &&
                numDonos == other.numDonos &&
                anoLancamento == other.anoLancamento &&
                Objects.equals(transportadora, other.transportadora);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, descricao, codigo, marca, precoBase, usado, numDonos, anoLancamento, transportadora);
    }

}
