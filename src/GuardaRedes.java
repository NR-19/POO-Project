import java.io.Serializable;

public class GuardaRedes extends Jogador implements Serializable {

    //variável de instância
    private int elasticidade;

    /**
     * Construtores.
     */

    /**
     * Construtor por omissão de GuardaRedes.
     */
    public GuardaRedes() {
        super();
        this.elasticidade = 0;
    }

    /**
     * Construtor parametrizado de GuardaRedes.
     */
    public GuardaRedes(String nome, int numero, int velocidade, int resistencia, int destreza,
                       int impulsao, int jogo_de_cabeca, int remate, int passe, int elasticidade) {
        super(nome, numero, velocidade, resistencia, destreza, impulsao, jogo_de_cabeca, remate, passe);
        this.elasticidade = elasticidade;
    }

    /**
     * Contrutor de cópia de GuardaRedes.
     */
    public GuardaRedes(GuardaRedes gr) {
        super(gr);
        this.elasticidade = gr.getElasticidade();
    }

    /**
     * Métodos de instância.
     */

    /**
     * Devolve a elasticidade do GuardaRedes.
     *
     * @return elasticidade do GuardaRedes
     */
    public int getElasticidade() {
        return this.elasticidade;
    }

    /**
     * Atualiza a nova elasticidade do GuardaRedes.
     *
     * @param elasticidade nova elasticidade do GuardaRedes
     */
    public void setElasticidade(int elasticidade) {
        this.elasticidade = elasticidade;
    }

    /**
     * Método que determinda o Overall de um Guarda-Redes.
     * Dando prioridade à elasticidade, Destreza e Impulsão.
     */
    @Override
    public int overall() {
        return (int) Math.round(this.elasticidade * 0.30 +
                this.getVelocidade() * 0.05 +
                this.getResistencia() * 0.05 +
                this.getDestreza() * 0.20 +
                this.getImpulsao() * 0.20 +
                this.getJogo_de_cabeca() * 0.05 +
                this.getRemate() * 0.10 +
                this.getPasse() * 0.05);
    }


    /**
     * Método que implementa a igualdade entre dois GuardaRedes.
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
        GuardaRedes gr = (GuardaRedes) o;
        return gr.getElasticidade() == this.elasticidade;
    }

    /**
     * Método que devolve a representação em String do GuardaRedes.
     *
     * @return String com as características do GuardaRedes
     */
    public String toString() {
        StringBuffer sb = new StringBuffer("Guarda Redes {\n");
        sb.append(super.toString());
        sb.append("\tElasticidade: ").append(elasticidade).append("\n");
        sb.append('}');
        return sb.toString();
    }

    /**
     * Método que faz uma cópia do objeto recetor da mensagem.
     * Para tal invoca o construtor de cópia.
     *
     * @return objeto clone do objeto que recebe a mensagem
     */
    public GuardaRedes clone() {
        return new GuardaRedes(this);
    }

    public static GuardaRedes parse(String input) {
        String[] campos = input.split(",");
        return new GuardaRedes(campos[0], Integer.parseInt(campos[1]),
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
