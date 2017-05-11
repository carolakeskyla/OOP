package application;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FailiLugeja extends Main {
    public List<Aine> aineListike() {
        List<Aine> ainelist = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new FileReader("ainefail.txt"));
            while (scanner.hasNextLine()) {
                String[] ainerida = scanner.nextLine().split(";");
                ainelist.add(new Aine(Integer.parseInt(ainerida[0].replace(".", "")),
                        ainerida[1], Integer.parseInt(ainerida[2]),
                        Integer.parseInt(ainerida[3]), Double.parseDouble(ainerida[4])));
            }
            scanner.close();
        } catch (FileNotFoundException e) {

        }
        return ainelist;
    }
}
