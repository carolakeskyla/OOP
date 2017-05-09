package application;

public class Aine{
    public int indeks;
    public String nimetus;
    public int EAP;
    public double tehtudTunnid;
    public int ettenähtudTunnid;

    public Aine(int indeks, String nimetus, int eAP, int ettenähtudTunnid, double tehtudTunnid) {
        super();
        this.indeks = indeks;
        this.nimetus = nimetus;
        EAP = eAP;
        this.ettenähtudTunnid = ettenähtudTunnid;
        this.tehtudTunnid = tehtudTunnid;
    }

    public int getIndeks() {
        return indeks;
    }

    public double getTehtudTunnid() {
        return tehtudTunnid;
    }
    public void setTehtudTunnid(double tehtudTunnid) {
        this.tehtudTunnid = tehtudTunnid;
    }
    public String getNimetus() {
        return nimetus;
    }
    public int getEAP() {
        return EAP;
    }
    public int getEttenähtudTunnid() {
        return ettenähtudTunnid;
    }
    @Override
    public String toString() {
        return indeks + "." + ";" + nimetus + ";" + EAP + ";" + ettenähtudTunnid + ";" + tehtudTunnid;
    }
}