package application;
	
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class Main extends Application {
	List<Aine> uusList;
	
	public static void kustutaFailiSisu() throws IOException {
        FileWriter filewriter = new FileWriter("ainefail.txt", false);
        PrintWriter printwriter = new PrintWriter(filewriter, false);
        printwriter.flush();
        printwriter.close();
        filewriter.close();
    }
	
    public static void lisaFaili(List<Aine> ainelist) {
        PrintWriter failprint;
		try {
			failprint = new PrintWriter("ainefail.txt");
			for (Aine aine : ainelist) {
	            failprint.println(aine.toString());
	            }
	        failprint.close();
	        System.out.println("Faili salvestamine edukas!");
		} 
		catch (FileNotFoundException e) {
		} 
    }
    
    @Override
    public void stop(){
        System.out.println("Programm sulgub, toimub faili salvestamine...");
        lisaFaili(uusList);
    }
    
	public void start(Stage primaryStage) {
		AineteLisamine lisamine = new AineteLisamine();
		FailiLugeja listiSaamine = new FailiLugeja();
        List<Aine> list = listiSaamine.aineListike();
        ListiAined muutmine = new ListiAined(list);
        uusList = muutmine.getAinetelist();
		try {
			//FileWriter filewriter = new FileWriter("ainefail.txt", true);
			System.out.println("Teretulemast ainemahu hindamise programmi. \nSelles programmis saad sisestada ained, " +
					"mida õpid, kui palju aega sa nendele järjepidevalt kulutad ja näha \nkokkuvõtlikku ülevaadet " +
					"kuivõrd kulutatud aeg vastab ettenähtud õpimahule.");
			
			 //eemalda viimane for fileserialization
			kustutaFailiSisu();
			
		}
		catch(IOException e) {
			Alert teavitus = new Alert(AlertType.ERROR);
			teavitus.setTitle("Viga!");
			teavitus.setHeaderText("Viga!");
			teavitus.setContentText("Faili ei leitud.");
			teavitus.showAndWait();	
		}
		
		try {
			GridPane root = new GridPane();
			root.setHgap(10);
			root.setVgap(10);
			root.setPadding(new Insets(0, 10, 0, 10));
			
			Scene scene = new Scene(root,400,400);
			Label silt_nimetus = new Label("Aine nimetus:");
			root.add(silt_nimetus, 0, 0);
			
			Label silt_tunnid = new Label("Tehtud tundide arv:");
			root.add(silt_tunnid, 0, 1);
			
			TextField väli_nimetus = new TextField();
			root.add(väli_nimetus, 1, 0);
			
			TextField väli_tunnid = new TextField();
			root.add(väli_tunnid, 1, 1);
			
			Button nupp_lisaTunde = new Button("Lisa tunde");
			root.add(nupp_lisaTunde, 0, 2);
			nupp_lisaTunde.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {
					try{
						String nimetus = väli_nimetus.getText();
						try {
						double tunnid = Integer.parseInt(väli_tunnid.getText());
						uusList = lisamine.lisaAineid(muutmine, nimetus, tunnid).getAinetelist();
						}
						catch(NumberFormatException numbriErind) {
							Alert teavitus = new Alert(AlertType.ERROR);
							teavitus.setTitle("Viga!");
							teavitus.setHeaderText("Viga!");
							teavitus.setContentText("Sisestatud tundide arv ei ole number");
							teavitus.showAndWait();	
						}
					}
					catch(IOException a) {		
					}
				}
			});
			Button nupp_graafik = new Button("Kuva tabel");
			root.add(nupp_graafik, 1, 2);
			nupp_graafik.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {
					Graafik graafik = new Graafik();
					graafik.looGraafik(uusList);
				}
			});
				
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
