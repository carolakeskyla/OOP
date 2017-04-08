import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FailiLugeja extends Main {
    public List<Aine> aineListike() throws FileNotFoundException {
        List<Aine> ainelist = new ArrayList<>();
        File fail = new File("ainefail.txt");
        try {
            Scanner scanner = new Scanner(fail);
            while (scanner.hasNextLine()) {
                String[] ainerida = scanner.nextLine().split(";");
                ainelist.add(new Aine(Integer.parseInt(ainerida[0].replace(".", "")),
                        ainerida[1], Integer.parseInt(ainerida[2]),
                        Integer.parseInt(ainerida[3]), Double.parseDouble(ainerida[4])));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            //e.printStackTrace(); //kuvab errori; comment, sest kasutajale pole tarvis seda
        }
        return ainelist;
    }
}