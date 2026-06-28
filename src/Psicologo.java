public class Psicologo extends Profissional {
    private String abordagem;
    
    public Psicologo(String nome, String registroProfissional){
        super(nome, "psicologia", registroProfissional);
    }
    public Psicologo(String nome, String registroProfissional, double valor) {
        super(nome, "psicologia", registroProfissional, valor);
    }
    public Psicologo(String nome, String registroProfissional, double valorConsulta, String[] dias, int totalDias) {
        super(nome, "psicologia", registroProfissional, valorConsulta, dias, totalDias);
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
