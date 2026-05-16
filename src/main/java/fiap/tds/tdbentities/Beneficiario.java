package fiap.tds.tdbentities;

public class Beneficiario {

    private int idBeneficiario;
    private int idPreBeneficiario; // FK
    private String nmPreBeneficiario;
    private String cpfPreBeneficiario;

    public Beneficiario() {
    }

    public Beneficiario(int idBeneficiario, int idPreBeneficiario) {
        this.idBeneficiario = idBeneficiario;
        this.idPreBeneficiario = idPreBeneficiario;
    }

    public Beneficiario(int idPreBeneficiario) {
        this.idPreBeneficiario = idPreBeneficiario;
    }

    public int getIdBeneficiario() { return idBeneficiario; }
    public void setIdBeneficiario(int idBeneficiario) { this.idBeneficiario = idBeneficiario; }

    public int getIdPreBeneficiario() { return idPreBeneficiario; }
    public void setIdPreBeneficiario(int idPreBeneficiario) { this.idPreBeneficiario = idPreBeneficiario; }

    public String getNmPreBeneficiario() { return nmPreBeneficiario; }
    public void setNmPreBeneficiario(String nmPreBeneficiario) { this.nmPreBeneficiario = nmPreBeneficiario; }

    public String getCpfPreBeneficiario() { return cpfPreBeneficiario; }
    public void setCpfPreBeneficiario(String cpfPreBeneficiario) { this.cpfPreBeneficiario = cpfPreBeneficiario; }
}
