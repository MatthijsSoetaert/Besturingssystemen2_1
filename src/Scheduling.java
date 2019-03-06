import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Scheduling {

    private int amountOfProcesses;
    private int schedulingOption;
    private List<Process> processesList;

    //Constructors
    public Scheduling() {
    }

    //Voert het gepaste algoritme uit naargelang de keuze
    public List<Percentile> execute() {
        XMLProcessor xmlProcessor = new XMLProcessor(amountOfProcesses);
        processesList = xmlProcessor.getListWithProcesses();

        System.out.println("sched: " + schedulingOption);

        List<Process> temporaryList = new ArrayList<>();
        switch (schedulingOption) {
            case 1:
                FirstComeFirstServed firstComeFirstServed = new FirstComeFirstServed(processesList);
                temporaryList = firstComeFirstServed.execute();
                break;
            case 2:
                ShortestProcessNext shortestProcessNext = new ShortestProcessNext(processesList);
                System.out.println("proccessesLijstgrootte: " + processesList.size());
                temporaryList = shortestProcessNext.execute();
                break;
            default:
                break;
        }

        return convertToPercentiles(temporaryList);
    }

    //Omzetten van de waarden per process naar die van percentielen
    public List<Percentile> convertToPercentiles(List<Process> processes) {
        List<Percentile> percentileList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Percentile p = new Percentile();
            for (int j = 0; j < processes.size() / 100; j++) {
                p.addProcess(processes.get((i * 100) + j));
            }
            p.calculateValues();
            percentileList.add(p);
        }
        return percentileList;
    }


    //Getters en Setters
    public int getAmountOfProcesses() {
        return amountOfProcesses;
    }

    public void setAmountOfProcesses(int amountOfProcesses) {
        this.amountOfProcesses = amountOfProcesses;
    }

    public int getSchedulingOption() {
        return schedulingOption;
    }

    public void setSchedulingOption(int schedulingOption) {
        this.schedulingOption = schedulingOption;
    }


}
