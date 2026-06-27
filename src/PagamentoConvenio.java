public class PagamentoConvenio extends Pagamento{
    private String convenio;
    
    public PagamentoConvenio(int indiceConsulta, double valorFinal, String convenio) {
        super(indiceConsulta, valorFinal, "convenio");
        this.convenio = convenio;
    }
    
    public String getConvenio(){
        return convenio;
    }
    
    public void setConvenio(String convenio){
        this.convenio = convenio;
    }
    
    @Override
    public double calcularValorFinal(){
        double valor = getValorFinal();
        switch (convenio) {
            case "SaudePlus":
                valor -= valor * 0.40;
                break;
            case "VidaMais":
                valor -= valor * 0.30;
                break;
            case "BemEstar":
                valor -= valor * 0.50;
                break;
        }
        setValorFinal(valor);
        return valor;
    }
}
