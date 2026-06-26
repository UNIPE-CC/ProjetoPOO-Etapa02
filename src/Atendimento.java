public class Atendimento {
    private int indiceConsulta;
    private String observacoes;
    private String diagnostico;
    private String[] procedimentos;
    private int totalProcedimentos;

    public int getIndiceConsulta(){
        return indiceConsulta;
    }
    
    public void setIndiceConsulta(int indiceConsulta){
        this.indiceConsulta = indiceConsulta;
    }
    
    public String getObservacoes(){
        return observacoes;
    }
    
    public void setObservacoes(String observacoes){
        this.observacoes = observacoes;
    }
    
    public String getDiagnostico(){
        return diagnostico;
    }
    
    public void setDiagnostico(String diagnostico){
        this.diagnostico = diagnostico;
    }
    
    public String[] getProcedimentos(){
        return procedimentos;
    }
    
    public void setProcedimentos(String[] procedimentos){
        this.procedimentos = procedimentos;
    }
    
    public int getTotalProcedimentos(){
        return totalProcedimentos;
    }
    
    public void setTotalProcedimentos(int totalProcedimentos){
        this.totalProcedimentos = totalProcedimentos;
    }
    

    // registro basico - so observacoes
    public Atendimento(int indiceConsulta, String observacoes) {
        this.indiceConsulta = indiceConsulta;
        this.observacoes = observacoes;
        this.diagnostico = "";
        this.procedimentos = new String[10];
        this.totalProcedimentos = 0;
    }

    public Atendimento(int indiceConsulta, String observacoes, String diagnostico) {
        this.indiceConsulta = indiceConsulta;
        this.observacoes = observacoes;
        this.diagnostico = diagnostico;
        this.procedimentos = new String[10];
        this.totalProcedimentos = 0;
    }

    // registro completo com procedimentos ja definidos
    public Atendimento(int indiceConsulta, String observacoes, String diagnostico,
                       String[] procedimentos, int totalProcedimentos) {
        this.indiceConsulta = indiceConsulta;
        this.observacoes = observacoes;
        this.diagnostico = diagnostico;
        this.procedimentos = new String[10];
        this.totalProcedimentos = totalProcedimentos;
        for (int i = 0; i < totalProcedimentos; i++) {
            this.procedimentos[i] = procedimentos[i];
        }
    }

    // adiciona um por vez
    public void adicionarProcedimento(String procedimento) {
        if (totalProcedimentos < 10) {
            procedimentos[totalProcedimentos] = procedimento;
            totalProcedimentos++;
        }
    }

    // adiciona varios de uma vez
    public void adicionarProcedimento(String[] procs, int quantidade) {
        for (int i = 0; i < quantidade; i++) {
            if (totalProcedimentos < 10) {
                procedimentos[totalProcedimentos] = procs[i];
                totalProcedimentos++;
            }
        }
    }

    public String exibirResumo() {
        String resumo = "Observacoes: " + observacoes;

        if (!diagnostico.equals("")) {
            resumo = resumo + "\nDiagnostico: " + diagnostico;
        }

        if (totalProcedimentos > 0) {
            resumo = resumo + "\nProcedimentos: ";
            for (int i = 0; i < totalProcedimentos; i++) {
                resumo = resumo + procedimentos[i];
                if (i < totalProcedimentos - 1) {
                    resumo = resumo + ", ";
                }
            }
        }
        return resumo;
    }
}
