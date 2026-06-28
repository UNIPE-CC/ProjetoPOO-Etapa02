public class Fisioterapeuta extends Profissional{
    private int totalSessoesPrevistas;
    private int sessoes;

    public Fisioterapeuta(String nome, String registroProfissional){
        super(nome, "fisioterapia", registroProfissional);
    }
    public Fisioterapeuta(String nome, String registroProfissional, double valor) {
        super(nome, "fisioterapia", registroProfissional, valor);
    }
    public Fisioterapeuta(String nome, String registroProfissional,double valorConsulta, String[] dias, int totalDias) {
        super(nome, "fisioterapia", registroProfissional, valorConsulta, dias, totalDias);
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
        return "Espec: " + getEspecialidade() + " | Reg: " + getRegistroProfissional() +"tSessoes: " + this.totalSessoesPrevistas + " | Valor: R$" + getValorConsulta();
    }
    
    @Override
    public void registrarEspecifico(Atendimento atendimento){
        atendimento.setObservacoes(atendimento.getObservacoes() + "Total Sessoes: " + totalSessoesPrevistas);
    }
}