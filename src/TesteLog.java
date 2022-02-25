import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;

public class TesteLog {
    public static void main(String[] args) {
        Manager man = new Manager();
        try {
            man = Manager.loadFromLog("logsv2.txt");
        } catch (NumeroExistenteException e) {
            e.printStackTrace();
        } catch (LinhaIncorretaException e) {
            e.printStackTrace();
        }

        Map<String, Equipa> equipas = man.getEquipas();
        Jogo novo = new Jogo(equipas.get("Sporting Club Prokofiev"), equipas.get("Mendelssohn F. C."), LocalDate.now());
        try {
            novo.simularJogo();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (NumeroNaoExistenteException e) {
            e.printStackTrace();
        }
        man.addJogo(novo);

        try {
            man.writeToObj("tmpobj.dat");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Manager lido = new Manager();

        try {
            lido = Manager.readFromObj("tmpobj.dat");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("\nTeste leitura de objeto");
        System.out.println(lido.getEquipas().get("Sporting Club Prokofiev").toString());
    }
}
