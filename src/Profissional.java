import java.util.ArrayList;
import java.util.List;

public abstract class Profissional extends Pessoa {
    private String especialidade;
    private String registroProfissional;
    private double valorConsulta;
    private List<String> diasDisponiveis;
    private List<HorarioDisponivel> horarios;

    // SOBRECARGA: construtores com diferentes parâmetros
    public Profissional(String nome, String cpf, String especialidade) {
        super(nome, cpf);
        setEspecialidade(especialidade);
        this.registroProfissional = "";
        this.valorConsulta = 0.0;
        this.diasDisponiveis = new ArrayList<>();
        this.horarios = new ArrayList<>();
    }

    public Profissional(String nome, String cpf, String especialidade, String registroProfissional) {
        super(nome, cpf);
        setEspecialidade(especialidade);
        setRegistroProfissional(registroProfissional);
        this.valorConsulta = 0.0;
        this.diasDisponiveis = new ArrayList<>();
        this.horarios = new ArrayList<>();
    }

    public Profissional(String nome, String cpf, String especialidade, String registroProfissional, double valorConsulta) {
        super(nome, cpf);
        setEspecialidade(especialidade);
        setRegistroProfissional(registroProfissional);
        setValorConsulta(valorConsulta);
        this.diasDisponiveis = new ArrayList<>();
        this.horarios = new ArrayList<>();
    }

    public Profissional(String nome, String cpf, String especialidade, String registroProfissional, 
                        double valorConsulta, List<String> dias) {
        super(nome, cpf);
        setEspecialidade(especialidade);
        setRegistroProfissional(registroProfissional);
        setValorConsulta(valorConsulta);
        this.diasDisponiveis = new ArrayList<>();
        this.horarios = new ArrayList<>();
        if (dias != null) {
            for (String dia : dias) {
                adicionarDiaDisponivel(dia);
            }
        }
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        if (especialidadeValida(especialidade)) {
            this.especialidade = especialidade;
        } else {
            this.especialidade = "";
        }
    }

    public String getRegistroProfissional() {
        return registroProfissional;
    }

    public void setRegistroProfissional(String registroProfissional) {
        if (registroProfissional != null && !registroProfissional.trim().isEmpty()) {
            this.registroProfissional = registroProfissional.trim();
        } else {
            this.registroProfissional = "";
        }
    }

    public double getValorConsulta() {
        return valorConsulta;
    }

    public void setValorConsulta(double valorConsulta) {
        if (valorConsulta >= 0) {
            this.valorConsulta = valorConsulta;
        } else {
            this.valorConsulta = 0.0;
        }
    }

    public List<String> getDiasDisponiveis() {
        return new ArrayList<>(diasDisponiveis);
    }

    public void adicionarDiaDisponivel(String dia) {
        if (dia != null && !dia.trim().isEmpty()) {
            this.diasDisponiveis.add(dia.trim());
        }
    }

    public void removerDiaDisponivel(String dia) {
        if (dia != null) {
            this.diasDisponiveis.remove(dia.trim());
        }
    }

    // AGREGAÇÃO: Profissional possui horários, mas eles existem independentemente
    public List<HorarioDisponivel> getHorarios() {
        return new ArrayList<>(horarios);
    }

    public void adicionarHorario(HorarioDisponivel horario) {
        if (horario != null && !this.horarios.contains(horario)) {
            this.horarios.add(horario);
        }
    }

    public void removerHorario(HorarioDisponivel horario) {
        if (horario != null) {
            this.horarios.remove(horario);
        }
    }

    public boolean atendeNoDia(String dia) {
        if (dia == null) return false;
        for (String diaDisponivel : diasDisponiveis) {
            if (diaDisponivel.equalsIgnoreCase(dia.trim())) {
                return true;
            }
        }
        return false;
    }

    public boolean horarioDisponivel(String dia, String turno) {
        for (HorarioDisponivel horario : horarios) {
            if (horario.getDiaSemana().equalsIgnoreCase(dia) && 
                horario.getTurno().equalsIgnoreCase(turno)) {
                return true;
            }
        }
        return false;
    }

    private boolean especialidadeValida(String esp) {
        if (esp == null) return false;
        String e = esp.trim().toLowerCase();
        return e.equals("clinica geral") ||
               e.equals("fisioterapia") ||
               e.equals("psicologia") ||
               e.equals("nutricao");
    }

    // SOBRECARGA: métodos atualizar com diferentes parâmetros
    public void atualizar(String registro, double valor) {
        setRegistroProfissional(registro);
        setValorConsulta(valor);
    }

    public void atualizar(String registro, double valor, List<String> dias) {
        setRegistroProfissional(registro);
        setValorConsulta(valor);
        this.diasDisponiveis.clear();
        if (dias != null) {
            for (String dia : dias) {
                adicionarDiaDisponivel(dia);
            }
        }
    }

    public abstract void registrarEspecifico(Atendimento atendimento);

    // SOBRESCRITA: redefine comportamento para exibir informações específicas
    @Override
    public String exibirResumo() {
        return "Nome: " + getNome() + " | CPF: " + getCpf() + 
               " | Espec: " + especialidade + " | Reg: " + registroProfissional + 
               " | Valor: R$" + String.format("%.2f", valorConsulta);
    }
}
