import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import java.text.DecimalFormat;


public class Encomenda implements Serializable {
    private ArrayList<Artigos> encomenda;
    private int tamanho;
    private double preco;
    private String estado;
    private LocalDate dataCriacao;
    private LocalDate dataEntrega;

    ////////////////////

    public Encomenda(){
        this.encomenda = null;
        this.tamanho = 0;
        this.preco = 0;
        this.estado = "";
        this.dataCriacao = null;
        this.dataEntrega = null;
    }

    public Encomenda(ArrayList<Artigos> encomenda, int tamanho, double preco, String estado, LocalDate dataCriacao, LocalDate dataEntrega){
        this.encomenda = encomenda;
        this. tamanho = tamanho;
        this.preco = preco;
        this.estado = estado;
        this.dataCriacao = dataCriacao;
        this.dataEntrega = dataEntrega;
    }

    public Encomenda (Encomenda e){
        this.encomenda = e.encomenda;
        this.tamanho = e.tamanho;
        this.preco = e.preco;
        this.estado = e.estado;
        this.dataCriacao = e.dataCriacao;
        this.dataEntrega = e.dataEntrega;
    }

    ////////////////////

    public void setEncomenda(ArrayList<Artigos> encomenda) {
        this.encomenda = encomenda;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public void setDataEntrega(LocalDate dataEntrega){
        this.dataEntrega = dataEntrega;
    }

    ////////////////////

    public ArrayList<Artigos> getArtigos() {
        return encomenda;
    }

    public int getTamanho() {
        return tamanho;
    }

    public double getPreco() {
        return preco;
    }

    public String getEstado() {
        return estado;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public LocalDate getDataEntrega() {
        return dataEntrega;
    }

    ////////////////////

    public Encomenda clone(){
        return new Encomenda(this);
    }


    public String toString() {
        DecimalFormat df = new DecimalFormat("#.##");
        StringBuilder sb = new StringBuilder();
        sb.append("Encomenda:\n");
        for (Artigos artigo : encomenda) {
            sb.append(artigo.toString());
            sb.append("\n");
        }
        return sb + "Tamanho: " + this.tamanho + " Preço: " + df.format(this.preco) + " Estado: " + this.estado +
                " Data de criação: " + this.dataCriacao + " Data prevista de entrega: " + this.dataEntrega;
    }

    ////////////////////


    public ArrayList<Tuple<Transportadoras, Integer>> contarTransportadoras(ArrayList<Artigos> listaArtigos) {
        ArrayList<Tuple<Transportadoras, Integer>> resultado = new ArrayList<>();
        
        for (Artigos artigo : listaArtigos) {
            Transportadoras transportadora = artigo.getTransportadora();

            boolean encontrada = false;
            for (Tuple<Transportadoras, Integer> t : resultado) {
                if (t.getFirst().equals(transportadora)) {
                    t.setSecond(t.getSecond() + 1);
                    encontrada = true;
                    break;
                }
            }

            if (!encontrada) {
                resultado.add(new Tuple<>(transportadora, 1));
            }
        }

        return resultado;
    }


    public double calcularPreçoEncomenda(ArrayList<Artigos> listaCompras){
        ArrayList<Tuple<Transportadoras, Integer>> itensPorTransportadora = contarTransportadoras(listaCompras);
        double precoFinal = 0;
        double precoExpedicao = 0;
        double precoArtigos = 0;

        for (Tuple<Transportadoras, Integer> t : itensPorTransportadora){
            t.getFirst().setTamanhoEncomenda(t.getSecond());
            System.out.println(t.getFirst());
            precoExpedicao += t.getFirst().calcularPreçoExpedicao();
        }

        for (Artigos artigo : listaCompras) {
                System.out.println(artigo.calcularPreco());
                precoArtigos += artigo.calcularPreco();
        }

        precoFinal = precoExpedicao + precoArtigos;
        return precoFinal;
    }

    public LocalDate calcularDataEntrega(){
        LocalDate dataEntrega;
        if(this.tamanho < 3){
            dataEntrega = this.dataCriacao.plusWeeks(1);
        }
        else if(this.tamanho < 6){
            dataEntrega = this.dataCriacao.plusWeeks(2);
        }
        else{
            dataEntrega = this.dataCriacao.plusWeeks(3);
        }
        return dataEntrega;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Encomenda)) {
            return false;
        }
        Encomenda other = (Encomenda) obj;
        return Objects.equals(encomenda, other.encomenda) &&
               tamanho == other.tamanho &&
               Double.compare(preco, other.preco) == 0 &&
               Objects.equals(estado, other.estado) &&
               Objects.equals(dataCriacao, other.dataCriacao) &&
               Objects.equals(dataEntrega, other.dataEntrega);
    }

    @Override
    public int hashCode() {
        return Objects.hash(encomenda, tamanho, preco, estado, dataCriacao, dataEntrega);
    }
}
