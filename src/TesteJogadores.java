import java.time.LocalDate;

public class TesteJogadores {
    public static void main(String[] args) throws InterruptedException {

        //EQUIPA 1

//        Jogador gr1 = new GuardaRedes("Patricio fora de epoca", 123, 1, 1, 1, 1, 1, 1, 1, 1);
//        Jogador gr2 = new GuardaRedes("Patricio", 1, 1, 1, 1, 1, 1, 1, 1, 1);
//
//        Jogador defesa1 = new Defesa("defesa1", 2, 1, 1, 1, 1, 1, 1, 1);
//        Jogador defesa2 = new Defesa("defesa2", 3, 1, 1, 1, 1, 1, 1, 1);
//        Jogador defesa3 = new Defesa("defesa3", 12, 1, 1, 1, 1, 1, 1, 1);
//
//        Jogador lateral1 = new Lateral("nelson", 4, 1, 1, 1, 1, 1, 1, 1, 1);
//        Jogador lateral2 = new Lateral("nelson2", 5, 1, 1, 1, 1, 1, 1, 1, 1);
//        Jogador lateral3 = new Lateral("nelson3", 13, 1, 1, 1, 1, 1, 1, 1, 1);
//
//        Jogador medio1 = new Médio("medio1", 6, 1, 1, 1, 1, 1, 1, 1, 1);
//        Jogador medio2 = new Médio("medio2", 8, 1, 1, 1, 1, 1, 1, 1, 1);
//        Jogador medio3 = new Médio("medio3", 10, 1, 1, 1, 1, 1, 1, 1, 1);
//        Jogador medio4 = new Médio("medio4", 19, 1, 1, 1, 1, 1, 1, 1, 1);
//        Jogador medio5 = new Médio("medio5", 27, 1, 1, 1, 1, 1, 1, 1, 1);
//
//        Jogador atacante1 = new Avancado("rrrrenaldo", 7, 1, 1, 1, 1, 1, 1, 1);
//        Jogador atacante2 = new Avancado("ppppeeepe", 9, 1, 1, 1, 1, 1, 1, 1);
//        Jogador atacante3 = new Avancado("nelinho", 11, 1, 1, 1, 1, 1, 1, 1);
//        Jogador atacante4 = new Avancado("EDER CARALHO", 67, 1, 1, 1, 1, 1, 1, 1);
//
        Jogador gr1 = new GuardaRedes("Patricio fora de epoca", 1, 75, 78, 87, 91, 70, 76, 73, 93);
        Jogador gr2 = new GuardaRedes("Patricio", 99, 100, 77, 89, 92, 72, 76, 82, 95);

        Jogador defesa1 = new Defesa("defesa1", 2, 76, 80, 82, 86, 88, 69, 70);
        Jogador defesa2 = new Defesa("defesa2", 3, 82, 81, 82, 88, 92, 70, 72);
        Jogador defesa3 = new Defesa("defesa3", 12, 70, 73, 75, 77, 80, 68, 69);

        Jogador lateral1 = new Lateral("nelson", 4, 79, 82, 77, 73, 72, 79, 80, 81);
        Jogador lateral2 = new Lateral("nelson2", 5, 82, 78, 77, 74, 71, 80, 77, 80);
        Jogador lateral3 = new Lateral("nelson3", 13, 76, 75, 75, 70, 70, 72, 75, 74);

        Jogador medio1 = new Médio("medio1", 6, 79, 80, 79, 78, 77, 80, 81, 80);
        Jogador medio2 = new Médio("medio2", 8, 75, 80, 77, 84, 77, 75, 80, 86);
        Jogador medio3 = new Médio("medio3", 10, 80, 80, 80, 80, 80, 80, 80, 80);
        Jogador medio4 = new Médio("medio4", 19, 79, 79, 75, 79, 79, 75, 79, 79);
        Jogador medio5 = new Médio("medio5", 27, 76, 76, 76, 76, 76, 76, 76, 76);

        Jogador atacante1 = new Avancado("rrrrenaldo", 69, 81, 81, 81, 81, 81, 85, 81);
        Jogador atacante2 = new Avancado("ppppeeepe", 87, 79, 79, 79, 79, 79, 79, 79);
        Jogador atacante3 = new Avancado("nelinho", 67, 83, 77, 80, 80, 81, 82, 78);
        Jogador atacante4 = new Avancado("EDER CARALHO", 568, 79, 79, 80, 75, 75, 83, 80);

        Equipa equipa1 = new Equipa("Deucriste");
        try {
            equipa1.insereJogador(gr1);
            equipa1.insereJogador(gr2);
            equipa1.insereJogador(defesa1);
            equipa1.insereJogador(defesa2);
            equipa1.insereJogador(defesa3);
            equipa1.insereJogador(lateral1);
            equipa1.insereJogador(lateral2);
            equipa1.insereJogador(lateral3);
            equipa1.insereJogador(medio1);
            equipa1.insereJogador(medio2);
            equipa1.insereJogador(medio3);
            equipa1.insereJogador(medio4);
            equipa1.insereJogador(medio5);
            equipa1.insereJogador(atacante1);
            equipa1.insereJogador(atacante2);
            equipa1.insereJogador(atacante3);
            equipa1.insereJogador(atacante4);
        } catch (NumeroExistenteException e) {
            e.printStackTrace();
        }
//        equipa1.colocaTitulares(433);


        //EQUIPA 2

        Jogador gr11 = new GuardaRedes("gr11", 1, 75, 78, 87, 91, 70, 76, 73, 93);
        Jogador gr22 = new GuardaRedes("PATRICIO 2", 99, 79, 77, 89, 92, 72, 76, 82, 95);

        Jogador defesa11 = new Defesa("defesa11", 2, 76, 80, 82, 90, 90, 69, 77);
        Jogador defesa22 = new Defesa("defesa22", 3, 82, 81, 86, 88, 92, 70, 72);
        Jogador defesa33 = new Defesa("defesa33", 12, 70, 73, 75, 77, 80, 68, 69);

        Jogador lateral11 = new Lateral("lateral11", 4, 80, 82, 80, 79, 78, 77, 80, 81);
        Jogador lateral22 = new Lateral("lateral22", 5, 82, 78, 85, 78, 71, 77, 77, 80);
        Jogador lateral33 = new Lateral("lateral33", 13, 76, 75, 75, 70, 70, 72, 75, 74);

        Jogador medio11 = new Médio("medio11", 6, 79, 80, 79, 78, 77, 80, 78, 80);
        Jogador medio22 = new Médio("medio22", 8, 75, 80, 77, 84, 77, 75, 78, 86);
        Jogador medio33 = new Médio("medio33", 10, 80, 80, 80, 80, 80, 80, 78, 80);
        Jogador medio44 = new Médio("medio44", 19, 79, 79, 75, 79, 79, 75, 79, 79);
        Jogador medio55 = new Médio("medio55", 27, 76, 76, 76, 76, 76, 76, 76, 76);

        Jogador atacante11 = new Avancado("NELO CHAPAS", 7, 81, 81, 81, 81, 81, 80, 81);
        Jogador atacante22 = new Avancado("MACAAAAACO", 9, 79, 79, 79, 79, 79, 79, 79);
        Jogador atacante33 = new Avancado("ZE MARIA NICULAO", 11, 83, 77, 80, 80, 81, 100, 78);
        Jogador atacante44 = new Avancado("QUIM DA SIRENE DOS BOMBEIROS", 77, 79, 79, 80, 75, 75, 83, 80);


        Equipa equipa2 = new Equipa("Vila Franca");
        try {
            equipa2.insereJogador(gr11);
            equipa2.insereJogador(gr22);
            equipa2.insereJogador(defesa11);
            equipa2.insereJogador(defesa22);
            equipa2.insereJogador(defesa33);
            equipa2.insereJogador(lateral11);
            equipa2.insereJogador(lateral22);
            equipa2.insereJogador(lateral33);
            equipa2.insereJogador(medio11);
            equipa2.insereJogador(medio22);
            equipa2.insereJogador(medio33);
            equipa2.insereJogador(medio44);
            equipa2.insereJogador(medio55);
            equipa2.insereJogador(atacante11);
            equipa2.insereJogador(atacante22);
            equipa2.insereJogador(atacante33);
            equipa2.insereJogador(atacante44);
        } catch (NumeroExistenteException e) {
            e.printStackTrace();
        }
//        equipa2.colocaTitulares(442);

//        System.out.println("-----------\nOverall Defesas: " + equipa1.overallLateral() + '\n');
        Jogo skrty = new Jogo(equipa1, equipa2, LocalDate.now());
        try {
            skrty.simularJogo();
        } catch (NumeroNaoExistenteException e) {
            e.printStackTrace();
        }


        //equipa1.getSuplentes().values().forEach(e->System.out.println(e.toString()));
//        System.out.println(equipa1.toString());
//
//        System.out.println("-----------\nOverall Equipa: " + equipa1.overallTitulares() + '\n');
//        System.out.println("-----------\nOverall Ataque: " + equipa1.overallAtaque(433) + '\n');
//        System.out.println("-----------\nOverall Medio: " + equipa1.overallMedio(433) + '\n');
//        System.out.println("-----------\nOverall Defesas: " + equipa1.overallDefesa() + '\n');

        //System.out.println("\n" + gr1.equals(gr2));
        //System.out.println("\nOverall: " + defesa1.overall());
    }
}

