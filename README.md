🏥 Sistema de Clínica Médica

📐 Diagrama de Classes UML

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
