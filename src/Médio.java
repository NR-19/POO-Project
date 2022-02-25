import java.io.Serializable;

public class Médio extends Jogador implements Serializable {

    //variável de instância
    private int recuperacao_bola;

    /**
     * Construtores.
     */

    /**
     * Construtor por omissão de Médio.
     */
    public Médio() {
        super();
        this.recuperacao_bola = 0;
    }

    /**
     * Construtor parametrizado de Médio.
     */
    public Médio(String nome, int numero, int velocidade, int resistencia, int destreza,
                 int impulsao, int jogo_de_cabeca, int remate, int passe, int recuperacao_bola) {
        super(nome, numero, velocidade, resistencia, destreza, impulsao, jogo_de_cabeca, remate, passe);
        this.recuperacao_bola = recuperacao_bola;
    }

    /**
     * Contrutor de cópia de Médio.
     */
    public Médio(Médio m) {
        super(m);
        this.recuperacao_bola = m.getRecuperacao_bola();
    }

    /**
     * Métodos de instância.
     */

    /**
     * Devolve a capacidade de recuperação de bolas.
     *
     * @return capacidade de recuperação de bolas
     */
    public int getRecuperacao_bola() {
        return this.recuperacao_bola;
    }

    /**
     * Atualiza a nova capacidade de recuperação de bola.
     *
     * @param recuperacao_bola nova capacidade de recuperação de bola
     */
    public void setRecuperacao_bola(int recuperacao_bola) {
        this.recuperacao_bola = recuperacao_bola;
    }

    /**
     * Método que determinda o Overall de um Médio.
     * Dando prioridade ao Passe e Recuperação de bolas.
     */
    @Override
    public int overall() {
        return (int) Math.round(this.recuperacao_bola * 0.20 +
                this.getVelocidade() * 0.10 +
                this.getResistencia() * 0.15 +
                this.getDestreza() * 0.10 +
                this.getImpulsao() * 0.09 +
                this.getJogo_de_cabeca() * 0.09 +
                this.getRemate() * 0.10 +
                this.getPasse() * 0.17);
    }

    /**
     * Método que implementa a igualdade entre dois Médios.
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
        if (!super.equals(o)) return false;
        Médio m = (Médio) o;
        return m.getRecuperacao_bola() == this.recuperacao_bola;
    }

    /**
     * Método que devolve a representação em String do Médio.
     *
     * @return String com as características do Médio
     */
    public String toString() {
        StringBuffer sb = new StringBuffer("Médio {\n");
        sb.append(super.toString());
        sb.append("\tRecuperação Bola: ").append(recuperacao_bola).append("\n");
        sb.append('}');
        return sb.toString();
    }

    /**
     * Método que faz uma cópia do objeto recetor da mensagem.
     * Para tal invoca o construtor de cópia.
     *
     * @return objeto clone do objeto que recebe a mensagem
     */
    public Médio clone() {
        return new Médio(this);
    }

    public static Médio parse(String input) {
        String[] campos = input.split(",");
        return new Médio(campos[0], Integer.parseInt(campos[1]),
                Integer.parseInt(campos[2]),
                Integer.parseInt(campos[3]),
                Integer.parseInt(campos[4]),
                Integer.parseInt(campos[5]),
                Integer.parseInt(campos[6]),
                Integer.parseInt(campos[7]),
                Integer.parseInt(campos[8]),
                Integer.parseInt(campos[9]));
    }
}
