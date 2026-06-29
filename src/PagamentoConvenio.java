public class PagamentoConvenio extends Pagamento {
    private Convenio convenio;
    private String especialidade;

    public PagamentoConvenio(Consulta consulta, double valorFinal, Convenio convenio, String especialidade) throws PagamentoInvalidoException, ConvenioNaoCobreException {
        super(consulta, valorFinal, "convenio");
        validarConvenio(convenio, especialidade);
        this.convenio = convenio;
        this.especialidade = especialidade;
    }

    public PagamentoConvenio(Consulta consulta, double valorFinal, Convenio convenio) throws PagamentoInvalidoException, ConvenioNaoCobreException{
        super(consulta, valorFinal, "convenio");
        validarConvenio(convenio, null);
        this.convenio = convenio;
        this.especialidade = "";
    }

    private void validarConvenio(Convenio convenio, String especialidade) throws PagamentoInvalidoException, ConvenioNaoCobreException{
        if (convenio == null) {
            throw new PagamentoInvalidoException("Convenio nao pode ser nulo");
        }
        if (especialidade != null && !especialidade.isEmpty()) {
            if (!convenio.cobreEspecialidade(especialidade)) {
                throw new ConvenioNaoCobreException(
                    "Convenio " + convenio.getNomeConvenio() + 
                    " nao cobre a especialidade " + especialidade
                );
            }
        }
    }

    public Convenio getConvenio() {
        return convenio;
    }

    public void setConvenio(Convenio convenio) throws PagamentoInvalidoException {
        if (convenio == null) {
            throw new PagamentoInvalidoException("Convenio nao pode ser nulo");
        }
        this.convenio = convenio;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) throws ConvenioNaoCobreException{
        if (especialidade != null && !especialidade.trim().isEmpty()) {
            if (!convenio.cobreEspecialidade(especialidade)) {
                throw new ConvenioNaoCobreException(
                    "Convenio " + convenio.getNomeConvenio() + 
                    " nao cobre a especialidade " + especialidade
                );
            }
            this.especialidade = especialidade.trim();
        } else {
            this.especialidade = "";
        }
    }

    public double getCoberturaAplicada() {
        return convenio.getCobertura() * 100;
    }

    public double getValorDesconto() {
        return getValorFinal() * convenio.getCobertura();
    }

    // SOBRESCRITA: implementação específica para convênio
    @Override
    public double calcularValorFinal() {
        double valorBase = getValorFinal();
        double cobertura = convenio.getCobertura();
        double valorComDesconto = valorBase * (1 - cobertura);
        
        if (valorComDesconto < 0) {
            valorComDesconto = 0;
        }
        
        setValorFinal(valorComDesconto);
        return valorComDesconto;
    }

    @Override
    public String exibirResumo() {
        StringBuilder sb = new StringBuilder();
        sb.append("Pagamento Convênio");
        sb.append(" | Convênio: ").append(convenio.getNomeConvenio());
        sb.append(" | Cobertura: ").append(String.format("%.0f%%", getCoberturaAplicada()));
        sb.append(" | Desconto: R$").append(String.format("%.2f", getValorDesconto()));
        sb.append(" | Valor Final: R$").append(String.format("%.2f", getValorFinal()));
        if (especialidade != null && !especialidade.isEmpty()) {
            sb.append(" | Especialidade: ").append(especialidade);
        }
        return sb.toString();
    }

    @Override
    public String exportarDados() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.exportarDados());
        sb.append(";Convênio: ").append(convenio.getNomeConvenio());
        sb.append(";Cobertura: ").append(String.format("%.0f%%", getCoberturaAplicada()));
        sb.append(";Desconto: R$").append(String.format("%.2f", getValorDesconto()));
        return sb.toString();
    }
}
