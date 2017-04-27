package application;
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
    
    }
