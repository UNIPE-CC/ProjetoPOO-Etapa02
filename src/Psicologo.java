public class Psicologo extends Profissional {
    private String abordagem;

    // SOBRECARGA: construtores com diferentes parâmetros
    public Psicologo(String nome, String cpf, String registroProfissional) {
        super(nome, cpf, "psicologia", registroProfissional);
        this.abordagem = "";
    }

    public Psicologo(String nome, String cpf, String registroProfissional, double valorConsulta) {
        super(nome, cpf, "psicologia", registroProfissional, valorConsulta);
        this.abordagem = "";
    }

    public Psicologo(String nome, String cpf, String registroProfissional, double valorConsulta, 
                     java.util.List<String> dias) {
        super(nome, cpf, "psicologia", registroProfissional, valorConsulta, dias);
        this.abordagem = "";
    }

    public Psicologo(String nome, String cpf, String registroProfissional, double valorConsulta, 
                     String abordagem) {
        super(nome, cpf, "psicologia", registroProfissional, valorConsulta);
        setAbordagem(abordagem);
    }

    public String getAbordagem() {
        return abordagem;
    }

    public void setAbordagem(String abordagem) {
        if (abordagem != null && !abordagem.trim().isEmpty()) {
            this.abordagem = abordagem.trim();
        } else {
            this.abordagem = "";
        }
    }

    public boolean possuiAbordagemDefinida() {
        return abordagem != null && !abordagem.isEmpty();
    }

    // SOBRESCRITA: redefine comportamento para incluir informações específicas
    @Override
    public String exibirResumo() {
        StringBuilder sb = new StringBuilder();
        sb.append("Psicólogo: ").append(getNome());
        sb.append(" | CPF: ").append(getCpf());
        sb.append(" | Registro: ").append(getRegistroProfissional());
        sb.append(" | Valor: R$").append(String.format("%.2f", getValorConsulta()));
        sb.append("\nAbordagem Terapêutica: ");
        if (possuiAbordagemDefinida()) {
            sb.append(abordagem);
        } else {
            sb.append("Não definida");
        }
        return sb.toString();
    }

    // SOBRESCRITA: adiciona informações específicas ao atendimento
    @Override
    public void registrarEspecifico(Atendimento atendimento) {
        if (atendimento != null) {
            String info = "Psicologia - Abordagem: " + abordagem;
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
