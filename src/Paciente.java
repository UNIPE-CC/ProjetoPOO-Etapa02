public class Paciente extends Pessoa{
    private int idade;
    private String convenioNome;
    private boolean ativo;

    public Paciente(String nome, String cpf) {
        super(nome, cpf);
        this.idade = 0;
        this.convenioNome = "";
        this.ativo = true;
    }

    public Paciente(String nome, String cpf, int idade, String telefone) {
        super(nome, cpf, telefone);
        setIdade(idade);
        this.convenioNome = "";
        this.ativo = true;
    }

    // construtor com todos os dados
    public Paciente(String nome, String cpf, int idade, String telefone, String convenioNome) {
        super(nome, cpf, telefone);
        setIdade(idade);
        setConvenioNome(convenioNome);
        this.ativo = true;
    }

    // atualiza so idade e telefone
    // idade e conveio pode se criado um metodo get e set
    
    public void complementar(int idade, String telefone) {
        setIdade(idade);
        setTelefone(telefone);
    }

    // atualiza tudo incluindo convenio
    public void complementar(int idade, String telefone, String convenioNome) {
        setIdade(idade);
        setTelefone(telefone);
        setConvenioNome(convenioNome);
    }

    public void desativar() {
        this.ativo = false;
    }
    
    public int getIdade(){
        return idade;
    }
    
    public void setIdade(int idade) {
        if (idade < 0 || idade > 130) {
            System.out.println("Idade invalida");
            return;
        }
        this.idade = idade;
    }

    public boolean getAtivo(){
        return ativo;
    }
    
    public void setAtivo(boolean ativo){
        this.ativo = ativo;
    }
    
    public String getConvenioNome(){
        return convenioNome;
    }
    
    public void setConvenioNome(String convenioNome){
        if(convenioNome == null){
            convenioNome = "";
        }
        this.convenioNome = convenioNome;
    }
    
    @Override
    public String exibirResumo() {
        String status = "Sim";
        if (!ativo) {
            status = "Nao";
        }
        return "Paciente: "+super.getNome()+"|  Convenio: " + convenioNome + " | Ativo: " + status;
    }
}
