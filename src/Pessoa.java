public abstract class Pessoa {
    private String nome;
    private String cpf;
    private String telefone;
    private String dataNascimento;
    
    // SOBRECARGA: construtores com diferentes parâmetros
    public Pessoa() {
        this.nome = "";
        this.cpf = "";
        this.telefone = "";
        this.dataNascimento = "";
    }
    
    public Pessoa(String nome, String cpf) {
        setNome(nome);
        setCpf(cpf);
        this.telefone = "";
        this.dataNascimento = "";
    }
    
    public Pessoa(String nome, String cpf, String telefone) {
        setNome(nome);
        setCpf(cpf);
        setTelefone(telefone);
        this.dataNascimento = "";
    }
    
    public Pessoa(String nome, String cpf, String telefone, String dataNascimento) {
        setNome(nome);
        setCpf(cpf);
        setTelefone(telefone);
        setDataNascimento(dataNascimento);
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        if (nome != null && !nome.trim().isEmpty()) {
            this.nome = nome.trim();
        } else {
            this.nome = "";
        }
    }
    
    public String getCpf() {
        return cpf;
    }
    
    public void setCpf(String cpf) {
        if (cpf != null && !cpf.trim().isEmpty()) {
            this.cpf = cpf.trim();
        } else {
            this.cpf = "";
        }
    }
    
    public String getTelefone() {
        return telefone;
    }
    
    public void setTelefone(String telefone) {
        if (telefone != null && !telefone.trim().isEmpty()) {
            this.telefone = telefone.trim();
        } else {
            this.telefone = "";
        }
    }
    
    public String getDataNascimento() {
        return dataNascimento;
    }
    
    public void setDataNascimento(String dataNascimento) {
        if (dataNascimento != null && !dataNascimento.trim().isEmpty()) {
            this.dataNascimento = dataNascimento.trim();
        } else {
            this.dataNascimento = "";
        }
    }
    
    public abstract String exibirResumo();
}
