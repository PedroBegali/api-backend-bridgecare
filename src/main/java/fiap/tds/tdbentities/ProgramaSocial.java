package fiap.tds.tdbentities;

public class ProgramaSocial {

    private int idProgramaSocial;
    private String nmProgramaSocial;

    public ProgramaSocial() {
    }

    public ProgramaSocial(int idProgramaSocial, String nmProgramaSocial) {
        this.idProgramaSocial = idProgramaSocial;
        this.nmProgramaSocial = nmProgramaSocial;
    }

    public ProgramaSocial(String nmProgramaSocial) {
        this.nmProgramaSocial = nmProgramaSocial;
    }

    public int getIdProgramaSocial() { return idProgramaSocial; }
    public void setIdProgramaSocial(int idProgramaSocial) { this.idProgramaSocial = idProgramaSocial; }

    public String getNmProgramaSocial() { return nmProgramaSocial; }
    public void setNmProgramaSocial(String nmProgramaSocial) { this.nmProgramaSocial = nmProgramaSocial; }
}