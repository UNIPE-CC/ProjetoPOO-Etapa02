public class CpfCadastrado extends RuntimeException {
    public CpfCadastrado(String cpf) {
        super("CPF ja cadastrado!\n" +
                "Digite um CPF que não esteja cadastrado! ");
    }
}
class CpfNaoEncontrado extends RuntimeException{
    public CpfNaoEncontrado (String cpf){
        super("CPF não encontrado!");
    }
}
