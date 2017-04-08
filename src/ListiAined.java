import java.text.DecimalFormat;
import java.util.List;
import java.util.Scanner;
public class ListiAined { //
    List<Aine> Ainetelist;

    public ListiAined(List<Aine> aineteList) {
        super();
        Ainetelist = aineteList;
    }
    public List<Aine> getAinetelist() {
        return Ainetelist;
    }
    public void setAinetelist(List<Aine> ainetelist) {
        Ainetelist = ainetelist;
    }
    public List<Aine> lisaTunde(String nimetus, double tunnid) {
        int staatus = -1;
        List<Aine> aineteList = this.Ainetelist;
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