public class Process implements Comparable<Process> {

    private int PID;
    private int arrivalTime;
    private int serviceTime;
    private int startTime;
    private int endTime;
    private int turnAroundTime;
    private long normalizedTurnAroundTime;
    private int waitTime;

    //Constructors
    public Process() {
    }

    //Functions
    public void printProcess(){
        System.out.println("PID: " + PID);
        System.out.println("arrivalTime" + arrivalTime);
        System.out.println("serviceTime" + serviceTime);
        System.out.println("Starttime: " + startTime);
        System.out.println("EndTime: " + endTime);
        System.out.println("TAT: " + turnAroundTime);
        System.out.println("NormalizedTAT: " + normalizedTurnAroundTime);
        System.out.println("Waittime: " + waitTime);
        System.out.println("");
    }

    //Getters en setters
    public int getPID() {
        return PID;
    }

    public void setPID(int PID) {
        this.PID = PID;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(int serviceTime) {
        this.serviceTime = serviceTime;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public int getTurnAroundTime() {
        return turnAroundTime;
    }

    public void setTurnAroundTime() {
        this.turnAroundTime = waitTime + serviceTime;
    }

    public long getNormalizedTurnAroundTime() {
        return normalizedTurnAroundTime;
    }

    public void setNormalizedTurnAroundTime() {
        this.normalizedTurnAroundTime = turnAroundTime/serviceTime;
    }

    public int getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(int waitTime) {
        this.waitTime = waitTime;
    }

    @Override
    public int compareTo(Process p) {
        return serviceTime - p.getServiceTime();
    }
}
