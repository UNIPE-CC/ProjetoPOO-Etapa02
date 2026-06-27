public class ClinicoGeral extends Profissional {
    private String encaminhamento;
    
    public ClinicoGeral(String nome, String registroProfissional){
        super(nome, "clinico geral", registroProfissional);
    }
    public ClinicoGeral(String nome, String registroProfissional, double valor) {
        super(nome, "clinico geral", registroProfissional, valor);
    }
    public ClinicoGeral(String nome, String registroProfissional, double valorConsulta, String[] dias, int totalDias) {
        super(nome, "clinico geral", registroProfissional, valorConsulta, dias, totalDias);
    }
    
    public String getEncaminhamento(){
        return encaminhamento;
    }
    
    public void setEncaminhamento(String encaminhamento){
        this.encaminhamento = encaminhamento;
    }
    
    @Override
    public String exibirResumo() {
        return "Espec: " + getEspecialidade() + " | Reg: " + getRegistroProfissional() + "Encaminhamento: " + this.encaminhamento +" | Valor: R$" + getValorConsulta();
    }
    
    @Override
    public void registrarEspecifico(Atendimento atendimento){
        atendimento.setObservacoes(atendimento.getObservacoes() + "Encaminhamento :" + encaminhamento);
    }
}
