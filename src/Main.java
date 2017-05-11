package application;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;

public class Main extends Application {
    List<Aine> uusList;
    List<Aine> list;
    AineteLisamine lisamine;
    ListiAined muutmine;

    public static void kustutaFailiSisu() throws IOException {
        FileWriter filewriter = new FileWriter("ainefail.txt", false);
        PrintWriter printwriter = new PrintWriter(filewriter, false);
        printwriter.flush();
        printwriter.close();
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
        if (uusList.isEmpty()) {
            lisaFaili(list);
        } else {
            lisaFaili(uusList); }
    }
    public void tunnid(TextField väli_tunnid, String nimetus, ListiAined muutmine, AineteLisamine lisamine) {
        try {
            double tunnid = Integer.parseInt(väli_tunnid.getText());
            uusList = lisamine.lisaAineid(muutmine, nimetus, tunnid).getAinetelist();
        } catch (Exception numbriErind) {
            Alert teavitus = new Alert(AlertType.ERROR);
            teavitus.setTitle("Viga!");
            teavitus.setHeaderText("Viga!");
            teavitus.setContentText("Sisestatud tundide arv ei ole number!");
            teavitus.showAndWait();
        }}


    public void start(Stage primaryStage) {
        lisamine = new AineteLisamine();
        FailiLugeja listiSaamine = new FailiLugeja();
        list = listiSaamine.aineListike();
        muutmine = new ListiAined(list);
        uusList = muutmine.getAinetelist();
        try {
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
            root.setPadding(new Insets(5, 10, 30, 10));

            Scene scene = new Scene(root,400,300);

            Label kirjeldus = new Label("Teretulemast ainemahu hindamise programmi! \nSee programm" +
                    " näitab kuivõrd kattuvad Sinu õppeainete õpitud tunnid iga aine ettenähtud õppetundidega.");
            kirjeldus.setWrapText(true);
            kirjeldus.prefHeightProperty().bind(scene.heightProperty());
            kirjeldus.setFont(Font.font("Calibri", FontWeight.BOLD, 13));
            GridPane.setColumnSpan(kirjeldus, GridPane.REMAINING);
            root.add(kirjeldus, 0, 0);

            Label silt_nimetus = new Label("Vali aine:");
            root.add(silt_nimetus, 0, 1);

            List<String> lust = new ArrayList<>();
            lust.add(0, "-");
            for (Aine aine : uusList) {
                lust.add(aine.getNimetus());
            }

            ComboBox<String> dropdown = new ComboBox<>();
            dropdown.getItems().addAll(lust);
            dropdown.setValue("-");
            dropdown.setVisibleRowCount(5);
            root.add(dropdown, 1, 1);

            Label silt_nimetus2 = new Label("...või sisesta uue aine nimetus: ");
            root.add(silt_nimetus2, 0, 2);

            TextField väli_nimetus = new TextField();
            root.add(väli_nimetus, 1, 2);

            Label silt_tunnid = new Label("Õpitud tundide arv:");
            root.add(silt_tunnid, 0, 3);

            TextField väli_tunnid = new TextField();
            root.add(väli_tunnid, 1, 3);

            Button nupp_lisaTunde = new Button("Lisa tunde");
            root.add(nupp_lisaTunde, 1, 4);
            nupp_lisaTunde.setId("stiil-1");
            GridPane.setHalignment(nupp_lisaTunde, HPos.LEFT);

            nupp_lisaTunde.setOnAction(new EventHandler<ActionEvent>() {
                String nimetus;
                public void handle(ActionEvent e) {
                    String väärtus = dropdown.getValue();
                    //System.out.println(väärtus);
                    if (väärtus.equals("-")) {

                        try {
                            if (väli_nimetus.getText().trim().isEmpty()) {
                                throw new Exception("ERROR: Nimetus sisestamata");
                            } else {
                                nimetus = väli_nimetus.getText(); }
                                tunnid(väli_tunnid, nimetus, muutmine, lisamine);

                        } catch (Exception str) {
                            Alert teavitus = new Alert(AlertType.ERROR);
                            teavitus.setTitle("Viga!");
                            teavitus.setContentText("Aine nimetus on sisestamata!");
                            teavitus.showAndWait();
                        }
                    } else {
                        nimetus = väärtus;
                        tunnid(väli_tunnid, nimetus, muutmine, lisamine); }}

            });

            Button nupp_graafik = new Button("Kuva tabel");
            root.add(nupp_graafik, 1, 4);
            GridPane.setHalignment(nupp_graafik, HPos.RIGHT);
            nupp_graafik.setId("stiil-2");
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
