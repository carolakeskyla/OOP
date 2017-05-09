package application;

import java.util.List;

import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Graafik extends Main{
	
	public void looGraafik(List<Aine> ained) {
		Stage graafik = new Stage();
		
		graafik.setTitle("Bar Chart Sample");
        CategoryAxis xTelg = new CategoryAxis();
        NumberAxis yTelg = new NumberAxis();
        BarChart<String,Number> tulpdiag = new BarChart<String,Number>(xTelg,yTelg);
        
        tulpdiag.setTitle("Ainete tulpdiagramm");
        xTelg.setLabel("Aine");       
        yTelg.setLabel("Tunnid");
 
        XYChart.Series ettenähtud = new XYChart.Series();
        ettenähtud.setName("ettenähtud tunnid");
        for (Aine aine : ained) {
        	String nimetus = aine.getNimetus();
        	int ettenähtud_tunnid = aine.getEttenähtudTunnid();
        	ettenähtud.getData().add(new XYChart.Data<>(nimetus, ettenähtud_tunnid));
        	
        } 
        tulpdiag.getData().add(ettenähtud);
        
        XYChart.Series tehtud = new XYChart.Series();
        tehtud.setName("tehtud tunnid");
        for (Aine aine : ained) {
        	String nimetus = aine.getNimetus();
        	double tehtud_tunnid = aine.getTehtudTunnid();
        	tehtud.getData().add(new XYChart.Data<>(nimetus, tehtud_tunnid));
        }
        tulpdiag.getData().add(tehtud);
        tulpdiag.autosize();
        Scene scene  = new Scene(new BorderPane(tulpdiag),800,600);

        graafik.setScene(scene);
        graafik.setScene(scene);
        graafik.show();
    }	
	}


