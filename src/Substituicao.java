import java.io.Serializable;

/**
 * Classe que implemanta uma Substituição.
 */
public class Substituicao implements Serializable {
    //variáveis de instância
    private int jogadorSai;
    private int jogadorEntra;


    public Substituicao() {
        this.jogadorSai = 0;
        this.jogadorEntra = 0;
    }

    /**
     * Construtor parametrizado da Substituição.
     */
    public Substituicao(int jogadorSai, int jogadorEntra) {
        this.jogadorSai = jogadorSai;
        this.jogadorEntra = jogadorEntra;
    }

    public Substituicao(Substituicao s) {
        this.jogadorSai = s.jogadorSai;
        this.jogadorEntra = s.jogadorEntra;
    }

    public int getJogadorSai() {
        return this.jogadorSai;
    }

    public Substituicao clone() {
        return new Substituicao(this);
    }

    /**
     * Método que devolve a representação em String da Substituição.
     *
     * @return String com as informações da substituição
     */
    public String toString() {
        return "Jogador " + this.jogadorSai + " sai e entra " + this.jogadorEntra;
    }
}