public abstract class Profissional extends Pessoa {
    private String especialidade;
    private String registroProfissional;
    private double valorConsulta;
    private String[] diasDisponiveis;
    private int totalDias;

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public String getRegistroProfissional() {
        return registroProfissional;
    }

    public void setRegistroProfissional(String registroProfissional) {
        this.registroProfissional = registroProfissional;
    }

    public double getValorConsulta() {
        return valorConsulta;
    }

    public void setValorConsulta(double valorConsulta) {
        this.valorConsulta = valorConsulta;
    }

    public String[] getDiasDisponiveis() {
        return diasDisponiveis;
    }

    public void setDiasDisponiveis(String[] diasDisponiveis) {
        this.diasDisponiveis = diasDisponiveis;
    }

    public int getTotalDias() {
        return totalDias;
    }

    public void setTotalDias(int totalDias) {
        this.totalDias = totalDias;
    }

    // so nome e especialidade
    public Profissional(String nome, String especialidade) {
        super(nome, "");           //o cpf foi dedixado com variavel default
        this.especialidade = especialidade;
        this.registroProfissional = "";
        this.valorConsulta = 0;
        this.diasDisponiveis = new String[7];
        this.totalDias = 0;
    }

    public Profissional(String nome, String especialidade, String registroProfissional, double valorConsulta) {
        super(nome, "");
        this.especialidade = especialidade;
        this.registroProfissional = registroProfissional;
        this.valorConsulta = valorConsulta;
        this.diasDisponiveis = new String[7];
        this.totalDias = 0;
    }

    // construtor completo com dias
    public Profissional(String nome, String especialidade, String registroProfissional,double valorConsulta, String[] dias, int totalDias) {
        super(nome, "");
        this.especialidade = especialidade;
        this.registroProfissional = registroProfissional;
        this.valorConsulta = valorConsulta;
        this.diasDisponiveis = new String[7];
        this.totalDias = totalDias;
        for (int i = 0; i < totalDias; i++) {
            this.diasDisponiveis[i] = dias[i];
        }
    }

    public void atualizar(String registro, double valor) {
        this.registroProfissional = registro;
        this.valorConsulta = valor;
    }

    public void atualizar(String registro, double valor, String[] dias, int totalDias) {
        this.registroProfissional = registro;
        this.valorConsulta = valor;
        this.totalDias = totalDias;
        for (int i = 0; i < totalDias; i++) {
            this.diasDisponiveis[i] = dias[i];
        }
    }

    // verifica se o profissional atende naquele dia
    public boolean atendeNoDia(String dia) {
        for (int i = 0; i < totalDias; i++) {
            if (diasDisponiveis[i].equals(dia)) {
                return true;
            }
        }
        return false;
    }

    // valida as especialidades aceitas pela clinica
    public static boolean especialidadeValida(String esp) {
        if (esp.equals("clinica geral")) return true;
        if (esp.equals("fisioterapia")) return true;
        if (esp.equals("psicologia")) return true;
        if (esp.equals("nutricao")) return true;
        return false;
    }

    public abstract String registrarEspecifico();

    @Override
    public String exibirResumo() {
        return "Espec: " + especialidade + " | Reg: " + registroProfissional + " | Valor: R$" + valorConsulta;
    }
}
