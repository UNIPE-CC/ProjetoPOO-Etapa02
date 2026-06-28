import java.util.*;

public class Main {
    private static ClinicaServico servico;
    private static Scanner sc;

    public static void main(String[] args) {
        servico = new ClinicaServico();
        sc = new Scanner(System.in);
        
        // Dados iniciais para demonstração
        inicializarDadosDemo();
        
        int opcao = -1;
        while (opcao != 0) {
            try {
                System.out.println("\n=== CLINICA VIDAPLENA ===");
                System.out.println("1 - Pacientes");
                System.out.println("2 - Profissionais");
                System.out.println("3 - Consultas");
                System.out.println("4 - Atendimentos");
                System.out.println("5 - Pagamentos");
                System.out.println("6 - Relatorios");
                System.out.println("7 - Exportar Dados");
                System.out.println("0 - Sair");
                System.out.print("Escolha: ");
                opcao = lerInteiro();
                
                switch (opcao) {
                    case 1: menuPacientes(); break;
                    case 2: menuProfissionais(); break;
                    case 3: menuConsultas(); break;
                    case 4: menuAtendimentos(); break;
                    case 5: menuPagamentos(); break;
                    case 6: menuRelatorios(); break;
                    case 7: exportarDados(); break;
                    case 0: 
                        System.out.println("Sistema encerrado.");
                        break;
                    default: 
                        System.out.println("Opcao invalida!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: Digite um numero valido.");
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            } finally {
                if (opcao != 0) {
                    System.out.println("--- Operacao finalizada ---");
                }
            }
        }
        sc.close();
    }

    private static void inicializarDadosDemo() {
        try {
            // Pacientes
            Paciente p1 = new Paciente("Joao Silva", "11111111111", 30, "11999999999");
            Paciente p2 = new Paciente("Maria Santos", "22222222222", 25, "11988888888");
            servico.cadastrarPaciente(p1);
            servico.cadastrarPaciente(p2);
            
            // Profissionais
            Fisioterapeuta f1 = new Fisioterapeuta("Dr. Carlos", "33333333333", "CRT123", 150.0);
            f1.adicionarDiaDisponivel("segunda");
            f1.adicionarDiaDisponivel("quarta");
            f1.adicionarDiaDisponivel("sexta");
            f1.setTotalSessoesPrevistas(10);
            
            Psicologo ps1 = new Psicologo("Dra. Ana", "44444444444", "CRP456", 200.0);
            ps1.adicionarDiaDisponivel("terca");
            ps1.adicionarDiaDisponivel("quinta");
            ps1.setAbordagem("TCC");
            
            servico.cadastrarProfissional(f1);
            servico.cadastrarProfissional(ps1);
            
            System.out.println("Dados demo inicializados com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao inicializar dados: " + e.getMessage());
        }
    }

    // ---- METODOS AUXILIARES ----
    
    private static int lerInteiro() throws NumberFormatException {
        String entrada = sc.nextLine().trim();
        if (entrada.isEmpty()) {
            throw new NumberFormatException("Entrada vazia");
        }
        return Integer.parseInt(entrada);
    }

    private static double lerDouble() throws NumberFormatException {
        String entrada = sc.nextLine().trim();
        if (entrada.isEmpty()) {
            throw new NumberFormatException("Entrada vazia");
        }
        return Double.parseDouble(entrada);
    }

    private static String lerString() {
        return sc.nextLine().trim();
    }

    // ---- PACIENTES ----
    
    private static void menuPacientes() {
        int op = -1;
        while (op != 0) {
            try {
                System.out.println("\n--- PACIENTES ---");
                System.out.println("1 - Cadastrar");
                System.out.println("2 - Complementar cadastro");
                System.out.println("3 - Buscar por CPF");
                System.out.println("4 - Listar todos");
                System.out.println("5 - Desativar");
                System.out.println("6 - Ativar");
                System.out.println("0 - Voltar");
                System.out.print("Opcao: ");
                op = lerInteiro();

                switch (op) {
                    case 1: cadastrarPaciente(); break;
                    case 2: complementarPaciente(); break;
                    case 3: buscarPaciente(); break;
                    case 4: listarPacientes(); break;
                    case 5: desativarPaciente(); break;
                    case 6: ativarPaciente(); break;
                    case 0: break;
                    default: System.out.println("Opcao invalida!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: Digite um numero valido.");
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            } finally {
                System.out.println("--- Operacao finalizada ---");
            }
        }
    }

    private static void cadastrarPaciente() {
        try {
            System.out.print("Nome: ");
            String nome = lerString();
            System.out.print("CPF: ");
            String cpf = lerString();
            
            if (servico.getCpfsCadastrados().contains(cpf)) {
                throw new Exception("CPF ja cadastrado");
            }

            System.out.print("Idade: ");
            int idade = lerInteiro();
            System.out.print("Telefone: ");
            String telefone = lerString();
            System.out.print("Convenio (deixe em branco se nao tiver): ");
            String nomeConvenio = lerString();
            
            Paciente paciente;
            if (nomeConvenio.isEmpty()) {
                paciente = new Paciente(nome, cpf, idade, telefone);
            } else {
                Convenio convenio = new Convenio(nomeConvenio, 30);
                paciente = new Paciente(nome, cpf, idade, telefone, convenio);
            }
            servico.cadastrarPaciente(paciente);
            System.out.println("Paciente cadastrado com sucesso!");
            
        } catch (NumberFormatException e) {
            System.out.println("Erro: Digite um numero valido para idade.");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void complementarPaciente() {
        try {
            System.out.print("CPF: ");
            String cpf = lerString();
            Paciente paciente = servico.buscarPacientePorCpf(cpf);
            
            System.out.print("Idade: ");
            int idade = lerInteiro();
            System.out.print("Telefone: ");
            String telefone = lerString();
            
            System.out.print("Deseja informar convenio? (S/N): ");
            String resposta = lerString();
            if (resposta.equalsIgnoreCase("S")) {
                System.out.print("Nome do convenio: ");
                String nomeConvenio = lerString();
                Convenio convenio = new Convenio(nomeConvenio, 30);
                paciente.complementar(idade, telefone, convenio);
            } else {
                paciente.complementar(idade, telefone);
            }
            System.out.println("Cadastro atualizado com sucesso!");
            
        } catch (PacienteNaoEncontradoException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Erro: Digite um numero valido.");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void buscarPaciente() {
        try {
            System.out.print("CPF: ");
            String cpf = lerString();
            Paciente paciente = servico.buscarPacientePorCpf(cpf);
            System.out.println(paciente.exibirResumo());
        } catch (PacienteNaoEncontradoException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void listarPacientes() {
        List<Paciente> pacientes = servico.listarPacientes();
        if (pacientes.isEmpty()) {
            System.out.println("Nenhum paciente cadastrado.");
            return;
        }
        for (Paciente p : pacientes) {
            System.out.println(p.exibirResumo());
            System.out.println("---");
        }
    }

    private static void desativarPaciente() {
        try {
            System.out.print("CPF: ");
            String cpf = lerString();
            servico.desativarPaciente(cpf);
            System.out.println("Paciente desativado com sucesso!");
        } catch (PacienteNaoEncontradoException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void ativarPaciente() {
        try {
            System.out.print("CPF: ");
            String cpf = lerString();
            servico.ativarPaciente(cpf);
            System.out.println("Paciente ativado com sucesso!");
        } catch (PacienteNaoEncontradoException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    // ---- PROFISSIONAIS ----
    
    private static void menuProfissionais() {
        int op = -1;
        while (op != 0) {
            try {
                System.out.println("\n--- PROFISSIONAIS ---");
                System.out.println("1 - Cadastrar");
                System.out.println("2 - Listar todos");
                System.out.println("3 - Filtrar por especialidade");
                System.out.println("0 - Voltar");
                System.out.print("Opcao: ");
                op = lerInteiro();

                switch (op) {
                    case 1: cadastrarProfissional(); break;
                    case 2: listarProfissionais(); break;
                    case 3: filtrarProfissionais(); break;
                    case 0: break;
                    default: System.out.println("Opcao invalida!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: Digite um numero valido.");
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }

    private static void cadastrarProfissional() {
        try {
            System.out.print("Nome: ");
            String nome = lerString();
            System.out.print("CPF: ");
            String cpf = lerString();
            System.out.print("Registro: ");
            String registro = lerString();
            System.out.print("Especialidade (clinica geral/fisioterapia/psicologia/nutricao): ");
            String especialidade = lerString();
            
            System.out.print("Valor consulta: ");
            double valor = lerDouble();
            
            Profissional profissional;
            switch (especialidade.toLowerCase()) {
                case "clinica geral":
                    profissional = new ClinicoGeral(nome, cpf, registro, valor);
                    break;
                case "fisioterapia":
                    profissional = new Fisioterapeuta(nome, cpf, registro, valor);
                    break;
                case "psicologia":
                    profissional = new Psicologo(nome, cpf, registro, valor);
                    break;
                case "nutricao":
                    profissional = new Nutricionista(nome, cpf, registro, valor);
                    break;
                default:
                    throw new Exception("Especialidade invalida!");
            }
            
            System.out.print("Dias de atendimento (separados por virgula): ");
            String diasInput = lerString();
            if (!diasInput.isEmpty()) {
                for (String dia : diasInput.split(",")) {
                    profissional.adicionarDiaDisponivel(dia.trim());
                }
            }
            
            servico.cadastrarProfissional(profissional);
            System.out.println("Profissional cadastrado com sucesso!");
            
        } catch (NumberFormatException e) {
            System.out.println("Erro: Digite um numero valido.");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void listarProfissionais() {
        List<Profissional> profissionais = servico.listarProfissionais();
        if (profissionais.isEmpty()) {
            System.out.println("Nenhum profissional cadastrado.");
            return;
        }
        for (Profissional p : profissionais) {
            System.out.println(p.exibirResumo());
            System.out.println("---");
        }
    }

    private static void filtrarProfissionais() {
        try {
            System.out.print("Especialidade: ");
            String esp = lerString();
            List<Profissional> profissionais = servico.buscarProfissionaisPorEspecialidade(esp);
            
            if (profissionais.isEmpty()) {
                System.out.println("Nenhum profissional encontrado para esta especialidade.");
                return;
            }
            for (Profissional p : profissionais) {
                System.out.println(p.exibirResumo());
                System.out.println("---");
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    // ---- CONSULTAS ----
    
    private static void menuConsultas() {
        int op = -1;
        while (op != 0) {
            try {
                System.out.println("\n--- CONSULTAS ---");
                System.out.println("1 - Agendar");
                System.out.println("2 - Cancelar");
                System.out.println("3 - Remarcar");
                System.out.println("4 - Listar todas");
                System.out.println("5 - Buscar por CPF");
                System.out.println("0 - Voltar");
                System.out.print("Opcao: ");
                op = lerInteiro();

                switch (op) {
                    case 1: agendarConsulta(); break;
                    case 2: cancelarConsulta(); break;
                    case 3: remarcarConsulta(); break;
                    case 4: listarConsultas(); break;
                    case 5: buscarConsultasPorPaciente(); break;
                    case 0: break;
                    default: System.out.println("Opcao invalida!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: Digite um numero valido.");
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }

    private static void agendarConsulta() {
        try {
            System.out.print("CPF do paciente: ");
            String cpf = lerString();
            System.out.print("CPF do profissional: ");
            String cpfProf = lerString();
            System.out.print("Data (DD/MM/AAAA): ");
            String data = lerString();
            System.out.print("Horario (HH:MM): ");
            String horario = lerString();
            System.out.print("Tipo (inicial/retorno/avaliacao): ");
            String tipo = lerString();
            
            servico.agendarConsulta(cpf, cpfProf, data, horario, tipo);
            System.out.println("Consulta agendada com sucesso!");
            
        } catch (PacienteNaoEncontradoException | ProfissionalNaoEncontradoException | 
                 PacienteInativoException | HorarioIndisponivelException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void cancelarConsulta() {
        try {
            System.out.print("CPF do paciente: ");
            String cpf = lerString();
            System.out.print("Data (DD/MM/AAAA): ");
            String data = lerString();
            System.out.print("Horario (HH:MM): ");
            String horario = lerString();
            
            servico.cancelarConsulta(cpf, data, horario);
            System.out.println("Consulta cancelada com sucesso!");
            
        } catch (PacienteNaoEncontradoException | ConsultaNaoEncontradaException | 
                 OperacaoInvalidaException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void remarcarConsulta() {
        try {
            System.out.print("CPF do paciente: ");
            String cpf = lerString();
            System.out.print("Data original (DD/MM/AAAA): ");
            String dataOrig = lerString();
            System.out.print("Horario original (HH:MM): ");
            String horarioOrig = lerString();
            System.out.print("Nova data (DD/MM/AAAA): ");
            String novaData = lerString();
            System.out.print("Novo horario (HH:MM): ");
            String novoHorario = lerString();
            
            servico.remarcarConsulta(cpf, dataOrig, horarioOrig, novaData, novoHorario);
            System.out.println("Consulta remarcada com sucesso!");
            
        } catch (PacienteNaoEncontradoException | ConsultaNaoEncontradaException | 
                 HorarioIndisponivelException | OperacaoInvalidaException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void listarConsultas() {
        List<Consulta> consultas = servico.listarConsultas();
        if (consultas.isEmpty()) {
            System.out.println("Nenhuma consulta cadastrada.");
            return;
        }
        for (Consulta c : consultas) {
            System.out.println(c.exibirResumo());
            System.out.println("---");
        }
    }

    private static void buscarConsultasPorPaciente() {
        try {
            System.out.print("CPF: ");
            String cpf = lerString();
            List<Consulta> consultas = servico.buscarConsultasPorPaciente(cpf);
            
            if (consultas.isEmpty()) {
                System.out.println("Nenhuma consulta encontrada para este paciente.");
                return;
            }
            for (Consulta c : consultas) {
                System.out.println(c.exibirResumo());
                System.out.println("---");
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    // ---- ATENDIMENTOS ----
    
    private static void menuAtendimentos() {
        int op = -1;
        while (op != 0) {
            try {
                System.out.println("\n--- ATENDIMENTOS ---");
                System.out.println("1 - Registrar atendimento");
                System.out.println("2 - Listar atendimentos");
                System.out.println("0 - Voltar");
                System.out.print("Opcao: ");
                op = lerInteiro();

                switch (op) {
                    case 1: registrarAtendimento(); break;
                    case 2: listarAtendimentos(); break;
                    case 0: break;
                    default: System.out.println("Opcao invalida!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: Digite um numero valido.");
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }

    private static void registrarAtendimento() {
        try {
            System.out.print("CPF do paciente: ");
            String cpf = lerString();
            System.out.print("Data da consulta (DD/MM/AAAA): ");
            String data = lerString();
            System.out.print("Horario da consulta (HH:MM): ");
            String horario = lerString();
            System.out.print("Observacoes: ");
            String observacoes = lerString();
            System.out.print("Diagnostico: ");
            String diagnostico = lerString();
            System.out.print("Data do atendimento (DD/MM/AAAA): ");
            String dataRegistro = lerString();
            
            System.out.print("Procedimentos (separados por virgula): ");
            String procsInput = lerString();
            List<String> procedimentos = new ArrayList<>();
            if (!procsInput.isEmpty()) {
                for (String proc : procsInput.split(",")) {
                    procedimentos.add(proc.trim());
                }
            }
            
            Atendimento atendimento = servico.registrarAtendimento(cpf, data, horario, observacoes, 
                                                                    diagnostico, dataRegistro, procedimentos);
            System.out.println("Atendimento registrado com sucesso!");
            System.out.println(atendimento.exibirResumo());
            
        } catch (PacienteNaoEncontradoException | ConsultaNaoEncontradaException | 
                 OperacaoInvalidaException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void listarAtendimentos() {
        List<Atendimento> atendimentos = servico.listarAtendimentos();
        if (atendimentos.isEmpty()) {
            System.out.println("Nenhum atendimento registrado.");
            return;
        }
        for (Atendimento a : atendimentos) {
            System.out.println(a.exibirResumo());
            System.out.println("---");
        }
    }

    // ---- PAGAMENTOS ----
    
    private static void menuPagamentos() {
        int op = -1;
        while (op != 0) {
            try {
                System.out.println("\n--- PAGAMENTOS ---");
                System.out.println("1 - Registrar pagamento");
                System.out.println("2 - Listar pagamentos");
                System.out.println("0 - Voltar");
                System.out.print("Opcao: ");
                op = lerInteiro();

                switch (op) {
                    case 1: registrarPagamento(); break;
                    case 2: listarPagamentos(); break;
                    case 0: break;
                    default: System.out.println("Opcao invalida!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: Digite um numero valido.");
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }

    private static void registrarPagamento() {
        try {
            System.out.print("CPF do paciente: ");
            String cpf = lerString();
            System.out.print("Data da consulta (DD/MM/AAAA): ");
            String data = lerString();
            System.out.print("Horario da consulta (HH:MM): ");
            String horario = lerString();
            System.out.print("Tipo (dinheiro/pix/cartao/convenio): ");
            String tipo = lerString();
            System.out.print("Valor base: ");
            double valor = lerDouble();
            
            int parcelas = 1;
            if (tipo.equalsIgnoreCase("cartao")) {
                System.out.print("Parcelas (1-6): ");
                parcelas = lerInteiro();
            }
            
            Pagamento pagamento = servico.registrarPagamento(cpf, data, horario, tipo, valor, parcelas);
            System.out.println("Pagamento registrado com sucesso!");
            System.out.println(pagamento.exibirResumo());
            
        } catch (PacienteNaoEncontradoException | ConsultaNaoEncontradaException | 
                 PagamentoInvalidoException | ConvenioNaoCobreException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Erro: Digite um numero valido.");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void listarPagamentos() {
        Set<Pagamento> pagamentos = servico.listarPagamentos();
        if (pagamentos.isEmpty()) {
            System.out.println("Nenhum pagamento registrado.");
            return;
        }
        for (Pagamento p : pagamentos) {
            System.out.println(p.exibirResumo());
            System.out.println("---");
        }
    }

    // ---- RELATORIOS ----
    
    private static void menuRelatorios() {
        int op = -1;
        while (op != 0) {
            try {
                System.out.println("\n--- RELATORIOS ---");
                System.out.println("1 - Relatorio Unificado (Ligacao Dinamica)");
                System.out.println("2 - Relatorio de Consultas");
                System.out.println("3 - Relatorio Financeiro");
                System.out.println("0 - Voltar");
                System.out.print("Opcao: ");
                op = lerInteiro();

                switch (op) {
                    case 1: 
                        // DEMONSTRA LIGAÇÃO DINÂMICA (R5)
                        servico.gerarRelatorioUnificado();
                        break;
                    case 2: 
                        servico.gerarRelatorioConsultas();
                        break;
                    case 3: 
                        servico.gerarRelatorioFinanceiro();
                        break;
                    case 0: break;
                    default: System.out.println("Opcao invalida!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: Digite um numero valido.");
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }

    // ---- EXPORTACAO ----
    
    private static void exportarDados() {
        try {
            servico.exportarDados();
            System.out.println("Dados exportados com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao exportar dados: " + e.getMessage());
        }
    }
}
