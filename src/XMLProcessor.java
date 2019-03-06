import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class XMLProcessor {

    private List<Process> listWithProcesses;

    public XMLProcessor(int i) {
        switch (i) {
            case 1:
                setListWithProcesses(XMLInlezen("resources/processen10000.xml"));
                break;
            case 2:
                setListWithProcesses(XMLInlezen("resources/processen20000.xml"));
                break;
            case 3:
                setListWithProcesses(XMLInlezen("resources/processen50000.xml"));
                break;
            default:
                setListWithProcesses(XMLInlezen("resources/processen10000.xml"));
                break;

        }
    }

    public List<Process> XMLInlezen(String fileName) {
        List<Process> processList = new ArrayList<>();
        try {
            File inputFile = new File(fileName);

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(inputFile);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("process");

            for (int i = 0; i < nodeList.getLength(); i++) {
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
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return processList;
    }

    public List<Process> getListWithProcesses() {
        return listWithProcesses;
    }

    public void setListWithProcesses(List<Process> listWithProcesses) {
        this.listWithProcesses = listWithProcesses;
    }

}
