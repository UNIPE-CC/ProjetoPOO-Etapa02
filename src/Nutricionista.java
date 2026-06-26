public class Nutricionista extends Profissionais{
    private String planoAlimentar;

    public Nutricionista(String nome, String planoAlimentar) {
        super(nome, "");
        this.planoAlimentar = planoAlimentar;
    }

    public String getPlanoAlimentar() {
        return planoAlimentar;
    }

    public void setPlanoAlimentar(String planoAlimentar) {
        this.planoAlimentar = planoAlimentar;
    }

    @Override
    public String exibirResumo() {
        return "Espec: " + especialidade + " | Reg: " + registroProfissional + " | Plano Alimentar: " + planoAlimentar + " | Valor: R$" + valorConsulta;
    }

    @Override
    public String registrarEspecifico(Atendimento atendimento) {
        atendimento.setDetalhesEspecificos("Plano Alimentar: " + this.planoAlimentar);
        return "Plano alimentar adicionado ao atendimento.";
    }
}

