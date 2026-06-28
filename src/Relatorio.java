import java.util.List;
import java.util.Map;
import java.util.Set;

public class Relatorio {

    // SOBRECARGA: gerarRelatorio com diferentes parâmetros
    
    // RELATÓRIO UNIFICADO - DEMONSTRA LIGAÇÃO DINÂMICA (R5)
    public static void gerarRelatorioUnificado(List<Pessoa> todasPessoas) {
        System.out.println("\n=== RELATORIO UNIFICADO DE PESSOAS ===");
        System.out.println("Total de pessoas cadastradas: " + todasPessoas.size());
        System.out.println("----------------------------------------");
        
        int totalPacientes = 0;
        int totalProfissionais = 0;
        
        for (Pessoa pessoa : todasPessoas) {
            // LIGAÇÃO DINÂMICA: o método executado depende do tipo REAL do objeto
            System.out.println(pessoa.exibirResumo());
            
            if (pessoa instanceof Paciente) {
                totalPacientes++;
            } else if (pessoa instanceof Profissional) {
                totalProfissionais++;
            }
            System.out.println("----------------------------------------");
        }
        
        System.out.println("Total de Pacientes: " + totalPacientes);
        System.out.println("Total de Profissionais: " + totalProfissionais);
    }

    public static void gerarRelatorioConsultas(List<Consulta> consultas) {
        System.out.println("\n=== RELATORIO GERAL DE CONSULTAS ===");
        if (consultas.isEmpty()) {
            System.out.println("Nenhuma consulta registrada.");
            return;
        }
        
        for (Consulta consulta : consultas) {
            System.out.println(consulta.exibirResumo());
            System.out.println("---");
        }
    }

    public static void gerarRelatorioConsultasPorProfissional(List<Consulta> consultas, 
                                                               Profissional profissional) {
        if (profissional == null) {
            System.out.println("Profissional nao encontrado.");
            return;
        }
        
        System.out.println("\n=== RELATORIO - " + profissional.getNome() + " ===");
        boolean encontrou = false;
        
        for (Consulta consulta : consultas) {
            if (consulta.getProfissional() != null && 
                consulta.getProfissional().getRegistroProfissional()
                    .equals(profissional.getRegistroProfissional())) {
                System.out.println(consulta.exibirResumo());
                System.out.println("---");
                encontrou = true;
            }
        }
        
        if (!encontrou) {
            System.out.println("Nenhuma consulta encontrada para este profissional.");
        }
    }

    public static void gerarRelatorioConsultasPorPeriodo(List<Consulta> consultas, 
                                                         String dataInicio, String dataFim) {
        System.out.println("\n=== RELATORIO - " + dataInicio + " a " + dataFim + " ===");
        boolean encontrou = false;
        
        for (Consulta consulta : consultas) {
            if (estaNoIntervalo(consulta.getData(), dataInicio, dataFim)) {
                System.out.println(consulta.exibirResumo());
                System.out.println("---");
                encontrou = true;
            }
        }
        
        if (!encontrou) {
            System.out.println("Nenhuma consulta encontrada no periodo.");
        }
    }

    public static void gerarRelatorioFinanceiro(List<Consulta> consultas, 
                                                 Set<Pagamento> pagamentos) {
        int realizadas = 0;
        int canceladas = 0;
        int agendadas = 0;
        double totalFaturado = 0.0;
        double totalDescontos = 0.0;
        
        for (Consulta consulta : consultas) {
            if (consulta.isRealizada()) {
                realizadas++;
            } else if (consulta.isCancelada()) {
                canceladas++;
            } else if (consulta.isAgendada() || consulta.isRemarcada()) {
                agendadas++;
            }
        }
        
        for (Pagamento pagamento : pagamentos) {
            totalFaturado += pagamento.getValorFinal();
            
            // Calcula desconto aproximado (para dinheiro e convenio)
            if (pagamento instanceof PagamentoDinheiro) {
                PagamentoDinheiro dinheiro = (PagamentoDinheiro) pagamento;
                totalDescontos += dinheiro.getValorDesconto();
            } else if (pagamento instanceof PagamentoConvenio) {
                PagamentoConvenio convenio = (PagamentoConvenio) pagamento;
                totalDescontos += convenio.getValorDesconto();
            }
        }
        
        System.out.println("\n=== RESUMO FINANCEIRO ===");
        System.out.println("Consultas Agendadas: " + agendadas);
        System.out.println("Consultas Realizadas: " + realizadas);
        System.out.println("Consultas Canceladas: " + canceladas);
        System.out.println("Total de Pagamentos: " + pagamentos.size());
        System.out.println("Total Faturado: R$" + String.format("%.2f", totalFaturado));
        System.out.println("Total em Descontos: R$" + String.format("%.2f", totalDescontos));
    }

    public static void gerarRelatorioCancelamentos(List<Consulta> consultas) {
        System.out.println("\n=== RELATORIO DE CANCELAMENTOS ===");
        int totalCanceladas = 0;
        
        for (Consulta consulta : consultas) {
            if (consulta.isCancelada()) {
                System.out.println(consulta.exibirResumo());
                System.out.println("---");
                totalCanceladas++;
            }
        }
        
        if (totalCanceladas == 0) {
            System.out.println("Nenhuma consulta cancelada.");
        } else {
            System.out.println("Total de cancelamentos: " + totalCanceladas);
        }
    }

    public static void exportarDados(List<? extends Exportavel> itens) {
        System.out.println("\n=== EXPORTACAO DE DADOS ===");
        if (itens.isEmpty()) {
            System.out.println("Nenhum dado para exportar.");
            return;
        }
        
        int contador = 1;
        for (Exportavel item : itens) {
            System.out.println("--- ITEM " + contador + " ---");
            System.out.println(item.exportarDados());
            System.out.println();
            contador++;
        }
        System.out.println("Total de itens exportados: " + (contador - 1));
    }

    private static boolean estaNoIntervalo(String data, String inicio, String fim) {
        if (data == null || data.isEmpty() || inicio == null || fim == null) {
            return false;
        }
        int valorData = converterDataParaNumero(data);
        int valorInicio = converterDataParaNumero(inicio);
        int valorFim = converterDataParaNumero(fim);
        return valorData >= valorInicio && valorData <= valorFim;
    }

    private static int converterDataParaNumero(String data) {
        try {
            String[] partes = data.split("/");
            if (partes.length == 3) {
                int dia = Integer.parseInt(partes[0]);
                int mes = Integer.parseInt(partes[1]);
                int ano = Integer.parseInt(partes[2]);
                return ano * 10000 + mes * 100 + dia;
            }
        } catch (NumberFormatException e) {
            // Data inválida
        }
        return 0;
    }
}
