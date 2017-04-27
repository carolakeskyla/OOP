package application;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Scanner;

public class AineteLisamine extends Main {
    public ListiAined lisaAineid(ListiAined muutmine, String nimetus, double tundideArv) throws IOException {
        
        System.out.println("Sisesta aine nimetus. Sisesta 'X' ja Enter, et lõpetada: ");
        if (nimetus.equals("X")) {
        	}

        System.out.println("Sisesta õpitud tundide arv. Sisesta '0' ja Enter, et aineks õpitud tunde kuvada: ");

        muutmine.setAinetelist(lisaTunde(muutmine, nimetus, tundideArv));
        
        return muutmine;
    }
    public List<Aine> lisaTunde(ListiAined list, String nimetus, double tunnid) {
        int staatus = -1;
        List<Aine> aineteList = list.Ainetelist;
        for (Aine aine : aineteList) {
            if (aine.getNimetus().equals(nimetus.trim())) {
                staatus = 1;
                double olemasolev = aine.getTehtudTunnid();
                aine.setTehtudTunnid(olemasolev + tunnid);
                DecimalFormat ükskomakoht = new DecimalFormat("0.0"); //ükskomakoht ümardab TehtudTunnid ühe koma kohaliseks /C
                System.out.println("'" + aine.getNimetus() + "' õpitud tunnid kokku: " + ükskomakoht.format(aine.getTehtudTunnid()));
                break;
            }
        }
        if (staatus == -1) {
            Scanner sisend = new Scanner(System.in);
            System.out.println("Sisestage aine '" + nimetus + "' EAP-d");
            int EAP = sisend.nextInt();
            int ettenähtudTunnid = EAP*26;
            aineteList.add(new Aine(aineteList.size()+1, nimetus, EAP, ettenähtudTunnid, tunnid));

        }
        return aineteList;
    }
}