import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class AineteLisamine extends Main {
    public ListiAined lisaAineid() throws IOException {
        Scanner sc = new Scanner(System.in);
        FailiLugeja listiSaamine = new FailiLugeja();
        List<Aine> list = listiSaamine.aineListike();
        ListiAined muutmine = new ListiAined(list);

        while (true) {
            System.out.println("Sisesta aine nimetus. Sisesta 'X' ja Enter, et lõpetada: ");
            String nimetus = sc.nextLine();
            if (nimetus.equals("X")) {
                break; }

            System.out.println("Sisesta õpitud tundide arv. Sisesta '0' ja Enter, et aineks õpitud tunde kuvada: ");

            Double tundideArv = Double.parseDouble(sc.nextLine());
            muutmine.setAinetelist(muutmine.lisaTunde(nimetus, tundideArv));
        }
        sc.close();
        return muutmine;
    }
}