import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe que implemanta um Jogador.
 */
public abstract class Jogador implements Serializable, Comparable<Jogador> {

    //variáveis de instância
    private String nome;
    private int numero;
    private int velocidade;
    private int resistencia;
    private int destreza;
    private int impulsao;
    private int jogo_de_cabeca;
    private int remate;
    private int passe;
    private List<String> historico;

    /**
     * Construtores da classe Jogador.
     * Declaração dos construtores por omissão (vazio),
     * parametrizado e de cópia.
     */

    /**
     * Construtor por omissão de Jogador.
     */
    public Jogador() {
        this.nome = "";
        this.numero = 0;
        this.velocidade = 0;
        this.resistencia = 0;
        this.destreza = 0;
        this.impulsao = 0;
        this.jogo_de_cabeca = 0;
        this.remate = 0;
        this.passe = 0;
        this.historico = new ArrayList<>();
    }

    /**
     * Construtor parametrizado de Jogador.
     * Aceita como parâmetros os valores das suas características futebolísticas.
     */
    public Jogador(String nome, int numero, int velocidade, int resistencia, int destreza,
                   int impulsao, int jogo_de_cabeca, int remate, int passe) {
        this.nome = nome;
        this.numero = numero;
        this.velocidade = velocidade;
        this.resistencia = resistencia;
        this.destreza = destreza;
        this.impulsao = impulsao;
        this.jogo_de_cabeca = jogo_de_cabeca;
        this.remate = remate;
        this.passe = passe;
        this.historico = new ArrayList<>();
    }

    /**
     * Contrutor de cópia de Jogador.
     * Aceita como parâmetro outro Jogador e utiliza os métodos
     * de acesso aos valores das variáveis de instância.
     */
    public Jogador(Jogador jogador) {
        this.nome = jogador.getNome();
        this.numero = jogador.getNumero();
        this.velocidade = jogador.getVelocidade();
        this.resistencia = jogador.getResistencia();
        this.destreza = jogador.getDestreza();
        this.impulsao = jogador.getImpulsao();
        this.jogo_de_cabeca = jogador.getJogo_de_cabeca();
        this.remate = jogador.getRemate();
        this.passe = jogador.getPasse();
        this.historico = jogador.getHistorico();
    }

    /**
     * Métodos de instância.
     */

    /**
     * Devolve o nome do Jogador.
     *
     * @return nome do Jogador
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Atualiza o novo nome do Jogador.
     *
     * @param nome novo nome do Jogador
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Devolve o numero do Jogador.
     *
     * @return numero do Jogador
     */
    public int getNumero() {
        return this.numero;
    }

    /**
     * Atualiza o numero do Jogador.
     *
     * @param numero numero do Jogador
     */
    public void setNumero(int numero) {
        this.numero = numero;
    }

    /**
     * Devolve a velocidade do Jogador.
     *
     * @return velocidade do Jogador
     */
    public int getVelocidade() {
        return this.velocidade;
    }

    /**
     * Atualiza a nova velocidade do Jogador.
     *
     * @param velocidade nova velocidade do Jogador
     */
    public void setVelocidade(int velocidade) {
        this.velocidade = velocidade;
    }

    /**
     * Devolve a resistência do Jogador.
     *
     * @return resistência do Jogador
     */
    public int getResistencia() {
        return this.resistencia;
    }

    /**
     * Atualiza a nova resistência do Jogador.
     *
     * @param resistencia nova resistência do Jogador
     */
    public void setResistencia(int resistencia) {
        this.resistencia = resistencia;
    }

    /**
     * Devolve a destreza do JOgador.
     *
     * @return destreza do JOgador
     */
    public int getDestreza() {
        return this.destreza;
    }

    /**
     * Atualiza a nova destreza do Jogador.
     *
     * @param destreza nova destreza do Jogador
     */
    public void setDestreza(int destreza) {
        this.destreza = destreza;
    }

    /**
     * Devolve a impulsão do Jogador.
     *
     * @return impulsão do Jogador
     */
    public int getImpulsao() {
        return this.impulsao;
    }

    /**
     * Atualiza a nova impulsão do Jogador.
     *
     * @param impulsao nova impulsão do Jogador
     */
    public void setImpulsao(int impulsao) {
        this.impulsao = impulsao;
    }

    /**
     * Devolve o jogo de cabeça do Jogador.
     *
     * @return jogo de cabeça do Jogador
     */
    public int getJogo_de_cabeca() {
        return this.jogo_de_cabeca;
    }

    /**
     * Atualiza o novo jogo de cabeça do Jogador.
     *
     * @param jogo_de_cabeca novo jogo de cabeça do Jogador
     */
    public void setJogo_de_cabeca(int jogo_de_cabeca) {
        this.jogo_de_cabeca = jogo_de_cabeca;
    }


    /**
     * Devolve o remate do Jogador.
     *
     * @return remate do Jogador
     */
    public int getRemate() {
        return this.remate;
    }

    /**
     * Atualiza o novo reamte do Jogador.
     *
     * @param remate novo reamte do Jogador
     */
    public void setRemate(int remate) {
        this.remate = remate;
    }

    /**
     * Devolve a capacidade de passe do Jogador.
     *
     * @return capacidade de passe do Jogador
     */
    public int getPasse() {
        return this.passe;
    }

    /**
     * Atualiza a nova capacidade de passe do Jogador.
     *
     * @param passe nova capacidade de passe do Jogador
     */
    public void setPasse(int passe) {
        this.passe = passe;
    }

    public List<String> getHistorico() {
        return new ArrayList<>(this.historico);
    }

    public void setHistorico(List<String> historico) {
        this.historico = new ArrayList<>(historico);
    }

    public void adicionaHistorico(String equipa) {
        this.historico.add(equipa);
    }

    /**
     * Método que determina o overall de um Jogador.
     */
    public abstract int overall();

    /**
     * Método que implementa a igualdade entre dois Jogadores.
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
        Jogador j = (Jogador) o;
        return j.getNome().equals(this.nome) &&
                j.getNumero() == this.numero &&
                j.getVelocidade() == this.velocidade &&
                j.getResistencia() == this.resistencia &&
                j.getDestreza() == this.destreza &&
                j.getImpulsao() == this.impulsao &&
                j.getJogo_de_cabeca() == this.jogo_de_cabeca &&
                j.getRemate() == this.remate &&
                j.getPasse() == this.passe &&
                j.getHistorico().equals(this.historico);
    }

    /**
     * Método que devolve a representação em String do Jogador.
     *
     * @return String com as características do Jogador
     */
    public String toString() {
        StringBuffer sb = new StringBuffer("  Características:\n");
        sb.append("\tNome: '").append(nome).append("'\n");
        sb.append("\tNúmero: ").append(numero).append("\n");
        sb.append("\tVelociade: ").append(velocidade).append("\n");
        sb.append("\tResistência: ").append(resistencia).append("\n");
        sb.append("\tDestreza: ").append(destreza).append("\n");
        sb.append("\tImpulsão: ").append(impulsao).append("\n");
        sb.append("\tJogo de Cabeça: ").append(jogo_de_cabeca).append("\n");
        sb.append("\tRemate: ").append(remate).append("\n");
        sb.append("\tCapacidade de Passe: ").append(passe).append("\n");
        sb.append("\tOverall: ").append(this.overall()).append("\n");
        sb.append("\tHistorico do Jogador: ").append(historico.toString()).append("\n");
        return sb.toString();
    }

    /**
     * Método que faz uma cópia do objeto recetor da mensagem.
     * Para tal invoca o construtor de cópia.
     *
     * @return objeto clone do objeto que recebe a mensagem
     */
    public abstract Jogador clone();

    /**
     * Baseado na ordenação decrescente de Overall do jogador.
     */
    public int compareTo(Jogador j) {
        return j.overall() - this.overall();
    }

}
