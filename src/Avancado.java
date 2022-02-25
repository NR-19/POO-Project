import java.io.Serializable;

public class Avancado extends Jogador implements Serializable {

    /**
     * Construtores.
     */

    /**
     * Construtor por omissão de Avancado.
     */
    public Avancado() {
        super();
    }

    /**
     * Construtor parametrizado de Avancado.
     */
    public Avancado(String nome, int numero, int velocidade, int resistencia, int destreza,
                    int impulsao, int jogo_de_cabeca, int remate, int passe) {
        super(nome, numero, velocidade, resistencia, destreza, impulsao, jogo_de_cabeca, remate, passe);
    }

    /**
     * Contrutor de cópia de Avancado.
     */
    public Avancado(Avancado a) {
        super(a);
    }

    /**
     * Método que determinda o Overall de um Avançado.
     * Dando prioridade ao Remate.
     */
    @Override
    public int overall() {
        return (int) Math.round(
                this.getVelocidade() * 0.15 +
                        this.getResistencia() * 0.15 +
                        this.getDestreza() * 0.10 +
                        this.getImpulsao() * 0.15 +
                        this.getJogo_de_cabeca() * 0.15 +
                        this.getRemate() * 0.20 +
                        this.getPasse() * 0.10);
    }

    /**
     * Método que implementa a igualdade entre dois Avancados.
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
     * Método que devolve a representação em String do Avancado.
     *
     * @return String com as características do Avancado
     */
    public String toString() {
        StringBuffer sb = new StringBuffer("Avancado {\n");
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
    public Avancado clone() {
        return new Avancado(this);
    }

    public static Avancado parse(String input) {
        String[] campos = input.split(",");
        return new Avancado(campos[0], Integer.parseInt(campos[1]),
                Integer.parseInt(campos[2]),
                Integer.parseInt(campos[3]),
                Integer.parseInt(campos[4]),
                Integer.parseInt(campos[5]),
                Integer.parseInt(campos[6]),
                Integer.parseInt(campos[7]),
                Integer.parseInt(campos[8]));
    }
}
