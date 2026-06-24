public class Convenio {
    private String nome;
    private int cobertura;

    public Convenio (String nome, int cobertura){
        this.nome = nome;
        this.cobertura = cobertura;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setCobertura(int cobertura) {
        this.cobertura = cobertura;
    }

    public int getCobertura() {
        return cobertura;
    }
}
