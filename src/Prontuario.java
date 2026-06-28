import java.util.ArrayList;
import java.util.List;

public class Prontuario {
    private String observacoes;
    private String diagnostico;
    private List<String> procedimentos;
    private String dataRegistro;

    public Prontuario(String observacoes, String diagnostico, String dataRegistro) {
        this.observacoes = observacoes;
        this.diagnostico = diagnostico;
        this.dataRegistro = dataRegistro;
        this.procedimentos = new ArrayList<>();
    }

    public Prontuario(String observacoes, String diagnostico, List<String> procedimentos, String dataRegistro) {
        this.observacoes = observacoes;
        this.diagnostico = diagnostico;
        this.procedimentos = new ArrayList<>(procedimentos);
        this.dataRegistro = dataRegistro;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public List<String> getProcedimentos() {
        return new ArrayList<>(procedimentos);
    }

    public void adicionarProcedimento(String procedimento) {
        if (procedimento != null && !procedimento.trim().isEmpty()) {
            this.procedimentos.add(procedimento);
        }
    }

    public void adicionarProcedimentos(List<String> novosProcedimentos) {
        if (novosProcedimentos != null) {
            for (String proc : novosProcedimentos) {
                if (proc != null && !proc.trim().isEmpty()) {
                    this.procedimentos.add(proc);
                }
            }
        }
    }

    public String getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(String dataRegistro) {
        this.dataRegistro = dataRegistro;
    }
}