import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.layout.*;
import javafx.stage.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
import java.util.Scanner;

public class Main extends Application {

    static int processenAantal = 1;
    static int schedulingManier = 4;

    @Override
    public void start(Stage stage) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25,25,25,25));

        Scene scene = new Scene(grid, 700, 300);

        //Bovenkant
        Text titel = new Text("Maak uw keuze: ");
        titel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(titel,0,0,2,1);

        Text aantalProcessen = new Text("Kies het aantal processen");
        grid.add(aantalProcessen,1,1,1,1);

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
                    processenAantal = (Integer.parseInt(groep1.getSelectedToggle().getUserData().toString()));
                    System.out.println(processenAantal);
                    System.out.println(schedulingManier);
                }
            }
        });

        grid.add(rb1,1,2,1,1);
        grid.add(rb2,2,2,1,1);
        grid.add(rb3,3,2,1,1);


        //Onderkant
        Text soortAlgoritme = new Text("Kies de gewenste manier:");
        grid.add(soortAlgoritme,1,3,1,1);

        final ToggleGroup groep2 = new ToggleGroup();
        RadioButton rb4 = new RadioButton("FCFS");
        rb4.setToggleGroup(groep2);
        rb4.setUserData("1");

        RadioButton rb5 = new RadioButton("SJF");
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

        groep2.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ov,
                                Toggle old_toggle, Toggle new_toggle) {
                if (groep2.getSelectedToggle() != null) {
                    schedulingManier = Integer.parseInt(groep2.getSelectedToggle().getUserData().toString());
                    System.out.println(processenAantal);
                    System.out.println(schedulingManier);
                }
            }
        });

        grid.add(rb4,1,4,1,1);
        grid.add(rb5,2,4,1,1);
        grid.add(rb6,3,4,1,1);
        grid.add(rb7,4,4,1,1);
        grid.add(rb8,5,4,1,1);
        grid.add(rb9,6,4,1,1);
        grid.add(rb10,7,4,1,1);


        //BevestigenKnop
        Button button = new Button("Bevestig");

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                uitvoeren();
            }
        });

        grid.add(button,1,5,1,1);
        stage.setScene(scene);
        stage.show();
    }


    public static void uitvoeren(){
        XMLInlezen(processenAantal);
    }


    public static void main(String[] args){
        launch();
    }

    
    public static List<Process> XMLInlezen(int listNumber){

        List<Process> processList = new ArrayList<>();

        try {
            File inputFile;
            switch (listNumber){
                case 1: inputFile = new File("resources/processen10000.xml");
                        break;
                case 2: inputFile = new File("resources/processen20000.xml");
                        break;
                case 3: inputFile = new File("resources/processen50000.xml");
                        break;
                default: inputFile = new File("resources/processen10000.xml");
                        break;

            }

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document doc = builder.parse(inputFile);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("process");

            for(int i = 0; i < nodeList.getLength(); i++){
                Node node = nodeList.item(i);

                Element element = (Element) node;

                Process p = new Process();

                int pid = Integer.parseInt(element.getElementsByTagName("pid").item(0).getTextContent());
                int arrivalTime = Integer.parseInt(element.getElementsByTagName("arrivaltime").item(0).getTextContent());
                int serviceTime = Integer.parseInt(element.getElementsByTagName("servicetime").item(0).getTextContent());


                p.setPID(pid);
                p.setArrivalTime(arrivalTime);
                p.setServiceTime(serviceTime);

                processList.add(p);
                System.out.println("process: " +  i);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return processList;
    }
}
