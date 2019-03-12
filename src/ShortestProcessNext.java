import java.util.*;

public class ShortestProcessNext {

    //Variables
    private Queue<Process> processQueue;
    private List<Process> waitingProcesses;
    private List<Process> finishedProcesses;
    private int currentTime;
    private double previousEndTime;
    private int size;

    //Constructors
    public ShortestProcessNext(List<Process> processList) {
        this.processQueue = new LinkedList<>(processList);
        waitingProcesses = new ArrayList<>();
        finishedProcesses = new ArrayList<>();
        previousEndTime = 0;
        size = processList.size();
    }

    //Functies
    public List<Process> execute() {
        while (finishedProcesses.size() != size) {

            if (!processQueue.isEmpty()) {
                while (processQueue.peek().getArrivalTime() < previousEndTime) {
                    Process p = processQueue.remove();
                    waitingProcesses.add(p);
                    if (processQueue.isEmpty()) {
                        break;
                    }
                }
            }

            Process p = new Process();
            if (waitingProcesses.isEmpty()) {
                p = processQueue.remove();
                p.setStartTime(p.getArrivalTime());
            } else {
                p = findProcessWithShortestServiceTime(waitingProcesses);
                waitingProcesses.remove(p);
                p.setStartTime(previousEndTime);
            }
            previousEndTime = p.executeProcess();
            p.setTurnAroundTime();
            p.setNormalizedTurnAroundTime();
            finishedProcesses.add(p);
        }
        Collections.sort(finishedProcesses);

        return finishedProcesses;
    }

    public Process findProcessWithShortestServiceTime(List<Process> processList) {
        Process shortestProcess = new Process();
        shortestProcess.setServiceTime(999999);
        for (Process p : processList) {
            if (p.getServiceTime() < shortestProcess.getServiceTime()) {
                shortestProcess = p;
            }
        }

        return shortestProcess;
    }

    //Getters en Setters
    public Queue<Process> getProcessQueue() {
        return processQueue;
    }

    public void setProcessList(Queue<Process> processQueue) {
        this.processQueue = processQueue;
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
