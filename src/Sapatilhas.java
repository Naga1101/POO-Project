import java.io.Serializable;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Objects;

public class Sapatilhas extends Artigos implements Serializable {
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
                      boolean ehPremium, String nome, String descricao, int codigo, String marca, int anoLancamento, Transportadoras transportadora){
        super(nome, descricao, codigo, marca, precoBase, usado, numDonos, anoLancamento, transportadora);
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

    public double getTamanho(){
        return tamanho;
    }

    public boolean getAtacadores(){
        return atacadores;
    }

    public String getCor(){
        return cor;
    }

    @Override
    public double getPrecoFinal(){
        return preçoFinal;
    }

    public boolean getEhPremium(){
        return ehPremium;
    }

    ////////////////////

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

    public Sapatilhas clone(){
        return new Sapatilhas(this);
    }

    @Override
    public String toString(){
        DecimalFormat df = new DecimalFormat("#.##");
        String premium;
        if(ehPremium) premium = "Sim";
        else premium = "Não";
        return  super.toString() + "| Preço: " + df.format(this.preçoFinal) +   "€ | Tamanho: " + this.tamanho +
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

        return super.calcularPreco() + precoFinal;
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

