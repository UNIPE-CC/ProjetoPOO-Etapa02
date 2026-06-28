import java.util.HashSet;
import java.util.Set;

public class Convenio {
    private String nomeConvenio;
    private double cobertura;
    private Set<Paciente> pacientes;

    public Convenio (String nomeConvenio, double cobertura){
        this.nomeConvenio = nomeConvenio;
        this.cobertura = cobertura/100;
        this.pacientes = new HashSet<>();
    }

    public void setNomeConvenio(String nomeConvenio) {
        this.nomeConvenio = nomeConvenio;
    }
    public String getNomeConvenio() {
        return nomeConvenio;
    }

    public void setCobertura(double cobertura) {
        this.cobertura = cobertura/100;
    }
    public double getCobertura() {
        return cobertura;
    }

    public void addPaciente (Paciente paciente) {
        this.pacientes.add(paciente);
    }

    public void RemoverPacientePorCpf (String cpf) {
        for(Paciente paciente: this.pacientes) {
            if (paciente.getCpf().equals(cpf)) {
                this.pacientes.remove(paciente);
            }
        }
    }

    public void pesquisarPacientePorCpf (String cpf) {
        for(Paciente paciente: this.pacientes) {
            if (paciente.getCpf().equals(cpf)) {
                System.out.println(paciente.exibirResumo());
            }
        }
    }

    public void exibirResumo(){
        System.out.println("Convênio: "+ this.nomeConvenio+"\n" +
                "cobertura: "+ (this.cobertura * 100) +"% \n" +
                "Pacientes:");
        for(Paciente paciente : this.pacientes) {
            String nomePaciente = paciente.getNome();
            System.out.println(nomePaciente);
        }
    }
}