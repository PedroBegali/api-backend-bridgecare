package fiap.tds.tdbentities;

public class PreBeneficiario {
    private int idPreBeneficiario;
    private String nmPreBeneficiario;
    private String cpfPreBeneficiario;
    private String sxPreBeneficiario;
    private String dsProblemaDentario;
    private String dtNascimento;
    private String stSituacao;
    private int idProgramaSocial;
    private int idSolicitante;
    private int idEndereco;
    private int idTriagem;
    private Endereco endereco;

    public PreBeneficiario() {
    }

    public PreBeneficiario(int idPreBeneficiario, String nmPreBeneficiario, String cpfPreBeneficiario,
                           String sxPreBeneficiario, String dsProblemaDentario, String stSituacao,
                           int idProgramaSocial, int idSolicitante, int idEndereco) {
        this.idPreBeneficiario = idPreBeneficiario;
        this.nmPreBeneficiario = nmPreBeneficiario;
        this.cpfPreBeneficiario = cpfPreBeneficiario;
        this.sxPreBeneficiario = sxPreBeneficiario;
        this.dsProblemaDentario = dsProblemaDentario;
        this.stSituacao = stSituacao;
        this.idProgramaSocial = idProgramaSocial;
        this.idSolicitante = idSolicitante;
        this.idEndereco = idEndereco;
    }


    public int getIdPreBeneficiario() {
        return idPreBeneficiario;
    }

    public void setIdPreBeneficiario(int idPreBeneficiario) {
        this.idPreBeneficiario = idPreBeneficiario;
    }

    public String getNmPreBeneficiario() {
        return nmPreBeneficiario;
    }

    public void setNmPreBeneficiario(String nmPreBeneficiario) {
        this.nmPreBeneficiario = nmPreBeneficiario;
    }

    public String getCpfPreBeneficiario() {
        return cpfPreBeneficiario;
    }

    public void setCpfPreBeneficiario(String cpfPreBeneficiario) {
        this.cpfPreBeneficiario = cpfPreBeneficiario;
    }

    public String getSxPreBeneficiario() {
        return sxPreBeneficiario;
    }

    public void setSxPreBeneficiario(String sxPreBeneficiario) {
        this.sxPreBeneficiario = sxPreBeneficiario;
    }

    public String getDsProblemaDentario() {
        return dsProblemaDentario;
    }

    public void setDsProblemaDentario(String dsProblemaDentario) {
        this.dsProblemaDentario = dsProblemaDentario;
    }

    public String getStSituacao() {
        return stSituacao;
    }

    public void setStSituacao(String stSituacao) {
        this.stSituacao = stSituacao;
    }

    public int getIdProgramaSocial() {
        return idProgramaSocial;
    }

    public void setIdProgramaSocial(int idProgramaSocial) {
        this.idProgramaSocial = idProgramaSocial;
    }

    public int getIdSolicitante() {
        return idSolicitante;
    }

    public void setIdSolicitante(int idSolicitante) {
        this.idSolicitante = idSolicitante;
    }

    public int getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(int idEndereco) {
        this.idEndereco = idEndereco;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(String dtNascimento) {
        this.dtNascimento = dtNascimento;
    }


    public int getIdTriagem() {
        return idTriagem;
    }

    public void setIdTriagem(int idTriagem) {
        this.idTriagem = idTriagem;
    }
}