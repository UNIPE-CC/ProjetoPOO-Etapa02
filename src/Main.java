import java.util.*;

public class Main {

    static Map<String, Paciente> pacientesMap = new LinkedHashMap<>();

    static Map<String, Profissional> profissionalMap = new LinkedHashMap<>();

    static List<Consulta> consultas = new ArrayList<>();

    static Map<Integer,Atendimento> atendimentos = new LinkedHashMap<>();

    static Set<Pagamento> pagamentos = new HashSet<>();

    static Map<String, Double> multas = new LinkedHashMap<>();

    static Scanner sc = new Scanner(System.in);



    //da para colocar um tratamento de erro... numberFormaException, tratar entrada de String
    // da para colcar em todos os menus


    // Ao utilizar a collection: a melhor escolha será a "linkedHashMap", 
    //ela deixa ordenado, não deixa duplicar e tem busca por chave.
    // serão mais utilizadas no Paciente e Profissional, por cauisa das buscas. Os demais um list resolve.


    public static void main(String[] args) {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n=== CLINICA VIDAPLENA ===");
            System.out.println("1 - Pacientes");
            System.out.println("2 - Profissionais");
            System.out.println("3 - Consultas");
            System.out.println("4 - Atendimentos");
            System.out.println("5 - Pagamentos");
            System.out.println("6 - Relatorios");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            opcao = Integer.parseInt(sc.nextLine());

            switch (opcao) {
                case 1: menuPacientes(); break;
                case 2: menuProfissionais(); break;
                case 3: menuConsultas(); break;
                case 4: menuAtendimentos(); break;
                case 5: menuPagamentos(); break;
                case 6: menuRelatorios(); break;
                case 0: break;
                default: System.out.println("Opcao invalida!"); break;
            }
        }
        System.out.println("Sistema encerrado.");
    }

    // ---- PACIENTES ----

    public static void menuPacientes() {
        int op = -1;
        while (op != 0) {
            System.out.println("\n--- PACIENTES ---");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Complementar cadastro");
            System.out.println("3 - Buscar por CPF");
            System.out.println("4 - Listar todos");
            System.out.println("5 - Desativar");
            System.out.println("0 - Voltar");
            System.out.print("Opcao: ");
            op = Integer.parseInt(sc.nextLine());

            switch (op) {
                case 1: cadastrarPaciente(); break;
                case 2: complementarPaciente(); break;
                case 3: buscarPaciente(); break;
                case 4: listarPacientes(); break;
                case 5: desativarPaciente(); break;
                case 0: break;
                default: System.out.println("Opcao invalida!"); break;
            }
        }
    }


<<<<<<< HEAD
    public static void cadastrarPaciente() throws CpfCadastrado {
=======
    public static void cadastrarPaciente() throws PacienteJaCadastradoException {
>>>>>>> 30686b66f4a0d05a443022b8503c74946f17987c

            System.out.print("Nome: ");
            String nome = sc.nextLine();
            System.out.print("CPF: ");
            String cpf = sc.nextLine();

        try {
            // verifica se ja existe
            if (pacientesMap.containsKey(cpf)) {
<<<<<<< HEAD
                throw new CpfCadastrado(cpf);
            }
            System.out.print("Tipo (1-Minimo / 2-Com idade e tel / 3-Completo): ");
=======
                throw new PacienteJaCadastradoException(cpf);
            }
            System.out.println("Tipo (1-Minimo / 2-Com idade e tel / 3-Completo): ");
>>>>>>> 30686b66f4a0d05a443022b8503c74946f17987c
            int tipo = Integer.parseInt(sc.nextLine());

            if (tipo == 1) {
                Paciente paciente = new Paciente(nome, cpf);
                pacientesMap.put(cpf, paciente);

            } else if (tipo == 2) {
                System.out.print("Idade: ");
                int idade = Integer.parseInt(sc.nextLine());
                System.out.print("Telefone: ");
                String tel = sc.nextLine();

                Paciente paciente = new Paciente(nome, cpf, idade, tel);
                pacientesMap.put(cpf, paciente);

            } else {
                System.out.print("Idade: ");
                int idade = Integer.parseInt(sc.nextLine());
                System.out.print("Telefone: ");
                String tel = sc.nextLine();
                System.out.print("Convenio: ");
                String conv = sc.nextLine();

                Paciente paciente = new Paciente(nome, cpf, idade, tel, conv);
                pacientesMap.put(cpf, paciente);

            }
            System.out.println("Paciente cadastrado com sucesso!");
<<<<<<< HEAD
        } catch(CpfCadastrado e){
=======
        } catch(PacienteJaCadastradoException e){
>>>>>>> 30686b66f4a0d05a443022b8503c74946f17987c
            System.out.println(e);
        }
    }


<<<<<<< HEAD
    public static void complementarPaciente() throws CpfNaoEncontrado {
=======
    public static void complementarPaciente() throws PacienteNaoEncontradoException {
>>>>>>> 30686b66f4a0d05a443022b8503c74946f17987c
        //try e catch caso não ache o paciente
        System.out.print("CPF: ");
        String cpf = sc.nextLine();

        try{
            if (!pacientesMap.containsKey(cpf)) {
<<<<<<< HEAD
                throw new CpfNaoEncontrado(cpf);
            }
=======
                throw new PacienteNaoEncontradoException(cpf);
            }

                System.out.print("Vai informar convenio? (1-Nao / 2-Sim): ");
            int tipo = Integer.parseInt(sc.nextLine());
>>>>>>> 30686b66f4a0d05a443022b8503c74946f17987c

                System.out.print("Vai informar convenio? (1-Nao / 2-Sim): ");
            int tipo = Integer.parseInt(sc.nextLine());
            System.out.print("Idade: ");
            int idade = Integer.parseInt(sc.nextLine());
            System.out.print("Telefone: ");
            String tel = sc.nextLine();

            if(tipo == 1) {
                pacientesMap.get(cpf).complementar(idade, tel);
            } else {
                System.out.print("Convenio: ");
                String conv = sc.nextLine();

                pacientesMap.get(cpf).complementar(idade, tel, conv);
            }
            System.out.println("Cadastro atualizado!");

<<<<<<< HEAD
        } catch(CpfNaoEncontrado e){
=======
        } catch(PacienteNaoEncontradoException e){
>>>>>>> 30686b66f4a0d05a443022b8503c74946f17987c
            System.out.println(e);
        }
    }

    public static void buscarPaciente() throws PacienteNaoEncontradoException {
        System.out.print("CPF: ");
        String cpf = sc.nextLine();
<<<<<<< HEAD

        Paciente paciente = pacientesMap.get(cpf);
        
        if(paciente == null){
            System.out.println("Paciente nao encontrado");
            return;
        }
        System.out.println(paciente.exibirResumo());
=======
    try{
        if (!pacientesMap.containsKey(cpf)) {
            throw new PacienteNaoEncontradoException(cpf);
        }
        System.out.println(pacientesMap.get(cpf).exibirResumo());

    }catch (PacienteNaoEncontradoException e){
        System.out.println (e);
>>>>>>> 30686b66f4a0d05a443022b8503c74946f17987c
    }

    }

    public static void listarPacientes() throws NenhumPacienteEncException {
        try {
            if (pacientesMap.size() == 0) {
                throw new NenhumPacienteEncException();
            }

            for (Paciente paciente : pacientesMap.values()) {
                System.out.println(paciente.exibirResumo());
            }
        }catch (NenhumPacienteEncException e){
            System.out.println (e);
        }
    }
    public static void desativarPaciente() throws PacienteNaoEncontradoException {
        System.out.print("CPF: ");
        String cpf = sc.nextLine();
        try {
            if (!pacientesMap.containsKey(cpf)) {
                throw new PacienteNaoEncontradoException(cpf);
            }
            pacientesMap.get(cpf).desativar();
            System.out.println("Paciente desativado");

<<<<<<< HEAD
        Paciente paciente = pacientesMap.get(cpf);

        if(paciente == null){
            System.out.println("Paciente não encontrado");
            return;
        }
        paciente.desativar();
        System.out.println("Paciente desativado.");
=======
        } catch (PacienteNaoEncontradoException e){
            System.out.println (e);
        }
>>>>>>> 30686b66f4a0d05a443022b8503c74946f17987c
    }


    // ---- PROFISSIONAIS ----

    public static void menuProfissionais() {
        int op = -1;
        while (op != 0) {
            System.out.println("\n--- PROFISSIONAIS ---");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Atualizar cadastro");
            System.out.println("3 - Listar todos");
            System.out.println("4 - Filtrar por especialidade");
            System.out.println("0 - Voltar");
            System.out.print("Opcao: ");
            op = Integer.parseInt(sc.nextLine());

            switch (op) {
                case 1: cadastrarProfissional(); break;
                case 2: atualizarProfissional(); break;
                case 3: listarProfissionais(); break;
                case 4: filtrarProfissionais(); break;
                case 0: break;
                default: System.out.println("Opcao invalida!"); break;
            }
        }
    }

    public static void cadastrarProfissional() throws EspecialidadeInvalidaException, ProfissionalJaCadastradoException {
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.println("Registro: ");//mod
        String reg = sc.nextLine();
        System.out.print("Especialidade (clinica geral/fisioterapia/psicologia/nutricao): ");
        String esp = sc.nextLine();
        try {
            if (!Profissional.especialidadeValida(esp)) {
                throw new EspecialidadeInvalidaException(esp);
            }
            try{
                if(profissionalMap.containsKey(reg)){
                    throw new ProfissionalJaCadastradoException(reg);
                }

            System.out.print("Tipo (1-Minimo / 2-Com registro e valor / 3-Completo): ");
            int tipo = Integer.parseInt(sc.nextLine());

            if (tipo == 1) {
                if (esp.equals("clinica geral")) {
                    profissionalMap.put(reg, new ClinicoGeral(nome, reg));//mod

                } else if (esp.equals("fisioterapia")) {
                    profissionalMap.put(reg, new Fisioterapeuta(nome, reg));//mod

                } else if (esp.equals("psicologia")) {
                    profissionalMap.put(reg, new Psicologo(nome, reg));//mod

                } else if (esp.equals("nutricao")) {
                    profissionalMap.put(reg, new Nutricionista(nome, reg));// mod

                }

            } else if (tipo == 2) {
                System.out.print("Valor consulta: ");
                double valor = Double.parseDouble(sc.nextLine());

                if (esp.equals("clinica geral")) {
                    profissionalMap.put(reg, new ClinicoGeral(nome, reg, valor));//mod

                } else if (esp.equals("fisioterapia")) {
                    profissionalMap.put(reg, new Fisioterapeuta(nome, reg, valor));//mod

                } else if (esp.equals("psicologia")) {
                    profissionalMap.put(reg, new Psicologo(nome, reg, valor));//mod

                } else if (esp.equals("nutricao")) {
                    profissionalMap.put(reg, new Nutricionista(nome, reg, valor));//mod

                }

            } else {
                System.out.print("Valor consulta: ");
                double valor = Double.parseDouble(sc.nextLine());
                System.out.print("Quantos dias atende? ");
                int qtd = Integer.parseInt(sc.nextLine());
                String[] dias = new String[7];
                for (int i = 0; i < qtd; i++) {
                    System.out.print("Dia " + (i + 1) + ": ");
                    dias[i] = sc.nextLine();
                }

                if (esp.equals("clinica geral")) {
                    profissionalMap.put(reg, new ClinicoGeral(nome, reg, valor, dias, qtd));//mod

                } else if (esp.equals("fisioterapia")) {
                    profissionalMap.put(reg, new Fisioterapeuta(nome, reg, valor, dias, qtd));//mod

                } else if (esp.equals("psicologia")) {
                    profissionalMap.put(reg, new Psicologo(nome, reg, valor, dias, qtd));//mod

                } else if (esp.equals("nutricao")) {
                    profissionalMap.put(reg, new Nutricionista(nome, reg, valor, dias, qtd));//mod

                }
            }
            System.out.println("Profissional cadastrado!");
        }catch (ProfissionalJaCadastradoException e){
            System.out.println (e);}
        }catch (EspecialidadeInvalidaException e){
            System.out.println (e);
        }
    }

    public static void atualizarProfissional() {
        System.out.print("CPF do profissional: ");
        String cpf = sc.nextLine();

        System.out.print("Vai informar dias? (1-Nao / 2-Sim): ");
        int tipo = Integer.parseInt(sc.nextLine());

        System.out.print("Registro: ");
        String reg = sc.nextLine();
        System.out.print("Valor consulta: ");
        double valor = Double.parseDouble(sc.nextLine());

        if (tipo == 1) {
            profissionalMap.get(cpf).atualizar(reg, valor);
        } else {
            System.out.print("Quantos dias? ");
            int qtd = Integer.parseInt(sc.nextLine());
            String[] dias = new String[7];
            for (int i = 0; i < qtd; i++) {
                System.out.print("Dia " + (i+1) + ": ");
                dias[i] = sc.nextLine();
            }
            profissionalMap.get(cpf).atualizar(reg, valor, dias, qtd);
        }
        System.out.println("Profissional atualizado!");
    }


    public static void listarProfissionais() {
        if (profissionalMap.size() == 0) {
            System.out.println("Nenhum profissional cadastrado.");
            return;
        }
        for (Profissional profissional : profissionalMap.values()) {
            System.out.println(profissional.exibirResumo());
        }
    }

    public static void filtrarProfissionais() {
        System.out.print("Especialidade: ");
        String esp = sc.nextLine();

        for (Profissional profissional : profissionalMap.values()) {
            if (profissional.getEspecialidade().equals(esp)){
                System.out.println(profissional.exibirResumo());
            }
        }
    }

    // ---- CONSULTAS ----

    public static void menuConsultas() {
        int op = -1;
        while (op != 0) {
            System.out.println("\n--- CONSULTAS ---");
            System.out.println("1 - Agendar (escolher profissional)");
            System.out.println("2 - Agendar (busca por especialidade)");
            System.out.println("3 - Cancelar");
            System.out.println("4 - Remarcar");
            System.out.println("5 - Listar todas");
            System.out.println("6 - Buscar por CPF");
            System.out.println("0 - Voltar");
            System.out.print("Opcao: ");
            op = Integer.parseInt(sc.nextLine());

            switch (op) {
                case 1: agendarComProfissional(); break;
                case 2: agendarPorEspecialidade(); break;
                case 3: cancelarConsulta(); break;
                case 4: remarcarConsulta(); break;
                case 5: listarConsultas(); break;
                case 6: buscarConsultasPorPaciente(); break;
                case 0: break;
                default: System.out.println("Opcao invalida!"); break;
            }
        }
    }

    public static void agendarComProfissional() {
        System.out.print("CPF do paciente: ");
        String cpf = sc.nextLine();

        if (!pacientesMap.get(cpf).getAtivo()) {
            System.out.println("Paciente inativo. Nao e possivel agendar.");
            return;
        }
        System.out.print("Registro do profissional: ");
        String reg = sc.nextLine();

        Profissional profissional = profissionalMap.get(reg);
        if (profissional.getValorConsulta() == 0) {
            System.out.println("Profissional sem valor definido. Nao pode agendar.");
            return;
        }

        System.out.print("Data (DD/MM/AAAA): ");
        String data = sc.nextLine();
        System.out.print("Horario (HH:MM): ");
        String horario = sc.nextLine();

        // verifica dia da semana
        String diaSemana = descobrirDiaSemana(data);
        if (!profissional.atendeNoDia(diaSemana)) {
            System.out.println("Profissional nao atende nesse dia.");
            return;
        }

        // verifica conflito
        if (temConflito(reg, data, horario)) {
            System.out.println("Horario ocupado!");
            String sugestao = sugerirHorario(reg, data);
            if (sugestao.equals("")) {
                System.out.println("Nenhum horario disponivel nesse dia.");
                return;
            }
            System.out.println("Sugestao: " + sugestao);
            System.out.print("Aceita? (1-Sim / 2-Nao): ");
            int aceita = Integer.parseInt(sc.nextLine());
            if (aceita == 1) {
                horario = sugestao;
            } else {
                return;
            }

            System.out.print("Informar tipo? (1-Nao / 2-Sim): ");
            int infoTipo = Integer.parseInt(sc.nextLine());

            if (infoTipo == 1) {
                consultas.add(new Consulta(cpf, reg, data, horario));

            } else {
                System.out.print("Tipo (inicial/retorno/avaliacao): ");
                String tipo = sc.nextLine();
                consultas.add(new Consulta(cpf, reg, data, horario, tipo));

            }
            System.out.println("Consulta agendada com sucesso!");
        }
    }

    public static void agendarPorEspecialidade() {
        System.out.print("CPF do paciente: ");
        String cpf = sc.nextLine();

        if (!pacientesMap.get(cpf).getAtivo()) {
            System.out.println("Paciente inativo. Nao e possivel agendar.");
            return;
        }

        System.out.print("Especialidade: ");
        String esp = sc.nextLine();
        System.out.print("Data (DD/MM/AAAA): ");
        String data = sc.nextLine();
        System.out.print("Horario (HH:MM): ");
        String horario = sc.nextLine();

        String diaSemana = descobrirDiaSemana(data);

        // procura profissional disponivel
        //try e catch
        for (Profissional profissional : profissionalMap.values()) {
            if (profissional.getEspecialidade().equals(esp)
                    && profissional.getValorConsulta() > 0
                    && profissional.atendeNoDia(diaSemana)
                    && !temConflito(profissional.getCpf(), data, horario)) {

                consultas.add(new Consulta(cpf, profissional.getCpf(), data, horario));
                System.out.println("Consulta agendada com " + profissional.getNome() + "!");
            }
        }
    }


    public static void cancelarConsulta() {
        System.out.print("CPF: ");
        String cpf = sc.nextLine();
        System.out.print("Data (DD/MM/AAAA): ");
        String data = sc.nextLine();
        System.out.print("Horario (HH:MM): ");
        String horario = sc.nextLine();

        for (Consulta consulta : consultas) {
            if (consulta.cpfPaciente.equals(cpf)
                    && consulta.data.equals(data)
                    && consulta.horario.equals(horario)) {
                if (consulta.status.equals("realizada")) {
                    System.out.println("Consulta ja realizada. Nao pode cancelar.");
                    return;
                }

                if (consulta.status.equals("cancelada")) {
                    System.out.println("Consulta ja cancelada.");
                    return;
                }

                // calculo da multa
                System.out.print("Horario atual (HH:MM): ");
                String horaAtual = sc.nextLine();

                int hConsulta = Integer.parseInt(horario.substring(0, 2));
                int hAgora = Integer.parseInt(horaAtual.substring(0, 2));
                int diff = hConsulta - hAgora;

                if (diff < 2) {
                    System.out.println("Multa de R$50.00 aplicada!");
                    multas.put(cpf,  50.0);
                }

                System.out.print("Informar motivo? (1-Nao / 2-Sim): ");
                int temMotivo = Integer.parseInt(sc.nextLine());

                if (temMotivo == 1) {
                    consulta.cancelar();
                } else {
                    System.out.print("Motivo: ");
                    String motivo = sc.nextLine();
                    String msg = consulta.cancelar(motivo);
                    System.out.println(msg);
                }
            }
            System.out.println("Consulta cancelada.");
        }
    }


    public static void remarcarConsulta() {
        System.out.print("CPF: ");
        String cpf = sc.nextLine();
        System.out.print("Data original (DD/MM/AAAA): ");
        String dataOrig = sc.nextLine();
        System.out.print("Horario original (HH:MM): ");
        String horarioOrig = sc.nextLine();

        System.out.print("Apenas trocar horario no mesmo dia? (1-Sim / 2-Nao): ");
        int tipo = Integer.parseInt(sc.nextLine());

        String novaData;
        String novoHorario;
        if (tipo == 1) {
            novaData = dataOrig;
            System.out.print("Novo horario: ");
            novoHorario = sc.nextLine();
        } else {
            System.out.print("Nova data (DD/MM/AAAA): ");
            novaData = sc.nextLine();
            System.out.print("Novo horario (HH:MM): ");
            novoHorario = sc.nextLine();
        }

        for (Consulta consulta : consultas) {
            if (consulta.cpfPaciente.equals(cpf)
                    && consulta.data.equals(dataOrig)
                    && consulta.horario.equals(horarioOrig)
                    && consulta.status.equals("agendada")) {

                String registroProf = consulta.registroProfissional;

                // se mudou de dia, verifica se prof atende
                if (tipo == 2) {
                    String dia = descobrirDiaSemana(novaData);
                    if (!profissionalMap.get(registroProf).atendeNoDia(dia)) {
                        System.out.println("Profissional nao atende nesse dia.");
                        return;
                    }
                }

                if (temConflito(registroProf, novaData, novoHorario)) {
                    System.out.println("Horario ocupado. Nao foi possivel remarcar.");
                    return;
                }

                consulta.remarcar();
                consulta = new Consulta(cpf, registroProf, novaData, novoHorario, consulta.tipo);
                System.out.println("Consulta remarcada com sucesso!");

            }
        }
    }


    public static void listarConsultas() {
        if (consultas.size() == 0) {
            System.out.println("Nenhuma consulta.");
            return;
        }
        int cont = 1;
        for (Consulta consulta : consultas) {
            System.out.println("[" + cont + "] " + consulta.exibirResumo());
            cont++;
        }
    }

    public static void buscarConsultasPorPaciente() {
        System.out.print("CPF: ");
        String cpf = sc.nextLine();
        boolean achou = false;
        int cont = 1;
        for (Consulta consulta : consultas) {
            System.out.println("[" + cont + "] " + consulta.exibirResumo());
            cont ++;
            achou = true;
        }
        if (!achou) {
            System.out.println("Nenhuma consulta encontrada.");
        }
    }

    // verifica se ja tem consulta nesse horario com esse profissional
    public static boolean temConflito(String registroProfissional, String data, String horario) {
        for (Consulta consulta : consultas) {
            if (consulta.registroProfissional.equals(registroProfissional)
                    && consulta.data.equals(data)
                    && consulta.horario.equals(horario)
                    && consulta.status.equals("agendada")) {
                return true;
            }
        }
        return false;
    }

    // sugere proximo horario livre (de hora em hora, 08h ate 18h)
    public static String sugerirHorario(String reg, String data) {
        for (int h = 8; h <= 18; h++) {
            String teste;
            if (h < 10) {
                teste = "0" + h + ":00";
            } else {
                teste = h + ":00";
            }
            if (!temConflito(reg, data, teste)) {
                return teste;
            }
        }
        return "";
    }

    // descobre dia da semana a partir da data
    // operação opcional
    public static String descobrirDiaSemana(String data) {
        int dia = Integer.parseInt(data.substring(0, 2));
        int mes = Integer.parseInt(data.substring(3, 5));
        int ano = Integer.parseInt(data.substring(6, 10));

        // ajuste pra formula funcionar com janeiro e fevereiro
        if (mes < 3) {
            mes = mes + 12;
            ano = ano - 1;
        }

        int k = ano % 100;
        int j = ano / 100;

        // formula de zeller
        int resultado = (dia + (13 * (mes + 1)) / 5 + k + k/4 + j/4 - 2*j) % 7;
        if (resultado < 0) resultado = resultado + 7;

        // 0=sabado, 1=domingo, 2=segunda...
        String[] nomes = {"sabado", "domingo", "segunda", "terca", "quarta", "quinta", "sexta"};
        //System.out.println("DEBUG dia semana: " + nomes[resultado]); // pra testar
        return nomes[resultado];
    }

    // ---- ATENDIMENTOS ----

    public static void menuAtendimentos() {
        int op = -1;
        while (op != 0) {
            System.out.println("\n--- ATENDIMENTOS ---");
            System.out.println("1 - Registrar atendimento");
            System.out.println("0 - Voltar");
            System.out.print("Opcao: ");
            op = Integer.parseInt(sc.nextLine());

            if (op == 1) registrarAtendimento();
        }
    }


    public static void registrarAtendimento() {

        System.out.print("Indice da consulta: ");
        int idxConsulta = Integer.parseInt(sc.nextLine());

        if (idxConsulta < 0 || idxConsulta >= consultas.size()) {
            System.out.println("Indice invalido.");
            return;
        }
        if (!consultas.get(idxConsulta).status.equals("agendada")) {
            System.out.println("So pode registrar atendimento em consulta agendada.");
            return;
        }

        System.out.print("Observacoes: ");
        String obs = sc.nextLine();

            System.out.print("Tipo de registro (1-So obs / 2-Com diagnostico / 3-Completo): ");
            int tipo = Integer.parseInt(sc.nextLine());

            Atendimento atendimento = new Atendimento();
            if (tipo == 1) {
                atendimento = new Atendimento(idxConsulta, obs);
                atendimentos.put(idxConsulta ,atendimento);


            } else if (tipo == 2) {
                System.out.print("Diagnostico: ");
                String diag = sc.nextLine();
                atendimento = new Atendimento(idxConsulta, obs, diag);
                atendimentos.put(idxConsulta, atendimento);

            } else {
                System.out.print("Diagnostico: ");
                String diag = sc.nextLine();

                System.out.print("Como informar procedimentos? (1-Um por vez / 2-Todos de uma vez): ");
                int forma = Integer.parseInt(sc.nextLine());

                String[] procs = new String[10];
                int qtdProcs = 0;

                if (forma == 1) {
                    String proc = "";
                    while (!proc.equals("fim") && qtdProcs < 10) {
                        System.out.print("Procedimento (ou 'fim'): ");
                        proc = sc.nextLine();
                        if (!proc.equals("fim")) {
                            procs[qtdProcs] = proc;
                            qtdProcs++;
                        } else {
                            System.out.print("Quantos? ");
                            qtdProcs = Integer.parseInt(sc.nextLine());
                            if (qtdProcs > 10) qtdProcs = 10;
                            for (int i = 0; i < qtdProcs; i++) {
                                System.out.print("Proc " + (i + 1) + ": ");
                                procs[i] = sc.nextLine();
                            }
                        }
                        atendimento = new Atendimento(idxConsulta, obs, diag, procs, qtdProcs);
                        atendimentos.put(idxConsulta, atendimento);

                    }
                }
            }
        consultas.get(idxConsulta).realizar();
        System.out.println("\n--- RESUMO ---");
        System.out.println(atendimento.exibirResumo());
        System.out.println("Consulta marcada como realizada.");
    }


    // ---- PAGAMENTOS ----

    public static void menuPagamentos() {
        int op = -1;
        while (op != 0) {
            System.out.println("\n--- PAGAMENTOS ---");
            System.out.println("1 - Pagamento direto");
            System.out.println("2 - Pagamento automatico");
            System.out.println("3 - Listar pagamentos");
            System.out.println("0 - Voltar");
            System.out.print("Opcao: ");
            op = Integer.parseInt(sc.nextLine());

            switch (op) {
                case 1: pagamentoDireto(); break;
                case 2: pagamentoAutomatico(); break;
                case 3: listarPagamentos(); break;
                case 0: break;
                default: System.out.println("Opcao invalida!"); break;
            }
        }
    }

    public static void pagamentoDireto() {
        System.out.print("Indice da consulta: ");
        int idxConsulta = Integer.parseInt(sc.nextLine());

        if (idxConsulta < 0 || idxConsulta >= consultas.size()) {
            System.out.println("Indice invalido.");
            return;
        }

        System.out.print("Valor: ");
        double valor = Double.parseDouble(sc.nextLine());
        System.out.print("Tipo (dinheiro/cartao/convenio): ");
        String tipoPag = sc.nextLine();

        if (tipoPag.equals("cartao")) {
            System.out.print("Parcelas (1 a 3): ");
            int parc = Integer.parseInt(sc.nextLine());
            if (parc < 1) parc = 1;
            if (parc > 3) parc = 3;
            pagamentos.add(new PagamentoCartao(idxConsulta, valor, parc));
            if (parc > 1) {
                double vlrParc = Math.round((valor / parc) * 100.0) / 100.0;
                System.out.println("Pagamento em " + parc + "x de R$" + vlrParc);
            }
        } else {
            pagamentos.add(new PagamentoCartao(idxConsulta, valor));
        }
        System.out.println("Pagamento registrado!");
    }

    public static void pagamentoAutomatico() {
        System.out.print("Indice da consulta: ");
        int idxConsulta = Integer.parseInt(sc.nextLine());

        if (idxConsulta < 0 || idxConsulta >= consultas.size()) {
            System.out.println("Indice invalido.");
            return;
        }

        // obtem valor do profissional
        String regProf = consultas.get(idxConsulta).registroProfissional;
        double valorBase = profissionalMap.get(regProf).getValorConsulta();

        // verifica convenio e tipo
        String cpfPac = consultas.get(idxConsulta).cpfPaciente;

        boolean temConvenio = !pacientesMap.get(cpfPac).getConvenioNome().equals("");
        boolean ehRetorno = consultas.get(idxConsulta).tipo.equals("retorno");

        double desconto = 0;
        if (ehRetorno) desconto = desconto + 20;
        if (temConvenio) desconto = desconto + 40;

        System.out.print("Tem multa pendente? (1-Nao / 2-Sim): ");
        int temMulta = Integer.parseInt(sc.nextLine());
        double valorMulta = 0;

        double valorFinal;
        if (temMulta == 1 && desconto == 0) {
            valorFinal = Pagamento.calcularValor(valorBase);
        } else if (temMulta == 1) {
            valorFinal = Pagamento.calcularValor(valorBase, desconto);
        } else {
            System.out.print("Valor da multa: ");
            valorMulta = Double.parseDouble(sc.nextLine());
            valorFinal = Pagamento.calcularValor(valorBase, desconto, valorMulta);
        }

        // mostra detalhes
        System.out.println("Valor base: R$" + valorBase);
        System.out.println("Desconto: " + desconto + "%");
        if (valorMulta > 0) System.out.println("Multa: R$" + valorMulta);
        double vlrFinalArredondado = Math.round(valorFinal * 100.0) / 100.0;
        System.out.println("Valor final: R$" + vlrFinalArredondado);

        System.out.print("Tipo (dinheiro/cartao/convenio): ");
        String tipoPag = sc.nextLine();

        if (tipoPag.equals("cartao")) {
            System.out.print("Parcelas (1 a 3): ");
            int parc = Integer.parseInt(sc.nextLine());
            if (parc < 1) parc = 1;
            if (parc > 3) parc = 3;
            pagamentos.add(new PagamentoCartao(idxConsulta, valorFinal, parc));
            double vlrParc = Math.round((valorFinal / parc) * 100.0) / 100.0;
            System.out.println("Pagamento em " + parc + "x de R$" + vlrParc);
        } else {
            pagamentos.add(new PagamentoCartao(idxConsulta, valorFinal));
        }
        System.out.println("Pagamento registrado!");
    }

    public static void listarPagamentos() {
        if (pagamentos.size() == 0) {
            System.out.println("Nenhum pagamento registrado.");
            return;
        }
        for (Pagamento pagamento : pagamentos) {
            System.out.println(pagamento.exibirResumo());
        }
    }

    // ---- RELATORIOS ----

    public static void menuRelatorios() {
        int op = -1;
        while (op != 0) {
            System.out.println("\n--- RELATORIOS ---");
            System.out.println("1 - Geral");
            System.out.println("2 - Por profissional");
            System.out.println("3 - Por periodo");
            System.out.println("4 - Resumo financeiro");
            System.out.println("0 - Voltar");
            System.out.print("Opcao: ");
            op = Integer.parseInt(sc.nextLine());

            switch (op) {
                case 1:
                    Relatorio.gerarRelatorio(consultas, atendimentos);
                    break;
                case 2:
                    System.out.print("Registro do profissional: ");
                    String reg = sc.nextLine();
                    Profissional profissional = profissionalMap.get(reg);
                    Relatorio.gerarRelatorio(consultas, atendimentos, profissional);
                    break;
                case 3:
                    System.out.print("Data inicio (DD/MM/AAAA): ");
                    String ini = sc.nextLine();
                    System.out.print("Data fim (DD/MM/AAAA): ");
                    String fim = sc.nextLine();
                    Relatorio.gerarRelatorio(consultas, atendimentos, ini, fim);
                    break;
                case 4:
                    Relatorio.gerarResumoFinanceiro(consultas, pagamentos, multas);
                    break;
                case 0: break;
                default: System.out.println("Opcao invalida!"); break;
            }
        }
    }
}
