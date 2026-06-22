public class Paciente extends Pessoa{
    private int idade;
    private String convenioNome;
    private boolean ativo;

    public Paciente(String nome, String cpf) {
        setNome(nome);
        setCpf(cpf);
        this.idade = 0;
        setTelefone("");
        this.convenioNome = "";
        this.ativo = true;
    }

    public Paciente(String nome, String cpf, int idade, String telefone) {
        setNome(nome);
        setCpf(cpf);
        this.idade = idade;
        setTelefone(telefone);
        this.convenioNome = "";
        this.ativo = true;
    }

    // construtor com todos os dados
    public Paciente(String nome, String cpf, int idade, String telefone, String convenioNome) {
        setNome(nome);
        setCpf(cpf);
        this.idade = idade;
        setTelefone(telefone);
        this.convenioNome = convenioNome;
        this.ativo = true;
    }

    // atualiza so idade e telefone
    public void complementar(int idade, String telefone) {
        this.idade = idade;
        setTelefone(telefone);
    }

    // atualiza tudo incluindo convenio
    public void complementar(int idade, String telefone, String convenioNome) {
        this.idade = idade;
        setTelefone(telefone);
        this.convenioNome = convenioNome;
    }

    public void desativar() {
        this.ativo = false;
    }

    @Override
    public String exibirResumo() {
        String status = "Sim";
        if (!ativo) {
            status = "Nao";
        }
        return "Nome: " + getNome() + " | CPF: " + getCpf() + " | Idade: " + idade
                + " | Tel: " + getTelefone() + " | Convenio: " + convenioNome
                + " | Ativo: " + status;
    }
}
