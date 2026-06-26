public class Psicologo extends Profissional {
    private String abordagem;
    
    public psicologo(String nome){
        super(nome, "");
        this.abordagem = abordagem;
    }
    
    public String getAbordagem(){
        return abordagem;
    }
    
    public void setAbordagem(String abordagem){
        this.abordagem = abordagem;
    }
    
    @Override
    public String exibirResumo() {
        return "Espec: " + super(especialidade) + " | Reg: " + super(registroProfissional) + " | Valor: R$" + super(valorConsulta);
    }
    
    @Override
    public String registrarEspecifico(Atendimento atendimento){
        return ("Qual o registro: ");
    }
}
