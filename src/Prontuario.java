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
        return procedimentos;
    }

    public String getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(String dataRegistro) {
        this.dataRegistro = dataRegistro;
    }
}