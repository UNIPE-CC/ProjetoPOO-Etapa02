public class Nutricionista extends Profissional{
    private String planoAlimentar;

    public Nutricionista(String nome) {
        super(nome, "nutricionista");
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
        atendimento.setDetalhesEspecificos("Plano Alimentar: " + this.planoAlimentar);
        return "Plano alimentar adicionado ao atendimento.";
    }
}

