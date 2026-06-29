public class Consulta implements Agendavel, Exportavel {
    private Paciente paciente;
    private Profissional profissional;
    private String data;
    private String horario;
    private String tipo;
    private String status;

    // SOBRECARGA: construtores com diferentes parâmetros
    public Consulta(Paciente paciente, Profissional profissional, String data, String horario) {
        this.paciente = paciente;
        this.profissional = profissional;
        setData(data);
        setHorario(horario);
        this.tipo = "inicial";
        this.status = "agendada";
    }

    public Consulta(Paciente paciente, Profissional profissional, String data, String horario, String tipo) {
        this.paciente = paciente;
        this.profissional = profissional;
        setData(data);
        setHorario(horario);
        setTipo(tipo);
        this.status = "agendada";
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Profissional getProfissional() {
        return profissional;
    }

    public void setProfissional(Profissional profissional) {
        this.profissional = profissional;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        if (data != null && !data.trim().isEmpty()) {
            this.data = data.trim();
        } else {
            this.data = "";
        }
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        if (horario != null && !horario.trim().isEmpty()) {
            this.horario = horario.trim();
        } else {
            this.horario = "";
        }
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        if (tipo != null && !tipo.trim().isEmpty()) {
            this.tipo = tipo.trim();
        } else {
            this.tipo = "inicial";
        }
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if (status != null) {
            String s = status.trim().toLowerCase();
            if (s.equals("agendada") || s.equals("realizada") || 
                s.equals("cancelada") || s.equals("remarcada")) {
                this.status = s;
            } else {
                this.status = "agendada";
            }
        } else {
            this.status = "agendada";
        }
    }

    public boolean isAgendada() {
        return status.equals("agendada");
    }

    public boolean isRealizada() {
        return status.equals("realizada");
    }

    public boolean isCancelada() {
        return status.equals("cancelada");
    }

    public boolean isRemarcada() {
        return status.equals("remarcada");
    }

    public boolean isAtiva() {
        return status.equals("agendada") || status.equals("remarcada");
    }

    // SOBRESCRITA: implementação da interface Agendavel
    @Override
    public void agendar() {
        if (status.equals("cancelada") || status.equals("")) {
            this.status = "agendada";
        }
    }

    @Override
    public String cancelar() {
        if (status.equals("realizada")) {
            return "Consulta ja realizada. Nao pode cancelar.";
        }
        if (status.equals("cancelada")) {
            return "Consulta ja cancelada.";
        }
        this.status = "cancelada";
        return "Consulta cancelada com sucesso.";
    }

    // SOBRECARGA: método cancelar com motivo adicional
    public String cancelar(String motivo) {
        String resultado = cancelar();
        if (motivo != null && !motivo.trim().isEmpty()) {
            return resultado + " Motivo: " + motivo.trim();
        }
        return resultado;
    }

    @Override
    public void remarcar() {
        if (status.equals("agendada")) {
            this.status = "remarcada";
        }
    }

    public void realizar() {
        if (status.equals("agendada") || status.equals("remarcada")) {
            this.status = "realizada";
        }
    }

    public void reativar() {
        if (status.equals("cancelada")) {
            this.status = "agendada";
        }
    }

    // SOBRESCRITA: implementação da interface Exportavel
    @Override
    public String exportarDados() {
        StringBuilder sb = new StringBuilder();
        sb.append("CONSULTA;\n");
        sb.append("Paciente: ").append(paciente != null ? paciente.getNome() : "N/A").append(";");
        sb.append("CPF: ").append(paciente != null ? paciente.getCpf() : "N/A").append(";");
        sb.append("Profissional: ").append(profissional != null ? profissional.getNome() : "N/A").append(";");
        sb.append("Registro: ").append(profissional != null ? profissional.getRegistroProfissional() : "N/A").append(";");
        sb.append("Data: ").append(data).append(";");
        sb.append("Horario: ").append(horario).append(";");
        sb.append("Tipo: ").append(tipo).append(";");
        sb.append("Status: ").append(status);
        return sb.toString();
    }

    public String exibirResumo() {
        StringBuilder sb = new StringBuilder();
        sb.append("Paciente: ").append(paciente != null ? paciente.getNome() : "N/A");
        sb.append(" | Profissional: ").append(profissional != null ? profissional.getNome() : "N/A");
        sb.append(" | Data: ").append(data);
        sb.append(" | Horário: ").append(horario);
        sb.append(" | Tipo: ").append(tipo);
        sb.append(" | Status: ").append(status);
        return sb.toString();
    }

    @Override
    public String toString() {
        return exibirResumo();
    }
}
