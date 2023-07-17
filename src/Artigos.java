import java.io.Serializable;
import java.util.Objects;

public abstract class Artigos implements Serializable, Cloneable{
    String nome;
    String descricao;
    int  codigo;
    String marca;
    double precoBase;
    boolean usado;
    int numDonos;
    int anoLancamento;
    String estado;
    Transportadoras transportadora;

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
        this.estado = "";
        this.transportadora = null;
    }

    public Artigos(String nome, String descricao, int codigo, String marca,
                    double precoBase, boolean usado, int numDonos, int anoLancamento, String estado, Transportadoras transportadora){
        this.nome = nome;
        this.descricao = descricao;
        this.codigo = codigo;
        this.marca = marca;
        this.precoBase = precoBase;
        this.usado = usado;
        this.numDonos = numDonos;
        this.anoLancamento = anoLancamento;
        this.estado = estado;
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
        this.estado = a.getEstado();
        this.transportadora = a.transportadora;
    }

    ////////////////////


    public abstract String getNome();

    public abstract String getDescricao();

    public abstract int getCodigo();

    public abstract String getMarca();

    public abstract double getPrecoBase();
    
    public abstract double getPrecoFinal();
    
    public abstract boolean getUsado();

    public abstract int getNumDonos();

    public abstract int getAnoLancamento();
    
    public abstract String getEstado();

    public abstract Transportadoras getTransportadora();

    ////////////////////

    public abstract void setNome(String nome);

    public abstract void setDescricao(String descricao);

    public abstract void setCodigo(int codigo);

    public abstract void setMarca(String marca);
    
    public abstract void setPrecoBase(double precoBase);
    
    public abstract void setUsado(boolean usado);
    
    public abstract void setNumDonos(int numDonos);
    
    public abstract void setAnoLancamento(int anoLancamento);

    public abstract void setEstadoLis();
    
    public abstract void setEstadoEnc();
    
    public abstract void setTransportadora(Transportadoras transportadora);

    ////////////////////
    
    @Override
    public Artigos clone() {
        try {
            return (Artigos) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    public abstract double calcularPreco();
    
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
                Objects.equals(estado, other.estado) &&
                Double.compare(precoBase, other.precoBase) == 0 &&
                usado == other.usado &&
                numDonos == other.numDonos &&
                anoLancamento == other.anoLancamento &&
                Objects.equals(transportadora, other.transportadora);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, descricao, codigo, marca, precoBase, usado, numDonos, anoLancamento, estado, transportadora);
    }

}
