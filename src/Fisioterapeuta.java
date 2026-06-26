public class Fisioterapeuta extends Profissional{
    private int totalSessoesPrevistas;
    private int sessoes;

    public Fisioterapeuta(String nome){
        super(nome, "fisioterapia");
    }
    
    public int getTotalSessoesPrevistas(){
        return totalSessoesPrevistas;
    }
    
    public void setTotalSessoesPrevistas(int totalSessoesPrevistas){
        this.totalSessoesPrevistas = totalSessoesPrevistas;
    }
    
    public int getSessoes(){
        return sessoes;
    }
    
    public void setSessoes(int qntSessoes){
        this.sessoes = qntSessoes;
    }
    
    
    @Override
    public String exibirResumo() {
        return "Espec: " + getEspecialidade() + " | Reg: " + getRegistroProfissional() + " | Valor: R$" + getValorConsulta();
    }
    
    @Override
    public void registrarEspecifico(Atendimento atendimento){
        return atendimento;
    }
}