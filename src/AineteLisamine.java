package application;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class AineteLisamine extends Main {
    public ListiAined lisaAineid(ListiAined muutmine, String nimetus, double tundideArv) throws IOException {
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
        	Stage SecondaryStage = new Stage();
        	GridPane aken_ap = new GridPane();
			aken_ap.setHgap(10);
			aken_ap.setVgap(10);
			aken_ap.setPadding(new Insets(0, 10, 0, 10));
			Scene stseen = new Scene(aken_ap,300,100);
			
			Label silt_eap = new Label("Sisesta aine \"" + nimetus + "\" EAP-d: ");
			aken_ap.add(silt_eap, 0, 0);
			
			TextField väli_eapd = new TextField();
			aken_ap.add(väli_eapd, 0, 1);
			
			Button nupp_eapd = new Button("Määra EAP-d");
			aken_ap.add(nupp_eapd, 0, 2);
			nupp_eapd.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {
					try{
						int EAP = Integer.parseInt(väli_eapd.getText());
						int ettenähtudTunnid = EAP*26;
						aineteList.add(new Aine(aineteList.size()+1, nimetus, EAP, ettenähtudTunnid, tunnid));
						Alert aine_lisatud = new Alert(AlertType.INFORMATION);
						aine_lisatud.setTitle("Aine lisamine");
						aine_lisatud.setHeaderText("EAP sisestamine");
						aine_lisatud.setContentText("EAP-d on määratud ja aine on edukalt lisatud");
						aine_lisatud.showAndWait();
						SecondaryStage.close();
						}
					catch(NumberFormatException numbriErind) {
						Alert teavitus = new Alert(AlertType.ERROR);
						teavitus.setTitle("Viga!");
						teavitus.setHeaderText("Viga!");
						teavitus.setContentText("Sisestatud EAP-de arv ei ole number");
						teavitus.showAndWait();	
					}		
				}
			});
			SecondaryStage.setScene(stseen);
			SecondaryStage.show();
        }
        
        return aineteList;
    }
}