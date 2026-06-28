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

    // Getters e métodos de cadastro/busca (Commit 1 e 2)
    // ... (código dos commits anteriores)

    public void agendarConsulta(String cpfPaciente, String registroProfissional, String data, String horario, String tipo) 
            throws PacienteNaoEncontradoException, ProfissionalNaoEncontradoException, 
                   PacienteInativoException, HorarioIndisponivelException {
        
        Paciente paciente = buscarPacientePorCpf(cpfPaciente);
        if (!paciente.isAtivo()) {
            throw new PacienteInativoException("Paciente " + paciente.getNome() + " esta inativo");
        }
        
        Profissional profissional = buscarProfissionalPorRegistro(registroProfissional);
        if (profissional.getValorConsulta() <= 0) {
            throw new HorarioIndisponivelException("Profissional sem valor definido");
        }
        
        String diaSemana = descobrirDiaSemana(data);
        if (!profissional.atendeNoDia(diaSemana)) {
            throw new HorarioIndisponivelException("Profissional nao atende no dia " + diaSemana);
        }
        
        if (temConflito(registroProfissional, data, horario)) {
            throw new HorarioIndisponivelException("Horario " + horario + " ja esta ocupado");
        }
        
        Consulta consulta = new Consulta(paciente, profissional, data, horario, tipo);
        consultas.add(consulta);
    }

    public void cancelarConsulta(String cpfPaciente, String data, String horario) 
            throws PacienteNaoEncontradoException, ConsultaNaoEncontradaException, OperacaoInvalidaException {
        
        buscarPacientePorCpf(cpfPaciente);
        
        for (Consulta consulta : consultas) {
            if (consulta.getPaciente() != null && 
                consulta.getPaciente().getCpf().equals(cpfPaciente) &&
                consulta.getData().equals(data) &&
                consulta.getHorario().equals(horario)) {
                
                if (consulta.isRealizada()) {
                    throw new OperacaoInvalidaException("Consulta ja realizada. Nao pode cancelar.");
                }
                if (consulta.isCancelada()) {
                    throw new OperacaoInvalidaException("Consulta ja cancelada.");
                }
                consulta.cancelar();
                return;
            }
        }
        throw new ConsultaNaoEncontradaException("Consulta nao encontrada para CPF " + cpfPaciente + " em " + data + " " + horario);
    }

    private boolean temConflito(String registroProfissional, String data, String horario) {
        for (Consulta consulta : consultas) {
            if (consulta.getProfissional() != null &&
                consulta.getProfissional().getRegistroProfissional().equals(registroProfissional) &&
                consulta.getData().equals(data) &&
                consulta.getHorario().equals(horario) &&
                (consulta.isAgendada() || consulta.isRemarcada())) {
                return true;
            }
        }
        return false;
    }

    private String descobrirDiaSemana(String data) {
        try {
            int dia = Integer.parseInt(data.substring(0, 2));
            int mes = Integer.parseInt(data.substring(3, 5));
            int ano = Integer.parseInt(data.substring(6, 10));
            if (mes < 3) {
                mes = mes + 12;
                ano = ano - 1;
            }
            int k = ano % 100;
            int j = ano / 100;
            int resultado = (dia + (13 * (mes + 1)) / 5 + k + k / 4 + j / 4 - 2 * j) % 7;
            if (resultado < 0) resultado = resultado + 7;
            String[] nomes = {"sabado", "domingo", "segunda", "terca", "quarta", "quinta", "sexta"};
            return nomes[resultado];
        } catch (Exception e) {
            return "";
        }
    }
}