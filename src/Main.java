import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
import java.util.Scanner;

public class Main{

    public static void main(String[] args){

        //1) Processen inlezen
        System.out.println("Welk bestand wil je gebruiken?");
        System.out.println("10000 processen --> 1");
        System.out.println("20000 processen --> 2");
        System.out.println("50000 processen --> 3");

        Scanner sc = new Scanner(System.in);
        int listNumber = sc.nextInt();

        List<Process> processes = new ArrayList<>();
        processes = XMLInlezen(listNumber);

        for(Process p : processes){
            System.out.println(p.getServiceTime());
        }
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
