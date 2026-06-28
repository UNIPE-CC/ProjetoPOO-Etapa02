🏥 Sistema de Clínica Médica

1. 📐 Diagrama de Classes UML

### 🔷 Interfaces

```

┌──────────────────────────┐      ┌───────────────────────────┐
│      <<interface>>       │      │      <<interface>>        │
│        Agendavel         │      │        Exportavel         │
│ ──────────────────────── │      │ ───────────────────────── │
│ + agendar(): void        │      │ + exportarDados(): String │
│ + cancelar(): String     │      └───────────────────────────┘
│ + remarcar(): void       │
└──────────────────────────┘

```

### 🔷 Hierarquia de Pessoa

```
                    ┌──────────────────────────────────────┐
                    │             <<abstract>>             │
                    │                Pessoa                │
                    │ ──────────────────────────────────── │
                    │ - nome: String                       │
                    │ - cpf: String                        │
                    │ - telefone: String                   │
                    │ - dataNascimento: String             │
                    │ ──────────────────────────────────── │
                    │ + getNome(): String                  │
                    │ + getCpf(): String                   │
                    │ + getTelefone(): String              │
                    │ + getDataNascimento(): String        │
                    │ + exibirDadosBasicos(): String       │
                    │ + exibirResumo(): String <<abstract>>│
                    └──────────────┬───────────────────────┘
                                   │
                 ┌─────────────────┼─────────────────┐
                 ▼                                   ▼
    ┌────────────────────────┐        ┌───────────────────────────────────────────────────────┐
    │        Paciente        │        │       Profissional <<abstract>>                       │
    │ ────────────────────── │        │ ───────────────────────────────────────────────────── │
    │ - idade: int           │        │ - especialidade: String                               │
    │ - convenio: Convenio   │        │ - registroProfissional: String                        │
    │ - ativo: boolean       │        │ - valorConsulta: double                               │
    │ ────────────────────── │        │ - horariosDisponiveis: List<HorarioDisponivel>        │
    │ + getIdade(): int      │        │ ───────────────────────────────────────────────────── │
    │ + getConvenio()        │        │ + getEspecialidade(): String                          │
    │ + isAtivo(): boolean   │        │ + getValorConsulta(): double                          │
    │ + desativar(): void    │        │ + atendeNoDia(String): boolean                        │
    │ + complementar(...)    │        │ + temHorarioDisponivel(...): boolean                  │
    │ + exibirResumo()       │        │ + atualizar(...): void                                │
    └────────────────────────┘        │ + registrarEspecifico(Atendimento): void <<abstract>> │
                                      │ + exibirResumo(): String                              │
                                      └───────────────────────────┬───────────────────────────┘
                                                                  │
                                           ┌──────────────┬───────┼───────┬─────────────────┐
                                           ▼              ▼               ▼                 ▼
                          ┌────────────────────────┐ ┌──────────────┐ ┌──────────────┐ ┌──────────────┐
                          │ ClinicoGeral           │ │Fisioterapeuta│ │  Psicologo   │ │Nutricionista │
                          │ ────────────────────── │ │ ──────────── │ │ ──────────── │ │ ──────────── │
                          │-encaminhamento: String │ │-totalSessoes │ │ -abordagem   │ │-planoAlimen- │
                          │ ────────────────────── │ │  Previstas   │ │  : String    │ │  tar: String │
                          │+registrarEspecifico()  │ │  : int       │ │              │ │              │
                          │+exibirResumo           │ │+registrarEspe│ │+registrarEspe│ │+registrarEspe│
                          └────────────────────────┘ │ cifico(...)  │ │ cifico(...)  │ │ cifico(...)  │
                                                     │+exibirResumo │ │+exibirResumo  │ │+exibirResumo │
                                                     └──────────────┘ └──────────────┘ └──────────────┘
```

> **Herança (3 níveis):** `Pessoa` → `Profissional` → Especialidades

---

### 🔷 Consulta

```
┌────────────────────────────────────────────────────────────┐
│                         Consulta                           │
│            (implements Agendavel, Exportavel)              │
│ ────────────────────────────────────────────────────────── │
│ - paciente: Paciente                                       │
│ - profissional: Profissional                               │
│ - data: String                                             │
│ - horario: String                                          │
│ - tipo: String                                             │
│ - status: String                                           │
│ ────────────────────────────────────────────────────────── │
│ + Consulta(Paciente, Profissional, String, String)         │
│ + Consulta(Paciente, Profissional, String, String, String) │
│ + agendar(): void                                          │
│ + cancelar(): String                                       │
│ + remarcar(): void                                         │
│ + realizar(): void                                         │
│ + exportarDados(): String                                  │
│ + exibirResumo(): String                                   │
│ + isAgendada(): boolean                                    │
│ + isRealizada(): boolean                                   │
│ + isCancelada(): boolean                                   │
│ + getPaciente(): Paciente                                  │
│ + getProfissional(): Profissional                          │
│ + getData(): String                                        │
│ + getHorario(): String                                     │
│ + getStatus(): String                                      │
│ + setStatus(String): void                                  │
└────────────────────────────────────────────────────────────┘
```
---

### 🔷 Atendimento, Prontuário e Convênio

```
┌──────────────────────────────────┐        ┌────────────────────────────────────┐
│           Atendimento            │        │              Convenio              │
│ ──────────────────────────────── │        │ ────────────────────────────────── │
│ - consulta: Consulta             │        │ - nomeConvenio: String             │
│ - prontuario: Prontuario ◄─COMP  │        │ - cobertura: double                │
│ ──────────────────────────────── │        │ - especialidadesCobertas: List     │
│ + Atendimento(Consulta,...)      │        │ - pacientes: Set<Paciente>         │
│ + getConsulta(): Consulta        │        │ ────────────────────────────────── │
│ + getProntuario(): Prontuario    │        │ + getNomeConvenio(): String        │
│ + adicionarObservacao(String)    │        │ + getCobertura(): double           │
│ + adicionarProcedimento(String)  │        │ + cobreEspecialidade(String): bool │
│ + exibirResumo(): String         │        │ + adicionarPaciente(Paciente)      │
│ + exportarDados(): String        │        │ + exibirResumo(): String           │
└──────────────┬───────────────────┘        └────────────────────────────────────┘
               │ COMPOSIÇÃO
               ▼
    ┌────────────────────────────────┐
    │           Prontuario           │
    │ ────────────────────────────── │
    │ - observacoes: String          │
    │ - diagnostico: String          │
    │ - procedimentos: List<String>  │
    │ - dataRegistro: String         │
    │ ────────────────────────────── │
    │ + getObservacoes(): String     │
    │ + getDiagnostico(): String     │
    │ + getProcedimentos(): List     │
    │ + adicionarProcedimento(String)│
    │ + adicionarProcedimentos(List) │
    │ + exibirResumo(): String       │
    │ + exportarDados(): String      │
    └────────────────────────────────┘
```
---

### 🔷 Pagamento

```
                    ┌───────────────────────────────┐
                    │           Pagamento           │
                    │ ───────────────────────────── │
                    │ - consulta: Consulta          │
                    │ - valorFinal: double          │
                    │ - parcelas: int               │
                    │ ───────────────────────────── │
                    │ + calcularValorFinal(): double│
                    │ + exibirResumo(): String      │
                    │ + getConsulta(): Consulta     │
                    │ + getValorFinal(): double     │
                    │ + getParcelas(): int          │
                    └────────────┬──────────────────┘
                                 │
              ┌──────────────────┼──────────────────┐
              ▼                  ▼                  ▼
    ┌──────────────────┐ ┌──────────────────┐ ┌────────────────────────┐
    │PagamentoDinheiro │ │ PagamentoCartao  │ │  PagamentoConvenio     │
    │ ──────────────── │ │ ──────────────── │ │ ────────────────────── │
    │ + calcularValor  │ │ + calcularValor  │ │ - convenio: Convenio   │
    │   Final(): double│ │   Final(): double│ │ - especialidade: String│
    │                  │ │                  │ │ + calcularValor        │
    │                  │ │                  │ │   Final(): double      │
    └──────────────────┘ └──────────────────┘ └────────────────────────┘
```
---

### 🔷 HorarioDisponivel

```
┌──────────────────────────────────┐
│        HorarioDisponivel         │
│ ──────────────────────────────── │
│ - diaSemana: String              │
│ - turno: String                  │
│ ──────────────────────────────── │
│ + getDiaSemana(): String         │
│ + getTurno(): String             │
│ + exibirResumo(): String         │
│ + equals(Object): boolean        │
│ + hashCode(): int                │
└──────────────────────────────────┘
```
---

### 🔷 ClinicaServico (Serviço Principal)

```
┌──────────────────────────────────────────────────────────────────────┐
│                          ClinicaServico                              │
│ ──────────────────────────────────────────────────────────────────── │
│ - pacientes: Map<String, Paciente>                                   │
│ - profissionais: Map<String, Profissional>                           │
│ - consultas: List<Consulta>                                          │
│ - atendimentos: List<Atendimento>                                    │
│ - pagamentos: Set<Pagamento>                                         │
│ - todasPessoas: List<Pessoa>                                         │
│ - cpfsCadastrados: Set<String>                                       │
│ ──────────────────────────────────────────────────────────────────── │
│ + cadastrarPaciente(Paciente): void                                  │
│ + buscarPacientePorCpf(String): Paciente                             │
│ + cadastrarProfissional(Profissional): void                          │
│ + buscarProfissionalPorCpf(String): Profissional                     │
│ + buscarProfissionalPorRegistro(String): Profissional                │
│ + agendarConsulta(String, String, String, String, String): void      │
│ + cancelarConsulta(String, String, String): void                     │
│ + remarcarConsulta(String, String, String, String, String): void     │
│ + registrarAtendimento(...): Atendimento                             │
│ + registrarPagamento(...): Pagamento                                 │
│ + listarPacientes(): void                                            │
│ + listarProfissionais(): void                                        │
│ + listarConsultas(): void                                            │
│ + getPacientes(): Map<String, Paciente>                              │
│ + getProfissionais(): Map<String, Profissional>                      │
│ + getConsultas(): List<Consulta>                                     │
│ + getAtendimentos(): List<Atendimento>                               │
│ + getPagamentos(): Set<Pagamento>                                    │
│ + getTodasPessoas(): List<Pessoa>                                    │
└──────────────────────────────────────────────────────────────────────┘
```
---

### 🔷 Relatorio

```
┌──────────────────────────────────────────┐
│                Relatorio                 │
│ ──────────────────────────────────────── │
│ + gerarRelatorioUnificado(List<Pessoa>)  │
│ + gerarRelatorioConsultas()              │
│ + gerarRelatorioPorProfissional()        │
│ + gerarResumoFinanceiro()                │
│ + exportarDados()                        │
└──────────────────────────────────────────┘
```
---

## 🔗 Relacionamentos

| Tipo | Origem | Destino |
|------|--------|---------|
| **Associação** | `Paciente` | `Convenio` |
| **Agregação** | `Profissional` | `List<HorarioDisponivel>` |
| **Composição** | `Atendimento` | `Prontuario` |
| **Implementação** | `Consulta` | `Agendavel`, `Exportavel` |
| **Herança** | `Paciente`, `Profissional` | `Pessoa` |
| **Herança** | `ClinicoGeral`, `Fisioterapeuta`, `Psicologo`, `Nutricionista` | `Profissional` |
| **Herança** | `PagamentoDinheiro`, `PagamentoCartao`, `PagamentoConvenio` | `Pagamento` |

## 2. Mapa de Aplicação dos Conceitos

### 2.1. Encapsulamento

| Classe | Atributos Privados | Getters/Setters | Validação em Setters |
|---|---|---|---|
| `Pessoa` | `nome`, `cpf`, `telefone`, `dataNascimento` | Sim | `setNome()` - não vazio, `setCpf()` - não vazio |
| `Paciente` | `idade`, `convenio`, `ativo` | Sim | `setIdade()` - 0 a 130 |
| `Profissional` | `especialidade`, `registro`, `valorConsulta`, `horarios` | Sim | `setValorConsulta()` - >= 0, `setEspecialidade()` - valida lista |
| `ClinicoGeral` | `encaminhamento` | Sim | `setEncaminhamento()` - não vazio |
| `Fisioterapeuta` | `totalSessoesPrevistas` | Sim | `setTotalSessoesPrevistas()` - >= 0 |
| `Psicologo` | `abordagem` | Sim | `setAbordagem()` - não vazio |
| `Nutricionista` | `planoAlimentar` | Sim | `setPlanoAlimentar()` - não vazio |
| `Consulta` | `paciente`, `profissional`, `data`, `horario`, `tipo`, `status` | Sim | `setData()`, `setHorario()`, `setTipo()`, `setStatus()` - não vazios |
| `Atendimento` | `consulta`, `prontuario` | Sim | `setConsulta()` - não nulo |
| `Prontuario` | `observacoes`, `diagnostico`, `procedimentos`, `dataRegistro` | Sim | `setObservacoes()`, `setDiagnostico()`, `setDataRegistro()` - não vazios |
| `Pagamento` | `consulta`, `valorFinal`, `parcelas` | Sim | `setValorFinal()` - >= 0, `setParcelas()` - 1 a 6 |
| `Convenio` | `nomeConvenio`, `cobertura`, `especialidades`, `pacientes` | Sim | `setNomeConvenio()` - não vazio, `setCobertura()` - 0 a 1 |

### 2.2. Modificadores de Acesso

| Modificador | Classe/Método | Justificativa |
|---|---|---|
| `private` | Todos os atributos | Encapsulamento — proteção do estado interno |
| `private` | `Relatorio.converterDataParaNumero()` | Método auxiliar interno |
| `private` | `Relatorio.estaNoIntervalo()` | Método auxiliar interno |
| `private` | `ClinicaServico.temConflito()` | Método auxiliar interno |
| `private` | `ClinicaServico.descobrirDiaSemana()` | Método auxiliar interno |
| `protected` | `Profissional.especialidadeValida()` | Acessível apenas pelas subclasses |
| `public` | Todos os métodos de serviço | Interface pública do sistema |
| `public` | Todos os métodos das classes de modelo | Acesso externo necessário |

### 2.3. Herança

```
Nível 1: Pessoa (abstract)
         ├── nome, cpf, telefone, dataNascimento
         └── exibirResumo() (abstract)

Nível 2: Paciente (extends Pessoa)
         ├── idade, convenio, ativo
         └── exibirResumo() (sobrescrito)

Nível 2: Profissional (extends Pessoa, abstract)
         ├── especialidade, registro, valorConsulta, horarios
         └── registrarEspecifico() (abstract)

Nível 3: ClinicoGeral (extends Profissional)
         ├── encaminhamento
         ├── exibirResumo() (sobrescrito)
         └── registrarEspecifico() (sobrescrito)

Nível 3: Fisioterapeuta (extends Profissional)
         ├── totalSessoesPrevistas
         ├── exibirResumo() (sobrescrito)
         └── registrarEspecifico() (sobrescrito)

Nível 3: Psicologo (extends Profissional)
         ├── abordagem
         ├── exibirResumo() (sobrescrito)
         └── registrarEspecifico() (sobrescrito)

Nível 3: Nutricionista (extends Profissional)
         ├── planoAlimentar
         ├── exibirResumo() (sobrescrito)
         └── registrarEspecifico() (sobrescrito)
```
### 2.4. Sobrecarga vs Sobrescrita

#### Sobrecarga (Compile-time Polymorphism)

| Classe | Método Sobrecarregado | Assinaturas |
|---|---|---|
| `Pessoa` | Construtores | `(String, String)`, `(String, String, String)`, `(String, String, String, String)` |
| `Paciente` | Construtores | `(String, String)`, `(String, String, int, String)`, `(String, String, int, String, Convenio)`, `(String, String, int, String, String, Convenio)` |
| `Paciente` | `complementar()` | `(int, String)`, `(int, String, Convenio)` |
| `Profissional` | Construtores | 6 variações |
| `Profissional` | `atualizar()` | `(String, double)`, `(String, double, List)` |
| `Consulta` | Construtores | `(Paciente, Profissional, String, String)`, `(Paciente, Profissional, String, String, String)` |
| `Pagamento` | Construtores | `(Consulta, double)`, `(Consulta, double, int)` |
| `Prontuario` | Construtores | `(String, String, String)`, `(String, String, List, String)` |
| `Convenio` | Construtores | `(String, double)`, `(String, double, List)` |

#### Sobrescrita (Runtime Polymorphism)

| Classe | Método Sobrescrito | `@Override` | Comportamento Específico |
|---|---|---|---|
| `Paciente` | `exibirResumo()` | Sim | Inclui idade, convênio, status |
| `ClinicoGeral` | `exibirResumo()` | Sim | Inclui encaminhamento |
| `ClinicoGeral` | `registrarEspecifico()` | Sim | Adiciona encaminhamento ao atendimento |
| `Fisioterapeuta` | `exibirResumo()` | Sim | Inclui total de sessões |
| `Fisioterapeuta` | `registrarEspecifico()` | Sim | Adiciona sessões ao atendimento |
| `Psicologo` | `exibirResumo()` | Sim | Inclui abordagem |
| `Psicologo` | `registrarEspecifico()` | Sim | Adiciona abordagem ao atendimento |
| `Nutricionista` | `exibirResumo()` | Sim | Inclui plano alimentar |
| `Nutricionista` | `registrarEspecifico()` | Sim | Adiciona plano ao atendimento |
| `PagamentoDinheiro` | `calcularValorFinal()` | Sim | Aplica 5% de desconto |
| `PagamentoCartao` | `calcularValorFinal()` | Sim | Aplica taxa para parcelas > 3 |
| `PagamentoConvenio` | `calcularValorFinal()` | Sim | Aplica cobertura do convênio |
| `Consulta` | `exportarDados()` | Sim | Formata dados da consulta |

### 2.5. Ligação Dinâmica

Exemplo 1: Relatorio.gerarRelatorioUnificado(List<Pessoa>)

```
for (Pessoa pessoa : pessoas) {
    System.out.println(pessoa.exibirResumo());  // LIGAÇÃO DINÂMICA
    // O método executado depende do tipo REAL do objeto:
    // - Se for Paciente → chama Paciente.exibirResumo()
    // - Se for ClinicoGeral → chama ClinicoGeral.exibirResumo()
    // - Se for Fisioterapeuta → chama Fisioterapeuta.exibirResumo()
    // - Se for Psicologo → chama Psicologo.exibirResumo()
    // - Se for Nutricionista → chama Nutricionista.exibirResumo()
}
```
Exemplo 2: Relatorio.gerarRelatorioPagamentos(List<Pagamento>)
```
for (Pagamento pagamento : pagamentos) {
    double valor = pagamento.calcularValorFinal();  // LIGAÇÃO DINÂMICA
    // O método executado depende do tipo REAL do objeto:
    // - Se for PagamentoDinheiro → desconto de 5%
    // - Se for PagamentoCartao → taxa por parcelas
    // - Se for PagamentoConvenio → cobertura do convenio
}
```
### 2.6. Classes Abstratas

| Classe | Métodos Abstratos | Métodos Concretos |
|---|---|---|
| `Pessoa` | `exibirResumo(): String` | `exibirDadosBasicos(): String`, getters/setters |
| `Profissional` | `registrarEspecifico(Atendimento): void` | `exibirResumo(): String`, getters/setters, `atendeNoDia()`, `atualizar()` |
| `Pagamento` | `calcularValorFinal(): double` | `exibirResumo(): String`, getters/setters |

### 2.7. Interfaces

| Interface | Métodos | Implementada por |
|---|---|---|
| `Agendavel` | `agendar(): void`, `cancelar(): String`, `remarcar(): void` | `Consulta` |
| `Exportavel` | `exportarDados(): String` | `Consulta`, `Atendimento`, `Prontuario` |

Uso Polimórfico via Interface

```
// Exportacao de dados
List<Exportavel> exportaveis = new ArrayList<>();
exportaveis.add(consulta);
exportaveis.add(atendimento);
exportaveis.add(prontuario);
Relatorio.exportarDados(exportaveis);  // Polimorfismo via interface
```

### 2.8. Relacionamentos

Associação: Paciente ↔ Convenio

```
public class Paciente extends Pessoa {
    private Convenio convenio;  // ASSOCIAÇÃO
    // Paciente conhece Convenio, ambos existem independentemente
}

public class Convenio {
    private Set<Paciente> pacientes;  // ASSOCIAÇÃO BIDIRECIONAL
    // Convenio conhece Pacientes
}
```

Composição: Atendimento → Prontuario

```
public class Atendimento {
    private Prontuario prontuario;  // COMPOSIÇÃO
    // Prontuario só existe dentro de Atendimento
    // Criado no construtor de Atendimento
    // Se Atendimento for removido, Prontuario também é
}

public Atendimento(Consulta consulta, String observacoes, String diagnostico, String dataRegistro) {
    this.consulta = consulta;
    this.prontuario = new Prontuario(observacoes, diagnostico, dataRegistro);  // CRIADO DENTRO
}
```

### 2.9. Exceções Personalizadas

| Exceção | Construtores | Onde é Lançada |
|---|---|---|
| `PacienteInativoException` | `(String)`, `(String, Throwable)` | `ClinicaServico.agendarConsulta()` |
| `PacienteNaoEncontradoException` | `(String)`, `(String, Throwable)` | `ClinicaServico.buscarPacientePorCpf()` |
| `ProfissionalNaoEncontradoException` | `(String)`, `(String, Throwable)` | `ClinicaServico.buscarProfissionalPorCpf()`, `buscarProfissionalPorRegistro()` |
| `HorarioIndisponivelException` | `(String)`, `(String, Throwable)` | `ClinicaServico.agendarConsulta()`, `remarcarConsulta()` |
| `ConsultaNaoEncontradaException` | `(String)`, `(String, Throwable)` | `ClinicaServico.cancelarConsulta()`, `remarcarConsulta()`, `registrarAtendimento()` |
| `OperacaoInvalidaException` | `(String)`, `(String, Throwable)` | `ClinicaServico.cancelarConsulta()`, `registrarAtendimento()` |
| `PagamentoInvalidoException` | `(String)`, `(String, Throwable)` | `ClinicaServico.registrarPagamento()` |
| `ConvenioNaoCobreException` | `(String)`, `(String, Throwable)` | `ClinicaServico.registrarPagamento()` |

Tratamento de Erros (try/catch/finally/throws)

```
try {
    servico.agendarConsulta(cpf, registro, data, horario, tipo);
} catch (PacienteNaoEncontradoException | ProfissionalNaoEncontradoException |
         PacienteInativoException | HorarioIndisponivelException e) {
    System.out.println("Erro: " + e.getMessage());
} catch (NumberFormatException e) {
    System.out.println("Erro: Valor numerico invalido.");
} catch (Exception e) {
    System.out.println("Erro: " + e.getMessage());
} finally {
    System.out.println("--- Operacao finalizada ---");
}
```

### 2.10. Coleções

| Coleção | Estrutura | Uso | Justificativa |
|---|---|---|---|
| `Map<String, Paciente>` | `HashMap` | `ClinicaServico.pacientes` | Busca por CPF — mais eficiente que percorrer lista |
| `Map<String, Profissional>` | `HashMap` | `ClinicaServico.profissionais` | Busca por CPF — mais eficiente |
| `List<Consulta>` | `ArrayList` | `ClinicaServico.consultas` | Ordem de inserção importa; acesso por índice necessário |
| `List<Atendimento>` | `ArrayList` | `ClinicaServico.atendimentos` | Ordem de inserção importa |
| `Set<Pagamento>` | `HashSet` | `ClinicaServico.pagamentos` | Apenas verificação de existência; sem ordem necessária |
| `List<Pessoa>` | `ArrayList` | `ClinicaServico.todasPessoas` | Relatório unificado — percorrer todos |
| `Set<String>` | `HashSet` | `ClinicaServico.cpfsCadastrados` | Controle de unicidade — `contains()` eficiente |
| `List<String>` | `ArrayList` | `Prontuario.procedimentos` | Ordem de inclusão importa |
| `List<String>` | `ArrayList` | `Convenio.especialidadesCobertas` | Ordem de inclusão importa |
| `List<HorarioDisponivel>` | `ArrayList` | `Profissional.horariosDisponiveis` | Ordem de inserção importa |

## 3. Jornadas de Usuário

### 3.1. Jornadas da AV1 (1 a 13)

| Jornada | Objetivo | Implementação |
|---|---|---|
| 1. Cadastro Simplificado | Cadastro rápido com nome e CPF | `Paciente(String nome, String cpf)` |
| 2. Cadastro Completo | Cadastro com todos os dados | `Paciente(String nome, String cpf, int idade, String telefone, Convenio convenio)` |
| 3. Complementação | Atualizar dados após cadastro | `Paciente.complementar(int idade, String telefone, Convenio convenio)` |
| 4. Cadastro Profissional | Cadastro de profissionais com especialidade | `ClinicoGeral`, `Fisioterapeuta`, `Psicologo`, `Nutricionista` |
| 5. Agendamento por Profissional | Escolher profissional específico | `ClinicaServico.agendarConsulta()` |
| 6. Agendamento por Especialidade | Busca automática por especialidade | `Main.agendarPorEspecialidade()` |
| 7. Tratamento de Conflitos | Evitar sobreposição de horários | `HorarioIndisponivelException` |
| 8. Registro de Atendimento | Registrar informações clínicas | `ClinicaServico.registrarAtendimento()` |
| 9. Cancelamento | Cancelar consulta com regras de multa | `ClinicaServico.cancelarConsulta()` |
| 10. Remarcação | Alterar data/horário | `ClinicaServico.remarcarConsulta()` |
| 11. Processamento de Pagamentos | Registrar pagamentos | `ClinicaServico.registrarPagamento()` |
| 12. Desativação | Desativar paciente | `Paciente.desativar()` |
| 13. Emissão de Relatórios | Relatórios gerenciais | `Relatorio.gerarRelatorioConsultas()`, `Relatorio.gerarResumoFinanceiro()` |

### 3.2. Novas Jornadas da AV2 (14 a 30)

| Jornada | Objetivo | Implementação |
|---|---|---|
| 14. Cadastro com Validação | Validação robusta de dados | `try/catch` com `NumberFormatException`, validação nos setters |
| 15. Relatório Unificado | Lista consolidada de todas as pessoas | `Relatorio.gerarRelatorioUnificado(List<Pessoa>)` — ligação dinâmica |
| 16. Cadastro Nutricionista | Atributo plano alimentar | `Nutricionista.planoAlimentar` |
| 17. Cadastro Psicólogo | Atributo abordagem terapêutica | `Psicologo.abordagem` |
| 18. Agendamento Inativo | Bloquear paciente inativo | `PacienteInativoException` |
| 19. Conflitos de Horário | Tratamento com sugestões | `HorarioIndisponivelException` |
| 20. Cobertura de Convênio | Validar cobertura de especialidade | `Convenio.cobreEspecialidade()`, `ConvenioNaoCobreException` |
| 21. Pagamento Dinheiro | 5% de desconto | `PagamentoDinheiro.calcularValorFinal()` |
| 22. Pagamento Cartão | Parcelamento com taxas | `PagamentoCartao.calcularValorFinal()` |
| 23. Pagamento Convênio | Cobertura percentual | `PagamentoConvenio.calcularValorFinal()` |
| 24. Atendimento com Prontuário | Composição `Atendimento` → `Prontuario` | `Atendimento.prontuario` (composição) |
| 25. Registro Especializado | Informações específicas do profissional | `Profissional.registrarEspecifico()` (abstrato) |
| 26. Exportação de Dados | Exportar dados para integração | `Exportavel.exportarDados()` |
| 27. Busca Instantânea | Busca por CPF usando `Map` | `ClinicaServico.pacientes` (`HashMap`) |
| 28. Controle de Unicidade | CPFs únicos usando `Set` | `ClinicaServico.cpfsCadastrados` (`HashSet`) |
| 29. Gestão de Horários | Reaproveitamento de horários | `HorarioDisponivel` (agregação) |
| 30. Tratamento de Erros | Encerramento seguro | `try/catch/finally` em todas as operações |

## 4. Exceções — Cenários Obrigatórios

| Cenário | Exceção | Local de Tratamento |
|---|---|---|
| Texto onde espera número | `NumberFormatException` | Todos os menus do `Main` |
| Agendar paciente inativo | `PacienteInativoException` | `Main.agendarComProfissional()`, `Main.agendarPorEspecialidade()` |
| CPF inexistente | `PacienteNaoEncontradoException` | `Main.complementarPaciente()`, `Main.buscarPaciente()` |
| Profissional inexistente | `ProfissionalNaoEncontradoException` | `Main.agendarComProfissional()`, `Main.relatorioPorProfissional()` |
| Horário ocupado | `HorarioIndisponivelException` | `Main.agendarComProfissional()`, `Main.remarcarConsulta()` |
| Dia não atende | `HorarioIndisponivelException` | `Main.agendarComProfissional()` |
| Cancelar consulta realizada | `OperacaoInvalidaException` | `Main.cancelarConsulta()` |
| Cancelar consulta cancelada | `OperacaoInvalidaException` | `Main.cancelarConsulta()` |
| Atendimento não agendado | `OperacaoInvalidaException` | `Main.registrarAtendimento()` |
| Tipo pagamento inválido | `PagamentoInvalidoException` | `Main.registrarPagamento()` |
| Parcelas fora do limite | `PagamentoInvalidoException` | `Main.registrarPagamento()` |
| Convênio não cobre | `ConvenioNaoCobreException` | `Main.registrarPagamento()` |
| Consulta não encontrada | `ConsultaNaoEncontradaException` | `Main.buscarConsultasPorPaciente()`, `Main.cancelarConsulta()` |
