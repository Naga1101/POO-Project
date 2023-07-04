import java.io.Serializable;
import java.util.Objects;

public class Transportadoras implements Serializable {
    private int id;
    private String nome;
    private boolean premium;
    private double margemLucro;
    private double imposto;
    private int tamanhoEncomenda;

    ////////////////////

    public Transportadoras() {
        this.id = 0;
        this.nome = "";
        this.premium = false;
        this.margemLucro = 0;
        this.imposto = 0;
        this.tamanhoEncomenda = 0;
    }

    public Transportadoras(int id, String nome, boolean premium, double margemLucro, double imposto, int tamanhoEncomenda){
        this.id = id;
        this.nome = nome;
        this.premium = premium;
        this.margemLucro = margemLucro;
        this.imposto = imposto;
        this.tamanhoEncomenda = tamanhoEncomenda;
    }

    public Transportadoras(Transportadoras t){
        this.id = t.id;
        this.nome = t.nome;
        this.premium = t.premium;
        this.margemLucro = t.margemLucro;
        this.imposto = t.imposto;
        this.tamanhoEncomenda = t.tamanhoEncomenda;
    }

    ////////////////////
    public void setId(int id) {
        this.id = id;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPremium(boolean premium) {
        this.premium = premium;
    }

    public void setImposto(double imposto) {
        this.imposto = imposto;
    }

    public void setMargemLucro(double margemLucro) {
        this.margemLucro = margemLucro;
    }

    public void setTamanhoEncomenda(int tamanhoEncomenda) {
        this.tamanhoEncomenda = tamanhoEncomenda;
    }

    ////////////////////
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public boolean getPremium() {
        return premium;
    }

    public double getMargemLucro() {
        return margemLucro;
    }

    public double getImposto() {
        return imposto;
    }

    public int getTamanhoEncomenda() {
        return tamanhoEncomenda;
    }

    ////////////////////

    public Transportadoras clone(){
        return new Transportadoras(this);
    }

    public String toString(){
        return "Id: " + id + "| Nome: " + this.nome + " | Premium: " + this.premium +
                " | Margem de lucro: " + this.margemLucro + " | Imposto: " + this.imposto;
    }

    ////////////////////

    public double calcularPreçoExpedicao(){
        double precoFinal = 0;

        if(this.premium){
            precoFinal = (3 * this.margemLucro * (1 + this.imposto));
        }
        else {
            if(this.tamanhoEncomenda <= 1){
                precoFinal = (1 * this.margemLucro * (1 + this.imposto)) * 0.9;
            }
            if(this.tamanhoEncomenda >= 2 && this.tamanhoEncomenda <= 5){
                precoFinal = (2 * this.margemLucro * (1 + this.imposto)) * 0.9;
            }
            if(this.tamanhoEncomenda > 5){
                precoFinal = (3 * this.margemLucro * (1 + this.imposto)) * 0.9;
            }
        }
        return precoFinal;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        
        Transportadoras other = (Transportadoras) obj;
        return id == other.id &&
               premium == other.premium &&
               Double.compare(margemLucro, other.margemLucro) == 0 &&
               Double.compare(imposto, other.imposto) == 0 &&
               tamanhoEncomenda == other.tamanhoEncomenda &&
               Objects.equals(nome, other.nome);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id, nome, premium, margemLucro, imposto, tamanhoEncomenda);
    }
}
