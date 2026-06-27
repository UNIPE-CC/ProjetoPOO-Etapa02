public class PagamentoDinheiro extends Pagamento{
    
    public PagamentoDinheiro(int indiceConsulta, double valorFinal) {
        super(indiceConsulta, valorFinal, "dinheiro");
    }
    
    @Override
    public double calcularValorFinal(){
        double valor = getValorFinal() * 0.95;
        setValorFinal(valor);
        return valor;
    }
}
