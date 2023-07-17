import java.io.Serializable;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Objects;

public class Sapatilhas extends Artigos implements Serializable, Cloneable{
    private double tamanho;
    private boolean atacadores;
    private String cor;
    private double preçoFinal;
    private boolean ehPremium;

    ////////////////////

    public Sapatilhas(){
        super();
        this.tamanho = 0;
        this.atacadores = false;
        this.cor = "";
        this.preçoFinal = 0;
        this.ehPremium = false;
    }
    
    public Sapatilhas(int tamanho, boolean atacadores, String cor, int anoLançamento, boolean usado, int numDonos, double precoBase, double preçoFinal,
                      boolean ehPremium, String nome, String descricao, int codigo, String marca, int anoLancamento, String estado, Transportadoras transportadora){
        super(nome, descricao, codigo, marca, precoBase, usado, numDonos, anoLancamento, estado, transportadora);
        this.tamanho = tamanho;
        this.atacadores = atacadores;
        this.cor = cor;
        this.preçoFinal = preçoFinal;
        this.ehPremium = ehPremium;
    }

    public Sapatilhas(Sapatilhas s){
        super(s);
        this.tamanho = s.getTamanho();
        this.atacadores = s.getAtacadores();
        this.cor = s.getCor();
        this.preçoFinal = s.calcularPreco();
        this.ehPremium = s.getEhPremium();
    }

    ////////////////////
    
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
        return preçoFinal;
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
    public String getEstado(){
        return estado;
    }
    
    @Override
    public Transportadoras getTransportadora() {
        return transportadora;
    }
    
    public double getTamanho(){
        return tamanho;
    }

    public boolean getAtacadores(){
        return atacadores;
    }

    public String getCor(){
        return cor;
    }

    public boolean getEhPremium(){
        return ehPremium;
    }

    ////////////////////
    
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
    public void setEstadoLis(){
         this.estado = "Listado";
    }
    
    @Override
    public void setEstadoEnc(){
         this.estado = "Em viagem";
    }
    
    @Override
    public void setTransportadora(Transportadoras transportadora) {
        this.transportadora = transportadora;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public void setTamanho(double tamanho) {
        this.tamanho = tamanho;
    }

    public void setAtacadores(boolean atacadores) {
        this.atacadores = atacadores;
    }

    public void setPrecoFinal(double precoFinal) {
        this.preçoFinal = precoFinal;
    }

    public void setEhPremium(boolean ehPremium) {
        this.ehPremium = ehPremium;
    }

    ////////////////////

    @Override
    public Sapatilhas clone() {
            Sapatilhas cloned = (Sapatilhas) super.clone();
            cloned.tamanho = this.tamanho;
            cloned.atacadores = this.atacadores;
            cloned.cor = this.cor;
            cloned.preçoFinal = this.preçoFinal;
            cloned.ehPremium = this.ehPremium;

            return cloned;
    }

    @Override
    public String toString(){
        DecimalFormat df = new DecimalFormat("#.##");
        String premium;
        if(ehPremium) premium = "Sim";
        else premium = "Não";
        return  super.toString() + "| Preço das Sapatilhas: " + df.format(this.preçoFinal) +   "€ | Tamanho: " + this.tamanho +
                "| Atacadores: " + this.atacadores + "| Cor: " + this.cor + "| Premium: " + premium;
    }

    ////////////////////
    @Override
    public double calcularPreco() {
        double precoFinal = this.getPrecoBase();
        if (this.getUsado()) {
            precoFinal = (precoFinal / this.getNumDonos());
        }
        if (this.getTamanho() > 45) {
            precoFinal = precoFinal * 0.9;
        }
        if (this.getEhPremium()) {
            int anosPassados = LocalDate.now().getYear() - this.getAnoLancamento();
            precoFinal = precoFinal + (0.1 * anosPassados * precoFinal);
        }

        return precoFinal;
    }
    
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sapatilhas sapatilha = (Sapatilhas) o;
        return super.equals(o) &&
                Double.compare(sapatilha.tamanho, tamanho) == 0 &&
                atacadores == sapatilha.atacadores &&
                Objects.equals(cor, sapatilha.cor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), tamanho, atacadores, cor);
    }
}

