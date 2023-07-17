import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Objects;

/**
 * Escreva uma descrição da classe TShirts aqui.
 * 
 * @author (seu nome) 
 * @version (um número da versão ou uma data)
 */
public class TShirts extends Artigos implements Serializable, Cloneable{
    
    private String Tamanho; /// (S, M, L, X representa XL)
    private String Padrao;
    private double PrecoFinal;

    public TShirts(){
        super();
        this.Tamanho = "n/a";
        this.Padrao = "n/a";
        this.PrecoFinal = 0;
    }

    public TShirts(String Tamanho, String Padrao, boolean usado, double precoBase, double PrecoFinal, int numDonos, String nome,
                    String marca, String descricao, int codigo, int anoLancamento, String estado, Transportadoras transportadora){
        super(nome, descricao, codigo, marca, precoBase, usado, numDonos, anoLancamento, estado, transportadora);
        this.Tamanho = Tamanho;
        this.Padrao = Padrao;
        this.PrecoFinal = PrecoFinal;
    }
    
    public TShirts(TShirts t){
        super(t);
        this.Tamanho = t.getTamanho();
        this.Padrao = t.getPadrao();
        this.PrecoFinal = t.calcularPreco();
    }
    
    /////////////////////////
    
    @Override
    public String getNome(){
        return nome;
    }
    
    @Override
    public String getDescricao(){
        return descricao;
    }
    
    @Override
    public int getCodigo() {
        return codigo;
    }
    
    @Override
    public String getMarca() {
        return marca;
    }
    
    @Override
    public double getPrecoBase() {
        return precoBase;
    }
    
    @Override
    public double getPrecoFinal(){
        return PrecoFinal;
    }
    
    @Override
    public boolean getUsado(){
        return usado;
    }
    
    @Override
    public int getNumDonos() {
        return numDonos;
    }
    
    @Override
    public int getAnoLancamento() {
        return anoLancamento;
    }
    
    @Override
    public void setEstadoLis(){
         this.estado = "Listado";
    }
    
    @Override
    public void setEstadoEnc(){
         this.estado = "Em viagem";
    }
    
    @Override
    public String getEstado(){
        return estado;
    }
    
    @Override
    public Transportadoras getTransportadora() {
        return transportadora;
    }
    
    public String getTamanho(){
        return Tamanho;
    }
    
    public String getPadrao(){
        return Padrao;
    }
    
    //////////////////////////////
    
    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    @Override
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    @Override
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
    @Override
    public void setMarca(String marca) {
        this.marca = marca;
    }
    
    @Override
    public void setPrecoBase(double precoBase) {
        this.precoBase = precoBase;
    }
    
    @Override
    public void setUsado(boolean usado) {
        this.usado = usado;
    }
    
    @Override
    public void setNumDonos(int numDonos) {
        this.numDonos = numDonos;
    }
    
    @Override
    public void setAnoLancamento(int anoLancamento) {
        this.anoLancamento = anoLancamento;
    }
    
    @Override
    public void setTransportadora(Transportadoras transportadora) {
        this.transportadora = transportadora;
    }

    public void setTamanho(String Tamanho){
        this.Tamanho = Tamanho;
    }
    
    public void setPadrao(String Padrao){
        this.Padrao = Padrao;
    }
    
    public void setPrecoFinal(double PrecoFinal){
        this.PrecoFinal = PrecoFinal;
    }
    
    @Override
    public TShirts clone() {
            TShirts cloned = (TShirts) super.clone();
            cloned.Tamanho = this.Tamanho;
            cloned.Padrao = this.Padrao;
            cloned.PrecoFinal = this.PrecoFinal;

            return cloned;
    }

    @Override
    public double calcularPreco() {
        double precoFinal;
            if(this.getUsado()){
                if(this.getPadrao().equals("liso")){
                    precoFinal = this.getPrecoBase();
                    return precoFinal;
                }
                else{
                    precoFinal = this.getPrecoBase() * 0.5;
                    return precoFinal;
                }
            }
            else{
                precoFinal = this.getPrecoBase();
            }
        return precoFinal;
    }

    @Override
    public String toString(){
        DecimalFormat df = new DecimalFormat("#.##");
        return super.toString() + "| Preço da T-Shirt: " + df.format(this.PrecoFinal) + "€ | Tamanho: " + this.Tamanho + "| Padrao: " + this.Padrao;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TShirts tshirt = (TShirts) o;
        return super.equals(o) &&
                Objects.equals(Tamanho, tshirt.Tamanho) &&
                Objects.equals(Padrao, tshirt.Padrao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), Tamanho, Padrao);
    }
}
