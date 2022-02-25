import java.io.Serializable;

/**
 * Classe que implemanta Golo.
 */
public class Golo implements Serializable {
    //variáveis de instância
    private int minuto;
    private String equipa;
    private String jogador;

    /**
     * Construtor por omissão do Golo.
     */
    public Golo() {
        this.minuto = 0;
        this.equipa = "";
        this.jogador = "";
    }

    /**
     * Construtor parametrizado do Golo.
     */
    public Golo(int minuto, String equipa, String jogador) {
        this.minuto = minuto;
        this.equipa = equipa;
        this.jogador = jogador;
    }

    public Golo(Golo g) {
        this.minuto = g.minuto;
        this.equipa = g.equipa;
        this.jogador = g.jogador;
    }

    /**
     * Devolve a equipa do Jogador que marcou.
     *
     * @return equipa do Jogador que marcou
     */
    public String getEquipa() {
        return equipa;
    }

    /**
     * Atualiza a equipa do Jogador que marcou.
     *
     * @param equipa nova equipa
     */
    public void setEquipa(String equipa) {
        this.equipa = equipa;
    }


    public Golo clone() {
        return new Golo(this);
    }

    /**
     * Método que devolve a representação em String do Golo.
     *
     * @return String com as informações do Golo
     */
    public String toString() {
        return "Golo de " + this.jogador + " da equipa " + this.equipa + " aos " + this.minuto + " minutos";
    }
}
