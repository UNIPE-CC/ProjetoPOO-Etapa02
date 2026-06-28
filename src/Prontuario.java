import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa o prontuário de um paciente.
 * Contém observações, diagnóstico, procedimentos realizados e data de registro.
 */
public class Prontuario {
    
    private String observacoes;
    private String diagnostico;
    private List<String> procedimentos;
    private String dataRegistro;
    
    /**
     * Construtor básico do prontuário.
     * 
     * @param observacoes Observações sobre o paciente
     * @param diagnostico Diagnóstico do paciente
     * @param dataRegistro Data do registro no formato dd/MM/yyyy
     */
    public Prontuario(String observacoes, String diagnostico, String dataRegistro) {
        setObservacoes(observacoes);
        setDiagnostico(diagnostico);
        setDataRegistro(dataRegistro);
        this.procedimentos = new ArrayList<>();
    }

    /**
     * Construtor completo com lista de procedimentos.
     * 
     * @param observacoes Observações sobre o paciente
     * @param diagnostico Diagnóstico do paciente
     * @param procedimentos Lista de procedimentos realizados
     * @param dataRegistro Data do registro no formato dd/MM/yyyy
     */
    public Prontuario(String observacoes, String diagnostico, List<String> procedimentos, String dataRegistro) {
        setObservacoes(observacoes);
        setDiagnostico(diagnostico);
        setDataRegistro(dataRegistro);
        this.procedimentos = procedimentos != null ? new ArrayList<>(procedimentos) : new ArrayList<>();
    }
    
    public String getObservacoes() {
        return observacoes;
    }

    /**
     * Define as observações com validação.
     * 
     * @param observacoes Observações a serem definidas
     */
    public void setObservacoes(String observacoes) {
        if (observacoes != null && !observacoes.trim().isEmpty()) {
            this.observacoes = observacoes.trim();
        } else {
            this.observacoes = "";
        }
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    /**
     * Define o diagnóstico com validação.
     * 
     * @param diagnostico Diagnóstico a ser definido
     */
    public void setDiagnostico(String diagnostico) {
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

    /**
     * Define a data de registro com validação.
     * 
     * @param dataRegistro Data no formato dd/MM/yyyy
     */
    public void setDataRegistro(String dataRegistro) {
        if (dataRegistro != null && !dataRegistro.trim().isEmpty()) {
            this.dataRegistro = dataRegistro.trim();
        } else {
            this.dataRegistro = "";
        }
    }
    
    /**
     * Adiciona um procedimento à lista.
     * 
     * @param procedimento Procedimento a ser adicionado
     */
    public void adicionarProcedimento(String procedimento) {
        if (procedimento != null && !procedimento.trim().isEmpty()) {
            this.procedimentos.add(procedimento.trim());
        }
    }

    /**
     * Adiciona múltiplos procedimentos à lista.
     * 
     * @param novosProcedimentos Lista de procedimentos a serem adicionados
     */
    public void adicionarProcedimentos(List<String> novosProcedimentos) {
        if (novosProcedimentos != null) {
            for (String proc : novosProcedimentos) {
                if (proc != null && !proc.trim().isEmpty()) {
                    this.procedimentos.add(proc.trim());
                }
            }
        }
    }

    /**
     * Remove todos os procedimentos da lista.
     */
    public void limparProcedimentos() {
        this.procedimentos.clear();
    }

    /**
     * Verifica se o prontuário possui procedimentos registrados.
     * 
     * @return true se possui procedimentos, false caso contrário
     */
    public boolean possuiProcedimentos() {
        return !procedimentos.isEmpty();
    }
    
    /**
     * Retorna um resumo formatado do prontuário.
     * 
     * @return String com todos os dados do prontuário formatados
     */
    public String exibirResumo() {
        StringBuilder sb = new StringBuilder();
        sb.append("Data Registro: ").append(dataRegistro != null ? dataRegistro : "Não informada");
        sb.append("\nObservações: ").append(observacoes != null ? observacoes : "Nenhuma");
        sb.append("\nDiagnóstico: ").append(diagnostico != null ? diagnostico : "Não informado");
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