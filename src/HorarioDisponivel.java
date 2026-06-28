public class HorarioDisponivel {
    private static final String TURNO_MANHA = "manha";
    private static final String TURNO_TARDE = "tarde";
    
    private String diaSemana;
    private String turno;

    // SOBRECARGA: construtores com diferentes parâmetros
    public HorarioDisponivel() {
        this.diaSemana = "";
        this.turno = "";
    }

    public HorarioDisponivel(String diaSemana, String turno) {
        setDiaSemana(diaSemana);
        setTurno(turno);
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        if (diaSemana != null && !diaSemana.trim().isEmpty()) {
            this.diaSemana = diaSemana.trim().toLowerCase();
        } else {
            this.diaSemana = "";
        }
    }

    public String getTurno() {
        return turno;
    }

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

    public boolean isManha() {
        return turno.equals(TURNO_MANHA);
    }

    public boolean isTarde() {
        return turno.equals(TURNO_TARDE);
    }

    public boolean isValid() {
        return diaSemana != null && !diaSemana.isEmpty() && 
               turno != null && !turno.isEmpty();
    }

    public String exibirResumo() {
        if (isValid()) {
            return diaSemana + " (" + turno + ")";
        }
        return "Horário não definido";
    }

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
