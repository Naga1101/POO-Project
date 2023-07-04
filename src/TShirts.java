import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Objects;

/**
 * Escreva uma descrição da classe TShirts aqui.
 * 
 * @author (seu nome) 
 * @version (um número da versão ou uma data)
 */
public class TShirts extends Artigos implements Serializable
{
    
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
                    String marca, String descricao, int codigo, int anoLancamento, Transportadoras transportadora){
        super(nome, descricao, codigo, marca, precoBase, usado, numDonos, anoLancamento, transportadora);
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
    
    public String getTamanho(){
        return Tamanho;
    }
    
    public String getPadrao(){
        return Padrao;
    }

    @Override
    public double getPrecoFinal(){
        return PrecoFinal;
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
    
    public TShirts clone(){
        return new TShirts(this);
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
        return super.calcularPreco() + precoFinal;
    }

    @Override
    public String toString(){
        DecimalFormat df = new DecimalFormat("#.##");
        return super.toString() + "| Preço: " + df.format(this.PrecoFinal) + "€ | Tamanho: " + this.Tamanho + "| Padrao: " + this.Padrao;
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
