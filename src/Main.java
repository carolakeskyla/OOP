import java.io.*;
import java.util.ArrayList;
import java.util.List;
public class Main {

    public static void kustutaFailiSisu() throws IOException {
        FileWriter filewriter = new FileWriter("ainefail.txt", false);
        PrintWriter printwriter = new PrintWriter(filewriter, false);
        printwriter.flush();
        printwriter.close();
        filewriter.close();
    }
    public static void lisaFaili(List<Aine> ainelist) throws FileNotFoundException {
        PrintWriter failprint = new PrintWriter("ainefail.txt");
        for (Aine aine : ainelist) {
            failprint.println(aine.toString()); }
        failprint.close();
    }

    public static String lõpetaja = "X";
    public static ArrayList<String> aineList = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        //FileWriter filewriter = new FileWriter("ainefail.txt", true);
        System.out.println("Teretulemast ainemahu hindamise programmi. \nSelles programmis saad sisestada ained, " +
                "mida õpid, kui palju aega sa nendele järjepidevalt kulutad ja näha \nkokkuvõtlikku ülevaadet " +
                "kuivõrd kulutatud aeg vastab ettenähtud õpimahule.");
        AineteLisamine lisamine = new AineteLisamine();
        List<Aine> uusList = lisamine.lisaAineid().getAinetelist(); //eemalda viimane for fileserialization
        kustutaFailiSisu();
        lisaFaili(uusList);
    }
}
