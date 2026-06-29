import java.util.ArrayList;
import java.util.List;

public class Atendimento implements Exportavel {
    private Consulta consulta;
    private String observacoes;
    private String diagnostico;
    private Prontuario prontuario;
    private String dataAtendimento;

    // SOBRECARGA: construtores com diferentes parâmetros
    public Atendimento(Consulta consulta, String observacoes, String dataAtendimento) {
        this.consulta = consulta;
        setObservacoes(observacoes);
        this.diagnostico = "";
        this.dataAtendimento = dataAtendimento;
        // COMPOSIÇÃO: Prontuario só existe dentro de Atendimento
        this.prontuario = new Prontuario(observacoes, "", dataAtendimento);
    }

    public Atendimento(Consulta consulta, String observacoes, String diagnostico, String dataAtendimento) {
        this.consulta = consulta;
        setObservacoes(observacoes);
        setDiagnostico(diagnostico);
        this.dataAtendimento = dataAtendimento;
        // COMPOSIÇÃO: Prontuario só existe dentro de Atendimento
        this.prontuario = new Prontuario(observacoes, diagnostico, dataAtendimento);
    }

    public Atendimento(Consulta consulta, String observacoes, String diagnostico, 
                       List<String> procedimentos, String dataAtendimento) {
        this.consulta = consulta;
        setObservacoes(observacoes);
        setDiagnostico(diagnostico);
        this.dataAtendimento = dataAtendimento;
        // COMPOSIÇÃO: Prontuario só existe dentro de Atendimento
        this.prontuario = new Prontuario(observacoes, diagnostico, procedimentos, dataAtendimento);
    }

    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        if (observacoes != null && !observacoes.trim().isEmpty()) {
            this.observacoes = observacoes.trim();
        } else {
            this.observacoes = "";
        }
        if (prontuario != null) {
            prontuario.setObservacoes(this.observacoes);
        }
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        if (diagnostico != null && !diagnostico.trim().isEmpty()) {
            this.diagnostico = diagnostico.trim();
        } else {
            this.diagnostico = "";
        }
        if (prontuario != null) {
            prontuario.setDiagnostico(this.diagnostico);
        }
    }

    public String getDataAtendimento() {
        return dataAtendimento;
    }

    public void setDataAtendimento(String dataAtendimento) {
        if (dataAtendimento != null && !dataAtendimento.trim().isEmpty()) {
            this.dataAtendimento = dataAtendimento.trim();
        } else {
            this.dataAtendimento = "";
        }
        if (prontuario != null) {
            prontuario.setDataRegistro(this.dataAtendimento);
        }
    }

    // COMPOSIÇÃO: Prontuario é acessado apenas via métodos do Atendimento
    public Prontuario getProntuario() {
        return prontuario;
    }

    // SOBRECARGA: métodos adicionarProcedimento com diferentes parâmetros
    public void adicionarProcedimento(String procedimento) {
        if (prontuario != null) {
            prontuario.adicionarProcedimento(procedimento);
        }
    }

    public void adicionarProcedimentos(List<String> procedimentos) {
        if (prontuario != null) {
            prontuario.adicionarProcedimentos(procedimentos);
        }
    }

    public List<String> getProcedimentos() {
        if (prontuario != null) {
            return prontuario.getProcedimentos();
        }
        return new ArrayList<>();
    }

    public boolean possuiProcedimentos() {
        return prontuario != null && prontuario.possuiProcedimentos();
    }

    public void limparProcedimentos() {
        if (prontuario != null) {
            prontuario.limparProcedimentos();
        }
    }

    // SOBRESCRITA: implementação da interface Exportavel
    @Override
    public String exportarDados() {
        StringBuilder sb = new StringBuilder();
        sb.append("ATENDIMENTO;\n");
        sb.append("Paciente: ").append(consulta != null && consulta.getPaciente() != null ? 
                                       consulta.getPaciente().getNome() : "N/A").append(";");
        sb.append("CPF: ").append(consulta != null && consulta.getPaciente() != null ? 
                                  consulta.getPaciente().getCpf() : "N/A").append(";");
        sb.append("Profissional: ").append(consulta != null && consulta.getProfissional() != null ? 
                                           consulta.getProfissional().getNome() : "N/A").append(";");
        sb.append("Data Atendimento: ").append(dataAtendimento).append(";");
        sb.append("Diagnostico: ").append(diagnostico).append(";");
        sb.append("Observacoes: ").append(observacoes).append(";");
        sb.append("Procedimentos: ");
        if (prontuario != null && prontuario.possuiProcedimentos()) {
            List<String> procs = prontuario.getProcedimentos();
            for (int i = 0; i < procs.size(); i++) {
                sb.append(procs.get(i));
                if (i < procs.size() - 1) {
                    sb.append(", ");
                }
            }
        } else {
            sb.append("Nenhum");
        }
        return sb.toString();
    }

    public String exibirResumo() {
        StringBuilder sb = new StringBuilder();
        sb.append("Atendimento - ");
        if (consulta != null) {
            sb.append("Paciente: ").append(consulta.getPaciente() != null ? 
                                           consulta.getPaciente().getNome() : "N/A");
            sb.append(" | Profissional: ").append(consulta.getProfissional() != null ? 
                                                  consulta.getProfissional().getNome() : "N/A");
        }
        sb.append(" | Data: ").append(dataAtendimento);
        sb.append("\nObservações: ").append(observacoes);
        sb.append("\nDiagnóstico: ").append(diagnostico);
        sb.append("\n").append(prontuario != null ? prontuario.exibirResumo() : "");
        return sb.toString();
    }
}
