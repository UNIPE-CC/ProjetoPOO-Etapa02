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

    public void cadastrarPaciente(Paciente paciente) throws Exception {
        if (paciente == null) {
            throw new IllegalArgumentException("Paciente nao pode ser nulo");
        }
        String cpf = paciente.getCpf();
        if (cpfsCadastrados.contains(cpf)) {
            throw new Exception("CPF ja cadastrado");
        }
        pacientes.put(cpf, paciente);
        cpfsCadastrados.add(cpf);
        todasPessoas.add(paciente);
    }

    public Paciente buscarPacientePorCpf(String cpf) throws PacienteNaoEncontradoException {
        Paciente paciente = pacientes.get(cpf);
        if (paciente == null) {
            throw new PacienteNaoEncontradoException("Paciente com CPF " + cpf + " nao encontrado");
        }
        return paciente;
    }

    public void cadastrarProfissional(Profissional profissional) throws Exception {
        if (profissional == null) {
            throw new IllegalArgumentException("Profissional nao pode ser nulo");
        }
        String cpf = profissional.getCpf();
        if (cpfsCadastrados.contains(cpf)) {
            throw new Exception("CPF ja cadastrado");
        }
        profissionais.put(cpf, profissional);
        cpfsCadastrados.add(cpf);
        todasPessoas.add(profissional);
    }

    public Profissional buscarProfissionalPorCpf(String cpf) throws ProfissionalNaoEncontradoException {
        Profissional profissional = profissionais.get(cpf);
        if (profissional == null) {
            throw new ProfissionalNaoEncontradoException("Profissional com CPF " + cpf + " nao encontrado");
        }
        return profissional;
    }

    public Profissional buscarProfissionalPorRegistro(String registro) throws ProfissionalNaoEncontradoException {
        for (Profissional prof : profissionais.values()) {
            if (prof.getRegistroProfissional().equals(registro)) {
                return prof;
            }
        }
        throw new ProfissionalNaoEncontradoException("Profissional com registro " + registro + " nao encontrado");
    }
}