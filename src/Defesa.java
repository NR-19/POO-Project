import java.io.Serializable;

public class Defesa extends Jogador implements Serializable {

    /**
     * Construtores.
     */

    /**
     * Construtor por omissão de Defesa.
     */
    public Defesa() {
        super();
    }

    /**
     * Construtor parametrizado de Defesa.
     */
    public Defesa(String nome, int numero, int velocidade, int resistencia, int destreza,
                  int impulsao, int jogo_de_cabeca, int remate, int passe) {
        super(nome, numero, velocidade, resistencia, destreza, impulsao, jogo_de_cabeca, remate, passe);
    }

    /**
     * Contrutor de cópia de Defesa.
     */
    public Defesa(Defesa d) {
        super(d);
    }

    /**
     * Método que determinda o Overall de um Defesa.
     * Dando prioridade ao jogo de cabeça.
     */
    @Override
    public int overall() {
        return (int) Math.round(
                this.getVelocidade() * 0.15 +
                        this.getResistencia() * 0.10 +
                        this.getDestreza() * 0.15 +
                        this.getImpulsao() * 0.15 +
                        this.getJogo_de_cabeca() * 0.20 +
                        this.getRemate() * 0.10 +
                        this.getPasse() * 0.15);
    }

    /**
     * Método que implementa a igualdade entre dois Defesas.
     *
     * @param o objeto que é comparado com o recetor da mensagem
     * @return boolean resultado booleano da comparação do
     * parâmetro com o recetor
     */
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if ((o == null) || (this.getClass() != o.getClass()))
            return false;
        return super.equals(o);
    }

    /**
     * Método que devolve a representação em String do Defesa.
     *
     * @return String com as características do Defesa
     */
    public String toString() {
        StringBuffer sb = new StringBuffer("Defesa {\n");
        sb.append(super.toString());
        sb.append('}');
        return sb.toString();
    }

    /**
     * Método que faz uma cópia do objeto recetor da mensagem.
     * Para tal invoca o construtor de cópia.
     *
     * @return objeto clone do objeto que recebe a mensagem
     */
    public Defesa clone() {
        return new Defesa(this);
    }

    public static Defesa parse(String input) {
        String[] campos = input.split(",");
        return new Defesa(campos[0], Integer.parseInt(campos[1]),
                Integer.parseInt(campos[2]),
                Integer.parseInt(campos[3]),
                Integer.parseInt(campos[4]),
                Integer.parseInt(campos[5]),
                Integer.parseInt(campos[6]),
                Integer.parseInt(campos[7]),
                Integer.parseInt(campos[8]));
    }
}
