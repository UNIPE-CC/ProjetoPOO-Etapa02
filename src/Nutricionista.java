public class Nutricionista extends Profissional{
    private String planoAlimentar;

    public Nutricionista(String nome, String registroProfissional) {
        super(nome, "nutricionista", registroProfissional);
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

