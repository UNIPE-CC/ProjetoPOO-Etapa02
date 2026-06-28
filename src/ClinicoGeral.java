public class ClinicoGeral extends Profissional {
    private String encaminhamento;

    // SOBRECARGA: construtores com diferentes parâmetros
    public ClinicoGeral(String nome, String cpf, String registroProfissional) {
        super(nome, cpf, "clinica geral", registroProfissional);
        this.encaminhamento = "";
    }

    public ClinicoGeral(String nome, String cpf, String registroProfissional, double valorConsulta) {
        super(nome, cpf, "clinica geral", registroProfissional, valorConsulta);
        this.encaminhamento = "";
    }

    public ClinicoGeral(String nome, String cpf, String registroProfissional, double valorConsulta, 
                        java.util.List<String> dias) {
        super(nome, cpf, "clinica geral", registroProfissional, valorConsulta, dias);
        this.encaminhamento = "";
    }

    public ClinicoGeral(String nome, String cpf, String registroProfissional, double valorConsulta, 
                        String encaminhamento) {
        super(nome, cpf, "clinica geral", registroProfissional, valorConsulta);
        setEncaminhamento(encaminhamento);
    }

    public String getEncaminhamento() {
        return encaminhamento;
    }

    public void setEncaminhamento(String encaminhamento) {
        if (encaminhamento != null && !encaminhamento.trim().isEmpty()) {
            this.encaminhamento = encaminhamento.trim();
        } else {
            this.encaminhamento = "";
        }
    }

    public boolean possuiEncaminhamento() {
        return encaminhamento != null && !encaminhamento.isEmpty();
    }

    public void adicionarEncaminhamento(String especialidade, String motivo) {
        if (especialidade != null && !especialidade.trim().isEmpty()) {
            String novoEncaminhamento = especialidade.trim();
            if (motivo != null && !motivo.trim().isEmpty()) {
                novoEncaminhamento += " - " + motivo.trim();
            }
            setEncaminhamento(novoEncaminhamento);
        }
    }

    // SOBRESCRITA: redefine comportamento para incluir informações específicas
    @Override
    public String exibirResumo() {
        StringBuilder sb = new StringBuilder();
        sb.append("Clínico Geral: ").append(getNome());
        sb.append(" | CPF: ").append(getCpf());
        sb.append(" | Registro: ").append(getRegistroProfissional());
        sb.append(" | Valor: R$").append(String.format("%.2f", getValorConsulta()));
        sb.append("\nEncaminhamento: ");
        if (possuiEncaminhamento()) {
            sb.append(encaminhamento);
        } else {
            sb.append("Nenhum");
        }
        return sb.toString();
    }

    // SOBRESCRITA: adiciona informações específicas ao atendimento
    @Override
    public void registrarEspecifico(Atendimento atendimento) {
        if (atendimento != null) {
            String info = "Clínico Geral - Encaminhamento: " + encaminhamento;
            // CONCATENA em vez de sobrescrever
            String obsAtual = atendimento.getObservacoes();
            if (obsAtual != null && !obsAtual.isEmpty()) {
                atendimento.setObservacoes(obsAtual + " | " + info);
            } else {
                atendimento.setObservacoes(info);
            }
        }
    }
}
