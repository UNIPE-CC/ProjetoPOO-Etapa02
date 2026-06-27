public class PagamentoConvenio extends Pagamento{
    private Convenio convenio;
    
    public PagamentoConvenio(int indiceConsulta, double valorFinal, Convenio convenio) {
        super(indiceConsulta, valorFinal, "convenio");
        this.convenio = convenio;
    }
    
    public Convenio getConvenio(){
        return convenio;
    }
    
    public void setConvenio(Convenio convenio){
        this.convenio = convenio;
    }
    
    @Override
    public double calcularValorFinal(){
        double valor = getValorFinal();
        valor -= valor * convenio.getCobertura();
        setValorFinal(valor);
        return valor;
    }
}
