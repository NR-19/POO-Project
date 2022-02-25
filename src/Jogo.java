import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe que implemanta um Jogo.
 */
public class Jogo implements Serializable {

    //variáveis de instância
    private Equipa equipa1;
    private Equipa equipa2;
    private LocalDate data;

    private int golos1;
    private int golos2;
    private int faltas1;
    private int faltas2;
    private int posse_de_bola1;
    private int posse_de_bola2;
    private int remates1;
    private int remates2;
    private List<Golo> golos;
    private List<Integer> amarelos1;
    private List<Integer> amarelos2;
    private List<Integer> vermelhos1;
    private List<Integer> vermelhos2;
    private List<Substituicao> substituicoes1;
    private List<Substituicao> substituicoes2;

    /**
     * Construtor por omissão de um Jogo.
     */
    public Jogo(Equipa equipa1, Equipa equipa2, LocalDate data) {
        this.equipa1 = equipa1;
        this.equipa2 = equipa2;
        this.data = data;

        this.golos1 = 0;
        this.golos2 = 0;
        this.faltas1 = 0;
        this.faltas2 = 0;
        this.posse_de_bola1 = 0;
        this.posse_de_bola2 = 0;
        this.remates1 = 0;
        this.remates2 = 0;
        this.golos = new ArrayList<>();
        this.amarelos1 = new ArrayList<>();
        this.amarelos2 = new ArrayList<>();
        this.vermelhos1 = new ArrayList<>();
        this.vermelhos2 = new ArrayList<>();
        this.substituicoes1 = new ArrayList<>();
        this.substituicoes2 = new ArrayList<>();
    }

    public Jogo(Jogo j) {
        this.equipa1 = equipa1.clone();
        this.equipa2 = equipa2.clone();
        this.data = j.data;

        this.golos1 = j.golos1;
        this.golos2 = j.golos2;
        this.faltas1 = j.faltas1;
        this.faltas2 = j.faltas2;
        this.posse_de_bola1 = j.posse_de_bola1;
        this.posse_de_bola2 = j.posse_de_bola2;
        this.remates1 = j.remates1;
        this.remates2 = j.remates2;
        this.golos = j.golos.stream().map(Golo::clone).collect(Collectors.toList());
        this.amarelos1 = new ArrayList<>(j.amarelos1);
        this.amarelos2 = new ArrayList<>(j.amarelos2);
        this.vermelhos1 = new ArrayList<>(j.vermelhos1);
        this.vermelhos2 = new ArrayList<>(j.vermelhos2);
        this.substituicoes1 = j.substituicoes1.stream().map(Substituicao::clone).collect(Collectors.toList());
        this.substituicoes2 = j.substituicoes2.stream().map(Substituicao::clone).collect(Collectors.toList());
    }

    
    /*    EQUIPA1                           EQUIPA2
    |                |                |                |
    |                |                |                |
    |        1       |        2       |        3       |
    |                |                |                |
    |                |                |                |
     */

    public void simularJogo() throws InterruptedException, NumeroNaoExistenteException {

        int zona = 2;
        int tempo = 0;

        // Escolher equipa que comeca com o lance da moeda
        int start = Math.random() > 0.50 ? 1 : 2;
        int bola = start;

        // Escolher a tatica para cada equipa
        int tatica1 = Math.random() > 0.6 ? 442 : 433; // 60 % USA 433
        int tatica2 = Math.random() > 0.6 ? 442 : 433;
        System.out.println(this.equipa1.getNomeEquipa() + tatica1 + "\n" + this.equipa2.getNomeEquipa() + tatica2);

        // Escolher os melhores jogadores de cada equipa para colocar a titular
        equipa1.colocaTitulares(tatica1);
        equipa2.colocaTitulares(tatica2);

        // Calcular os overalls de cada posicao para simplificar calculos de probabilidades futuros
        int ataque1 = equipa1.overallAtaque(tatica1);
        int medios1 = equipa1.overallMedio(tatica1);
        int defesas1 = equipa1.overallDefesa();
        int lateral1 = equipa1.overallLateral();

        int ataque2 = equipa2.overallAtaque(tatica2);
        int medios2 = equipa2.overallMedio(tatica2);
        int defesas2 = equipa2.overallDefesa();
        int lateral2 = equipa2.overallLateral();

        // Guardar o numero de jogadores em cada posicao dependedo da tatica escolhida
        // Simplifica as expulsoes dos jogadores
        int nAtacantes1 = tatica1 == 433 ? 3 : 2;
        int nMedios1 = tatica1 == 433 ? 3 : 4;
        int nDefesas1 = 2;
        int nLaterais1 = 2;

        int nAtacantes2 = tatica2 == 433 ? 3 : 2;
        int nMedios2 = tatica1 == 433 ? 3 : 4;
        int nDefesas2 = 2;
        int nLaterais2 = 2;

        // Lista temporaria que guarda os jogadores expulsos de cada equipa
        List<Jogador> expulsoes1 = new ArrayList<>();
        List<Jogador> expulsoes2 = new ArrayList<>();

        //Guardar os guarda redes para calcular chance de golo sem estar sempre a procurar no Map dos titulares
        GuardaRedes redes1 = new GuardaRedes();
        for (Jogador a : equipa1.getTitulares().values())
            if (a instanceof GuardaRedes) {
                redes1 = (GuardaRedes) a.clone();
                break;
            }

        GuardaRedes redes2 = new GuardaRedes();
        for (Jogador a : equipa2.getTitulares().values())
            if (a instanceof GuardaRedes) {
                redes2 = (GuardaRedes) a.clone();
                break;
            }


        System.out.println("Moeda ao ar!");
        Thread.sleep(500);
        System.out.println("A equipa " + (start == 1 ? equipa1.getNomeEquipa() : equipa2.getNomeEquipa()) + " comeca com a bola");

        System.out.println("\t\t--------------- COMEÇO DE JOGO ---------------");
        //Casa simulação dura 5 minutos
        while (tempo <= 90) {
            System.out.println("\t--------- Minuto " + tempo + " ---------");

            System.out.print("A equipa " + (bola == 1 ? equipa1.getNomeEquipa() : equipa2.getNomeEquipa()) + " tem a bola ");
            switch (zona) {
                case 1:
                    System.out.print(bola == 1 ? "na sua area\n" : "na area do adversario\n");
                    break;
                case 2:
                    System.out.print("no meio campo\n");
                    break;
                case 3:
                    System.out.print(bola == 2 ? "na sua area\n" : "na area do adversario\n");
                    break;
            }


            // Comeco do calculo de probabilidade de faltas
            double falta = Math.random();
            int faltaEquipa = 0;

            int cartao = 0; // cartao 1 amaraelo // cartao 2 vermelho
            if (falta <= 0.20 && falta >= 0.10) // 10 % de chance a cada jogada de haver um cartao amarelo
                cartao = 1;
            else if (falta < 0.02) // 2% de chance para um vermelho
                cartao = 2;

            // Escolher a equipa que levou falta tendo maior probabilidade de ser a equipa atualmente a defender
            // (sem a posse de bola)
            // A equipa que levar falta e guardada na variavel faltaEquipa
            falta = Math.random();
            if (cartao != 0) {
                if (falta < 80) {
                    if (bola == 1)
                        faltaEquipa = 2;
                    else
                        faltaEquipa = 1;
                } else {
                    if (bola == 1)
                        faltaEquipa = 1;
                    else
                        faltaEquipa = 2;
                }
                System.out.println("A bola pertence a " + bola);
                System.out.print("Cartao " + (cartao == 1 ? "amarelo" : "vermelho"));
                System.out.print(" para a equipa " + ((faltaEquipa == 1) ? equipa1.getNomeEquipa() : equipa2.getNomeEquipa()));

                // Criar uma lista de jogadores que podem ter falta
                List<Jogador> possiveisFaltas;
                Jogador jogadorFalta; // Variavel para guardar Jogador que leva com a falta
                switch (zona) {
                    case 1: // ZONA 1 DO CAMPO
                        if (faltaEquipa == 1) {  // Escolher um jogador ao calhas dos jogadores possiveis
                            int jogador = (int) (Math.random() * (nDefesas1 + nLaterais1)); // Escolher um indice aleatorio com o numero de jogadores em campo
                            // Guardar numa lista os jogadores possiveis
                            possiveisFaltas = equipa1.getTitulares().values().stream().filter(e -> e instanceof Defesa || e instanceof Lateral).collect(Collectors.toList());
                            jogadorFalta = possiveisFaltas.get(jogador); // Selecionar o jogador com o indice aleatorio
                            if (cartao == 1) { // Cartao amarelo
                                if (this.amarelos1.contains(jogadorFalta.getNumero())) { // Jogador ja tem cartao amarelo
                                    this.vermelhos1.add(jogadorFalta.getNumero()); // Adicionar vermelho
                                    expulsoes1.add(jogadorFalta.clone()); // Expulsar jogador
                                    this.equipa1.removeJogador(jogadorFalta); // Remover dos titulares
                                    // ver se foi um defesa ou lateral expulso para reduzir no numero de jogadores em campo
                                    if (jogadorFalta instanceof Defesa)
                                        nDefesas1--;
                                    else
                                        nLaterais1--;
                                    System.out.println("O Jogador " + jogadorFalta.getNome() + "foi expulso!");
                                } else
                                    this.amarelos1.add(jogadorFalta.getNumero());
                            } else {
                                this.vermelhos1.add(jogadorFalta.getNumero());
                                expulsoes1.add(jogadorFalta.clone());
                                this.equipa1.removeJogador(jogadorFalta);
                                if (jogadorFalta instanceof Defesa)
                                    nDefesas1--;
                                else
                                    nLaterais1--;
                                System.out.println("O Jogador " + jogadorFalta.getNome() + "foi expulso!");
                            }
                            System.out.print(" para o Jogador " + jogadorFalta.getNome() + "\n");
                            faltas1++;
                        } else {
                            int jogador = (int) (Math.random() * nAtacantes2);
                            possiveisFaltas = equipa2.getTitulares().values().stream().filter(e -> e instanceof Avancado).collect(Collectors.toList());
                            jogadorFalta = possiveisFaltas.get(jogador);
                            if (cartao == 1) {
                                if (this.amarelos2.contains(jogadorFalta.getNumero())) {
                                    this.vermelhos2.add(jogadorFalta.getNumero());
                                    expulsoes2.add(jogadorFalta.clone());
                                    this.equipa2.removeJogador(jogadorFalta);
                                    nAtacantes2--;
                                    System.out.println("O Jogador " + jogadorFalta.getNome() + "foi expulso!");
                                } else
                                    this.amarelos2.add(jogadorFalta.getNumero());
                            } else {
                                this.vermelhos2.add(jogadorFalta.getNumero());
                                expulsoes2.add(jogadorFalta.clone());
                                this.equipa2.removeJogador(jogadorFalta);
                                nAtacantes2--;
                                System.out.println("O Jogador " + jogadorFalta.getNome() + "foi expulso!");
                            }
                            System.out.print(" para o Jogador " + jogadorFalta.getNome() + "\n");
                            faltas2++;
                        }
                        break;

                    // MEIO CAMPO
                    // Repete-se a estrategia para escolher um dos jogadores
                    case 2:
                        if (faltaEquipa == 1) {
                            int jogador = (int) (Math.random() * nMedios1);
                            possiveisFaltas = equipa1.getTitulares().values().stream().filter(e -> e instanceof Médio).collect(Collectors.toList());
//                            System.out.println("\nA Tatica da equipa com falta : " + taticaTemp + " \\ jogadores disponiveis " + possiveisFaltas.size());
                            jogadorFalta = possiveisFaltas.get(jogador);
                            if (cartao == 1) {
                                if (this.amarelos1.contains(jogadorFalta.getNumero())) {
                                    this.vermelhos1.add(jogadorFalta.getNumero());
                                    expulsoes1.add(jogadorFalta.clone());
                                    this.equipa1.removeJogador(jogadorFalta);
                                    nMedios1--;
                                    System.out.println("O Jogador " + jogadorFalta.getNome() + "foi expulso!");
                                } else
                                    this.amarelos1.add(jogadorFalta.getNumero());
                            } else {
                                this.vermelhos1.add(jogadorFalta.getNumero());
                                expulsoes1.add(jogadorFalta.clone());
                                this.equipa1.removeJogador(jogadorFalta);
                                nMedios1--;
                                System.out.println("O Jogador " + jogadorFalta.getNome() + "foi expulso!");
                            }
                            System.out.print(" para o Jogador " + jogadorFalta.getNome() + "\n");
                            faltas1++;
                        } else {
                            int jogador = (int) (Math.random() * nMedios2);
                            possiveisFaltas = equipa2.getTitulares().values().stream().filter(e -> e instanceof Médio).collect(Collectors.toList());
//                            System.out.println("\nA Tatica da equipa com falta : " + taticaTemp + " \\ jogadores disponiveis " + possiveisFaltas.size());
                            jogadorFalta = possiveisFaltas.get(jogador);
                            if (cartao == 1) {
                                if (this.amarelos2.contains(jogadorFalta.getNumero())) {
                                    this.vermelhos2.add(jogadorFalta.getNumero());
                                    expulsoes2.add(jogadorFalta.clone());
                                    this.equipa2.removeJogador(jogadorFalta);
                                    nMedios2--;
                                    System.out.println("O Jogador " + jogadorFalta.getNome() + "foi expulso!");
                                } else
                                    this.amarelos2.add(jogadorFalta.getNumero());
                            } else {
                                this.vermelhos2.add(jogadorFalta.getNumero());
                                expulsoes2.add(jogadorFalta.clone());
                                this.equipa2.removeJogador(jogadorFalta);
                                nMedios2--;
                                System.out.println("O Jogador " + jogadorFalta.getNome() + "foi expulso!");
                            }
                            System.out.print(" para o Jogador " + jogadorFalta.getNome() + "\n");
                            faltas2++;
                        }
                        break;


                    case 3:
                        if (faltaEquipa == 1) {

                            int jogador = (int) (Math.random() * nAtacantes1);
                            possiveisFaltas = equipa1.getTitulares().values().stream().filter(e -> e instanceof Avancado).collect(Collectors.toList());
                            jogadorFalta = possiveisFaltas.get(jogador);
                            if (cartao == 1) {
                                if (this.amarelos1.contains(jogadorFalta.getNumero())) {
                                    this.vermelhos1.add(jogadorFalta.getNumero());
                                    expulsoes1.add(jogadorFalta.clone());
                                    this.equipa1.removeJogador(jogadorFalta);
                                    nAtacantes1--;
                                    System.out.println("O Jogador " + jogadorFalta.getNome() + "foi expulso!");
                                } else
                                    this.amarelos1.add(jogadorFalta.getNumero());
                            } else {
                                this.vermelhos1.add(jogadorFalta.getNumero());
                                expulsoes1.add(jogadorFalta.clone());
                                this.equipa1.removeJogador(jogadorFalta);
                                nAtacantes1--;
                                System.out.println("O Jogador " + jogadorFalta.getNome() + "foi expulso!");
                            }
                            System.out.print(" para o Jogador " + jogadorFalta.getNome() + "\n");
                            faltas1++;
                        } else {
                            int jogador = (int) (Math.random() * (nDefesas2 + nLaterais2));
                            possiveisFaltas = equipa2.getTitulares().values().stream().filter(e -> e instanceof Defesa || e instanceof Lateral).collect(Collectors.toList());
                            jogadorFalta = possiveisFaltas.get(jogador);
                            if (cartao == 1) {
                                if (this.amarelos2.contains(jogadorFalta.getNumero())) {
                                    this.vermelhos2.add(jogadorFalta.getNumero());
                                    expulsoes2.add(jogadorFalta.clone());
                                    this.equipa2.removeJogador(jogadorFalta);
                                    if (jogadorFalta instanceof Defesa)
                                        nDefesas2--;
                                    else
                                        nLaterais2--;
                                    System.out.println("O Jogador " + jogadorFalta.getNome() + "foi expulso!");
                                } else
                                    this.amarelos2.add(jogadorFalta.getNumero());
                            } else {
                                this.vermelhos2.add(jogadorFalta.getNumero());
                                expulsoes2.add(jogadorFalta.clone());
                                this.equipa2.removeJogador(jogadorFalta);
                                if (jogadorFalta instanceof Defesa)
                                    nDefesas2--;
                                else
                                    nLaterais2--;
                                System.out.println("O Jogador " + jogadorFalta.getNome() + "foi expulso!");
                            }
                            System.out.print(" para o Jogador " + jogadorFalta.getNome() + "\n");
                            faltas2++;
                        }
                }
            }


            Thread.sleep(150);
            switch (zona) {
                case 1:
                    if (bola == 2) { // Bola dos atacantes na zona dos defensores
                        if (sucessoAtaque(ataque2, medios2, lateral2, medios1, defesas1)) { // Testa se o ataque teve sucesso
                            System.out.println("Ataque com sucesso");
                            Golo tentativa = tentativaGolo(equipa2, redes1, tempo); // Tentativa de marcar golo
                            // Se for criado um golo nao vazio pela funcao anterior foi marcado um golo
                            if (!tentativa.getEquipa().isEmpty()) {
                                Thread.sleep(200);
                                System.out.println("GOOOOOLOOOOOO!!!!");
                                this.golos.add(tentativa);
                                System.out.println(tentativa.toString());
                                // Posse de bola sempre incrementada a cada simulacao para a equipa com posse de bola nessa instancia
                                posse_de_bola2++;
                                this.golos2++;
                            } else {
                                System.out.println("Defesa de " + redes1.getNome());
                                bola = 1;
                                zona = 2;
                                posse_de_bola1++;
                            }
                            remates2++;
                        } else {
                            System.out.println("A Equipa " + equipa1.getNomeEquipa() + " interseta um passe e avanca para o meio campo");
                            bola = 1;
                            zona = 2;
                            posse_de_bola1++;
                        }
                    } else {
                        if (avancoToMeio(lateral1, medios1, medios2, ataque2)) { // Tentativa de avanco para o meio campo
                            System.out.println("Avanco para o meio campo pela equipa " + equipa1.getNomeEquipa());
                            zona = 2;
                            posse_de_bola1++;
                        } else {
                            System.out.println("A equipa " + equipa2.getNomeEquipa() + " recupera a bola e passa ao ataque");
                            bola = 2;
                            zona = 1;
                            posse_de_bola2++;
                        }
                    }
                    tempo += 5;
                    break;

                case 2:
                    if (bola == 1) {
                        // Tentativa de controlo do meio campo resultante num avanco para a zona do adversario caso tenha sucesso
                        if (sucessoControlo(defesas1, lateral1, medios1, ataque1, defesas2, lateral2, medios2, ataque2)) {
                            System.out.println("A equipa " + equipa1.getNomeEquipa() + " ganha controlo do meio campo");
                            zona = 3;
                            posse_de_bola1++;
                        } else {
                            System.out.println(equipa2.getNomeEquipa() + " rouba a bola");
                            bola = 2;
                            posse_de_bola2++;
                        }
                    } else {
                        if (sucessoControlo(defesas1, lateral1, medios1, ataque1, defesas2, lateral2, medios2, ataque2)) {
                            System.out.println("A equipa " + equipa2.getNomeEquipa() + " ganha controlo do meio campo");
                            zona = 1;
                            posse_de_bola2++;
                        } else {
                            System.out.println(equipa1.getNomeEquipa() + " rouba a bola");
                            bola = 1;
                            posse_de_bola1++;
                        }
                    }
                    tempo += 5;
                    break;

                // Estrategia do caso 1 espelhada para a equipa oposta
                case 3:
                    if (bola == 1) {
                        if (sucessoAtaque(ataque1, medios1, lateral1, medios2, defesas2)) { // TESTA SE O ATAQUE TEVE SUCESSO
                            System.out.println("Ataque com sucesso");
                            Golo tentativa = tentativaGolo(equipa1, redes2, tempo);
                            if (!tentativa.getEquipa().isEmpty()) {
                                Thread.sleep(200);
                                System.out.println("GOOOOOLOOOOOO!!!!");
                                this.golos.add(tentativa);
                                System.out.println(tentativa.toString());
                                posse_de_bola1++;
                                this.golos1++;
                            } else {
                                System.out.println("Defesa de " + redes2.getNome());
                                bola = 2;
                                zona = 3;
                                posse_de_bola2++;
                            }
                            remates1++;
                        } else {
                            System.out.println("A Equipa " + equipa2.getNomeEquipa() + " interseta um passe e avanca para o meio campo");
                            bola = 2;
                            zona = 2;
                            posse_de_bola2++;
                        }
                    } else {
                        if (avancoToMeio(lateral2, medios2, medios1, ataque1)) { // talvez um fora aqui ou falta
                            System.out.println("Avanco para o meio campo pela equipa " + equipa2.getNomeEquipa());
                            zona = 2;
                            posse_de_bola2++;
                        } else {
                            System.out.println("A equipa " + equipa1.getNomeEquipa() + " recupera a bola e passa ao ataque");
                            bola = 1;
                            zona = 3;
                            posse_de_bola1++;
                        }
                    }
                    tempo += 5;
                    break;
            }

            //// FIM DA PRIMEIRA PARTE
            if (tempo == 45) {
                zona = 2; // Comeca no meio campo
                bola = (start == 1) ? 2 : 1; // Ver quem comecou com a bola e dar a bola ao oposto


                System.out.println("\n\t\t\t\t----- Fim da primeira Parte -----\n\t\t\t\t\t------ Intervalo ------");
                //ESTATISTICAS
                System.out.println("\t\t\t" + equipa1.getNomeEquipa() + " --- " + golos1 + " -- -- -- " + golos2 + " --- " + equipa2.getNomeEquipa());
                System.out.println(estatisticasIntervalo());
                //SUBSTITUIÇÕES
                System.out.println("Substituições: ");
                Substituicao substituicao1 = equipa1.substituiJogador(1);
                this.substituicoes1.add(substituicao1);
                Substituicao substituicao2 = equipa2.substituiJogador(1);
                this.substituicoes2.add(substituicao2);
                if (substituicao1.getJogadorSai() != 0) {
                    System.out.println("Da equipa " + equipa1.getNomeEquipa() + " o " + substituicao1.toString());
                }
                if (substituicao2.getJogadorSai() != 0) {
                    System.out.println("Da equipa " + equipa2.getNomeEquipa() + " o " + substituicao2.toString());
                }

                Thread.sleep(2000);
                System.out.println("\n\t\t\t\t  ----- Fim do Intervalo -----\n");
            }

            if (tempo == 65) {
                Substituicao substituicao1 = equipa1.substituiJogador(2);
                this.substituicoes1.add(substituicao1);
                Substituicao substituicao2 = equipa2.substituiJogador(2);
                this.substituicoes2.add(substituicao2);
                if (substituicao1.getJogadorSai() != 0) {
                    System.out.println("Da equipa " + equipa1.getNomeEquipa() + " o " + substituicao1.toString());
                }
                if (substituicao2.getJogadorSai() != 0) {
                    System.out.println("Da equipa " + equipa2.getNomeEquipa() + " o " + substituicao2.toString());
                }

                substituicao1 = equipa1.substituiJogador(3);
                this.substituicoes1.add(substituicao1);
                substituicao2 = equipa2.substituiJogador(3);
                this.substituicoes2.add(substituicao2);
                if (substituicao1.getJogadorSai() != 0) {
                    System.out.println("Da equipa " + equipa1.getNomeEquipa() + " o " + substituicao1.toString());
                }
                if (substituicao2.getJogadorSai() != 0) {
                    System.out.println("Da equipa " + equipa2.getNomeEquipa() + " o " + substituicao2.toString());
                }
            }
        }

        System.out.println("\n\t\t\t\t----- Fim do Jogo -----\n");
        System.out.println(estatisticasFinais());
        System.out.println("\t" + equipa1.getNomeEquipa() + " --- " + golos1 + " --- --- --- " + golos2 + " --- " + equipa2.getNomeEquipa());
        Thread.sleep(2000);

    }

    public boolean sucessoControlo(int defesasA, int lateralA, int mediosA, int ataqueA, int defesasD, int lateralD, int mediosD, int ataqueD) {
        double ataque = 0.1 * defesasA + 0.2 * lateralA + 0.4 * mediosA + 0.3 * ataqueA; // Importancias diferentes para as varias posicoes
        double defesa = 0.3 * defesasD + 0.2 * lateralD + 0.4 * mediosD + 0.1 * ataqueD;

        // Calcular a probabilidade de sucesso tendo em conta as diferencas de habilidade de cada equipa
        int diferenca = (int) (ataque - defesa);
        double chance;
        if (0 <= diferenca && diferenca < 5)
            chance = 0.50;
        else if (5 <= diferenca && diferenca < 10)
            chance = 0.60;
        else if (diferenca >= 10)
            chance = 0.65;
        else if (-5 <= diferenca)
            chance = 0.45;
        else if (-10 <= diferenca)
            chance = 0.40;
        else
            chance = 0.30;

        return Math.random() <= chance;

    }


    public boolean avancoToMeio(int lateralAtacantes, int mediosAtacantes, int mediosDefensores, int avancadosDefensores) {
        double ataque = 0.5 * lateralAtacantes + 0.5 * mediosAtacantes;
        double defesa = 0.6 * mediosDefensores + 0.4 * avancadosDefensores;

        int diferenca = (int) (ataque - defesa);
        double chance;
        if (0 <= diferenca && diferenca < 5)
            chance = 0.55;
        else if (5 <= diferenca && diferenca < 10)
            chance = 0.65;
        else if (diferenca >= 10)
            chance = 0.75;
        else if (-5 <= diferenca)
            chance = 0.50;
        else if (-10 <= diferenca)
            chance = 0.45;
        else
            chance = 0.40;

        return Math.random() <= chance;
    }

    public boolean sucessoAtaque(int atacantes, int mediosAtacantes, int lateralAtacantes, int defesas, int mediosDefensores) {
        double ataque = 0.5 * atacantes + 0.3 * mediosAtacantes + 0.2 * lateralAtacantes; // Overall de ataque com pesos diferentes
        double defesa = 0.65 * defesas + 0.35 * mediosDefensores; // Overall de defesa com pesos diferentes

        int diferenca = (int) (ataque - defesa);
        double chance;
        if (0 <= diferenca && diferenca < 5)
            chance = 0.55;
        else if (5 <= diferenca && diferenca < 10)
            chance = 0.60;
        else if (diferenca >= 10)
            chance = 0.65;
        else if (-5 <= diferenca)
            chance = 0.45;
        else if (-10 <= diferenca)
            chance = 0.40;
        else
            chance = 0.35;

        return Math.random() <= chance;

    }


    public Golo tentativaGolo(Equipa equipaAtacar, GuardaRedes guardaRedes, int tempo) {
        System.out.println("Tentativa de golo ");
        // Guardar numa lista os possiveis marcadores com melhores habilidades de remate
        List<Jogador> atacantes = null;
        for (int i = 0; i <= 11; i++) {
            atacantes = equipaAtacar.getTitulares()
                    .values()
                    .stream()
                    .filter(e -> e instanceof Avancado || e instanceof Médio || e instanceof Lateral)
                    .sorted((c1, c2) -> c2.getRemate() - c1.getRemate())
                    .collect(Collectors.toList());
        }

        // Escolher o jogador marcador tendo em conta as habilidades de remate e o numero de jogadores em campo
        double remata = Math.random();
        Jogador striker;
        int nTotalAtacantes = atacantes.size();
        if (remata < 0.3)
            striker = atacantes.get(0);
        else if (0.3 <= remata && remata < 0.5)
            striker = atacantes.get(1);
        else if (0.5 <= remata && remata < 0.65)
            striker = atacantes.get(2);
        else if (0.65 <= remata && remata < 0.75)
            striker = atacantes.get(3);
        else if (0.75 <= remata && remata < 0.85 && nTotalAtacantes > 4)
            striker = atacantes.get(4);
        else if (0.85 <= remata && remata < 0.9 && nTotalAtacantes > 5)
            striker = atacantes.get(5);
        else if (0.9 <= remata && remata < 0.95 && nTotalAtacantes > 6)
            striker = atacantes.get(6);
        else if (nTotalAtacantes > 7)
            striker = atacantes.get(7);
        else
            striker = atacantes.get(((int) (Math.random() * nTotalAtacantes)));

        // calcular diferenca da capacidade de remate do atacante a rematar e do guarda redes
        int diferenca = striker.getRemate() - guardaRedes.overall();

        double chance;
        if (0 <= diferenca && diferenca < 5)
            chance = 0.55;
        else if (5 <= diferenca && diferenca < 10)
            chance = 0.60;
        else if (diferenca >= 10)
            chance = 0.65;
        else if (-5 <= diferenca)
            chance = 0.35;
        else if (-10 <= diferenca)
            chance = 0.3;
        else
            chance = 0.15;

        if (Math.random() <= chance)
            return new Golo(tempo, equipaAtacar.getNomeEquipa(), striker.getNome()); // E criado o golo com o marcador
        else
            return new Golo(); // Golo vazio representa falha de golo para a main

    }

    public String estatisticasIntervalo() {
        return "Golos: \t\t\t" + this.golos1 + "\t\t\t\t\t\t\t\t" + this.golos2 + "\n" +
                "Remates: \t\t" + this.remates1 + "\t\t\t\t\t\t\t\t" + this.remates2 + "\n" +
                "Posse de bola: \t" + Math.round((double) this.posse_de_bola1 / 9 * 100) + '%' + "\t\t\t\t\t\t\t\t" + Math.round((double) this.posse_de_bola2 / 9 * 100) + '%' + "\n" +
                "Faltas: \t\t" + this.faltas1 + "\t\t\t\t\t\t\t\t" + this.faltas2 + "\n" +
                "Amarelos: \t\t" + this.amarelos1.size() + "\t\t\t\t\t\t\t\t" + this.amarelos2.size() + "\n" +
                "Vermelhos: \t\t" + this.vermelhos1.size() + "\t\t\t\t\t\t\t\t" + this.vermelhos2.size() + "\n";

    }

    public String estatisticasFinais() {
        return "Golos: \t\t\t" + this.golos1 + "\t\t\t\t\t\t\t\t" + this.golos2 + "\n" +
                "Remates: \t\t" + this.remates1 + "\t\t\t\t\t\t\t\t" + this.remates2 + "\n" +
                "Posse de bola: \t" + Math.round((double) this.posse_de_bola1 / 19 * 100) + '%' + "\t\t\t\t\t\t\t\t" + Math.round((double) this.posse_de_bola2 / 19 * 100) + '%' + "\n" +
                "Faltas: \t\t" + this.faltas1 + "\t\t\t\t\t\t\t\t" + this.faltas2 + "\n" +
                "Amarelos: \t\t" + this.amarelos1.size() + "\t\t\t\t\t\t\t\t" + this.amarelos2.size() + "\n" +
                "Vermelhos: \t\t" + this.vermelhos1.size() + "\t\t\t\t\t\t\t\t" + this.vermelhos2.size() + "\n";

    }

    public Jogo clone() {
        return new Jogo(this);
    }


}