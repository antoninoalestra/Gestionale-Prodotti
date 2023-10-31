public class Prodotto {
    private String nome;
    private double prezzo;
    private int quantita;

    public Prodotto(String nome, double prezzo, int quantita){
        this.nome = nome;
        this.prezzo = prezzo;
        this.quantita = quantita;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public void setPrezzo(double prezzo){
        this.prezzo = prezzo;
    }

    public void setQuantita(int quantita){
        this.quantita = quantita;
    }

    public String getNome(){
        return nome;
    }

    public double getPrezzo(){
        return prezzo;
    }

    public int getQuantita(){
        return quantita;
    }

    @Override
    public String toString(){
        return "Prodotto: " + nome + " - Prezzo: " + prezzo + " euro" + " - Quantità: " + quantita;
    }
}
