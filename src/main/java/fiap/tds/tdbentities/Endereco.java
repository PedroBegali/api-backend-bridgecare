package fiap.tds.tdbentities;

public class Endereco {

    private int idEndereco;
    private String nmLocal; // 'C', 'P' ou 'T'
    private String nmLogradouro;
    private int nrLogradouro;
    private String nmBairro;
    private String nmCidade;
    private String nrCep;

    public Endereco() {
    }

    public Endereco(int idEndereco, String nmLocal, String nmLogradouro, int nrLogradouro, String nmBairro, String nmCidade, String nrCep) {
        this.idEndereco = idEndereco;
        this.nmLocal = nmLocal;
        this.nmLogradouro = nmLogradouro;
        this.nrLogradouro = nrLogradouro;
        this.nmBairro = nmBairro;
        this.nmCidade = nmCidade;
        this.nrCep = nrCep;
    }

    public Endereco(String nmLocal, String nmLogradouro, int nrLogradouro, String nmBairro, String nmCidade, String nrCep) {
        this.nmLocal = nmLocal;
        this.nmLogradouro = nmLogradouro;
        this.nrLogradouro = nrLogradouro;
        this.nmBairro = nmBairro;
        this.nmCidade = nmCidade;
        this.nrCep = nrCep;
    }

    public int getIdEndereco() { return idEndereco; }
    public void setIdEndereco(int idEndereco) { this.idEndereco = idEndereco; }

    public String getNmLocal() { return nmLocal; }
    public void setNmLocal(String nmLocal) { this.nmLocal = nmLocal; }

    public String getNmLogradouro() { return nmLogradouro; }
    public void setNmLogradouro(String nmLogradouro) { this.nmLogradouro = nmLogradouro; }

    public int getNrLogradouro() { return nrLogradouro; }
    public void setNrLogradouro(int nrLogradouro) { this.nrLogradouro = nrLogradouro; }

    public String getNmBairro() { return nmBairro; }
    public void setNmBairro(String nmBairro) { this.nmBairro = nmBairro; }

    public String getNmCidade() { return nmCidade; }
    public void setNmCidade(String nmCidade) { this.nmCidade = nmCidade; }

    public String getNrCep() { return nrCep; }
    public void setNrCep(String nrCep) { this.nrCep = nrCep; } // Alterado para String

}
