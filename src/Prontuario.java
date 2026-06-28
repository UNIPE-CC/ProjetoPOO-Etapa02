import java.util.ArrayList;
import java.util.List;

public class Prontuario {
    private String observacoes;
    private String diagnostico;
    private List<String> procedimentos;
    private String dataRegistro;

    // COMPOSIÇÃO: construtor package-private - só pode ser criado dentro do pacote
    // Este construtor só deve ser chamado por Atendimento
    Prontuario(String observacoes, String diagnostico, String dataRegistro) {
        setObservacoes(observacoes);
        setDiagnostico(diagnostico);
        setDataRegistro(dataRegistro);
        this.procedimentos = new ArrayList<>();
    }

    // SOBRECARGA: construtor com lista de procedimentos
    Prontuario(String observacoes, String diagnostico, List<String> procedimentos, String dataRegistro) {
        setObservacoes(observacoes);
        setDiagnostico(diagnostico);
        setDataRegistro(dataRegistro);
        this.procedimentos = procedimentos != null ? new ArrayList<>(procedimentos) : new ArrayList<>();
    }

    public String getObservacoes() {
        return observacoes;
    }

    void setObservacoes(String observacoes) {
        if (observacoes != null && !observacoes.trim().isEmpty()) {
            this.observacoes = observacoes.trim();
        } else {
            this.observacoes = "";
        }
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    void setDiagnostico(String diagnostico) {
        if (diagnostico != null && !diagnostico.trim().isEmpty()) {
            this.diagnostico = diagnostico.trim();
        } else {
            this.diagnostico = "";
        }
    }

    public List<String> getProcedimentos() {
        return new ArrayList<>(procedimentos);
    }

    public String getDataRegistro() {
        return dataRegistro;
    }

    void setDataRegistro(String dataRegistro) {
        if (dataRegistro != null && !dataRegistro.trim().isEmpty()) {
            this.dataRegistro = dataRegistro.trim();
        } else {
            this.dataRegistro = "";
        }
    }

    // SOBRECARGA: métodos adicionarProcedimento com diferentes parâmetros
    void adicionarProcedimento(String procedimento) {
        if (procedimento != null && !procedimento.trim().isEmpty()) {
            this.procedimentos.add(procedimento.trim());
        }
    }

    void adicionarProcedimentos(List<String> novosProcedimentos) {
        if (novosProcedimentos != null) {
            for (String proc : novosProcedimentos) {
                if (proc != null && !proc.trim().isEmpty()) {
                    this.procedimentos.add(proc.trim());
                }
            }
        }
    }

    void limparProcedimentos() {
        this.procedimentos.clear();
    }

    boolean possuiProcedimentos() {
        return !procedimentos.isEmpty();
    }

    int getTotalProcedimentos() {
        return procedimentos.size();
    }

    public String exibirResumo() {
        StringBuilder sb = new StringBuilder();
        sb.append("Data Registro: ").append(dataRegistro != null && !dataRegistro.isEmpty() ? 
                                           dataRegistro : "Não informada");
        sb.append("\nObservações: ").append(observacoes != null && !observacoes.isEmpty() ? 
                                            observacoes : "Nenhuma");
        sb.append("\nDiagnóstico: ").append(diagnostico != null && !diagnostico.isEmpty() ? 
                                            diagnostico : "Não informado");
        sb.append("\nProcedimentos: ");
        if (procedimentos.isEmpty()) {
            sb.append("Nenhum procedimento registrado");
        } else {
            for (int i = 0; i < procedimentos.size(); i++) {
                sb.append(i + 1).append(". ").append(procedimentos.get(i));
                if (i < procedimentos.size() - 1) {
                    sb.append("\n               ");
                }
            }
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return exibirResumo();
    }
}
