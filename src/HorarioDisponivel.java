public class HorarioDisponivel {
    private String diaSemana;
    private String turno;

    public HorarioDisponivel(String diaSemana, String turno) {
        setDiaSemana(diaSemana);
        setTurno(turno);
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        if (diaSemana != null && !diaSemana.trim().isEmpty()) {
            this.diaSemana = diaSemana;
        }
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        if (turno != null && (turno.equalsIgnoreCase("manha") || turno.equalsIgnoreCase("tarde"))) {
            this.turno = turno.toLowerCase();
        }
    }

    public String exibirResumo() {
        return diaSemana + " (" + turno + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        HorarioDisponivel that = (HorarioDisponivel) obj;
        return diaSemana.equals(that.diaSemana) && turno.equals(that.turno);
    }

    @Override
    public int hashCode() {
        return diaSemana.hashCode() + turno.hashCode();
    }
}