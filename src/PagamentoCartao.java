public class PagamentoCartao extends Pagamento {
    private static final int MAX_PARCELAS = 6;
    private static final int PARCELAS_SEM_JUROS = 3;
    private static final double TAXA_PARCELA_EXTRA = 2.5;

    // SOBRECARGA: construtores com diferentes parâmetros
    public PagamentoCartao(Consulta consulta, double valorFinal, int parcelas) throws PagamentoInvalidoException {
        super(consulta, valorFinal, "cartao", parcelas);
        validarParcelas(parcelas);
    }

    public PagamentoCartao(Consulta consulta, double valorFinal) {
        super(consulta, valorFinal, "cartao");
    }

    private void validarParcelas(int parcelas) throws PagamentoInvalidoException {
        if (parcelas < 1 || parcelas > MAX_PARCELAS) {
            throw new PagamentoInvalidoException(
                "Numero de parcelas invalido. Minimo 1, maximo " + MAX_PARCELAS
            );
        }
    }

    // SOBRESCRITA: implementação específica para cartão
    @Override
    public double calcularValorFinal() {
        double valorBase = getValorFinal();
        int parcelas = getParcelas();
        
        if (parcelas <= PARCELAS_SEM_JUROS) {
            setValorFinal(valorBase);
            return valorBase;
        }
        
        int parcelasExtras = parcelas - PARCELAS_SEM_JUROS;
        double taxaTotal = parcelasExtras * TAXA_PARCELA_EXTRA;
        double valorComTaxa = valorBase * (1 + taxaTotal / 100);
        
        setValorFinal(valorComTaxa);
        return valorComTaxa;
    }

    public double getValorParcela() {
        int parcelas = getParcelas();
        if (parcelas > 0) {
            return Math.round((getValorFinal() / parcelas) * 100.0) / 100.0;
        }
        return 0.0;
    }

    public boolean temJuros() {
        return getParcelas() > PARCELAS_SEM_JUROS;
    }

    public double getTaxaTotal() {
        int parcelas = getParcelas();
        if (parcelas <= PARCELAS_SEM_JUROS) {
            return 0.0;
        }
        int parcelasExtras = parcelas - PARCELAS_SEM_JUROS;
        return parcelasExtras * TAXA_PARCELA_EXTRA;
    }

    @Override
    public String exibirResumo() {
        StringBuilder sb = new StringBuilder();
        sb.append("Pagamento Cartão");
        sb.append(" | Parcelas: ").append(getParcelas());
        if (temJuros()) {
            sb.append(" | Taxa: ").append(String.format("%.1f%%", getTaxaTotal()));
        } else {
            sb.append(" | Sem juros");
        }
        sb.append(" | Valor Final: R$").append(String.format("%.2f", getValorFinal()));
        if (getParcelas() > 1) {
            sb.append(" (R$").append(String.format("%.2f", getValorParcela())).append(" cada)");
        }
        return sb.toString();
    }
}
