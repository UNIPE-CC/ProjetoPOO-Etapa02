public class Nutricionista extends Profissional {
    private String planoAlimentar;

    // SOBRECARGA: construtores com diferentes parâmetros
    public Nutricionista(String nome, String cpf, String registroProfissional) {
        super(nome, cpf, "nutricao", registroProfissional);
        this.planoAlimentar = "";
    }

    public Nutricionista(String nome, String cpf, String registroProfissional, double valorConsulta) {
        super(nome, cpf, "nutricao", registroProfissional, valorConsulta);
        this.planoAlimentar = "";
    }

    public Nutricionista(String nome, String cpf, String registroProfissional, double valorConsulta, 
                         java.util.List<String> dias) {
        super(nome, cpf, "nutricao", registroProfissional, valorConsulta, dias);
        this.planoAlimentar = "";
    }

    public Nutricionista(String nome, String cpf, String registroProfissional, double valorConsulta, 
                         String planoAlimentar) {
        super(nome, cpf, "nutricao", registroProfissional, valorConsulta);
        setPlanoAlimentar(planoAlimentar);
    }

    public String getPlanoAlimentar() {
        return planoAlimentar;
    }

    public void setPlanoAlimentar(String planoAlimentar) {
        if (planoAlimentar != null && !planoAlimentar.trim().isEmpty()) {
            this.planoAlimentar = planoAlimentar.trim();
        } else {
            this.planoAlimentar = "";
        }
    }

    public boolean possuiPlanoAlimentar() {
        return planoAlimentar != null && !planoAlimentar.isEmpty();
    }

    public void atualizarPlanoAlimentar(String novoPlano) {
        setPlanoAlimentar(novoPlano);
    }

    // SOBRESCRITA: redefine comportamento para incluir informações específicas
    @Override
    public String exibirResumo() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nutricionista: ").append(getNome());
        sb.append(" | CPF: ").append(getCpf());
        sb.append(" | Registro: ").append(getRegistroProfissional());
        sb.append(" | Valor: R$").append(String.format("%.2f", getValorConsulta()));
        sb.append("\nPlano Alimentar: ");
        if (possuiPlanoAlimentar()) {
            sb.append(planoAlimentar);
        } else {
            sb.append("Não definido");
        }
        return sb.toString();
    }

    // SOBRESCRITA: adiciona informações específicas ao atendimento
    @Override
    public void registrarEspecifico(Atendimento atendimento) {
        if (atendimento != null) {
            String info = "Nutrição - Plano Alimentar: " + planoAlimentar;
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
