public class Nutricionista extends Profissional{
    private String planoAlimentar;

    public Nutricionista(String nome, String registroProfissional) {
        super(nome, "nutricionista", registroProfissional);
    }
    public Nutricionista(String nome, String registroProfissional, double valorConsulta) {
        super(nome, "nutricionista", registroProfissional, valorConsulta);
    }
    public Nutricionista(String nome, String registroProfissional, double valorConsulta, String[] dias, int totalDias) {
        super(nome, "nutricionista", registroProfissional, valorConsulta, dias, totalDias);
    }

    public String getPlanoAlimentar() {
        return planoAlimentar;
    }

    public void setPlanoAlimentar(String planoAlimentar) {
        this.planoAlimentar = planoAlimentar;
    }

    @Override
    public String exibirResumo() {
        return "Espec: " + getEspecialidade() + " | Reg: " + getRegistroProfissional()
                + " | Plano Alimentar: " + this.planoAlimentar + " | Valor: R$" + getValorConsulta();
    }

    @Override
    public void registrarEspecifico(Atendimento atendimento) {
        atendimento.setObservacoes("Plano Alimentar: " + this.planoAlimentar);
    }
}

