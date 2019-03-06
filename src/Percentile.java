import java.util.ArrayList;
import java.util.List;

public class Percentile {

    //Variables
    private long averageTAT;
    private long averageNormilizedTAT;
    private long averageWaitingTime;
    private List<Process> processes;

    //Constructors
    public Percentile(){
        processes = new ArrayList<>();
    }

    //Functions
    public void addProcess(Process process){
        processes.add(process);
    }

    public void calculateValues(){
        int totalTAT = 0;
        int totalNormalizedTAT = 0;
        int totalWaitingTime = 0;
        int listSize = processes.size();
        for(Process p : processes){
            totalTAT += p.getTurnAroundTime();
            totalNormalizedTAT += p.getNormalizedTurnAroundTime();
            totalWaitingTime += p.getWaitTime();
        }

        averageTAT = totalTAT/listSize;
        averageNormilizedTAT = totalNormalizedTAT/listSize;
        averageWaitingTime = totalWaitingTime/listSize;
    }

    //Getters & Setters
    public long getAverageTAT() {
        return averageTAT;
    }

    public void setAverageTAT(long averageTAT) {
        this.averageTAT = averageTAT;
    }

    public long getAverageNormilizedTAT() {
        return averageNormilizedTAT;
    }

    public void setAverageNormilizedTAT(long averageNormilizedTAT) {
        this.averageNormilizedTAT = averageNormilizedTAT;
    }

    public long getAverageWaitingTime() {
        return averageWaitingTime;
    }

    public void setAverageWaitingTime(long averageWaitingTime) {
        this.averageWaitingTime = averageWaitingTime;
    }

    public List<Process> getProcesses() {
        return processes;
    }

    public void setProcesses(List<Process> processes) {
        this.processes = processes;
    }


}
