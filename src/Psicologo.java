public class Psicologo extends Profissional {
    private String abordagem;
    
    public Psicologo(String nome){
        super(nome, "psicologia");
    }
    
    public String getAbordagem(){
        return abordagem;
    }
    
    public void setAbordagem(String abordagem){
        this.abordagem = abordagem;
    }
    
    @Override
    public String exibirResumo() {
        return "Espec: " + getEspecialidade() + " | Reg: " + getRegistroProfissional()
                + " | Valor: R$" + getValorConsulta() + "| Abordagem: " + this.abordagem;
    }
    
    @Override
    public void registrarEspecifico(Atendimento atendimento){
        atendimento.setObservacoes(atendimento.getObservacoes() + " | Abordagem: " + abordagem);
    }
}
