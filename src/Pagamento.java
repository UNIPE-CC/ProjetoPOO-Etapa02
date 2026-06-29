public abstract class Pagamento implements Exportavel {
    private Consulta consulta;
    private double valorFinal;
    private String tipoPagamento;
    private int parcelas;

    // SOBRECARGA: construtores com diferentes parâmetros
    public Pagamento(Consulta consulta, double valorFinal, String tipoPagamento) {
        this.consulta = consulta;
        setValorFinal(valorFinal);
        setTipoPagamento(tipoPagamento);
        this.parcelas = 1;
    }

    public Pagamento(Consulta consulta, double valorFinal, String tipoPagamento, int parcelas) {
        this.consulta = consulta;
        setValorFinal(valorFinal);
        setTipoPagamento(tipoPagamento);
        setParcelas(parcelas);
    }

    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }

    public double getValorFinal() {
        return valorFinal;
    }

    public void setValorFinal(double valorFinal) {
        if (valorFinal >= 0) {
            this.valorFinal = valorFinal;
        } else {
            this.valorFinal = 0.0;
        }
    }

    public String getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(String tipoPagamento) {
        if (tipoPagamento != null && !tipoPagamento.trim().isEmpty()) {
            this.tipoPagamento = tipoPagamento.trim().toLowerCase();
        } else {
            this.tipoPagamento = "";
        }
    }

    public int getParcelas() {
        return parcelas;
    }

    public void setParcelas(int parcelas) {
        if (parcelas >= 1 && parcelas <= 6) {
            this.parcelas = parcelas;
        } else {
            this.parcelas = 1;
        }
    }

    public double getValorParcela() {
        if (parcelas > 0) {
            return Math.round((valorFinal / parcelas) * 100.0) / 100.0;
        }
        return 0.0;
    }

    public abstract double calcularValorFinal() throws PagamentoInvalidoException;

    // SOBRESCRITA: implementação da interface Exportavel
    @Override
    public String exportarDados() {
        StringBuilder sb = new StringBuilder();
        sb.append("PAGAMENTO;\n");
        if (consulta != null) {
            sb.append("Paciente: ").append(consulta.getPaciente() != null ? 
                                           consulta.getPaciente().getNome() : "N/A").append(";");
            sb.append("CPF: ").append(consulta.getPaciente() != null ? 
                                      consulta.getPaciente().getCpf() : "N/A").append(";");
            sb.append("Profissional: ").append(consulta.getProfissional() != null ? 
                                               consulta.getProfissional().getNome() : "N/A").append(";");
            sb.append("Data: ").append(consulta.getData()).append(";");
            sb.append("Horario: ").append(consulta.getHorario()).append(";");
        }
        sb.append("Tipo: ").append(tipoPagamento).append(";");
        sb.append("Valor Final: ").append(String.format("%.2f", valorFinal)).append(";");
        sb.append("Parcelas: ").append(parcelas);
        if (parcelas > 1) {
            sb.append(" (R$").append(String.format("%.2f", getValorParcela())).append(" cada)");
        }
        return sb.toString();
    }

    public String exibirResumo() {
        StringBuilder sb = new StringBuilder();
        sb.append("Pagamento - Tipo: ").append(tipoPagamento);
        sb.append(" | Valor: R$").append(String.format("%.2f", valorFinal));
        sb.append(" | Parcelas: ").append(parcelas);
        if (parcelas > 1) {
            sb.append(" (R$").append(String.format("%.2f", getValorParcela())).append(" cada)");
        }
        if (consulta != null) {
            sb.append(" | Consulta: ").append(consulta.getData()).append(" ").append(consulta.getHorario());
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return exibirResumo();
    }
}
