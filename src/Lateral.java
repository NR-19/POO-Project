import java.io.Serializable;

public class Lateral extends Jogador implements Serializable {

    //variável de instância
    private int cruzamento;

    /**
     * Construtores.
     */

    /**
     * Construtor por omissão de Lateral.
     */
    public Lateral() {
        super();
        this.cruzamento = 0;
    }

    /**
     * Construtor parametrizado de Lateral.
     */
    public Lateral(String nome, int numero, int velocidade, int resistencia, int destreza,
                   int impulsao, int jogo_de_cabeca, int remate, int passe, int cruzamento) {
        super(nome, numero, velocidade, resistencia, destreza, impulsao, jogo_de_cabeca, remate, passe);
        this.cruzamento = cruzamento;
    }

    /**
     * Contrutor de cópia de Lateral.
     */
    public Lateral(Lateral l) {
        super(l);
        this.cruzamento = l.getCruzamento();
    }

    /**
     * Métodos de instância.
     */

    /**
     * Devolve a habilidade de efetuar cruzamentos.
     *
     * @return Habilidade de efetuar cruzamentos
     */
    public int getCruzamento() {
        return this.cruzamento;
    }

    /**
     * Atualiza a nova habilidade de cruzamento do Lateral.
     *
     * @param cruzamento nova habilidade de cruzamento do Lateral
     */
    public void setCruzamento(int cruzamento) {
        this.cruzamento = cruzamento;
    }

    /**
     * Método que determinda o Overall de um Lateral.
     * Dando prioridade ao Crusamento e Resitência.
     */
    @Override
    public int overall() {
        return (int) Math.round(this.cruzamento * 0.20 +
                this.getVelocidade() * 0.15 +
                this.getResistencia() * 0.20 +
                this.getDestreza() * 0.10 +
                this.getImpulsao() * 0.05 +
                this.getJogo_de_cabeca() * 0.05 +
                this.getRemate() * 0.10 +
                this.getPasse() * 0.15);
    }

    /**
     * Método que implementa a igualdade entre dois laterais.
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
        Lateral l = (Lateral) o;
        return l.getCruzamento() == this.cruzamento;
    }

    /**
     * Método que devolve a representação em String do Lateral.
     *
     * @return String com as características do Lateral
     */
    public String toString() {
        StringBuffer sb = new StringBuffer("Lateral {\n");
        sb.append(super.toString());
        sb.append("\tCruzamento: ").append(cruzamento).append("\n");
        sb.append('}');
        return sb.toString();
    }

    /**
     * Método que faz uma cópia do objeto recetor da mensagem.
     * Para tal invoca o construtor de cópia.
     *
     * @return objeto clone do objeto que recebe a mensagem
     */
    public Lateral clone() {
        return new Lateral(this);
    }

    public static Lateral parse(String input) {
        String[] campos = input.split(",");
        return new Lateral(campos[0], Integer.parseInt(campos[1]),
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
