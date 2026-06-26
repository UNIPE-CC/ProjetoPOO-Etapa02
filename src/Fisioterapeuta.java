public class Fisioterapeuta extends Profissional{
    private int totalSessoesPrevistas;
    private int sessoes;

    
    public Fisioterapeuta(String nome){
        super(nome, "");
        this.totalSessoesPrevistas = totalSessoesPrevistas;
        this.sessoes = sessoes;
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
        return "Espec: " + especialidade + " | Reg: " + registroProfissional + " | Valor: R$" + valorConsulta;
    }
    
    @Override
    public String registrarEspecifico(Atendimento atendimento){
        return atendimento;
    }
}