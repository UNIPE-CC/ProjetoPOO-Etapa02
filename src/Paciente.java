public class Paciente extends Pessoa {
    private int idade;
    private Convenio convenio;
    private boolean ativo;

    // SOBRECARGA: construtores com diferentes parâmetros
    public Paciente(String nome, String cpf) {
        super(nome, cpf);
        this.idade = 0;
        this.convenio = null;
        this.ativo = true;
    }

    public Paciente(String nome, String cpf, int idade, String telefone) {
        super(nome, cpf, telefone);
        setIdade(idade);
        this.convenio = null;
        this.ativo = true;
    }

    public Paciente(String nome, String cpf, int idade, String telefone, Convenio convenio) {
        super(nome, cpf, telefone);
        setIdade(idade);
        setConvenio(convenio);
        this.ativo = true;
    }

    // SOBRECARGA: métodos complementar com diferentes parâmetros
    public void complementar(int idade, String telefone) {
        setIdade(idade);
        setTelefone(telefone);
    }

    public void complementar(int idade, String telefone, Convenio convenio) {
        setIdade(idade);
        setTelefone(telefone);
        setConvenio(convenio);
    }

    public void desativar() {
        this.ativo = false;
    }

    public void ativar() {
        this.ativo = true;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        if (idade >= 0 && idade <= 130) {
            this.idade = idade;
        } else {
            this.idade = 0;
        }
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    // ASSOCIAÇÃO: Paciente conhece Convenio, mas ambos existem independentemente
    public Convenio getConvenio() {
        return convenio;
    }

    public void setConvenio(Convenio convenio) {
        this.convenio = convenio;
    }

    public String getConvenioNome() {
        if (convenio != null) {
            return convenio.getNomeConvenio();
        }
        return "Nenhum";
    }

    public boolean temConvenio() {
        return convenio != null;
    }

    // SOBRESCRITA: redefine comportamento para exibir informações específicas do paciente
    @Override
    public String exibirResumo() {
        StringBuilder sb = new StringBuilder();
        sb.append("Paciente: ").append(getNome());
        sb.append(" | CPF: ").append(getCpf());
        sb.append(" | Idade: ").append(idade);
        sb.append(" | Telefone: ").append(getTelefone());
        sb.append("\nConvenio: ").append(getConvenioNome());
        sb.append(" | Status: ").append(ativo ? "Ativo" : "Inativo");
        return sb.toString();
    }
}
