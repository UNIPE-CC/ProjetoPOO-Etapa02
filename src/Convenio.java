import java.util.HashSet;
import java.util.Set;

public class Convenio {
    private String nomeConvenio;
    private double cobertura;
    private Set<Paciente> pacientes;
    private Set<String> especialidadesCobertas;

    // SOBRECARGA: construtores com diferentes parâmetros
    public Convenio(String nomeConvenio, double cobertura) {
        setNomeConvenio(nomeConvenio);
        setCobertura(cobertura);
        this.pacientes = new HashSet<>();
        this.especialidadesCobertas = new HashSet<>();
    }

    public Convenio(String nomeConvenio, double cobertura, Set<String> especialidades) {
        setNomeConvenio(nomeConvenio);
        setCobertura(cobertura);
        this.pacientes = new HashSet<>();
        this.especialidadesCobertas = new HashSet<>();
        if (especialidades != null) {
            for (String esp : especialidades) {
                adicionarEspecialidadeCoberta(esp);
            }
        }
    }

    public String getNomeConvenio() {
        return nomeConvenio;
    }

    public void setNomeConvenio(String nomeConvenio) {
        if (nomeConvenio != null && !nomeConvenio.trim().isEmpty()) {
            this.nomeConvenio = nomeConvenio.trim();
        } else {
            this.nomeConvenio = "";
        }
    }

    public double getCobertura() {
        return cobertura;
    }

    public void setCobertura(double cobertura) {
        if (cobertura >= 0 && cobertura <= 100) {
            this.cobertura = cobertura / 100.0;
        } else {
            this.cobertura = 0.0;
        }
    }

    public Set<String> getEspecialidadesCobertas() {
        return new HashSet<>(especialidadesCobertas);
    }

    public void adicionarEspecialidadeCoberta(String especialidade) {
        if (especialidade != null && !especialidade.trim().isEmpty()) {
            this.especialidadesCobertas.add(especialidade.trim().toLowerCase());
        }
    }

    public void removerEspecialidadeCoberta(String especialidade) {
        if (especialidade != null) {
            this.especialidadesCobertas.remove(especialidade.trim().toLowerCase());
        }
    }

    public boolean cobreEspecialidade(String especialidade) {
        if (especialidade == null || especialidade.trim().isEmpty()) {
            return false;
        }
        return especialidadesCobertas.contains(especialidade.trim().toLowerCase());
    }

    public void adicionarPaciente(Paciente paciente) {
        if (paciente != null) {
            this.pacientes.add(paciente);
            // Atualiza a referência no paciente se necessário
            if (paciente.getConvenio() != this) {
                paciente.setConvenio(this);
            }
        }
    }

    public void removerPaciente(Paciente paciente) {
        if (paciente != null) {
            this.pacientes.remove(paciente);
            if (paciente.getConvenio() == this) {
                paciente.setConvenio(null);
            }
        }
    }

    public void removerPacientePorCpf(String cpf) {
        if (cpf == null) return;
        for (Paciente paciente : this.pacientes) {
            if (paciente.getCpf().equals(cpf)) {
                removerPaciente(paciente);
                return;
            }
        }
    }

    public Paciente buscarPacientePorCpf(String cpf) {
        if (cpf == null) return null;
        for (Paciente paciente : this.pacientes) {
            if (paciente.getCpf().equals(cpf)) {
                return paciente;
            }
        }
        return null;
    }

    public boolean possuiPaciente(String cpf) {
        if (cpf == null) return false;
        for (Paciente paciente : this.pacientes) {
            if (paciente.getCpf().equals(cpf)) {
                return true;
            }
        }
        return false;
    }

    public int getTotalPacientes() {
        return pacientes.size();
    }

    public String exibirResumo() {
        StringBuilder sb = new StringBuilder();
        sb.append("Convênio: ").append(nomeConvenio);
        sb.append("\nCobertura: ").append(String.format("%.0f", cobertura * 100)).append("%");
        sb.append("\nEspecialidades Cobertas: ");
        if (especialidadesCobertas.isEmpty()) {
            sb.append("Nenhuma");
        } else {
            boolean primeiro = true;
            for (String esp : especialidadesCobertas) {
                if (!primeiro) {
                    sb.append(", ");
                }
                sb.append(esp);
                primeiro = false;
            }
        }
        sb.append("\nTotal de Pacientes: ").append(pacientes.size());
        return sb.toString();
    }

    @Override
    public String toString() {
        return exibirResumo();
    }
}
