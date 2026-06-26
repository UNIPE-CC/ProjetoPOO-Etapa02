public class ClinicoGeral extends Profissional {
    private String encaminhamento;
    
    public ClinicoGeral(String nome){
        super(nome, "clinico geral");
    }
    
    public String getEncaminhamento(){
        return encaminhamento;
    }
    
    public void setEncaminhamento(String encaminhamento){
        this.encaminhamento = encaminhamento;
    }
    
    @Override
    public String exibirResumo() {
        return "Espec: " + getEspecialidade() + " | Reg: " + getRegistroProfissional() + " | Valor: R$" + getValorConsulta();
    }
    
    @Override
    public void registrarEspecifico(Atendimento atendimento){
        return;
    }
}
