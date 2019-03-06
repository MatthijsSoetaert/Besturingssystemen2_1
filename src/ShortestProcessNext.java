import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShortestProcessNext {

    //Variables
    private List<Process> processList;
    private List<Process> waitingProcesses;
    private List<Process> finishedProcesses;
    private Process previousProcess;
    private int currentTime = 0;

    //Constructors
    public ShortestProcessNext(List<Process> processList) {
        this.processList = new ArrayList<>(processList);
        waitingProcesses = new ArrayList<>();
        finishedProcesses = new ArrayList<>();
        previousProcess = new Process();
    }

    public List<Process> execute() {
        System.out.println(processList.size() + " kjljmjkmljl");
        for (int i = 0; i < processList.size(); i++) {
            Process p = processList.get(i);

            if (i == 0 || waitingProcesses.isEmpty()) {
                p.setStartTime(p.getArrivalTime());
                p.setEndTime(p.getServiceTime() + p.getArrivalTime());
                addProcessesToWaitingList(p);
                currentTime = p.getEndTime();
                p.setWaitTime(0);
                p.setTurnAroundTime();
                p.setNormalizedTurnAroundTime();
                finishedProcesses.add(p);
                processList.remove(p);
                previousProcess = p;
            } else if(!waitingProcesses.isEmpty()) {
                Process nextProcess = new Process();
                int shortestProcessTime = 9999999;

                for (Process process : waitingProcesses) {
                    if(shortestProcessTime > process.getServiceTime()){
                        nextProcess = process;
                        shortestProcessTime = process.getServiceTime();
                    }
                }

                nextProcess.setStartTime(currentTime);
                nextProcess.setEndTime(nextProcess.getStartTime() + nextProcess.getServiceTime());
                addProcessesToWaitingList(nextProcess);
                currentTime = nextProcess.getEndTime();
                nextProcess.setWaitTime(previousProcess.getEndTime() - nextProcess.getArrivalTime());
                finishedProcesses.add(nextProcess);
                processList.remove(nextProcess);
                previousProcess = nextProcess;
            }

        }
        FirstComeFirstServed fcfs = new FirstComeFirstServed(waitingProcesses);
        List<Process> t = fcfs.execute();
        finishedProcesses.addAll(t);
        Collections.sort(finishedProcesses);
        System.out.println("WaitingProcessList size:" + waitingProcesses.size());
        System.out.println("processlist size" + finishedProcesses.size());
        return finishedProcesses;
    }

    public void addProcessesToWaitingList(Process p){
        List<Process> tempList = new ArrayList<>();
        for (Process nextProcess : processList) {
            if (nextProcess.getArrivalTime() < p.getEndTime()) {
                tempList.add(nextProcess);
            } else {
                break;
            }
        }
        for(Process process: tempList) {
            waitingProcesses.add(process);
            processList.remove(process);
        }
    }

    //Getters en Setters
    public List<Process> getProcessList() {
        return processList;
    }

    public void setProcessList(List<Process> processList) {
        this.processList = processList;
    }

    public List<Process> getWaitingProcesses() {
        return waitingProcesses;
    }

    public void setWaitingProcesses(List<Process> waitingProcesses) {
        this.waitingProcesses = waitingProcesses;
    }

    public int getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(int currentTime) {
        this.currentTime = currentTime;
    }

}
