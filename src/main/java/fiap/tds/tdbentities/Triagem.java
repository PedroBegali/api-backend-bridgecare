package fiap.tds.tdbentities;

import java.time.LocalDate;
import java.time.LocalTime;

public class Triagem {

    private int idTriagem;
    private LocalDate dtTriagem;
    private LocalTime hrInicial;
    private LocalTime hrFinal;
    private int idEndereco;
    private int vagas;
    private String nmLocal;

    public Triagem() {
    }

    public Triagem(int idTriagem, LocalDate dtTriagem, LocalTime hrInicial, LocalTime hrFinal, int idEndereco, int vagas) {
        this.idTriagem = idTriagem;
        this.dtTriagem = dtTriagem;
        this.hrInicial = hrInicial;
        this.hrFinal = hrFinal;
        this.idEndereco = idEndereco;
        this.vagas = vagas;
    }

    public Triagem(LocalDate dtTriagem, LocalTime hrInicial, LocalTime hrFinal, int idEndereco, int vagas) {
        this.dtTriagem = dtTriagem;
        this.hrInicial = hrInicial;
        this.hrFinal = hrFinal;
        this.idEndereco = idEndereco;
        this.vagas = vagas;
    }

    // Getters e Setters Originais
    public int getIdTriagem() { return idTriagem; }
    public void setIdTriagem(int idTriagem) { this.idTriagem = idTriagem; }

    public LocalDate getDtTriagem() { return dtTriagem; }
    public void setDtTriagem(LocalDate dtTriagem) { this.dtTriagem = dtTriagem; }

    public LocalTime getHrInicial() { return hrInicial; }
    public void setHrInicial(LocalTime hrInicial) { this.hrInicial = hrInicial; }

    public LocalTime getHrFinal() { return hrFinal; }
    public void setHrFinal(LocalTime hrFinal) { this.hrFinal = hrFinal; }

    public int getIdEndereco() { return idEndereco; }
    public void setIdEndereco(int idEndereco) { this.idEndereco = idEndereco; }

    public int getVagas() { return vagas; }
    public void setVagas(int vagas) { this.vagas = vagas; }

    public String getNmLocal() { return nmLocal; }
    public void setNmLocal(String nmLocal) { this.nmLocal = nmLocal; }
}