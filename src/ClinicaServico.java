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

    public Set<String> getCpfsCadastrados() {
        return cpfsCadastrados;
    }

    // ---- PACIENTES ----

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

    public List<Paciente> listarPacientes() {
        return new ArrayList<>(pacientes.values());
    }

    public void desativarPaciente(String cpf) throws PacienteNaoEncontradoException {
        Paciente paciente = buscarPacientePorCpf(cpf);
        paciente.desativar();
    }

    public void ativarPaciente(String cpf) throws PacienteNaoEncontradoException {
        Paciente paciente = buscarPacientePorCpf(cpf);
        paciente.ativar();
    }

    // ---- PROFISSIONAIS ----

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

    public List<Profissional> buscarProfissionaisPorEspecialidade(String especialidade) {
        List<Profissional> resultado = new ArrayList<>();
        for (Profissional prof : profissionais.values()) {
            if (prof.getEspecialidade().equalsIgnoreCase(especialidade)) {
                resultado.add(prof);
            }
        }
        return resultado;
    }

    public List<Profissional> listarProfissionais() {
        return new ArrayList<>(profissionais.values());
    }

    // ---- CONSULTAS ----

    public void agendarConsulta(String cpfPaciente, String cpfProfissional, String data, String horario, String tipo) 
            throws PacienteNaoEncontradoException, ProfissionalNaoEncontradoException, 
                   PacienteInativoException, HorarioIndisponivelException {
        
        Paciente paciente = buscarPacientePorCpf(cpfPaciente);
        if (!paciente.isAtivo()) {
            throw new PacienteInativoException("Paciente " + paciente.getNome() + " esta inativo");
        }
        
        Profissional profissional = buscarProfissionalPorCpf(cpfProfissional);
        if (profissional.getValorConsulta() <= 0) {
            throw new HorarioIndisponivelException("Profissional sem valor definido");
        }
        
        String diaSemana = descobrirDiaSemana(data);
        if (!profissional.atendeNoDia(diaSemana)) {
            throw new HorarioIndisponivelException("Profissional nao atende no dia " + diaSemana);
        }
        
        if (temConflito(cpfProfissional, data, horario)) {
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

    public void remarcarConsulta(String cpfPaciente, String dataOrig, String horarioOrig, 
                                  String novaData, String novoHorario) 
            throws PacienteNaoEncontradoException, ConsultaNaoEncontradaException, 
                   HorarioIndisponivelException, OperacaoInvalidaException {
        
        buscarPacientePorCpf(cpfPaciente);
        
        for (Consulta consulta : consultas) {
            if (consulta.getPaciente() != null && 
                consulta.getPaciente().getCpf().equals(cpfPaciente) &&
                consulta.getData().equals(dataOrig) &&
                consulta.getHorario().equals(horarioOrig) &&
                consulta.isAgendada()) {
                
                String cpfProf = consulta.getProfissional().getCpf();
                String diaSemana = descobrirDiaSemana(novaData);
                if (!consulta.getProfissional().atendeNoDia(diaSemana)) {
                    throw new HorarioIndisponivelException("Profissional nao atende no dia " + diaSemana);
                }
                if (temConflito(cpfProf, novaData, novoHorario)) {
                    throw new HorarioIndisponivelException("Horario " + novoHorario + " ja esta ocupado");
                }
                
                Consulta novaConsulta = new Consulta(
                    consulta.getPaciente(),
                    consulta.getProfissional(),
                    novaData,
                    novoHorario,
                    consulta.getTipo()
                );
                consulta.remarcar();
                consultas.add(novaConsulta);
                return;
            }
        }
        throw new ConsultaNaoEncontradaException("Consulta nao encontrada para remarcacao");
    }

    public Consulta buscarConsulta(String cpfPaciente, String data, String horario) 
            throws ConsultaNaoEncontradaException {
        for (Consulta consulta : consultas) {
            if (consulta.getPaciente() != null && 
                consulta.getPaciente().getCpf().equals(cpfPaciente) &&
                consulta.getData().equals(data) &&
                consulta.getHorario().equals(horario)) {
                return consulta;
            }
        }
        throw new ConsultaNaoEncontradaException("Consulta nao encontrada");
    }

    public List<Consulta> listarConsultas() {
        return new ArrayList<>(consultas);
    }

    public List<Consulta> buscarConsultasPorPaciente(String cpf) {
        List<Consulta> resultado = new ArrayList<>();
        for (Consulta consulta : consultas) {
            if (consulta.getPaciente() != null && 
                consulta.getPaciente().getCpf().equals(cpf)) {
                resultado.add(consulta);
            }
        }
        return resultado;
    }

    // ---- ATENDIMENTOS ----

    public Atendimento registrarAtendimento(String cpfPaciente, String data, String horario, 
                                            String observacoes, String diagnostico, 
                                            String dataRegistro, List<String> procedimentos) 
            throws PacienteNaoEncontradoException, ConsultaNaoEncontradaException, OperacaoInvalidaException {
        
        buscarPacientePorCpf(cpfPaciente);
        Consulta consulta = buscarConsulta(cpfPaciente, data, horario);
        
        if (!consulta.isAgendada() && !consulta.isRemarcada()) {
            throw new OperacaoInvalidaException("So pode registrar atendimento em consulta agendada");
        }
        
        Atendimento atendimento = new Atendimento(consulta, observacoes, diagnostico, procedimentos, dataRegistro);
        consulta.realizar();
        atendimentos.add(atendimento);
        
        if (consulta.getProfissional() != null) {
            consulta.getProfissional().registrarEspecifico(atendimento);
        }
        
        return atendimento;
    }

    public List<Atendimento> listarAtendimentos() {
        return new ArrayList<>(atendimentos);
    }

    // ---- PAGAMENTOS ----

    public Pagamento registrarPagamento(String cpfPaciente, String data, String horario, 
                                        String tipoPagamento, double valorBase, int parcelas) 
            throws PacienteNaoEncontradoException, ConsultaNaoEncontradaException, 
                   PagamentoInvalidoException, ConvenioNaoCobreException {
        
        buscarPacientePorCpf(cpfPaciente);
        Consulta consulta = buscarConsulta(cpfPaciente, data, horario);
        
        if (!consulta.isRealizada()) {
            throw new PagamentoInvalidoException("Consulta nao realizada. Pagamento nao permitido.");
        }
        
        if (parcelas < 1 || parcelas > 6) {
            throw new PagamentoInvalidoException("Numero de parcelas invalido. Minimo 1, maximo 6.");
        }
        
        if (valorBase < 0) {
            throw new PagamentoInvalidoException("Valor nao pode ser negativo");
        }
        
        Pagamento pagamento;
        Paciente paciente = consulta.getPaciente();
        
        if (tipoPagamento.equalsIgnoreCase("dinheiro") || tipoPagamento.equalsIgnoreCase("pix")) {
            pagamento = new PagamentoDinheiro(consulta, valorBase);
        } else if (tipoPagamento.equalsIgnoreCase("cartao")) {
            pagamento = new PagamentoCartao(consulta, valorBase, parcelas);
        } else if (tipoPagamento.equalsIgnoreCase("convenio")) {
            Convenio convenio = paciente.getConvenio();
            if (convenio == null) {
                throw new PagamentoInvalidoException("Paciente nao possui convenio");
            }
            String especialidade = consulta.getProfissional().getEspecialidade();
            if (!convenio.cobreEspecialidade(especialidade)) {
                throw new ConvenioNaoCobreException("Convenio " + convenio.getNomeConvenio() + 
                    " nao cobre a especialidade " + especialidade);
            }
            pagamento = new PagamentoConvenio(consulta, valorBase, convenio, especialidade);
        } else {
            throw new PagamentoInvalidoException("Tipo de pagamento " + tipoPagamento + " nao reconhecido");
        }
        
        pagamento.calcularValorFinal();
        pagamentos.add(pagamento);
        return pagamento;
    }

    public Set<Pagamento> listarPagamentos() {
        return new HashSet<>(pagamentos);
    }

    // ---- METODOS AUXILIARES ----

    private boolean temConflito(String cpfProfissional, String data, String horario) {
        for (Consulta consulta : consultas) {
            if (consulta.getProfissional() != null &&
                consulta.getProfissional().getCpf().equals(cpfProfissional) &&
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
            if (data == null || data.isEmpty()) return "";
            String[] partes = data.split("/");
            if (partes.length != 3) return "";
            
            int dia = Integer.parseInt(partes[0]);
            int mes = Integer.parseInt(partes[1]);
            int ano = Integer.parseInt(partes[2]);
            
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

    // ---- RELATORIOS ----

    public void gerarRelatorioUnificado() {
        Relatorio.gerarRelatorioUnificado(todasPessoas);
    }

    public void gerarRelatorioConsultas() {
        Relatorio.gerarRelatorioConsultas(consultas);
    }

    public void gerarRelatorioFinanceiro() {
        Relatorio.gerarRelatorioFinanceiro(consultas, pagamentos);
    }

    public void exportarDados() {
        List<Exportavel> exportaveis = new ArrayList<>();
        exportaveis.addAll(consultas);
        exportaveis.addAll(atendimentos);
        exportaveis.addAll(pagamentos);
        Relatorio.exportarDados(exportaveis);
    }
}
