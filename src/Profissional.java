public abstract class Profissional extends Pessoa {
    protected String especialidade;
    protected String registroProfissional;
    protected double valorConsulta;

    public Profissional(String nome, String cpf, String especialidade) {
        super(nome, cpf);
        this.especialidade = especialidade;
        this.registroProfissional = "";
        this.valorConsulta = 0.0;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public String getRegistroProfissional() {
        return registroProfissional;
    }

    public void setRegistroProfissional(String registroProfissional) {
        this.registroProfissional = registroProfissional;
    }

    public double getValorConsulta() {
        return valorConsulta;
    }

    public void setValorConsulta(double valorConsulta) {
        this.valorConsulta = valorConsulta;
    }

    protected boolean especialidadeValida(String esp) {
        return esp.equalsIgnoreCase("clinica geral") ||
               esp.equalsIgnoreCase("fisioterapia") ||
               esp.equalsIgnoreCase("psicologia") ||
               esp.equalsIgnoreCase("nutricao");
    }

    public abstract void registrarEspecifico(Atendimento atendimento);

    @Override
    public String exibirResumo() {
        return "Nome: " + getNome() + " | CPF: " + getCpf() + " | Espec: " + especialidade +
                " | Reg: " + registroProfissional + " | Valor: R$" + valorConsulta;
    }
}