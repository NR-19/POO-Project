import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Manager implements Serializable {
    private Map<String, Equipa> equipas;
    private List<Jogo> jogos;

    public Manager() {
        this.equipas = new HashMap<>();
        this.jogos = new ArrayList<>();
    }

    public Manager(Map<String, Equipa> equipas, List<Jogo> jogos) {
        this.equipas = equipas.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().clone()));
        this.jogos = jogos.stream().map(Jogo::clone).collect(Collectors.toList());
    }

    public Manager(Manager m) {
        this.equipas = m.getEquipas();
        this.jogos = m.getJogos();
    }

    public Map<String, Equipa> getEquipas() {
        return this.equipas.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().clone()));
    }

    public List<Jogo> getJogos() {
        return this.jogos.stream().map(Jogo::clone).collect(Collectors.toList());
    }

    public void addJogo(Jogo novo) {
        this.jogos.add(novo);
    }

    public static Manager loadFromLog(String filepath) throws LinhaIncorretaException, NumeroExistenteException {
        List<String> linhas = lerFicheiro(filepath);
        Map<String, Equipa> equipas = new HashMap<>(); //nome, equipa
        List<Jogo> jogos = new ArrayList<>();
        Equipa ultima = null;
        Jogador j = null;
        String[] linhaPartida;
        for (String linha : linhas) {
            linhaPartida = linha.split(":", 2);
            switch (linhaPartida[0]) {
                case "Equipa":
                    Equipa e = Equipa.parse(linhaPartida[1]);
                    equipas.put(e.getNomeEquipa(), e);
                    ultima = e;
                    break;
                case "Guarda-Redes":
                    j = GuardaRedes.parse(linhaPartida[1]);
                    if (ultima == null)
                        throw new LinhaIncorretaException(); //we need to insert the player into the team
                    ultima.insereJogador(j.clone()); //if no team was parsed previously, file is not well-formed
                    break;
                case "Defesa":
                    j = Defesa.parse(linhaPartida[1]);
                    if (ultima == null)
                        throw new LinhaIncorretaException(); //we need to insert the player into the team
                    ultima.insereJogador(j.clone()); //if no team was parsed previously, file is not well-formed
                    break;
                case "Medio":
                    j = Médio.parse(linhaPartida[1]);
                    if (ultima == null)
                        throw new LinhaIncorretaException(); //we need to insert the player into the team
                    ultima.insereJogador(j.clone()); //if no team was parsed previously, file is not well-formed
                    break;
                case "Lateral":
                    j = Lateral.parse(linhaPartida[1]);
                    if (ultima == null)
                        throw new LinhaIncorretaException(); //we need to insert the player into the team
                    ultima.insereJogador(j.clone()); //if no team was parsed previously, file is not well-formed
                    break;
                case "Avancado":
                    j = Avancado.parse(linhaPartida[1]);
                    if (ultima == null)
                        throw new LinhaIncorretaException(); //we need to insert the player into the team
                    ultima.insereJogador(j.clone()); //if no team was parsed previously, file is not well-formed
                    break;
                case "Jogo":
//                    Jogo jo = Jogo.parse(linhaPartida[1]);
//                    jogos.add(jo);
                    break;
                default:
                    throw new LinhaIncorretaException();

            }
        }
        return new Manager(equipas, jogos);
    }

    public void writeToObj(String filename) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename));

        oos.writeObject(this);
        oos.flush();
        oos.close();
    }

    public static Manager readFromObj(String filename) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename));

        Manager read_man = (Manager) ois.readObject();
        ois.close();
        return read_man;
    }

    @Override
    public Manager clone() {
        return new Manager(this);
    }

    public static List<String> lerFicheiro(String nomeFich) {
        List<String> lines;
        try {
            lines = Files.readAllLines(Paths.get(nomeFich), StandardCharsets.UTF_8);
        } catch (IOException exc) {
            lines = new ArrayList<>();
        }
        return lines;
    }
}

