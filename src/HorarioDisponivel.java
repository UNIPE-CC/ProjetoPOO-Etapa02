/**
 * Classe que representa um horário disponível de um profissional.
 * Um horário é composto por um dia da semana e um turno (manhã ou tarde).
 */
public class HorarioDisponivel {
    
    private static final String TURNO_MANHA = "manha";
    private static final String TURNO_TARDE = "tarde";
    
    private String diaSemana;
    private String turno;

    /**
     * Construtor que cria um horário disponível.
     * 
     * @param diaSemana Dia da semana do horário (ex: "segunda", "terca")
     * @param turno Turno do horário ("manha" ou "tarde")
     */
    public HorarioDisponivel(String diaSemana, String turno) {
        setDiaSemana(diaSemana);
        setTurno(turno);
    }
    
    public String getDiaSemana() {
        return diaSemana;
    }

    /**
     * Define o dia da semana com validação.
     * 
     * @param diaSemana Dia da semana a ser definido
     */
    public void setDiaSemana(String diaSemana) {
        if (diaSemana != null && !diaSemana.trim().isEmpty()) {
            this.diaSemana = diaSemana.trim();
        } else {
            this.diaSemana = "";
        }
    }

    public String getTurno() {
        return turno;
    }

    /**
     * Define o turno com validação.
     * Apenas "manha" ou "tarde" são aceitos.
     * 
     * @param turno Turno a ser definido
     */
    public void setTurno(String turno) {
        if (turno != null) {
            String turnoNormalizado = turno.trim().toLowerCase();
            if (turnoNormalizado.equals(TURNO_MANHA) || turnoNormalizado.equals(TURNO_TARDE)) {
                this.turno = turnoNormalizado;
                return;
            }
        }
        this.turno = "";
    }

    /**
     * Retorna um resumo formatado do horário disponível.
     * 
     * @return String formatada com dia e turno
     */
    public String exibirResumo() {
        return diaSemana + " (" + turno + ")";
    }

    /**
     * Compara dois horários disponíveis.
     * Dois horários são considerados iguais se tiverem o mesmo dia e turno.
     * 
     * @param obj Objeto a ser comparado
     * @return true se forem iguais, false caso contrário
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        HorarioDisponivel that = (HorarioDisponivel) obj;
        return diaSemana != null && diaSemana.equals(that.diaSemana) &&
               turno != null && turno.equals(that.turno);
    }

    @Override
    public int hashCode() {
        int result = diaSemana != null ? diaSemana.hashCode() : 0;
        result = 31 * result + (turno != null ? turno.hashCode() : 0);
        return result;
    }
    
    @Override
    public String toString() {
        return exibirResumo();
    }
}