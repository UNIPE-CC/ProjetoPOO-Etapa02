import java.util.*;

public class ClinicaServico {
    private Map<String, Paciente> pacientes;
    private Map<String, Profissional> profissionais;
    private List<Consulta> consultas;
    private List<Atendimento> atendimentos;
    private Set<Pagamento> pagamentos;
    private List<Pessoa> todasPessoas;
    private Set<String> cpfsCadastrados;

    public ClinicaServico() {
        this.pacientes = new HashMap<>();
        this.profissionais = new HashMap<>();
        this.consultas = new ArrayList<>();
        this.atendimentos = new ArrayList<>();
        this.pagamentos = new HashSet<>();
        this.todasPessoas = new ArrayList<>();
        this.cpfsCadastrados = new HashSet<>();
    }

    public Map<String, Paciente> getPacientes() {
        return pacientes;
    }

    public Map<String, Profissional> getProfissionais() {
        return profissionais;
    }

    public List<Consulta> getConsultas() {
        return consultas;
    }

    public List<Atendimento> getAtendimentos() {
        return atendimentos;
    }

    public Set<Pagamento> getPagamentos() {
        return pagamentos;
    }

    public List<Pessoa> getTodasPessoas() {
        return todasPessoas;
    }
}