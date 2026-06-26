import java.util.HashSet;
import java.util.Set;

public class Convenio {
    private String nome;
    private double cobertura;
    private Set<Paciente> pacientes;

    public Convenio (String nome, double cobertura){
        this.nome = nome;
        this.cobertura = cobertura/100;
        this.pacientes = new HashSet<>();
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getNome() {
        return nome;
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
        System.out.println("Convênio: "+ this.nome+"\n" +
                "cobertura: "+ (this.cobertura * 100) +"% \n" +
                "Pacientes:");
        for(Paciente paciente : this.pacientes) {
            String nomePaciente = paciente.getNome();
            System.out.println(nomePaciente);
        }
    }
}
