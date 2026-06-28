public class PagamentoDinheiro extends Pagamento {
    private static final double DESCONTO_PADRAO = 0.05;
    private double descontoAplicado;

    public PagamentoDinheiro(Consulta consulta, double valorFinal) {
        super(consulta, valorFinal, "dinheiro");
        this.descontoAplicado = DESCONTO_PADRAO;
    }

    public PagamentoDinheiro(Consulta consulta, double valorFinal, double descontoPersonalizado) {
        super(consulta, valorFinal, "dinheiro");
        setDescontoAplicado(descontoPersonalizado);
    }

    public double getDescontoAplicado() {
        return descontoAplicado * 100;
    }

    public void setDescontoAplicado(double desconto) {
        if (desconto >= 0 && desconto <= 1) {
            this.descontoAplicado = desconto;
        } else {
            this.descontoAplicado = DESCONTO_PADRAO;
        }
    }

    public double getValorDesconto() {
        return getValorFinal() * descontoAplicado;
    }

    // SOBRESCRITA: implementação específica para dinheiro/PIX
    @Override
    public double calcularValorFinal() {
        double valorBase = getValorFinal();
        
        if (valorBase < 0) {
            throw new PagamentoInvalidoException("Valor nao pode ser negativo");
        }
        
        double valorComDesconto = valorBase * (1 - descontoAplicado);
        
        if (valorComDesconto < 0) {
            valorComDesconto = 0;
        }
        
        setValorFinal(valorComDesconto);
        return valorComDesconto;
    }

    public double calcularValorComDescontoPersonalizado(double desconto) {
        if (desconto < 0 || desconto > 1) {
            throw new PagamentoInvalidoException("Desconto invalido. Deve estar entre 0% e 100%");
        }
        double valorBase = getValorFinal();
        return valorBase * (1 - desconto);
    }

    @Override
    public String exibirResumo() {
        StringBuilder sb = new StringBuilder();
        sb.append("Pagamento Dinheiro/PIX");
        sb.append(" | Desconto: ").append(String.format("%.0f%%", getDescontoAplicado()));
        sb.append(" | Desconto: R$").append(String.format("%.2f", getValorDesconto()));
        sb.append(" | Valor Final: R$").append(String.format("%.2f", getValorFinal()));
        return sb.toString();
    }

    @Override
    public String exportarDados() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.exportarDados());
        sb.append(";Desconto: ").append(String.format("%.0f%%", getDescontoAplicado()));
        sb.append(";Valor Desconto: R$").append(String.format("%.2f", getValorDesconto()));
        return sb.toString();
    }
}
