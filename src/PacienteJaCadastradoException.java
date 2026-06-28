public class PacienteJaCadastradoException extends RuntimeException {
    public PacienteJaCadastradoException(String cpf) {
        super("Paciente ja cadastrado!\n" +
                "Digite um CPF que não esteja cadastrado! ");
    }
}
class PacienteNaoEncontradoException extends RuntimeException{
    public PacienteNaoEncontradoException (String cpf){
        super("Paciente não encontrado!");
    }
}
class NenhumPacienteEncException extends RuntimeException{
    public NenhumPacienteEncException (){
        super("Nenhum paciente registrado!");
    }
}
class PacienteInativoException extends RuntimeException {
    public PacienteInativoException (String cpf) {
        super("Paciente está inativo!");
    }
}



class EspecialidadeInvalidaException extends RuntimeException{
    public EspecialidadeInvalidaException (String esp){
        super("Especialidade inválida!");
    }
}
class ProfissionalJaCadastradoException extends RuntimeException{
    public ProfissionalJaCadastradoException (String reg){
        super("Profissional ja cadastrado!\n" +
                "Digite um registro que não esteja cadastrado! ");
    }
}
class ProfissionalNaoEncontradoException extends RuntimeException {
    public ProfissionalNaoEncontradoException (String reg) {
        super("Profissional não encontrado!");
    }
}
class NenhumProfissionalEncException extends RuntimeException {
    public NenhumProfissionalEncException () {
        super("Nenhum profissional registrado!");
    }
}
class ProfissionalSemValorDefinidoException extends RuntimeException {
    public ProfissionalSemValorDefinidoException () {

    }
}
class HorarioIndisponivelException extends RuntimeException {
    public  HorarioIndisponivelException (String diaSemana) {
        super("Profissional não atende nesse dia.");
    }
}


class NenhumaConsultaEncException extends RuntimeException {
    public NenhumaConsultaEncException () {
        super("Nenhuma consulta encontrada!");
    }
}
