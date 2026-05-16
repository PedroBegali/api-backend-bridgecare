package fiap.tds.tdbentities;

import java.time.LocalDate;
import java.time.LocalTime;

public class Consulta {

    private int idConsulta;
    private String dsProntuario; // CLOB
    private LocalDate dtConsulta;
    private LocalTime hrConsulta;
    private int idBeneficiario; // FK
    private int idDentista; // FK

    public Consulta() {
    }

    public Consulta(int idConsulta, String dsProntuario, LocalDate dtConsulta, LocalTime hrConsulta, int idBeneficiario, int idDentista) {
        this.idConsulta = idConsulta;
        this.dsProntuario = dsProntuario;
        this.dtConsulta = dtConsulta;
        this.hrConsulta = hrConsulta;
        this.idBeneficiario = idBeneficiario;
        this.idDentista = idDentista;
    }

    public Consulta(String dsProntuario, LocalDate dtConsulta, LocalTime hrConsulta, int idBeneficiario, int idDentista) {
        this.dsProntuario = dsProntuario;
        this.dtConsulta = dtConsulta;
        this.hrConsulta = hrConsulta;
        this.idBeneficiario = idBeneficiario;
        this.idDentista = idDentista;
    }

    public int getIdConsulta() { return idConsulta; }
    public void setIdConsulta(int idConsulta) { this.idConsulta = idConsulta; }

    public String getDsProntuario() { return dsProntuario; }
    public void setDsProntuario(String dsProntuario) { this.dsProntuario = dsProntuario; }

    public LocalDate getDtConsulta() { return dtConsulta; }
    public void setDtConsulta(LocalDate dtConsulta) { this.dtConsulta = dtConsulta; }

    public LocalTime getHrConsulta() { return hrConsulta; }
    public void setHrConsulta(LocalTime hrConsulta) { this.hrConsulta = hrConsulta; }

    public int getIdBeneficiario() { return idBeneficiario; }
    public void setIdBeneficiario(int idBeneficiario) { this.idBeneficiario = idBeneficiario; }

    public int getIdDentista() { return idDentista; }
    public void setIdDentista(int idDentista) { this.idDentista = idDentista; }
}