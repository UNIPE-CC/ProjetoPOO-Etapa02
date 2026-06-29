public class Fisioterapeuta extends Profissional {
    private int totalSessoesPrevistas;
    private int sessoesRealizadas;

    // SOBRECARGA: construtores com diferentes parâmetros
    public Fisioterapeuta(String nome, String cpf, String registroProfissional) {
        super(nome, cpf, "fisioterapia", registroProfissional);
        this.totalSessoesPrevistas = 0;
        this.sessoesRealizadas = 0;
    }

    public Fisioterapeuta(String nome, String cpf, String registroProfissional, double valorConsulta) {
        super(nome, cpf, "fisioterapia", registroProfissional, valorConsulta);
        this.totalSessoesPrevistas = 0;
        this.sessoesRealizadas = 0;
    }

    public Fisioterapeuta(String nome, String cpf, String registroProfissional, double valorConsulta, 
                          java.util.List<String> dias) {
        super(nome, cpf, "fisioterapia", registroProfissional, valorConsulta, dias);
        this.totalSessoesPrevistas = 0;
        this.sessoesRealizadas = 0;
    }

    public int getTotalSessoesPrevistas() {
        return totalSessoesPrevistas;
    }

    public void setTotalSessoesPrevistas(int totalSessoesPrevistas) {
        if (totalSessoesPrevistas >= 0) {
            this.totalSessoesPrevistas = totalSessoesPrevistas;
        } else {
            this.totalSessoesPrevistas = 0;
        }
    }

    public int getSessoesRealizadas() {
        return sessoesRealizadas;
    }

    public void setSessoesRealizadas(int sessoesRealizadas) {
        if (sessoesRealizadas >= 0) {
            this.sessoesRealizadas = sessoesRealizadas;
        } else {
            this.sessoesRealizadas = 0;
        }
    }

    public void registrarSessao() {
        if (sessoesRealizadas < totalSessoesPrevistas) {
            sessoesRealizadas++;
        }
    }

    public int getSessoesRestantes() {
        return totalSessoesPrevistas - sessoesRealizadas;
    }

    public boolean possuiSessoesPendentes() {
        return sessoesRealizadas < totalSessoesPrevistas;
    }

    // SOBRESCRITA: redefine comportamento para incluir informações específicas
    @Override
    public String exibirResumo() {
        StringBuilder sb = new StringBuilder();
        sb.append("Fisioterapeuta: ").append(getNome());
        sb.append(" | CPF: ").append(getCpf());
        sb.append(" | Registro: ").append(getRegistroProfissional());
        sb.append(" | Valor: R$").append(String.format("%.2f", getValorConsulta()));
        sb.append("\nTotal Sessões Previstas: ").append(totalSessoesPrevistas);
        sb.append(" | Sessões Realizadas: ").append(sessoesRealizadas);
        sb.append(" | Sessões Restantes: ").append(getSessoesRestantes());
        return sb.toString();
    }

    // SOBRESCRITA: adiciona informações específicas ao atendimento
    @Override
    public void registrarEspecifico(Atendimento atendimento) {
        if (atendimento != null) {
            String info = "Fisioterapia - Sessões Previstas: " + totalSessoesPrevistas + 
                          " | Realizadas: " + sessoesRealizadas;
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
