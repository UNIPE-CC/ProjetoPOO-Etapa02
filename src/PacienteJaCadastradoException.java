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
        super("Nenhum paciente encontrado!");
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