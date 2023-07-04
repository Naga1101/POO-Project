import java.io.Serializable;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Objects;

public class Malas extends Artigos implements Serializable {
    private double altura;
    private double largura;
    private double comprimento;
    private String material;
    private double preçoFinal;
    private boolean ehPremium;

    ////////////////////

    public Malas(){
        super();
        this.altura = 0;
        this.largura = 0;
        this.comprimento = 0;
        this.material = "";
        this.preçoFinal = 0;
        this.ehPremium = false;
    }
    public Malas(double altura, double largura, double comprimento, String material, int anoLancamento, double precoBase, boolean ehPremium, boolean usado, double preçoFinal, int numDonos, String nome, String descricao, int codigo, String marca, Transportadoras transportadora){
        super(nome, descricao, codigo, marca, precoBase, usado, numDonos, anoLancamento, transportadora);
        this.altura = altura;
        this.largura = largura;
        this.comprimento = comprimento;
        this.material = material;
        this.ehPremium = ehPremium;
        this.preçoFinal = preçoFinal;
    }

    public Malas(Malas m){
        super(m);
        this.altura = m.getAltura();
        this.comprimento = m.getComprimento();
        this.largura = m.getLargura();
        this.material = m.getMaterial();
        this.ehPremium = m.getEhPremium();
        this.preçoFinal = m.calcularPreco();
    }

    ////////////////////

    public double getAltura(){
        return altura;
    }

    public double getLargura(){
        return largura;
    }

    public double getComprimento(){
        return comprimento;
    }

    public String getMaterial(){
        return material;
    }

    public boolean getEhPremium(){
        return ehPremium;
    }

    @Override
    public double getPrecoFinal() {
        return preçoFinal;
    }

    ////////////////////

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public void setLargura(double largura) {
        this.largura = largura;
    }

    public void setComprimento(double comprimento) {
        this.comprimento = comprimento;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public void setEhPremium(boolean ehPremium) {
        this.ehPremium = ehPremium;
    }

    public void setPrecoFinal(double precoFinal) {
        this.preçoFinal = precoFinal;
    }

    ////////////////////

    public Malas clone(){
        return new Malas(this);
    }

    @Override
    public String toString(){
        DecimalFormat df = new DecimalFormat("#.##");
        String premium;
        if(ehPremium) premium = "Sim";
        else premium = "Não";
        return super.toString() + "| Preço: " + df.format(this.preçoFinal) + "€ | Altura: " + this.altura + "| Largura: " + this.largura +
                "| Comprimento: " + this.comprimento + "| Premium: " + premium + "| Material: " + this.material;
    }

    ////////////////////

    @Override
    public double calcularPreco(){
        double precoFinal = this.getPrecoBase();
        if (this.getUsado()){
            precoFinal = (precoFinal / this.getNumDonos());
        }
        if (this.getEhPremium()) {
            int anosPassados = LocalDate.now().getYear() - this.getAnoLancamento();
            precoFinal = precoFinal + (0.1 * anosPassados * precoFinal);
        }
        else{
            double descontoProporçao = (this.getAltura() * this.getLargura() * this.getComprimento()) / 100000;
            double descontoAnoColeçao = (LocalDate.now().getYear() - this.getAnoLancamento());
            precoFinal = precoFinal - descontoProporçao - descontoAnoColeçao;
        }

        return super.calcularPreco() + precoFinal;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Malas mala = (Malas) o;
        return super.equals(o) &&
                Double.compare(mala.altura, altura) == 0 &&
                Double.compare(mala.largura, largura) == 0 &&
                Double.compare(mala.comprimento, comprimento) == 0 &&
                Objects.equals(material, mala.material);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), altura, largura, comprimento, material);
    }

}