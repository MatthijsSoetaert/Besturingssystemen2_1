import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.layout.*;
import javafx.stage.*;

public class Main extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {

        final Scheduling scheduling = new Scheduling();

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Scene scene = new Scene(grid, 700, 300);

        //Titel van venster
        Text titel = new Text("Maak uw keuze: ");
        titel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(titel, 0, 0, 2, 1);

        //Aantal processen text
        Text aantalProcessen = new Text("Kies het aantal processen");
        grid.add(aantalProcessen, 1, 1, 1, 1);

        //Radio buttons voor aantal processen
        final ToggleGroup groep1 = new ToggleGroup();
        RadioButton rb1 = new RadioButton("10000");
        rb1.setToggleGroup(groep1);
        rb1.setUserData("1");

        RadioButton rb2 = new RadioButton("20000");
        rb2.setToggleGroup(groep1);
        rb2.setUserData("2");

        RadioButton rb3 = new RadioButton("50000");
        rb3.setToggleGroup(groep1);
        rb3.setUserData("3");

        groep1.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ov,
                                Toggle old_toggle, Toggle new_toggle) {
                if (groep1.getSelectedToggle() != null) {
                    scheduling.setAmountOfProcesses(Integer.parseInt(groep1.getSelectedToggle().getUserData().toString()));
                }
            }
        });

        grid.add(rb1, 1, 2, 1, 1);
        grid.add(rb2, 2, 2, 1, 1);
        grid.add(rb3, 3, 2, 1, 1);

        //Radio buttons voor de keuze van de scheduling manier
        Text soortAlgoritme = new Text("Kies de gewenste manier:");
        grid.add(soortAlgoritme, 1, 3, 1, 1);

        final ToggleGroup groep2 = new ToggleGroup();
        RadioButton rb4 = new RadioButton("FCFS");
        rb4.setToggleGroup(groep2);
        rb4.setUserData("1");

        RadioButton rb5 = new RadioButton("SPN");
        rb5.setToggleGroup(groep2);
        rb5.setUserData("2");

        RadioButton rb6 = new RadioButton("SRT");
        rb6.setToggleGroup(groep2);
        rb6.setUserData("3");

        RadioButton rb7 = new RadioButton("RR (q=2)");
        rb7.setToggleGroup(groep2);
        rb7.setUserData("4");

        RadioButton rb8 = new RadioButton("RR (q=8)");
        rb8.setToggleGroup(groep2);
        rb8.setUserData("5");

        RadioButton rb9 = new RadioButton("HRRN");
        rb9.setToggleGroup(groep2);
        rb9.setUserData("6");

        RadioButton rb10 = new RadioButton("MFM");
        rb10.setToggleGroup(groep2);
        rb10.setUserData("7");

        RadioButton rb11 = new RadioButton("Alles");
        rb10.setToggleGroup(groep2);
        rb10.setUserData("8");

        groep2.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ov,
                                Toggle old_toggle, Toggle new_toggle) {
                if (groep2.getSelectedToggle() != null) {
                    scheduling.setSchedulingOption(Integer.parseInt(groep2.getSelectedToggle().getUserData().toString()));
                    //System.out.println(processenAantal);
                    //System.out.println(schedulingManier);
                }
            }
        });

        grid.add(rb4, 1, 4, 1, 1);
        grid.add(rb5, 2, 4, 1, 1);
        grid.add(rb6, 3, 4, 1, 1);
        grid.add(rb7, 4, 4, 1, 1);
        grid.add(rb8, 5, 4, 1, 1);
        grid.add(rb9, 6, 4, 1, 1);
        grid.add(rb10, 7, 4, 1, 1);
        grid.add(rb11, 8, 4, 1, 1);

        //BevestigenKnop
        Button button = new Button("Bevestig");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                List<Percentile> graphValues = scheduling.execute();
                System.out.println("grootte na fcfs" + graphValues.size());

                toonGrafiek(graphValues);
            }
        });

        grid.add(button, 1, 5, 1, 1);
        stage.setScene(scene);
        stage.show();
    }

    //Functie voor het tonen van grafieken
    public static void toonGrafiek(List<Percentile> values) {
        //defining the axes
        final NumberAxis xAs = new NumberAxis();
        int waarde = 10;
        final NumberAxis yAs = new NumberAxis();

        XYChart.Series series = new XYChart.Series();

        //creating the chart
        final LineChart<Number, Number> lineChart = new LineChart<>(xAs, yAs);
        for(int i = 0; i < 100; i++) {
            series.getData().add(new XYChart.Data(i,values.get(i).getAverageNormilizedTAT() ));
        }

        lineChart.getData().add(series);

        //creating the new stage
        Stage grafiekVenster = new Stage();
        grafiekVenster.setTitle("grafiek");
        grafiekVenster.setScene(new Scene(lineChart, 1000, 800));
        grafiekVenster.show();
    }
}
