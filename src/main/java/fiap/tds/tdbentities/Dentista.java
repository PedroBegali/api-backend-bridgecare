package fiap.tds.tdbentities;

import java.time.LocalDate;

public class Dentista {

    private int idDentista;
    private String nmDentista;
    private LocalDate dtNascimento;
    private String sxDentista; // 'M' ou 'F'
    private String cpfDentista;
    private String croDentista;
    private String dsEspecialidade;
    private String stDentista; // 'A' ou 'I'

    public Dentista() {
    }

    // Construtor COM ID
    public Dentista(int idDentista, String nmDentista, LocalDate dtNascimento, String sxDentista, String cpfDentista, String croDentista, String dsEspecialidade, String stDentista) {
        this.idDentista = idDentista;
        this.nmDentista = nmDentista;
        this.dtNascimento = dtNascimento;
        this.sxDentista = sxDentista;
        this.cpfDentista = cpfDentista;
        this.croDentista = croDentista;
        this.dsEspecialidade = dsEspecialidade;
        this.stDentista = stDentista;
    }

    // Construtor SEM ID
    public Dentista(String nmDentista, LocalDate dtNascimento, String sxDentista, String cpfDentista, String croDentista, String dsEspecialidade, String stDentista) {
        this.nmDentista = nmDentista;
        this.dtNascimento = dtNascimento;
        this.sxDentista = sxDentista;
        this.cpfDentista = cpfDentista;
        this.croDentista = croDentista;
        this.dsEspecialidade = dsEspecialidade;
        this.stDentista = stDentista;
    }

    public int getIdDentista() { return idDentista; }
    public void setIdDentista(int idDentista) { this.idDentista = idDentista; }

    public String getNmDentista() { return nmDentista; }
    public void setNmDentista(String nmDentista) { this.nmDentista = nmDentista; }

    public LocalDate getDtNascimento() { return dtNascimento; }
    public void setDtNascimento(LocalDate dtNascimento) { this.dtNascimento = dtNascimento; }

    public String getSxDentista() { return sxDentista; }
    public void setSxDentista(String sxDentista) { this.sxDentista = sxDentista; }

    public String getCpfDentista() { return cpfDentista; }
    public void setCpfDentista(String cpfDentista) { this.cpfDentista = cpfDentista; }

    public String getCroDentista() { return croDentista; }
    public void setCroDentista(String croDentista) { this.croDentista = croDentista; }

    public String getDsEspecialidade() { return dsEspecialidade; }
    public void setDsEspecialidade(String dsEspecialidade) { this.dsEspecialidade = dsEspecialidade; }

    public String getStDentista() { return stDentista; }
    public void setStDentista(String stDentista) { this.stDentista = stDentista; }
}