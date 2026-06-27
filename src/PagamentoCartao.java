public class PagamentoCartao extends Pagamento{
    
    public PagamentoCartao(int indiceConsulta, double valorFinal, int parcelas) {
        super(indiceConsulta, valorFinal, "cartao", parcelas);
    }
    
    @Override
    public double calcularValorFinal(){
        double valor = getValorFinal();
        if (getParcelas() > 3) {
            int parcelasExtras = getParcelas() - 3;
            double taxa = parcelasExtras * 2.5;
            valor += valor * taxa / 100;
        }
        setValorFinal(valor);
        return valor;
    }
}
