package fiap.tds.tdbentities;

public class RelTriagemPreBenef {

    private int idTriagem; // FK
    private int idPreBeneficiario; // FK


    public RelTriagemPreBenef() {}

    public RelTriagemPreBenef(int idTriagem, int idPreBeneficiario) {
        this.idTriagem = idTriagem;
        this.idPreBeneficiario = idPreBeneficiario;
    }

    public int getIdTriagem() { return idTriagem; }
    public void setIdTriagem(int idTriagem) { this.idTriagem = idTriagem; }

    public int getIdPreBeneficiario() { return idPreBeneficiario; }
    public void setIdPreBeneficiario(int idPreBeneficiario) { this.idPreBeneficiario = idPreBeneficiario; }
}