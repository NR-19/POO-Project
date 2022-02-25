import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Classe que implementa uma Equipa.
 */
public class Equipa implements Serializable {

    //variáveis de instância
    private String nomeE;
    private Map<Integer, Jogador> titulares;
    private Map<Integer, Jogador> suplentes;

    /**
     * Construtores da classe Equipa.
     * Declaração dos construtores por omissão (vazio),
     * parametrizado e de cópia.
     */

    /**
     * Construtor por omissão de Equipa.
     */
    public Equipa() {
        this.nomeE = "";
        this.titulares = new HashMap<>();
        this.suplentes = new HashMap<>();
    }

    public Equipa(String nome) {
        this.nomeE = nome;
        this.titulares = new HashMap<>();
        this.suplentes = new HashMap<>();
    }

    /**
     * Construtor parametrizado de Equipa.
     */
    public Equipa(String nome, Map<Integer, Jogador> titulares, Map<Integer, Jogador> suplentes) {
        this.nomeE = nome;
        this.titulares = titulares.entrySet().stream().
                collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue().clone()));
        this.suplentes = suplentes.entrySet().stream().
                collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue().clone()));
    }

    /**
     * Contrutor de cópia de Jogador.
     */
    public Equipa(Equipa e) {
        this.nomeE = e.getNomeEquipa();
        this.titulares = e.getTitulares();
        this.suplentes = e.getSuplentes();
    }

    /**
     * Métodos de instância.
     */

    /**
     * Devolve o nome da Equipa.
     *
     * @return nome da Equipa
     */
    public String getNomeEquipa() {
        return this.nomeE;
    }

    /**
     * Atualiza o novo nome da Equipa.
     *
     * @param nomeE novo nome da Equipa
     */
    public void setNomeEquipa(String nomeE) {
        this.nomeE = nomeE;
    }

    /**
     * Devolve um Map com os jogadores Titulares.
     *
     * @return Map com os jogadores Titulares
     */
    public Map<Integer, Jogador> getTitulares() {
        return this.titulares.entrySet().stream().collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue().clone()));
    }

    /**
     * Devolve um Map com os jogadores Suplentes.
     *
     * @return Map com os jogadores Suplentes
     */
    public Map<Integer, Jogador> getSuplentes() {
        return this.suplentes.entrySet().stream().collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue().clone()));
    }

    /**
     * Insere um novo jogador no Map de Suplentes, caso o seu número seja novo na Equipa.
     *
     * @param j novo jogador
     * @throws NumeroExistenteException
     */
    public void insereJogador(Jogador j) throws NumeroExistenteException {
        if (this.titulares.containsKey(j.getNumero()) || this.suplentes.containsKey(j.getNumero()))
            throw new NumeroExistenteException("Jogador com Número já existente na Equipa.");
        else {
            j.adicionaHistorico(this.nomeE);
            this.suplentes.put(j.getNumero(), j.clone());

        }
    }

    /**
     * Remove um jogador caso o seu número exista na Equipa.
     *
     * @param j
     * @throws NumeroNaoExistenteException
     */
    public void removeJogador(Jogador j) throws NumeroNaoExistenteException {
        if (!(this.titulares.containsKey(j.getNumero()) || this.suplentes.containsKey(j.getNumero())))
            throw new NumeroNaoExistenteException("Jogador nao Encontrado.");
        else {
            this.titulares.remove(j.getNumero());
            this.suplentes.remove(j.getNumero());
        }
    }

    public void mudaTitular(int jogadorSai, int jogadorEntra) {
        Jogador sai = this.titulares.remove(jogadorSai);
        Jogador entra = this.suplentes.remove(jogadorEntra);
        this.titulares.put(entra.getNumero(), entra.clone());
        this.suplentes.put(sai.getNumero(), sai.clone());
    }


    /**
     * Método que faz a transferência do Jogador para uma nova equipa.
     *
     * @param nova Nova Equipa do Jogador
     * @param j    Jogador
     * @throws NumeroExistenteException
     * @throws NumeroNaoExistenteException
     */
    public void mudaEquipa(Equipa nova, Jogador j) throws NumeroExistenteException, NumeroNaoExistenteException {
        nova.insereJogador(j);
        this.removeJogador(j);
    }

    /**
     * Método que analiza todos os jogadores da Equipa pelo seu Overall, determina os melhores Jogadores
     * para a sua posição e coloca-os na Equipa Titular, de acordo com a tática escolhida.
     *
     * @param Tatica Tática usada pela Equipa
     */
    public void colocaTitulares(int Tatica) {
        List<Jogador> sorted = this.suplentes.values()
                .stream()
                .map(Jogador::clone)
                .sorted()
                .collect(Collectors.toList());
        int avancados, medios, defesas, laterais, redes;
        if (Tatica == 433) {
            redes = 1;
            defesas = 2;
            laterais = 2;
            medios = 3;
            avancados = 3;
        } else {
            redes = 1;
            defesas = 2;
            laterais = 2;
            medios = 4;
            avancados = 2;
        }

        for (Jogador jogador : sorted) {
            Jogador j = jogador.clone();

            if (j instanceof GuardaRedes) {
                if (redes > 0) {
                    this.titulares.put(j.getNumero(), j.clone());
                    this.suplentes.remove(j.getNumero());
                    redes--;
                }
            } else if (j instanceof Defesa) {
                if (defesas > 0) {
                    this.titulares.put(j.getNumero(), j.clone());
                    this.suplentes.remove(j.getNumero());
                    defesas--;
                }
            } else if (j instanceof Lateral) {
                if (laterais > 0) {
                    this.titulares.put(j.getNumero(), j.clone());
                    this.suplentes.remove(j.getNumero());
                    laterais--;
                }
            } else if (j instanceof Médio) {
                if (medios > 0) {
                    this.titulares.put(j.getNumero(), j.clone());
                    this.suplentes.remove(j.getNumero());
                    medios--;
                }
            } else if (j instanceof Avancado) {
                if (avancados > 0) {
                    this.titulares.put(j.getNumero(), j.clone());
                    this.suplentes.remove(j.getNumero());
                    avancados--;
                }
            }
        }
    }

    /**
     * Método que determina o overall da Equipa Titular.
     *
     * @return Overall da Equipa
     */
    public int overallTitulares() {
        return (int) (this.titulares.values()
                .stream()
                .mapToInt(Jogador::overall).sum() / 11);
    }

    /**
     * Método que determina o overall do Ataque. (Avançados)
     *
     * @param tatica Tática da Equipa
     * @return Overall do Ataque
     */
    public int overallAtaque(int tatica) {
        int atacantes;
        if (tatica == 433) atacantes = 3;
        else atacantes = 2;
        return (int) (this.titulares.values().stream().filter(e -> e instanceof Avancado).mapToInt(Jogador::overall).sum() / atacantes);
    }

    /**
     * Método que determina o overall dos Médios. (Médios)
     *
     * @param tatica Tática da Equipa
     * @return Overall dos Médios
     */
    public int overallMedio(int tatica) {
        int medios;
        if (tatica == 433) medios = 3;
        else medios = 4;
        return (int) (this.titulares.values().stream().filter(e -> e instanceof Médio).mapToInt(Jogador::overall).sum() / medios);
    }

    /**
     * Método que determina o overall da Defesa. (Defesas e Laterais)
     *
     * @return overall da Defesa
     */
    public int overallDefesa() {
        return (int) (this.titulares.values().stream().filter(e -> e instanceof Defesa || e instanceof Lateral).mapToInt(Jogador::overall).sum() / 4);
    }

    /**
     * Método que determina o overall dos Laterais. (Laterais)
     *
     * @return overall dos Laterais
     */
    public int overallLateral() {
        return (int) (this.titulares.values().stream().filter(e -> e instanceof Lateral).mapToInt(Jogador::overall).sum() / 2);
    }


    public Substituicao substituiJogador(int posicao) {
        Substituicao substituicao = new Substituicao();
        switch (posicao) {
            case 1:
                List<Jogador> avancadosSuplentes = this.suplentes.values().stream().filter(e -> e instanceof Avancado).sorted().collect(Collectors.toList());
                if (!avancadosSuplentes.isEmpty()) {
                    List<Jogador> avancados = this.titulares.values().stream().filter(e -> e instanceof Avancado).sorted().collect(Collectors.toList());
                    int jogadorEntra = avancadosSuplentes.get(0).getNumero();
                    int jogadorSai = avancados.get(avancados.size() - 1).getNumero();
                    substituicao = new Substituicao(jogadorSai, jogadorEntra);
                    this.mudaTitular(jogadorSai, jogadorEntra);
                }
                break;
            case 2:
                List<Jogador> mediosSuplentes = this.suplentes.values().stream().filter(e -> e instanceof Médio).sorted().collect(Collectors.toList());
                if (!mediosSuplentes.isEmpty()) {
                    List<Jogador> medios = this.titulares.values().stream().filter(e -> e instanceof Médio).sorted().collect(Collectors.toList());
                    int jogadorEntra = mediosSuplentes.get(0).getNumero();
                    int jogadorSai = medios.get(medios.size() - 1).getNumero();
                    substituicao = new Substituicao(jogadorSai, jogadorEntra);
                    this.mudaTitular(jogadorSai, jogadorEntra);
                }
                break;
            case 3:
                List<Jogador> lateralSuplentes = this.suplentes.values().stream().filter(e -> e instanceof Lateral).sorted().collect(Collectors.toList());
                if (!lateralSuplentes.isEmpty()) {
                    List<Jogador> laterais = this.titulares.values().stream().filter(e -> e instanceof Lateral).sorted().collect(Collectors.toList());
                    int jogadorEntra = lateralSuplentes.get(0).getNumero();
                    int jogadorSai = laterais.get(laterais.size() - 1).getNumero();
                    substituicao = new Substituicao(jogadorSai, jogadorEntra);
                    this.mudaTitular(jogadorSai, jogadorEntra);
                }
                break;
        }
        return substituicao;
    }

    @Override
    public Equipa clone() {
        return new Equipa(this);
    }

    public static Equipa parse(String input) {
        String[] campos = input.split(",");
        return new Equipa(campos[0]);
    }

    /**
     * Método que devolve a representação em String da Equipa.
     *
     * @return Equipa
     */
    @Override
    public String toString() {
        return "Equipa {" +
                "------------Equipa = '" + nomeE + '\'' +
                "------------Titulares = \n" + titulares +
                "------------Suplentes = \n" + suplentes;
    }

}
